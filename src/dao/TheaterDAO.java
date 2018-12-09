package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 영화관 DAO
public class TheaterDAO {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater";
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public TheaterDAO() {
		
	}
	
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
	
	
	// 영업중인 영화관 리스트를 (지점코드. 지점명) 형태로 반환
	public List<String> getTheaterList(){
		connectDB();
		String sql = "SELEECT branchNo, branchName FROM theater";
		List<String> theaterList = new ArrayList<>();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return theaterList; // 상영중인 영화 없으면 빈 리스트 리턴
			}
			
			do {
				String theater = rs.getInt("branchNo") + ". " + rs.getString("branchName");
				theaterList.add(theater);
				}while(rs.next());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();

		return theaterList;
		
	}
}
