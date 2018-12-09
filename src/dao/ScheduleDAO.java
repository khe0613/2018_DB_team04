package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import theater.Schedule;


// ���� DAO
public class ScheduleDAO {

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
	
	
	// ������ ������ ��ȭ�� ���� ���� �ڵ带 �޾�, ���� ��ü�� ��ȯ�ϴ� �Լ�
	public Schedule getScheduleList(int schNo){
		connectDB();
		String sql ="SELECT * FROM schedule WHERE schNo = ?";
		Schedule schedule = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return null;		// ������ null ��ȯ
			}else {
				schedule = new Schedule();
				schedule.setSchNo(schNo);
				schedule.setStartTime(rs.getString("startTime"));
				schedule.setEndTime(rs.getString("endTime"));
				schedule.setScreeningDate(rs.getString("screeningDate"));
				}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();
		
		return schedule;
	}
}
