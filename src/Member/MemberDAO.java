package Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
	
	
	// È¸¿ø °¡ÀÔ
	public boolean registerMember(String id, String pw, String name, String birth, String address, String phoneNum, int point){
		String sql = "INSERT INTO member (id, pw, name, birth, address, phoneNum, point) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, address);
			pstmt.setString(6, phoneNum);
			pstmt.setInt(7, point);
		} catch (Exception e) {
			
			return false;
		}
		
		
		return true;
	}
	
	
	
	// È¸¿ø Å»Åð
	public boolean deleteMember() {
		
		return false;
	}
}
