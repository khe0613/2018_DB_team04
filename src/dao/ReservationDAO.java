package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import theater.Reservation;

// 영화 예약 DAO
public class ReservationDAO {
	List<Reservation> resList;
	
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
	
	public String isPayment(String resNo) { // 결제여부를 판단함
		connectDB();
		String sql = "select ispayment from reservation where resNo =?"; // 예매번호의 결제여부 가져오기
		String check = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				check = rs.getString("ispayment");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disConnectDB();
		return check;
	}
	
	public boolean EndPayment(String resNo) { // 결제여부를 true로 변환
		connectDB();
		String sql = "update reservation set ispayment = ? where resNo =?"; // 예매번호의 결제여부 가져오기
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "true");
			pstmt.setString(2, resNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		disConnectDB();
		return true;
	}
	
	public List<Reservation> getPaymentListOfID(String id){
		 connectDB();
		 String sql = "select * from reservation where memberId =?"; // 아이디로 예매내역 가져오기
		 resList = new ArrayList<>();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
				Reservation res = new Reservation(id);
				res.setResNo(rs.getString("resNo"));
				res.setMovieNo(rs.getInt("movieNo"));
				res.setMovieSchedule(rs.getInt("movieSchedule"));
				res.setBookingTime(rs.getString("bookingTime"));
				res.setBookingDay(rs.getString("bookingDay"));
				res.setScreenNum(rs.getInt("screenNum"));
				res.setPrice(rs.getInt("price"));
				res.setIspayment(rs.getString("ispayment"));
				resList.add(res);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			disConnectDB();
		 return resList;
	}
	
	
	
	// 예매 내역 테이블을 확인해서 마지막 예매 번호를 반환하는 함수 (년월일-예매번호-예매장수)
	public int getFinalResNo() {
		connectDB();
		String sql ="SELECT substring_index(resNo, '-', 2) FROM reservation order by substring_index(resNo, '-', 2) desc";
		int final_resNo = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return 0;
			}
			
			final_resNo = Integer.parseInt(rs.getString(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
				
		
		disConnectDB();
		
		return final_resNo;
	
	}
	
	
	// 영화 예약을 실행한는 함수. 예약 내역에 예약정보를 추가한다.
	public boolean doReservation(Reservation reservation) {
		connectDB();
		String sql = "INSERT INTO reservation(resNo, memberId,  seatNo, movieNo, movieSchedule, "
				 + "bookingTime, bookingDay, branchNo, screenNum, price, ispayment)"
				 +  "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean success = true;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservation.getResNo());
			pstmt.setString(2, reservation.getId());
			pstmt.setInt(3, reservation.getSeatNo());
			pstmt.setInt(4, reservation.getMovieNo());
			pstmt.setInt(5, reservation.getMovieSchedule());
			pstmt.setString(6, reservation.getBookingTime());
			pstmt.setString(7, reservation.getBookingDay());
			pstmt.setInt(8, reservation.getBranchNo());
			pstmt.setInt(9, reservation.getScreenNum());
			pstmt.setInt(10, reservation.getPrice());
			pstmt.setString(11, reservation.getIspayment());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		disConnectDB();
		
		return true;
	}
	
	public List<Reservation> getReservationHistory(String memberId){
		connectDB();
		String sql ="SELECT * FROM reservation memberId = ?";
		List<Reservation> reservationHistory = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
		
			if(!rs.next()) {
				return reservationHistory;		// 예약 내역 없으면 빈 리스트 반환
			}
			
			do {
				
			}while(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();
		return reservationHistory;
	}
	
	
}
