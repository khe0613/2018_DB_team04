package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import theater.MovieInfoSetting;
import theater.VIPSearch;

public class VIPDAO {
	String jdbcUrl; 
	   String dbId;
	   String dbPass;
	   
	   Connection conn;
	   PreparedStatement pstmt;
	   
	   public VIPDAO() {
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
	   
	   /* VIP �˻��� ���� ���� ���̺��� ���� �������� */
	   public ArrayList<VIPSearch> getReservationList(String startTime, String endTime) {
		   ArrayList<VIPSearch> arrayList = new ArrayList<>();
		   try {
		         String SQL = "SELECT resNo, memberId FROM reservation WHERE bookingDay BETWEEN ? AND ?";
		         pstmt = conn.prepareStatement(SQL);
		         pstmt.setString(1, startTime);
		         pstmt.setString(2, endTime);
		         ResultSet rs = pstmt.executeQuery();
		         
		         // ��ȸ��� ���� ����
		         if(!rs.next()) {
		            return null;
		         }
		         
		         rs.beforeFirst(); // ù ������ �̵�  -> �̰� �³� ?
		         
		         // ��� ��������
		         while(rs.next()) {
		            int rsResNo = rs.getInt("resNo");
		            String rsMemberID = rs.getString("memberId");
		            VIPSearch vipSearch = new VIPSearch(rsResNo, rsMemberID);
		            
		            arrayList.add(vipSearch);
		         }
		         
		      }catch(Exception e){
		           e.printStackTrace();
		       }finally{
		           if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		            if(conn != null) try{conn.close();}catch(SQLException sqle){}
		       }
		   return arrayList;
	   }
}
