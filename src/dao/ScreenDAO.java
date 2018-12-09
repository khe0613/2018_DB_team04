package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import theater.Screen;

// ��â
//�󿵰� DAO
public class ScreenDAO {
	String jdbcUrl; 
	String dbId;
	String dbPass;
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	// DB ���� connect
	private void connectDB() {
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
	
	// db ���� disconnect
	public void disconnectDB() {
		if(rs!= null) try {rs.close();}catch (Exception e) {}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		if(conn != null) try{conn.close();}catch(SQLException sqle){}
	}
	
	// �󿵰� �� ������
		public int getScreenNum(int branchNo) {
			connectDB();
			String sql = "select count(*) from screen where branchNo = ?";
			int screenNum = 0;
			try { // � ������ ���� �󿵰� ��
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
		
	// �󿵰� ���
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
	
	// �󿵰� ����
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
	
	// �󿵰� ����
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
	
}
