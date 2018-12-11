package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.MovieDAO;
import dao.ScheduleDAO;
import dao.ScreeningTableDAO;
import dao.TheaterDAO;

public class Reservation {
	private int resNo;	
	private String id;						// 사용자 아이디
	private int payNo;
	private int movieNo;
	private int movieSchedule;
	private String bookingTime;
	private String bookingDay;
	private int screenNum;
	private int price;
	private String ispayment;
	
	public Reservation(String id) {
		this.setId(id);
	}
	
	public int getResNo() {
		return resNo;
	}

	public void setResNo(int resNo) {
		this.resNo = resNo;
	}

	public int getPayNo() {
		return payNo;
	}

	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public int getMovieSchedule() {
		return movieSchedule;
	}

	public void setMovieSchedule(int movieSchedule) {
		this.movieSchedule = movieSchedule;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getScreenNum() {
		return screenNum;
	}

	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
	}

	public String getBookingDay() {
		return bookingDay;
	}

	public void setBookingDay(String bookingDay) {
		this.bookingDay = bookingDay;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIspayment() {
		return ispayment;
	}

	public void setIspayment(String ispayment) {
		this.ispayment = ispayment;
	}
	
	
	public void ReservationStart() {
		   Print.printMessage("------------------ 영화예약관리 ------------------");
		      Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		      Print.printMessage("1: 예약  2: 예약내역조회  3: 취소");
		      
		      Scanner sc = new Scanner(System.in);
		      String menu = sc.next();
		      
		      // 영화 예약
		      if(menu.equals("1")) {
		    	  // 멤버 id 필요
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
		Scanner sc= new Scanner(System.in);
		
		System.out.println("예매하실 영화를 선택해주세요.");
		int movieNo = selectMovie();
		
		System.out.println("영화관을 선택해주세요.");
		int branchNo = selectTheater();
		
		// 선택한 영화관, 영화에 대한 일정 리스트 받아오기
		List<Schedule> scheduleList = getScheduleList(movieNo, branchNo);
		
		System.out.println("상영날짜를 선택해주세요.");
		String screeningDate = selectDate(scheduleList);
		
		System.out.println("상영시간을 선택해주세요.");
		String time = selectTime(scheduleList, screeningDate);
		
		// 선택한 날짜, 시간에 대한 일정 코드 얻어오기
		int schNo = getScheduleCode(scheduleList, screeningDate, time);	
		
		
		System.out.println("상영관을 선택해주세요.");
		int screenNo = selectScreen(movieNo, branchNo, schNo);
		
		// 선택한 영화, 영화관, 상영관, 상영 날짜, 상영 시간에 대한 상영표 코드 얻어오기
		int screeningtableNo = getScreeningTableNo(branchNo, screenNo, movieNo, schNo);
		
		
		System.out.println(screeningtableNo);
		System.out.print("예약할 좌석 수를 입력해주세요 : ");
		int seat_count = sc.nextInt();
		
		System.out.println("예약할 좌석을 선택해 주세요");
		//List<Integer> seat_list = 		//예약할 죄석 리스트
		
	}
	
	public void Reservationd(String id)  { // id를 인자로 받아옴
		
		/*
	
		
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
	
	
	// 선택한 영화, 영화관에 대한 스케줄(일정) 리스트 반환
	public List<Schedule> getScheduleList(int movieNo, int branchNo) { 
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		
		List<Integer> scheduleCodeList = screeningTableDAO.getScheduleCodeList(movieNo, branchNo);
		List<Schedule> scheduleList= new ArrayList<>();	// 스케줄  리스트
		for(int schNo : scheduleCodeList) {
			Schedule schedule = scheduleDAO.getScheduleList(schNo);
			if(schedule != null) {
				scheduleList.add(schedule);
			}
		}
		
		return scheduleList;
	}
	
	
	// 선택한 지점과 영화에 대한 상영 날짜 선택하기
	public String selectDate(List<Schedule> scheduleList) {
		List<String> dateList = new ArrayList<String>();
		
		for(Schedule schedule: scheduleList) {
			String date = schedule.getScreeningDate();
			if(!dateList.contains(date)) {
				dateList.add(date);
			}
			
		}
	
		System.out.println("-----------------선택한 지점과 영화에 대한 상영 날짜---------------");
		for(int i = 0; i<dateList.size(); i++) {
			System.out.print((i+1) +". " + dateList.get(i) +"\t");
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int selected = sc.nextInt();
		
		return dateList.get(selected-1);
	}
	
	
	// 선택한 지점, 영화, 날짜에 대한 상영 시간 선택하기
	public String selectTime(List<Schedule> scheduleList, String screeningDate) {
		List<String> timeList = new ArrayList<>();
		
		System.out.println("-----------------선택한 지점, 영화, 날짜에 대한 상영 날짜---------------");
		for(Schedule schedule : scheduleList) {
			if( schedule.getScreeningDate().equals(screeningDate)) {
				String time = schedule.getStartTime() + "~" + schedule.getEndTime();
				if(!timeList.contains(time)){
					timeList.add(time);
				}
			}
		}
		
		for(int i = 0; i < timeList.size(); i++) {
			System.out.print((i+1) +". " + timeList.get(i) +"\t");
		}
		System.out.println();
		
		
		
		Scanner sc = new Scanner(System.in);
		int selected = sc.nextInt();
		
		return timeList.get(selected-1);
	}
	
	// 상영 날짜, 상영 시간에 대한 일정 코드 얻어오기
	 public int getScheduleCode(List<Schedule> scheduleList, String screeningDate, String time) {
		 String start_time = time.split("~")[0];
		 String  end_time = time.split("~")[1];
		 int sch_code = -1;
		 
		 for(Schedule schedule: scheduleList) {
			 if(schedule.getStartTime().equals(start_time)
					 && schedule.getEndTime().equals(end_time)
					 && schedule.getScreeningDate().equals(screeningDate)) {
				 sch_code = schedule.getSchNo();
				 break;
			 }
		 }
		 
		 return sch_code;
	 }
	
	 // 선택한 영화, 영화관, 상영 날짜, 상영 시간에 대한 상영관 선택하기
	public int selectScreen(int movieNo, int branchNo, int schNo) {
		System.out.println("-----------------선택한 지점,영화, 날짜, 시간에 대한 상영관---------------");
		
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		List<Integer> screenList = screeningTableDAO.getScreenList(movieNo, branchNo, schNo);
		
		for(int i=0; i< screenList.size(); i++) {
			System.out.print((i+1) +". 상영관 " +screenList.get(i) +"\t");
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int selected = sc.nextInt();
		
		return screenList.get(selected - 1);
	}
	
	// 선택한 영화, 영화관, 상영관, 상영 날짜, 상영 시간에 대한 상영표 코드 얻어오기
	public int getScreeningTableNo(int branchNo, int screenNo, int movieNo, int schNo) {
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		
		int screeningTableNo = screeningTableDAO.getScreeningTableNo(branchNo, screenNo, movieNo, schNo);
		
		return screeningTableNo;
	}
	
	// 예약할 티켓 수에 대한 좌석 선택하기 (예약할 좌석 수 , 상영표 코드)를 인자로 받는다.
	public List<Integer> selectSeat(int seat_count, int screeningtableNo){
		
	}
	
	
}
