package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatDAO {
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
	
	// 특정 영화관, 상영관에 좌석 추가
	public boolean addSeat(int seatNo, int screenNo, int branchNo) {
		connectDB();
		String sql = "INSERT INTO seat(seatNo, screenNo, branchNo) "
					+ "VALUES(?, ?, ?)";
		boolean success = true;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatNo);
			pstmt.setInt(2, screenNo);
			pstmt.setInt(3, branchNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		disConnectDB();
		return success;
	}
	
	

	
}
