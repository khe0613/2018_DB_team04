package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import theater.Movie;

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
			}
			
			do {
				String movie = rs.getInt("movieNO") + ". " + rs.getString("movieName");
				movieList.add(movie);
			}while(rs.next());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		disConnectDB();
		return movieList;
	}
	
	public String getMovieName(int movieNo) { // ��ȭ �ڵ忡 ���� ��ȭ�̸� ��������
		connectDB();
		String sql = "SELECT * FROM movie where movieNo = ?";
		String movieName = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("movieName") != null) {
					movieName = rs.getString("movieName");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disConnectDB();
		return movieName;
	}
	
	   /* ��ȭ ���� ��� */
	   public boolean addMovieInfoSQL(String movieName, String directorName, String summary,
	         int showTime, String releaseDate, String rating, String perforMername, String genre) {
		   connectDB();
		   boolean success = true;
		   
	      
	      try {
	         String SQL = "INSERT INTO Movie (movieName, directorName, summary, showTime, releaseDate,"
	               + " rating, perforMername, genre)"
	               + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	         pstmt = conn.prepareStatement(SQL);
	         pstmt.setString(1, movieName);
	         pstmt.setString(2, directorName);
	         pstmt.setString(3, summary);
	         pstmt.setInt(4, showTime);
	         pstmt.setString(5, releaseDate);
	         pstmt.setString(6, rating);
	         pstmt.setString(7, perforMername);
	         pstmt.setString(8, genre);
	         pstmt.executeUpdate(); 
	        
	      }catch(Exception e) {
	    	  success = false;
	            e.printStackTrace();
	      }
	      
	      disConnectDB();
	      return success;
	   }
	   
	   /* ��ȭ ���� ���� */
	   public boolean rewriteMovieInfoSQL(int movieNO, String movieName, String directorName, String summary,
	         int showTime, String releaseDate, String rating, String perforMername, String genre) {
		  connectDB();
		   boolean success = true ; 
	      
	      try {
	         String SQL = "UPDATE movie SET"
	               + " movieName = ? , directorName = ?, summary = ?, showTime = ?, releaseDate = ?,"
	               + " rating = ?, perforMername = ?, genre = ?"
	               + " WHERE movieNO = ?";
	         pstmt = conn.prepareStatement(SQL);
	         pstmt.setString(1, movieName);
	         pstmt.setString(2, directorName);
	         pstmt.setString(3, summary);
	         pstmt.setInt(4, showTime);
	         pstmt.setString(5, releaseDate);
	         pstmt.setString(6, rating);
	         pstmt.setString(7, perforMername);
	         pstmt.setString(8, genre);
	         pstmt.setInt(9, movieNO);
	         pstmt.executeUpdate(); // ���� ����� �𸣰���
	         
	         
	      }catch(Exception e) {
	    	  success = false;
	            e.printStackTrace();
	      }
	      return success;
	   }
	   
	   /* ��ȭ ���� ���� */
	   public boolean deleteMovieInfoSQL(int movieNO) {
	      connectDB();
	      boolean success = true;
	      
	      try {
	         String SQL = "DELETE FROM Movie"
	               + " WHERE movieNO = ?";
	         pstmt = conn.prepareStatement(SQL);
	         pstmt.setInt(1, movieNO);
	         pstmt.executeUpdate(); 

	      }catch(Exception e) {
	    	  success = false;
	            e.printStackTrace();
	      }
	      
	      disConnectDB();
	      return success;
	   }

	   /* ��ȭ ���� ��� ��� */
	   public ArrayList<Movie> getMovieInfoListSQL() {
	      connectDB();
	      ArrayList<Movie> arrayList= new ArrayList<>();;
	      
	      try {
	         
	         String SQL = "SELECT * FROM movie";
	         pstmt = conn.prepareStatement(SQL);
	         ResultSet rs = pstmt.executeQuery();
	        
	         // ��ȸ��� ���� ����
	         if(!rs.next()) {
	            return null;
	         }
	         
	         rs.beforeFirst(); // ù ������ �̵�  -> �̰� �³� ?
	         
	         // ��� ��������
	         while(rs.next()) {
	            int rsMovieNO = rs.getInt("movieNO");
	            String rsMovieName = rs.getString("movieName");
	            String rsDirectorName = rs.getString("directorName");
	            String rsSummary = rs.getString("summary");
	            int rsShowtime = rs.getInt("showTime");
	            String rsReleaseDate = rs.getString("releaseDate");
	            String rsRating = rs.getString("rating");
	            String rsPerforMername = rs.getString("perforMername");
	            String rsGenre = rs.getString("genre");
	            
	            Movie movie = new Movie();
	            movie.setMovieNO(rsMovieNO);
	            movie.setMovieName(rsMovieName);
	            movie.setDirectorName(rsDirectorName);
	            movie.setSummary(rsSummary);
	            movie.setShowTime(rsShowtime);
	            movie.setReleaseDate(rsReleaseDate);
	            movie.setRating(rsRating);
	            movie.setPerforMername(rsPerforMername);
	            movie.setGenre(rsGenre);
	            
	            arrayList.add(movie);
	         }
	      
	        
	      }catch(Exception e){
	           e.printStackTrace();
	      }
	           disConnectDB();
	      return arrayList;
	   }
	   
}
	   
	

