package theater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.MovieDAO;

public class Reservation {
	String id;						// ����� ���̵�
	

	public Reservation(String id) {
		this.id = id;
	}
	
	public void ReservationStart() {
		   Print.printMessage("------------------ ��ȭ������� ------------------");
		      Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		      Print.printMessage("1: ����  2: ���೻����ȸ  3: ���");
		      
		      Scanner sc = new Scanner(System.in);
		      String menu = sc.next();
		      
		      // ��ȭ ����
		      if(menu.equals("1")) {
		    	  add();
		      }
		      
		      // ��ȭ ���೻�� ��ȸ
		      if(menu.equals("2")) {
		    	  
		      }
		      
		      // ��ȭ ���� ���
		      if(menu.equals("3")) {
		    	  
		      }
		            
	}
	
	private void add() {
		System.out.println("�����Ͻ� ��ȭ�� �������ּ���.");
		int movieNo = selectMovie();
	}
	
	public void Reservationd(String id)  { // id�� ���ڷ� �޾ƿ�
		
		/*
		System.out.println("��ȭ���� �������ּ���.");
		int branchNo = selectTheater(movieNo);
		
		System.out.println("�󿵳�¥�� �������ּ���.");
		int movieSchedule = selectDay(movieNo,branchNo);
		System.out.println("�󿵰��� �������ּ���.");
		int screenNum = selectScreen();
		System.out.println("�ð��� �������ּ���.");
		selectTime();
		System.out.println("������ �����Ͻðڽ��ϱ�?");
		int num = sc.nextInt();
		int price = num * 10000;
		System.out.println("�¼��� �������ּ���.");
		String seatNo = selectSeat(num);
		*/
		
	}
	
	public int selectMovie() { // ��ȭ ����
		System.out.println("-----------------������ ��ȭ ����---------------");
		MovieDAO movieDAO = new MovieDAO();
		
		List<String> movieList = movieDAO.getMovieList();
		
		System.out.println(movieList);
		
		
		System.out.println();
		
		//String selectMovie = sc.next(); // ��ȭ ����
		//sql = "select movieNo from Movie where movieName = ?";
	//	pstmt = conn.prepareStatement(sql);
	//	pstmt.setString(1, selectMovie);
	//	rs = pstmt.executeQuery();
	//	return rs.getInt("movieNo");
		return 1;
	}

	public int selectTheater(int movieNo) throws ClassNotFoundException, SQLException { // ��ȭ�� ����
		System.out.println("-----------------��ȭ�� ������ ����---------------");
		
		
		/*
		sql = "select theater.branchName from theater inner join ScreeningTable on (theater.branchNo = ScreeningTable.movieBranchNo) where = ?"; 
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, movieNo);
		rs = pstmt.executeQuery();
		while(rs.next()) { // �� ���� ���
			System.out.println(rs.getString("branchName") + "/t");
		}
		String selectTherter = sc.next();
		sql = "select branchNo from theater where branchName = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, selectTherter);
		rs = pstmt.executeQuery();
		
		return rs.getInt("branchNo");
		*/
		return 0;
	}
	
	
	
	public int selectDay(int movieNo, int branchNo) throws ClassNotFoundException, SQLException { // �� ��¥ ����
		System.out.println("-----------------������ ������ ��ȭ�� ���� �� ��¥---------------");
		
	//	sql = "";	// ��¥ �����ϱ�

		return 0;
	}
	
	public int selectScreen() { // �󿵰� ����
		System.out.println("-----------------������ ����,��ȭ,�� ��¥�� ���� �󿵰�---------------");


		return 0;
	}
	
	public void selectTime() { // �ð� ����
		
	}
	
	public String selectSeat(int num) { // �¼� ����
		return null;
	}
}
