package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import theater.Print;

// ��ȭ ���� DAO
public class ReservationDAO {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater?useSSL=false";
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";

	HashMap<Integer,Integer> chart;
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
	
	public HashMap<Integer,Integer> getMovieChart() {
		connectDB();
		chart = new HashMap<>();
		String sql = "SELECT movieNo FROM reservation";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int key = rs.getInt(1);
				if(chart.containsKey(key)) { // ��Ʈ�� ��ȭ�� �̹� �ִٸ� �߰� ����
					chart.put(key, chart.get(key) + 1);
				}else { // ��Ʈ�� ��ȭ�� �����Ƿ� �߰�
					chart.put(key, 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disConnectDB();
		return chart;
	}
	
	public String isPayment(String resNo) { // �������θ� �Ǵ���
		connectDB();
		String sql = "select ispayment from reservation where resNo =?"; // ���Ź�ȣ�� �������� ��������
		String check = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				check = rs.getString("ispayment");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disConnectDB();
		return check;
	}
	
	public boolean EndPayment(String resNo) { // �������θ� true�� ��ȯ
		connectDB();
		String sql = "update reservation set ispayment = ? where resNo =?"; // ���Ź�ȣ�� �������� ��������
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "true");
			pstmt.setString(2, resNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		disConnectDB();
		return true;
	}
}
