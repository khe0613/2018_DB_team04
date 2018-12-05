package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import theater.Member;

public class MemberDAO {
	String jdbcUrl; 
	String dbId;
	String dbPass;
	
	Connection conn;
	PreparedStatement pstmt;
	
	public MemberDAO() {
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
	
	public enum loginResult {
		SUCCESS,
		NOT_ID_IN_DB,
		INVALID_PW,
		DB_FAILED
	}
	
	// 로그인
	public loginResult loginMember(Member member) {
		String rsID, rsPWD;
		
		try {
			String SQL = "SELECT id, pw FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getId());
			ResultSet rs = pstmt.executeQuery();
			
			// 조회결과 아이디 없음
			if(!rs.next()) {
				return loginResult.NOT_ID_IN_DB;	
			}
			
			rsID = rs.getString("id");
			rsPWD = rs.getString("pw");
			
			// 비밀번호가 맞지 않음
			if(!rsPWD.equals(member.getPw())) {
					return loginResult.INVALID_PW;
				}
						
			return loginResult.SUCCESS;
		}catch(SQLException e){
	        e.printStackTrace();
	    }finally{
	    	 if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		      if(conn != null) try{conn.close();}catch(SQLException sqle){}
	    }
		return loginResult.DB_FAILED;
	}
	
	// 회원 가입
	public boolean registerMember(Member member) {
		String sql = "INSERT INTO member (id, pw, name, birth, address, phoneNum, point) VALUES (?, ?, ?, ?, ?, ?, ?)";
		boolean not_error_flag = false;												//	회원가입 중 에러발생 여부
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhoneNum());
			pstmt.setInt(7, member.getPoint());
			pstmt.executeUpdate();
			
			not_error_flag = true;
			
		} catch(SQLException e) {
			not_error_flag = false;
		}
	
		if(pstmt != null) {	try {pstmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if(conn != null) { try {conn.close();} catch (SQLException e) {e .printStackTrace(); } }
		
		if(not_error_flag) {
			System.out.println("회원가입에 성공하였습니다.\n");
		}else {
			System.out.println("회원가입에 실패하였습니다.\n");		
		}
		
		return not_error_flag;
	}
	
	
	
	
	// 회원 탈퇴
	public boolean deleteMember(String id)  {
		String sql = "DELETE FROM member WHERE id = ?";
		boolean not_error_flag = false;							//	회원탈퇴 중 에러발생 여부
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			not_error_flag = true;
		} catch (SQLException e) {
			not_error_flag = false;
		}
		
		if(pstmt != null) {	try {pstmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if(conn != null) { try {conn.close();} catch (SQLException e) {e .printStackTrace(); } }
		
		if(not_error_flag) {
			System.out.println("회원탈퇴에 성공하였습니다.\n");
		}else {
			System.out.println("회원탈퇴에 실패하였습니다.\n");		
		}
		
		return not_error_flag;
	}
	
	
	//회원 정보 수정
	public boolean modifyMember(String id, String pw, String name, String birth, String address, String phoneNum) {
		String sql = "UPDATE member SET  pw = ?, name = ?, birth = ?, address = ?, phoneNum = ? WHERE id = ?";
		boolean not_error_flag = false;											//	회원정보 수정 중 에러발생 여부
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, birth);
			pstmt.setString(4, address);
			pstmt.setString(5, phoneNum);
			pstmt.setString(6, id);
			pstmt.executeUpdate();
			
			not_error_flag = true;
			
		} catch(SQLException e) {
			not_error_flag = false;
		}
	
		if(pstmt != null) {	try {pstmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if(conn != null) { try {conn.close();} catch (SQLException e) {e .printStackTrace(); } }
		
		if(not_error_flag) {
			System.out.println("회원 정보 수정에 성공하였습니다.\n");
		}else {
			System.out.println("회원 정보 수정에 실패하였습니다.\n");		
		}
		
		return not_error_flag;
		
	}
}
