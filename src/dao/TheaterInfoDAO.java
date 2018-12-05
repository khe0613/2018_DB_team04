package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import theater.MovieInfoSetting;
import theater.TheaterInfo;

// 김창
// 영화관 DAO
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
				if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
			     if(conn != null) try{conn.close();}catch(SQLException sqle){}
				return -1;
			}
			
			branch_no = rs.getInt("branchNo");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(rs!= null) try {rs.close();}catch (Exception e) {}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
	    if(conn != null) try{conn.close();}catch(SQLException sqle){}
	     
		return branch_no;
	}
	
	/* 영화관 정보 목록 출력 */
	public ArrayList<TheaterInfo> getMovieInfoListSQL() {
		
		try {
			ArrayList<TheaterInfo> arrayList = new ArrayList<>();
			String SQL = "SELECT * FROM theater";
			pstmt = conn.prepareStatement(SQL);
			ResultSet rs = pstmt.executeQuery();
			
			// 조회결과 정보 없음
			if(!rs.next()) {
				return null;
			}
			
			rs.beforeFirst(); // 첫 행으로 이동  -> 이게 맞나 ?
			
			// 목록 꺼내오기
			while(rs.next()) {
				int rsBranchNo = rs.getInt("branchNo");
				int rsScreenNum = rs.getInt("screenNum");
				String rsAddress = rs.getString("address");
				String rsTel = rs.getString("tel");
				String rsBranchName = rs.getString("branchName");
				
				TheaterInfo theaterInfo = new TheaterInfo();
				theaterInfo.setBranchNo(rsBranchNo);
				theaterInfo.setScreenNum(rsScreenNum);
				theaterInfo.setAddress(rsAddress);
				theaterInfo.setTel(rsTel);
				theaterInfo.setBranchName(rsBranchName);
				
				arrayList.add(theaterInfo);
			}
			return arrayList;
		}catch(Exception e){
	        e.printStackTrace();
	    }finally{
	    	 if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		      if(conn != null) try{conn.close();}catch(SQLException sqle){}
	    }
		return null;
	}
	
}
