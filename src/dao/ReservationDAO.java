package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import theater.Print;

// 영화 예약 DAO
public class ReservationDAO {
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
	
	public HashMap<Integer,Integer> getMovieChart() {
		connectDB();
		chart = new HashMap<>();
		String sql = "SELECT movieNo FROM reservation";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int key = rs.getInt(1);
				if(chart.containsKey(key)) { // 차트에 영화가 이미 있다면 추가 ㄴㄴ
					chart.put(key, chart.get(key) + 1);
				}else { // 차트에 영화가 없으므로 추가
					chart.put(key, 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disConnectDB();
		return chart;
	}
	
	public boolean isPayment(String resNo) { // 결제여부를 판단함
		connectDB();
		String sql = "select ispayment from Member where id =?"; // 예매번호의 결제여부 가져오기
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		Boolean isPayment = rs.getBoolean("ispayment");
		if(isPayment == false) { // 결제여부확인
			Print.printMessage("-> 결제 유형을 선택해주세요.");
			Print.printMessage("1. 인터넷 결제 2. 현장 결제");
			int paymentType = sc.nextInt();
			if(paymentType == 1) { // 인터넷 결제
				Print.printMessage("-------------- 인 터 넷 결 제 --------------");
				InternetPayment(id,resNo);
			}else { // 현장 결제
				System.out.println("!! 현장에서 관리자에게 결제해주세요.");
			}			
		}else if(isPayment == null){
			System.out.println("존재하지 않는 예매번호입니다.");
		}else {
			System.out.println("!! 이미 결제가 완료된 티켓입니다.");
		}
		disConnectDB();
	}
}
