package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	
	// ȸ�� ����
	public boolean registerMember(String id, String pw, String name, String birth, String address, String phoneNum, int point) {
		String sql = "INSERT INTO member (id, pw, name, birth, address, phoneNum, point) VALUES (?, ?, ?, ?, ?, ?, ?)";
		boolean not_error_flag = false;												//	ȸ������ �� �����߻� ����
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, address);
			pstmt.setString(6, phoneNum);
			pstmt.setInt(7, point);
			pstmt.executeUpdate();
			
			not_error_flag = true;
			
		} catch(SQLException e) {
			not_error_flag = false;
		}
	
		if(pstmt != null) {	try {pstmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if(conn != null) { try {conn.close();} catch (SQLException e) {e .printStackTrace(); } }
		
		if(not_error_flag) {
			System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
		}else {
			System.out.println("ȸ�����Կ� �����Ͽ����ϴ�");		
		}
		
		return not_error_flag;
	}
	
	
	
	
	// ȸ�� Ż��
	public boolean deleteMember(String id)  {
		String sql = "DELETE FROM member WHERE id = ?";
		boolean not_error_flag = false;							//	ȸ��Ż�� �� �����߻� ����
		
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
			System.out.println("ȸ��Ż�� �����Ͽ����ϴ�.");
		}else {
			System.out.println("ȸ��Ż�� �����Ͽ����ϴ�.");		
		}
		
		return not_error_flag;
	}
	
	
	//ȸ�� ���� ����
	public boolean modifyMember(String id, String pw, String name, String birth, String address, String phoneNum, int point) {
		String sql = "UPDATE member SET id = ? , pw = ?, name = ?, birth = ?, address = ?, phoneNum = ?, point = ?) WHERE id = ?";
		boolean not_error_flag = false;											//	ȸ������ ���� �� �����߻� ����
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, address);
			pstmt.setString(6, phoneNum);
			pstmt.setInt(7, point);
			pstmt.setString(8, id);
			pstmt.executeUpdate();
			
			not_error_flag = true;
			
		} catch(SQLException e) {
			not_error_flag = false;
		}
	
		if(pstmt != null) {	try {pstmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if(conn != null) { try {conn.close();} catch (SQLException e) {e .printStackTrace(); } }
		
		if(not_error_flag) {
			System.out.println("ȸ�� ���� ������ �����Ͽ����ϴ�.");
		}else {
			System.out.println("ȸ�� ���� ������ �����Ͽ����ϴ�");		
		}
		
		return not_error_flag;
		
	}
}
