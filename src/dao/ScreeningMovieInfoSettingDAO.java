package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import theater.Print;

public class ScreeningMovieInfoSettingDAO {
	
	String jdbcUrl; 
	String dbId;
	String dbPass;
	
	Connection conn;
	PreparedStatement pstmt;
	
	public ScreeningMovieInfoSettingDAO() {
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
	
	/* 상영 영화 정보 등록 */
	public boolean addScreeningMovieInfoSQL(int movieBranchNo, int movieNo) {
		
		try {
			String SQL = "INSERT INTO listScreenToBranch (movieBranchNo, movieNo)"
					+ " VALUES (?, ?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, movieBranchNo);
			pstmt.setInt(2, movieNo);
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
	
	/* 상영 영화 정보 삭제 */
	public boolean deleteScreeningMovieInfoSQL(int movieBranchNo, int movieNo) {
		
		try {
			String SQL = "DELETE FROM listScreenToBranch"
					+ " WHERE movieBranchNo = ?"
					+ " AND movieNo = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, movieBranchNo);
			pstmt.setInt(2, movieNo);
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
