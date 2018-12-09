package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 영화 DAO
public class MovieDAO {
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
	
	// 상영중인 영화 리스트를 (영화코드. 영화명) 형태로 반환
	public List<String> getMovieList(){
		connectDB();
		String sql = "SELECT movieNo, movieName FROM movie";
		List<String> movieList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				return movieList;	// 상영중인 영화 없으면 빈 리스트 리턴
			}else {
				String movie = rs.getInt("movieNO") + ". " + rs.getString("movieName");
				movieList.add(movie);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		disConnectDB();
		return movieList;
	}
	
	public String getMovieName(int movieNo) { // 영화 코드에 대한 영화이름 가져오기
		connectDB();
		String sql = "SELECT * FROM movie inner join screeningtable on (movie.movieNo = screeningtable.movieNo) where ";
		String movieName = " ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			if(!rs.next()) { // 영화 정보가 없으면
				return null;
			}else {
				movieName = rs.getString("movieName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disConnectDB();
		return movieName;
	}
	
	
	
}
