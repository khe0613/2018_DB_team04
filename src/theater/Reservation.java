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
import dao.ScreeningTableDAO;
import dao.TheaterDAO;

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
		
		System.out.println("��ȭ���� �������ּ���.");
		int branchNo = selectTheater();
		
		System.out.println("�󿵳�¥�� �������ּ���.");
		int movieSchedule = selectDay(movieNo, branchNo);
		
	}
	
	public void Reservationd(String id)  { // id�� ���ڷ� �޾ƿ�
		
		/*
	
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
	
	// ��ȭ ����, ��ȭ ��ȣ ����
	public int selectMovie() { 
		System.out.println("-----------------������ ��ȭ ����---------------");
		MovieDAO movieDAO = new MovieDAO();
		List<String> movieList = movieDAO.getMovieList();
		
		for(String movie: movieList) {
			System.out.print(movie +"\t");
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int movieNumber = sc.nextInt();
		
		return movieNumber;
	}

	 // ��ȭ�� ����
	public int selectTheater() {
		System.out.println("-----------------��ȭ�� ������ ����---------------");
		TheaterDAO theaterDAO = new TheaterDAO();
		List<String> theaterList = theaterDAO.getTheaterList();
		
		for(String theater : theaterList) {
			System.out.print(theater + "\t");
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int theaterNumber = sc.nextInt();
		
		return theaterNumber;
	}
	
	
	// �� ��¥ ����
	public int selectDay(int movieNo, int branchNo) { 
		System.out.println("-----------------������ ������ ��ȭ�� ���� �� ��¥---------------");
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		
		List<Integer> scheduleCodeList = screeningTableDAO.getScheduleCodeList(movieNo, branchNo);
		
		
		
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
