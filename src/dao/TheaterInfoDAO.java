package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import theater.TheaterInfo;

public class TheaterInfoDAO {
	String jdbcUrl; 
	String dbId;
	String dbPass;
	
	Connection conn;
	PreparedStatement pstmt;
	
	public TheaterInfoDAO() {
		this.jdbcUrl = "jdbc:mysql://localhost:3306/theater";
		this.dbId = "parkyoonjung";
		this.dbPass = "qkrdbswjd";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int registerTheater(TheaterInfo theaterInfo) {
		
		// 등록하려는 영화관 정보가 영화관 테이블에 이미 등록되어 있는 경우
		if(get_branch_no(theaterInfo.getBranchName()) != -1) {
			System.out.println("영화관 등록에 실패하였습니다.\n");
			return -1;
		}
		

		
		String sql = "INSERT INTO theater (screenNum, address, tel, branchName) VALUES (?, ?, ?, ?)";
		boolean not_error_flag = false;												//	영화관 등록 중 에러발생 여부
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, theaterInfo.getScreenNum());
			pstmt.setString(2, theaterInfo.getAddress());
			pstmt.setString(3, theaterInfo.getTel());
			pstmt.setString(4, theaterInfo.getBranchName());
			pstmt.executeUpdate();
			
			not_error_flag = true;
			
		} catch(SQLException e) {
			not_error_flag = false;
		}
	
		if(pstmt != null) {	try {pstmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if(conn != null) { try {conn.close();} catch (SQLException e) {e .printStackTrace(); } }
		
		
		if(not_error_flag) {
			System.out.println("영화관 등록에 성공하였습니다.\n");
			return get_branch_no(theaterInfo.getBranchName());		// 등록한 영화관에게 부여된 지점번호 리턴
		}else {
			System.out.println("영화관 등록에 실패하였습니다.\n");
			return -1;
		}
		
	}
	
	public boolean modifyTheaterInfo(int branchNo, int screenNum, String address, String tel, String branchName) {
		String sql = "UPDATE theater SET screenNum = ?, address = ?, tel = ?, branchName = ?) WHERE branchNo = ?";
		boolean not_error_flag = false;											//	영화관 정보  수정 중 에러발생 여부
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, screenNum);
			pstmt.setString(2, address);
			pstmt.setString(3, tel);
			pstmt.setString(4, branchName);
			pstmt.setInt(5, branchNo);
			pstmt.executeUpdate();
			
			not_error_flag = true;
			
		} catch(SQLException e) {
			not_error_flag = false;
		}
	
		if(pstmt != null) {	try {pstmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if(conn != null) { try {conn.close();} catch (SQLException e) {e .printStackTrace(); } }
		
		if(not_error_flag) {
			System.out.println("영화관 정보 수정에 성공하였습니다.\n");
		}else {
			System.out.println("영화관 정보 수정에 실패하였습니다.\n");		
		}
		
		return not_error_flag;
	}
	
	public boolean removeTheater(int branchNo) {
		String sql = "DELETE FROM theater WHERE branchNo = ?";
		boolean not_error_flag = false;							//	영화관 삭제 중 에러발생 여부
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, branchNo);
			pstmt.executeUpdate();
			
			not_error_flag = true;
		} catch (SQLException e) {
			not_error_flag = false;
		}
		
		if(pstmt != null) {	try {pstmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if(conn != null) { try {conn.close();} catch (SQLException e) {e .printStackTrace(); } }
		
		if(not_error_flag) {
			System.out.println("영화관 삭제에 성공하였습니다.\n");
		}else {
			System.out.println("영화관 삭제에 실패하였습니다.\n");		
		}
		
		return not_error_flag;
	}
	
	
	
	
	
	// 영화관 지점명에 대한 지점코드를 반환하는 함수
	private int get_branch_no(String branchName) {
		String sql = "SELECT * FROM theater WHERE branchName = ?";
		ResultSet rs = null;
		int branch_no = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, branchName);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				if(rs!= null) try {rs.close();}catch (Exception e) {}
				return -1;
			}
			
			branch_no = rs.getInt("branchNo");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(rs!= null) try {rs.close();}catch (Exception e) {}
		return branch_no;
	}
	
	
}
