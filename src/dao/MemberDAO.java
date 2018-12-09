package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import theater.Member;

public class MemberDAO {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater?useSSL=false";
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
	private void disConnectDB() {
		if(rs!= null) try {rs.close();}catch (Exception e) {}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		if(conn != null) try{conn.close();}catch(SQLException sqle){}
	}
	
	
	public enum loginResult {
		SUCCESS,
		NOT_ID_IN_DB,
		INVALID_PW,
		DB_FAILED
	}
	
	// 로그인
	public loginResult loginMember(Member member) {
		connectDB();
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
	    }
		disConnectDB();
		return loginResult.DB_FAILED;
	}
	
	// 회원 가입
	public boolean registerMember(Member member) {
		connectDB();
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
	
		if(not_error_flag) {
			System.out.println("회원가입에 성공하였습니다.\n");
		}else {
			System.out.println("회원가입에 실패하였습니다.\n");		
		}
		disConnectDB();
		return not_error_flag;
	}
	
	
	
	
	// 회원 탈퇴
	public boolean deleteMember(String id)  {
		connectDB();
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

		if(not_error_flag) {
			System.out.println("회원탈퇴에 성공하였습니다.\n");
		}else {
			System.out.println("회원탈퇴에 실패하였습니다.\n");		
		}
		disConnectDB();
		return not_error_flag;
	}
	
	
	//회원 정보 수정
	public boolean modifyMember(String id, String pw, String name, String birth, String address, String phoneNum) {
		connectDB();
		String sql = "UPDATE member SET pw = ?, name = ?, birth = ?, address = ?, phoneNum = ? WHERE id = ?";
		boolean not_error_flag = false;												//	회원정보 수정 중 에러발생 여부
		
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

		if(not_error_flag) {
			System.out.println("회원 정보 수정에 성공하였습니다.\n");
		}else {
			System.out.println("회원 정보 수정에 실패하였습니다.\n");		
		}
		disConnectDB();
		return not_error_flag;
	}
	
	public int getPoint(String id) { // 인터넷 결제
		connectDB();
		int memberPoint = 0;
		String 	sql = "select point from Member where id =?"; // point정보 가져오기
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memberPoint = rs.getInt("point");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disConnectDB();
		return memberPoint;
	}
	
	public boolean usePoint(int memberPoint,String id) { // point사용후 저장
		connectDB();
		String 	sql = "update member set point = ? where id = ?"; // 고객의 포인트를 usePoint 만큼 감소하고 테이블에 저장
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberPoint);
			pstmt.setString(2,id);
			pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		disConnectDB();
		return true;
	}
}
