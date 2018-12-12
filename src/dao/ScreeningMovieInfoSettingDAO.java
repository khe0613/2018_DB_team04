package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import theater.ScreeningMovieInfoSetting;

public class ScreeningMovieInfoSettingDAO {
	
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
	
	/* 상영 영화 정보 등록 */
	public boolean addScreeningMovieInfoSQL(int movieBranchNo, int screenNo, int movieNo, int schNo) {
		connectDB();
		try {
			String SQL = "INSERT INTO screeningtable (movieBranchNo, screenNo, movieNo, schNo)"
					+ " VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, movieBranchNo);
			pstmt.setInt(2, screenNo);
			pstmt.setInt(3, movieNo);
			pstmt.setInt(4, schNo);
		    pstmt.executeUpdate(); 
		    
		}catch(Exception e) {
		      e.printStackTrace();
		      return false;
		}
		disconnectDB();
		return true;
	}
	
	/* 상영 영화 정보 삭제 */
	public boolean deleteScreeningMovieInfoSQL(int movieBranchNo, int screenNo, int movieNo, int schNo) {
		connectDB();
		try {
			String SQL = "DELETE FROM screeningtable"
					+ " WHERE movieBranchNo = ? and screenNo = ? and movieNo = ? and schNo = ?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, movieBranchNo);
			pstmt.setInt(2, screenNo);
			pstmt.setInt(3, movieNo);
			pstmt.setInt(4, schNo);
		    pstmt.executeUpdate(); 
		}catch(Exception e) {
		      e.printStackTrace();
		      return false;
		}		
		disconnectDB();
		  return true;
	}
	
	   /* 상영영화 정보 목록 출력 */
	   public ArrayList<ScreeningMovieInfoSetting> getScreenInfo() {
		  connectDB();
		  ArrayList<ScreeningMovieInfoSetting> arrayList = new ArrayList<>();
	      try {
	         String SQL = "SELECT * FROM screeningtable";
	         pstmt = conn.prepareStatement(SQL);
	         ResultSet rs = pstmt.executeQuery();

	         // 목록 꺼내오기
	         while(rs.next()) {
	            int rsScreentableNo = rs.getInt("screeningtableNo");
	            int rsBranchNo = rs.getInt("movieBranchNo");
	            int rsScreenNo = rs.getInt("screenNo");
	            int rsMovieNo = rs.getInt("movieNo");
	            int rsSchNo = rs.getInt("schNo");

	            ScreeningMovieInfoSetting screeningmovieinfosetting  = new ScreeningMovieInfoSetting();
	            screeningmovieinfosetting.setScreeningTableNo(rsScreentableNo);
	            screeningmovieinfosetting.setMovieBranchNo(rsBranchNo);
	            screeningmovieinfosetting.setScreenNo(rsScreenNo);
	            screeningmovieinfosetting.setMovieNo(rsMovieNo);
	            screeningmovieinfosetting.setSchNo(rsSchNo);
	            arrayList.add(screeningmovieinfosetting);
	         }
	      }catch(Exception e){
	           e.printStackTrace();
	      }
	      disconnectDB();
	      return arrayList;
	   }
}
