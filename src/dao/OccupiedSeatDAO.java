package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OccupiedSeatDAO {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater?useSSL=false";
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";

	HashMap<Integer,Integer> chart;
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
	
	// 예약이 완료된 좌석들의 리스트를 반환
	public List<Integer> getOccupiedSeatList(int screeningtableNo){
		connectDB();
		List<Integer> occupied_seat_list = new ArrayList<>();
		String sql = "SELECT seatNo FROM occupiedseat WHERE screeningtableNo = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, screeningtableNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return occupied_seat_list;
			}
			
			do {
				occupied_seat_list.add(rs.getInt("seatNo"));
			}while(rs.next());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		disConnectDB();
		return occupied_seat_list;
	}
	
	
	// 예약된 좌석 내역에 좌석을 추가
	public boolean add_occupied_seat(int screeningtableNo, int seatNo, int screenNo, int branchNo) {
		connectDB();
		String sql = "INSERT INTO occupiedseat (screeningtableNo, seatNo, screenNo, branchNo) "
						+ "VALUES(?, ?, ?, ?)";
		boolean success = true;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, screeningtableNo);
			pstmt.setInt(2, seatNo);
			pstmt.setInt(3, screenNo);
			pstmt.setInt(4, branchNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		
		disConnectDB();
		return success;
		
	}
	
}
