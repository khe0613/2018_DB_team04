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
	}
	
	public void Reservationd(String id)  { // id를 인자로 받아옴
		
		/*
		System.out.println("영화관을 선택해주세요.");
		int branchNo = selectTheater(movieNo);
		
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
	
	public int selectMovie() { // 영화 선택
		System.out.println("-----------------상영중인 영화 정보---------------");
		MovieDAO movieDAO = new MovieDAO();
		
		List<String> movieList = movieDAO.getMovieList();
		
		System.out.println(movieList);
		
		
		System.out.println();
		
		//String selectMovie = sc.next(); // 영화 선택
		//sql = "select movieNo from Movie where movieName = ?";
	//	pstmt = conn.prepareStatement(sql);
	//	pstmt.setString(1, selectMovie);
	//	rs = pstmt.executeQuery();
	//	return rs.getInt("movieNo");
		return 1;
	}

	public int selectTheater(int movieNo) throws ClassNotFoundException, SQLException { // 영화관 선택
		System.out.println("-----------------영화가 상영중인 지점---------------");
		
		
		/*
		sql = "select theater.branchName from theater inner join ScreeningTable on (theater.branchNo = ScreeningTable.movieBranchNo) where = ?"; 
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, movieNo);
		rs = pstmt.executeQuery();
		while(rs.next()) { // 상영 지점 출력
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
	
	
	
	public int selectDay(int movieNo, int branchNo) throws ClassNotFoundException, SQLException { // 상영 날짜 선택
		System.out.println("-----------------선택한 지점과 영화에 대한 상영 날짜---------------");
		
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
