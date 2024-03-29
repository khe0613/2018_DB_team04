package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import theater.Schedule;


// 일정 DAO
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
	
	
	// 선택한 지점과 영화에 대한 일정 코드를 받아, 일정 객체를 반환하는 함수
	public Schedule getScheduleList(int schNo){
		connectDB();
		String sql ="SELECT * FROM schedule WHERE schNo = ?";
		Schedule schedule = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return null;		// 없으면 null 반환
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
	
	   /* 일정 정보 목록 출력 */
	   public ArrayList<Schedule> getScheduleInfoListSQL() {
		   connectDB();
		   ArrayList<Schedule> arrayList = new ArrayList<>();
	      try {
	         String SQL = "SELECT * FROM schedule";
	         pstmt = conn.prepareStatement(SQL);
	         ResultSet rs = pstmt.executeQuery();

	         // 목록 꺼내오기
	         while(rs.next()) {
	            int rsSchNo = rs.getInt("SchNo");
	            String rsStartTime = rs.getString("startTime");
	            String rsEndTime = rs.getString("endTime");
	            String rsScreeningDate = rs.getString("ScreeningDate");
	            
	            Schedule schedule = new Schedule();
	            schedule.setSchNo(rsSchNo);
	            schedule.setStartTime(rsStartTime);
	            schedule.setEndTime(rsEndTime);
	            schedule.setScreeningDate(rsScreeningDate);
	            arrayList.add(schedule);
	         }
	      }catch(Exception e){
	           e.printStackTrace();
	       }
	      disConnectDB();
	      return arrayList;
	   }
}
