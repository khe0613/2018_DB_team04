package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// ��ȭ DAO
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
	
	// ������ ��ȭ ����Ʈ�� (��ȭ�ڵ�. ��ȭ��) ���·� ��ȯ
	public List<String> getMovieList(){
		connectDB();
		String sql = "SELECT movieNo, movieName FROM movie";
		List<String> movieList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				return movieList;	// ������ ��ȭ ������ �� ����Ʈ ����
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
	
	public String getMovieName(int movieNo) { // ��ȭ �ڵ忡 ���� ��ȭ�̸� ��������
		connectDB();
		String sql = "SELECT * FROM movie inner join screeningtable on (movie.movieNo = screeningtable.movieNo) where ";
		String movieName = " ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			if(!rs.next()) { // ��ȭ ������ ������
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
