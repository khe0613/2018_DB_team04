package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import theater.ScreeningTable;

public class ScreeningTableDAO {
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
	
	// ������ ��ȭ, ������ ���� ���� �ڵ� ����Ʈ�� ����
	public List<Integer> getScheduleCodeList(int movieNo, int branchNo){
		connectDB();
		String sql = "SELECT schNo FROM screeningTable WHERE (movieNo = ?) AND (movieBranchNo = ?)";
		List<Integer> scheduleCodeList = new ArrayList<>();
		
		try {
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, branchNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {				// ��ǥ�� ��ȭ�� ����
				return scheduleCodeList;
			}
			
			do {
				scheduleCodeList.add(rs.getInt("schNo"));
			}while(rs.next());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();
	
		return scheduleCodeList;
	}
	
	public boolean isScreening(int movieNo){ // ���� �������� üũ
		connectDB();
		String sql = "SELECT * FROM screeningTable WHERE movieNo = ?";
		
		try {
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {				// ��ǥ�� ��ȭ�� ����
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();
		return true;
	}
	
	
	// ������ ��ȭ, ����, �� ��¥�� ���� �󿵰� ����Ʈ ��ȯ
	public List<Integer> getScreenList(int movieNo, int branchNo,  int schNo){
		connectDB();
		String sql = "SELECT screenNo FROM screeningtable WHERE (movieNo = ?)"
				+ " AND (movieBranchNo = ?) AND (schNo = ?)";
		List<Integer> screenList = new ArrayList<>();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, branchNo);
			pstmt.setInt(3, schNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return screenList;		// ���� ��� �� ����Ʈ ��ȯ
			}
			
			do {
				screenList.add(rs.getInt("screenNo"));
			}while(rs.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();
		return screenList;
	}
	
	// ������ ��ȭ, ��ȭ��, �󿵰�, �� ��¥, �� �ð��� ���� ��ǥ �ڵ� ������
	public int getScreeningTableNo(int branchNo, int screenNo, int movieNo, int schNo) {
		connectDB();
		String sql = "SELECT screeningtableNo FROM screeningtable WHERE"
				+ " (movieBranchNo = ?) AND (screenNo = ?) AND (movieNo = ?) AND (schNo = ?)";
		int screeningTableNo = -1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, branchNo);
			pstmt.setInt(2, screenNo);
			pstmt.setInt(3, movieNo);
			pstmt.setInt(4, schNo);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return -1;					// ���ڷ� ���� �����鿡 ���� ���ڵ尡 ��ǥ�� ���� x
			}
			
			screeningTableNo = rs.getInt("screeningtableNo");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		disConnectDB();
		return screeningTableNo;
		
	}
	
	
	
	// ��ǥ ��ȣ�� ���� ���� �ڵ� ��ȯ
	public int getSchNo_about_screeningtableNo(int screeningtableNo) {
		connectDB();
		int schNo = 0;
		String sql = "SELECT schNo FROM screeningtable WHERE screeningtableNo = ?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, screeningtableNo);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return -1;
			}
			
			schNo = rs.getInt("schNo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		disConnectDB();
		return schNo;
	}
	
	
	/* �� ��ȭ ���� ��� */
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
		disConnectDB();
		return true;
	}
	
	/* �� ��ȭ ���� ���� */
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
		disConnectDB();
		  return true;
	}
	
	   /* �󿵿�ȭ ���� ��� ��� */
	   public ArrayList<ScreeningTable> getScreenInfo() {
		  connectDB();
		  ArrayList<ScreeningTable> arrayList = new ArrayList<>();
	      try {
	         String SQL = "SELECT * FROM screeningtable";
	         pstmt = conn.prepareStatement(SQL);
	         ResultSet rs = pstmt.executeQuery();

	         // ��� ��������
	         while(rs.next()) {
	            int rsScreentableNo = rs.getInt("screeningtableNo");
	            int rsBranchNo = rs.getInt("movieBranchNo");
	            int rsScreenNo = rs.getInt("screenNo");
	            int rsMovieNo = rs.getInt("movieNo");
	            int rsSchNo = rs.getInt("schNo");

	            ScreeningTable screeningmovieinfosetting  = new ScreeningTable();
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
	      disConnectDB();
	      return arrayList;
	   }
	
	
	
}
