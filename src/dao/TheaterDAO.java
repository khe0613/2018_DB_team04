package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import theater.Theater;

// ��ȭ�� DAO
public class TheaterDAO {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater?useSSL=false";
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public TheaterDAO() {
		
	}
	
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
	
	// �������� ��ȭ�� ����Ʈ�� (�����ڵ�. ������) ���·� ��ȯ
	public List<String> getTheaterList(){
		connectDB();
		String sql = "SELECT branchNo, branchName FROM theater";
		List<String> theaterList = new ArrayList<>();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return theaterList; // ������ ��ȭ ������ �� ����Ʈ ����
			}
			
			do {
				String theater = rs.getInt("branchNo") + ". " + rs.getString("branchName");
				theaterList.add(theater);
				}while(rs.next());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disConnectDB();

		return theaterList;
		
	}
	
	  public int registerTheater(Theater theater) {
	      
	      // ����Ϸ��� ��ȭ�� ������ ��ȭ�� ���̺� �̹� ��ϵǾ� �ִ� ���
	      if(get_branch_no(theater.getBranchName()) != -1) {
	         System.out.println("��ȭ�� ��Ͽ� �����Ͽ����ϴ�.\n");
	         return -1;
	      }
	      
	      connectDB();
	      
	      String sql = "INSERT INTO theater (screenNum, address, tel, branchName) VALUES (?, ?, ?, ?)";
	      boolean not_error_flag = false;                                    //   ��ȭ�� ��� �� �����߻� ����
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, theater.getScreenNum());
	         pstmt.setString(2, theater.getAddress());
	         pstmt.setString(3, theater.getTel());
	         pstmt.setString(4, theater.getBranchName());
	         pstmt.executeUpdate();
	         not_error_flag = true;
	         
	      } catch(SQLException e) {
	         e.printStackTrace();
	         not_error_flag = false;
	      }
	   
	      disConnectDB();
	      
	      if(not_error_flag) {
	         System.out.println("��ȭ�� ��Ͽ� �����Ͽ����ϴ�.\n");
	         return get_branch_no(theater.getBranchName());      // ����� ��ȭ������ �ο��� ������ȣ ����
	      }else {
	         System.out.println("��ȭ�� ��Ͽ� �����Ͽ����ϴ�.\n");
	         return -1;
	      }
	      
	   }
	   
	   public boolean modifyTheaterInfo(int branchNo, int screenNum, String address, String tel, String branchName) {
		   connectDB();
	      String sql = "UPDATE theater SET screenNum = ?, address = ?, tel = ?, branchName = ? WHERE branchNo = ?";
	      boolean not_error_flag = false;                                 //   ��ȭ�� ����  ���� �� �����߻� ����
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setInt(1, screenNum);
	         pstmt.setString(2, address);
	         pstmt.setString(3, tel);
	         pstmt.setString(4, branchName);
	         pstmt.setInt(5, branchNo);
	         pstmt.executeUpdate();
	         
	         not_error_flag = true;
	         
	      } catch(SQLException e) {
	         not_error_flag = false;
	      }
	   

	      
	      if(not_error_flag) {
	         System.out.println("��ȭ�� ���� ������ �����Ͽ����ϴ�.\n");
	      }else {
	         System.out.println("��ȭ�� ���� ������ �����Ͽ����ϴ�.\n");      
	      }
	      
	      disConnectDB();
	      
	      return not_error_flag;
	   }
	   
	   public boolean removeTheater(int branchNo) {
		   connectDB();
	      String sql = "DELETE FROM theater WHERE branchNo = ?";
	      boolean not_error_flag = false;                     //   ��ȭ�� ���� �� �����߻� ����
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setInt(1, branchNo);
	         pstmt.executeUpdate();
	         
	         not_error_flag = true;
	      } catch (SQLException e) {
	         not_error_flag = false;
	      }
	      
	      if(not_error_flag) {
	         System.out.println("��ȭ�� ������ �����Ͽ����ϴ�.\n");
	      }else {
	         System.out.println("��ȭ�� ������ �����Ͽ����ϴ�.\n");      
	      }
	      disConnectDB();
	      
	      return not_error_flag;
	   }
	   
	   
	   // ��ȭ�� �����ڵ忡 ���� �������� ��ȯ�ϴ� �Լ�(){
	  public String getBranchName(int branchNo) {
		  connectDB();
		  String sql = "SELECT branchName FROM theater WHERE branchNo = ?";
		  String branch_name = null;
		  
		  try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, branchNo);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				return null;
			}
			
			branch_name = rs.getString("branchName");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		  
		  disConnectDB();
		  return branch_name;
	  }
	   
	   
	   
	   // ��ȭ�� ������ ���� �����ڵ带 ��ȯ�ϴ� �Լ�
	   private int get_branch_no(String branchName) {
		   connectDB();
	      String sql = "SELECT * FROM theater WHERE branchName = ?";
	      ResultSet rs = null;
	      int branch_no = 0;
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, branchName);
	         rs = pstmt.executeQuery();
	         
	         if(!rs.next()) {
	            return -1;
	         }
	         
	         branch_no = rs.getInt("branchNo");
	         
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	      
	   disConnectDB();
	        
	      return branch_no;
	   }
	   
	   /* ��ȭ�� ���� ��� ��� */
	   public ArrayList<Theater> getMovieInfoListSQL() {
	      connectDB();
	      try {
	         ArrayList<Theater> arrayList = new ArrayList<>();
	         String SQL = "SELECT * FROM theater";
	         pstmt = conn.prepareStatement(SQL);
	         ResultSet rs = pstmt.executeQuery();
	         
	         // ��ȸ��� ���� ����
	         if(!rs.next()) {
	            return null;
	         }
	         
	         rs.beforeFirst(); // ù ������ �̵�  -> �̰� �³� ?
	         
	         // ��� ��������
	         while(rs.next()) {
	            int rsBranchNo = rs.getInt("branchNo");
	            int rsScreenNum = rs.getInt("screenNum");
	            String rsAddress = rs.getString("address");
	            String rsTel = rs.getString("tel");
	            String rsBranchName = rs.getString("branchName");
	            
	            Theater theater = new Theater();
	            theater.setBranchNo(rsBranchNo);
	            theater.setScreenNum(rsScreenNum);
	            theater.setAddress(rsAddress);
	            theater.setTel(rsTel);
	            theater.setBranchName(rsBranchName);
	            
	            arrayList.add(theater);
	         }
	         
	         disConnectDB();
	         return arrayList;
	      }catch(Exception e){
	    	  disConnectDB();
	           e.printStackTrace();
	       }
	      
	      return null;
	   }

}
