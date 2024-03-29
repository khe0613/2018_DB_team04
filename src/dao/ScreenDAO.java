package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import theater.Screen;

// 김창
//상영관 DAO
public class ScreenDAO {
	String jdbcUrl; 
	String dbId;
	String dbPass;
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	// DB 연결 connect
	private void connectDB() {
		this.jdbcUrl = "jdbc:mysql://localhost:3306/theater?useSSL=false";
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
	
	// 상영관 수 얻어오기
		public int getScreenNum(int branchNo) {
			connectDB();
			String sql = "select count(*) from screen where branchNo = ?";
			int screenNum = 0;
			try { // 어떤 지점에 대한 상영관 수
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, branchNo);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					screenNum = rs.getInt(1);					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
			disconnectDB();
			return screenNum;
		}
		
	// 상영관 등록
	public boolean registerScreen(Screen screen) {
		connectDB();
		String sql = "INSERT INTO screen values (?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, screen.getScreenNo());
			pstmt.setInt(2, screen.getBranchNo());
			pstmt.setInt(3, screen.getTotalseatNum());
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		disconnectDB();
		return true;
	}
	
	// 상영관 삭제
	public boolean removeScreen(int branchNo, int screenNo) {
		connectDB();
		String sql = "DELETE FROM screen WHERE (branchNo = ?) and (screenNo = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, branchNo);
			pstmt.setInt(2, screenNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		disconnectDB();
		return true;
	}
	
	// 상영관 수정
	public boolean modifyScreen(Screen screen) {
		connectDB();
		String sql = "UPDATE screen SET totalSeatNum = ? where screenNo = ? and branchNo = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, screen.getTotalseatNum());
			pstmt.setInt(2, screen.getScreenNo());
			pstmt.setInt(3, screen.getBranchNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		disconnectDB();
		return true;
	}
	
	// 특정 영화관, 상영관의 총 좌석수를 반환하는 함수
	public int getTotalSeat(int screenNo, int branchNo) {
		connectDB();
		int total_seat = 0;
		String sql = "SELECT totalSeatNum FROM screen WHERE"
				+ "(screenNo = ?) AND (branchNo = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, screenNo);
			pstmt.setInt(2, branchNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return total_seat;
			}
			
			total_seat = rs.getInt("totalSeatNum");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnectDB();
		return total_seat;
		
		
	}
	
	   /* 상영관 정보 목록 출력 */
	   public ArrayList<Screen> getScreenInfo() {
		  connectDB();
		  ArrayList<Screen> arrayList = new ArrayList<>();
	      try {
	         String SQL = "SELECT * FROM screen";
	         pstmt = conn.prepareStatement(SQL);
	         ResultSet rs = pstmt.executeQuery();

	         // 목록 꺼내오기
	         while(rs.next()) {
	            int rsScreenNO = rs.getInt("screenNo");
	            int rsBranchNo = rs.getInt("branchNo");
	            int rsTotalSeatNum = rs.getInt("totalSeatNum");

	            Screen screen = new Screen();
	            screen.setScreenNo(rsScreenNO);
	            screen.setBranchNo(rsBranchNo);
	            screen.setTotalseatNum(rsTotalSeatNum);
	            arrayList.add(screen);
	         }
	      }catch(Exception e){
	           e.printStackTrace();
	      }
	      disconnectDB();
	      return arrayList;
	   }
	   
}
