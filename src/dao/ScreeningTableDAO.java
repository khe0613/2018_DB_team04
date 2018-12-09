package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScreeningTableDAO {
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
	
	// 선택한 영화, 지점에 대한 일정 코드 리스트를 리턴
	public List<Integer> getScheduleCodeList(int movieNo, int branchNo){
		connectDB();
		String sql = "SELECT schNo FROM screeningTable WHERE (movieNo = ?) AND (movieBranchNo = ?)";
		List<Integer> scheduleCodeList = new ArrayList<>();
		
		try {
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, branchNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {				// 상영표에 영화가 없음
				return scheduleCodeList;
			}
			
			do {
				scheduleCodeList.add(rs.getInt("schNo"));
			}while(rs.next());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();
	
		return scheduleCodeList;
	}
	
	public boolean isScreening(int movieNo){ // 현재 상영중인지 체크
		connectDB();
		String sql = "SELECT * FROM screeningTable WHERE movieNo = ?";
		
		try {
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {				// 상영표에 영화가 없음
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();
		return true;
	}
	
	// 일단 보류
	// 선택한 영화, 지점, 상영 날짜에 대한 상영관 리스트 반환
	public List<Integer> getScreenList(int movieNo, int branchNo,  int schNo){
		connectDB();
		String sql = "SELECT screenNo FROM screeningtable WHERE (movieNo = ?)"
				+ " AND (movieBranchNo = ?) AND (schNo = ?)";
		List<Integer> screenList = new ArrayList<>();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, branchNo);
			pstmt.setInt(3, schNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return screenList;		// 없을 경우 빈 리스트 반환
			}
			
			do {
				screenList.add(rs.getInt("screenNo"));
			}while(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();
		return screenList;
	}
	
	
	
}
