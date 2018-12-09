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
	String id;						// 사용자 아이디
	

	public Reservation(String id) {
		this.id = id;
	}
	
	public void ReservationStart() {
		   Print.printMessage("------------------ 영화예약관리 ------------------");
		      Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		      Print.printMessage("1: 예약  2: 예약내역조회  3: 취소");
		      
		      Scanner sc = new Scanner(System.in);
		      String menu = sc.next();
		      
		      // 영화 예약
		      if(menu.equals("1")) {
		    	  add();
		      }
		      
		      // 영화 예약내역 조회
		      if(menu.equals("2")) {
		    	  
		      }
		      
		      // 영화 예약 취소
		      if(menu.equals("3")) {
		    	  
		      }
		            
	}
	
	private void add() {
		System.out.println("예매하실 영화를 선택해주세요.");
		int movieNo = selectMovie();
		
		System.out.println("영화관을 선택해주세요.");
		int branchNo = selectTheater();
		
		System.out.println("상영날짜를 선택해주세요.");
		int movieSchedule = selectDay(movieNo, branchNo);
		
	}
	
	public void Reservationd(String id)  { // id를 인자로 받아옴
		
		/*
	
		System.out.println("상영날짜를 선택해주세요.");
		int movieSchedule = selectDay(movieNo,branchNo);
		System.out.println("상영관을 선택해주세요.");
		int screenNum = selectScreen();
		System.out.println("시간을 선택해주세요.");
		selectTime();
		System.out.println("몇장을 예매하시겠습니까?");
		int num = sc.nextInt();
		int price = num * 10000;
		System.out.println("좌석을 선택해주세요.");
		String seatNo = selectSeat(num);
		*/
		
	}
	
	// 영화 선택, 영화 번호 리턴
	public int selectMovie() { 
		System.out.println("-----------------상영중인 영화 정보---------------");
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

	 // 영화관 선택
	public int selectTheater() {
		System.out.println("-----------------영화가 상영중인 지점---------------");
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
	
	
	// 상영 날짜 선택
	public int selectDay(int movieNo, int branchNo) { 
		System.out.println("-----------------선택한 지점과 영화에 대한 상영 날짜---------------");
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		
		List<Integer> scheduleCodeList = screeningTableDAO.getScheduleCodeList(movieNo, branchNo);
		
		
		
	//	sql = "";	// 날짜 선택하기

		return 0;
	}
	
	public int selectScreen() { // 상영관 선택
		System.out.println("-----------------선택한 지점,영화,상영 날짜에 대한 상영관---------------");


		return 0;
	}
	
	public void selectTime() { // 시간 선택
		
	}
	
	public String selectSeat(int num) { // 좌석 선택
		return null;
	}
}
