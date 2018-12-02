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
		
		if(check_duplicate_theater(theaterInfo.getBranchName())) {
			return -1;
		}
		
		String sql = "INSERT INTO theater (screenNum, address, tel, branchName) VALUES (?, ?, ?, ?)";
		boolean not_error_flag = false;												//	��ȭ�� ��� �� �����߻� ����
		
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
			System.out.println("��ȭ�� ��Ͽ� �����Ͽ����ϴ�.\n");
		}else {
			System.out.println("��ȭ�� ��Ͽ� �����Ͽ����ϴ�.\n");		
		}
		
		return not_error_flag;
	}
	
	public boolean modifyTheaterInfo(int branchNo, int screenNum, String address, String tel, String branchName) {
		String sql = "UPDATE theater SET branchNo = ?, screenNum = ?, address = ?, tel = ?, branchName = ?) WHERE branchNo = ?";
		boolean not_error_flag = false;											//	��ȭ�� ����  ���� �� �����߻� ����
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, branchNo);
			pstmt.setInt(2, screenNum);
			pstmt.setString(3, address);
			pstmt.setString(4, tel);
			pstmt.setString(5, branchName);
			pstmt.executeUpdate();
			
			not_error_flag = true;
			
		} catch(SQLException e) {
			not_error_flag = false;
		}
	
		if(pstmt != null) {	try {pstmt.close();} catch (SQLException e) { e.printStackTrace(); } }
		if(conn != null) { try {conn.close();} catch (SQLException e) {e .printStackTrace(); } }
		
		if(not_error_flag) {
			System.out.println("��ȭ�� ���� ������ �����Ͽ����ϴ�.");
		}else {
			System.out.println("��ȭ�� ���� ������ �����Ͽ����ϴ�");		
		}
		
		return not_error_flag;
	}
	
	public boolean removeTheater(int branchNo) {
		String sql = "DELETE FROM theater WHERE branchNo = ?";
		boolean not_error_flag = false;							//	��ȭ�� ���� �� �����߻� ����
		
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
			System.out.println("��ȭ�� ������ �����Ͽ����ϴ�.");
		}else {
			System.out.println("��ȭ�� ������ �����Ͽ����ϴ�.");		
		}
		
		return not_error_flag;
	}
	
	
	
	
	// ����Ϸ��� ��ȭ�� ������ ��ȭ�� ���̺� �̹� ��ϵǾ� �ִ��� üũ
	private boolean check_duplicate_theater(String branchName) {
		String sql = "SELECT * FROM theater WHERE branchName = ?";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, branchName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rs.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	// �߰��� ��ȭ���� �����ڵ带 ��ȯ�ϴ� �Լ�
	private int get_branch_no(String branchName) {
		String sql = "SELECT * FROM theater WHERE branchName = ?";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, branchName);
			rs = pstmt.executeQuery();
			
			rs.next();
			rs.getInt("branchNo")
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
