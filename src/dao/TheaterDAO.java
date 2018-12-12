package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import theater.Theater;

// 영화관 DAO
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
	
	// 영업중인 영화관 리스트를 (지점코드. 지점명) 형태로 반환
	public List<String> getTheaterList(){
		connectDB();
		String sql = "SELECT branchNo, branchName FROM theater";
		List<String> theaterList = new ArrayList<>();
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return theaterList; // 상영중인 영화 없으면 빈 리스트 리턴
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
	      
	      // 등록하려는 영화관 정보가 영화관 테이블에 이미 등록되어 있는 경우
	      if(get_branch_no(theater.getBranchName()) != -1) {
	         System.out.println("영화관 등록에 실패하였습니다.\n");
	         return -1;
	      }
	      
	      connectDB();
	      
	      String sql = "INSERT INTO theater (screenNum, address, tel, branchName) VALUES (?, ?, ?, ?)";
	      boolean not_error_flag = false;                                    //   영화관 등록 중 에러발생 여부
	      
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
	         System.out.println("영화관 등록에 성공하였습니다.\n");
	         return get_branch_no(theater.getBranchName());      // 등록한 영화관에게 부여된 지점번호 리턴
	      }else {
	         System.out.println("영화관 등록에 실패하였습니다.\n");
	         return -1;
	      }
	      
	   }
	   
	   public boolean modifyTheaterInfo(int branchNo, int screenNum, String address, String tel, String branchName) {
		   connectDB();
	      String sql = "UPDATE theater SET screenNum = ?, address = ?, tel = ?, branchName = ? WHERE branchNo = ?";
	      boolean not_error_flag = false;                                 //   영화관 정보  수정 중 에러발생 여부
	      
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
	         System.out.println("영화관 정보 수정에 성공하였습니다.\n");
	      }else {
	         System.out.println("영화관 정보 수정에 실패하였습니다.\n");      
	      }
	      
	      disConnectDB();
	      
	      return not_error_flag;
	   }
	   
	   public boolean removeTheater(int branchNo) {
		   connectDB();
	      String sql = "DELETE FROM theater WHERE branchNo = ?";
	      boolean not_error_flag = false;                     //   영화관 삭제 중 에러발생 여부
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setInt(1, branchNo);
	         pstmt.executeUpdate();
	         
	         not_error_flag = true;
	      } catch (SQLException e) {
	         not_error_flag = false;
	      }
	      
	      if(not_error_flag) {
	         System.out.println("영화관 삭제에 성공하였습니다.\n");
	      }else {
	         System.out.println("영화관 삭제에 실패하였습니다.\n");      
	      }
	      disConnectDB();
	      
	      return not_error_flag;
	   }
	   
	   
	   // 영화관 지점코드에 대한 지점명을 반환하는 함수(){
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
	   
	   
	   
	   // 영화관 지점명에 대한 지점코드를 반환하는 함수
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
	   
	   /* 영화관 정보 목록 출력 */
	   public ArrayList<Theater> getMovieInfoListSQL() {
	      connectDB();
	      try {
	         ArrayList<Theater> arrayList = new ArrayList<>();
	         String SQL = "SELECT * FROM theater";
	         pstmt = conn.prepareStatement(SQL);
	         ResultSet rs = pstmt.executeQuery();
	         
	         // 조회결과 정보 없음
	         if(!rs.next()) {
	            return null;
	         }
	         
	         rs.beforeFirst(); // 첫 행으로 이동  -> 이게 맞나 ?
	         
	         // 목록 꺼내오기
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
