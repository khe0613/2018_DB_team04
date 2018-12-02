package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import theater.Print;

public class MovieInfoSettingDAO {
	String jdbcUrl; 
	String dbId;
	String dbPass;
	
	Connection conn;
	PreparedStatement pstmt;
	
	public MovieInfoSettingDAO() {
		this.jdbcUrl = "jdbc:mysql://localhost:3306/theater";
		this.dbId = "parkyoonjung";
		this.dbPass = "qkrdbswjd";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* 영화 정보 등록 */
	public boolean addMovieInfoSQL(String movieName, String directorName, String summary,
			int showTime, String releaseDate, String rating, String perforMername, String genre) {
		
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
		    int result = pstmt.executeUpdate(); // 성공 결과를 모르겠음
		    // 한 행만 영향을 받으므로?
		    /*
		    if(result == 1) {
		    	Print.printMessage("result 1");
		    	return true;
		    }
		    */
		    Print.printMessage("result " + result);
		    return true;
		}catch(Exception e) {
		      e.printStackTrace();
		}finally {
		      if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		      if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return false;
	}
	
	/* 영화 정보 수정 */
	public boolean rewriteMovieInfoSQL(int movieNO, String movieName, String directorName, String summary,
			int showTime, String releaseDate, String rating, String perforMername, String genre) {
		
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
		    int result = pstmt.executeUpdate(); // 성공 결과를 모르겠음
		    // 한 행만 영향을 받으므로?
		    /*
		    if(result == 1) {
		    	Print.printMessage("result 1");
		    	return true;
		    }
		    */
		    Print.printMessage("result " + result);
		    return true;
		}catch(Exception e) {
		      e.printStackTrace();
		}finally {
		      if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		      if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return false;
	}
	
	/* 영화 정보 삭제 */
	public boolean deleteMovieInfoSQL(int movieNO) {
		
		try {
			String SQL = "DELETE FROM Movie"
					+ " WHERE movieNO = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, movieNO);
		    int result = pstmt.executeUpdate(); // 성공 결과를 모르겠음
		    
		    // 한 행만 영향을 받으므로?
		    /*
		    if(result == 1) {
		    	Print.printMessage("result 1");
		    	return true;
		    }
		    */
		    
		    Print.printMessage("result " + result);
		    return true;
		}catch(Exception e) {
		      e.printStackTrace();
		}finally {
		      if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		      if(conn != null) try{conn.close();}catch(SQLException sqle){}
		}
		return false;
	}
	
}
