package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import theater.Screen;

// 김창
//상영관 DAO
public class ScreenDAO {
	String jdbcUrl; 
	String dbId;
	String dbPass;
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public ScreenDAO() {
		connectDB();
	}
	
	// DB 연결 connect
	private void connectDB() {
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
	
	// db 연결 disconnect
	public void disconnectDB() {
		if(rs!= null) try {rs.close();}catch (Exception e) {}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		if(conn != null) try{conn.close();}catch(SQLException sqle){}
	}
	
	
	// 상영관 등록
	public boolean registerScreen(Screen screen) {
		String sql = "INSERT INTO screen (screenNo, branchNo, schNo, totalSeatNum, leftSeatNum) values (?, ?, ?, ?, ?)";
		boolean not_error_flag = true;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, screen.getScreenNo());
			pstmt.setInt(2, screen.getBranchNo());
			pstmt.setInt(3, screen.getScheduleCode());
			pstmt.setInt(4, screen.getTotalSeatNum());
			pstmt.setInt(5, screen.getLeftSeatNum());
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			not_error_flag = false;
		}
		
		return not_error_flag;
	}
	
	// 상영관 삭제
	public boolean removeScreen(int screenNo, int branchNo) {
		String sql = "DELETE FROM screen WHERE (screenNo = ?) and (branchNo = ?)";
		boolean not_error_flag = true;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, screenNo);
			pstmt.setInt(2, branchNo);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			not_error_flag = false;
		}
		
		
		return not_error_flag;
	}
	
	// 상영관 수정
	public boolean modifyScreen(Screen screen) {
		String sql = "UPDATE screen SET screenNo = ?, branchNo = ?, schNo = ?,"
				    + "totalSeatNum = ?, leftSeatNum = ?";
		boolean not_error_flag = true;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, screen.getScreenNo());
			pstmt.setInt(2, screen.getBranchNo());
			pstmt.setInt(3, screen.getScheduleCode());
			pstmt.setInt(4, screen.getTotalSeatNum());
			pstmt.setInt(5, screen.getLeftSeatNum());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			not_error_flag = false;
		}
		
		return not_error_flag;
	}
	
}
