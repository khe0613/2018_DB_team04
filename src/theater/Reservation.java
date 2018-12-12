package theater;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import dao.MovieDAO;
import dao.ReservationDAO;
import dao.ScheduleDAO;
import dao.ScreenDAO;
import dao.ScreeningTableDAO;
import dao.TheaterDAO;

public class Reservation {
	private String resNo;	
	private String id;						// 사용자 아이디
	private int seatNo;
	private int movieNo;
	private int movieSchedule;
	private String bookingTime;
	private String bookingDay;
	private int branchNo;
	private int screenNum;
	private int price;
	private String ispayment;
	
	public Reservation(String id) {
		this.id = id;
		this.price = 11000;
	}
	
	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public int getSeatNo() {
		return seatNo;
	}


	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
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


	public String getBookingDay() {
		return bookingDay;
	}

	public void setBookingDay(String bookingDay) {
		this.bookingDay = bookingDay;
	}

	public int getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(int branchNo) {
		this.branchNo = branchNo;
	}

	public int getScreenNum() {
		return screenNum;
	}

	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
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
		    	  add();
		      }
		      
		      // 영화 예약내역 조회
		      if(menu.equals("2")) {
		    	  getReservationHisotry();	//예약 내역 조회
		      }
		      
		      // 영화 예약 취소
		      if(menu.equals("3")) {
		    	  cancelReservation();
		    	  
		      }
		            
	}
	
	private void add() {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("예매하실 영화를 선택해주세요.");
		this.movieNo = selectMovie();
		
		System.out.println("영화관을 선택해주세요.");
		this.branchNo = selectTheater();
		
		// 선택한 영화관, 영화에 대한 일정 리스트 받아오기
		List<Schedule> scheduleList = getScheduleList();
	
		System.out.println("상영날짜를 선택해주세요.");
		String screeningDate = selectDate(scheduleList);
		
		System.out.println("상영시간을 선택해주세요.");
		String time = selectTime(scheduleList, screeningDate);
		
		// 선택한 날짜, 시간에 대한 일정 코드 얻어오기
		int schNo = getScheduleCode(scheduleList, screeningDate, time);	
		
		
		System.out.println("상영관을 선택해주세요.");
		this.screenNum = selectScreen(schNo);
		
		// 선택한 영화, 영화관, 상영관, 상영 날짜, 상영 시간에 대한 상영표 코드 얻어오기
		this.movieSchedule = getScreeningTableNo(schNo);
		
		
		System.out.print("예약할 좌석 수를 입력해주세요 : ");
		int seat_count = sc.nextInt();
		
		System.out.println("예약할 좌석을 선택해 주세요");
		List<Integer> seat_list = selectSeat(seat_count);		//예약할 죄석 리스트
		
		System.out.println("총 결제 금액은 " + String.format("%,d", (this.price * seat_count)) +"원 입니다");
	
		
		
		if(!doReservation(seat_list)) {
			System.out.println("예약에 실패하였습니다.");
		}else {
			System.out.println("예약에 성공하였습니다.");
		}
		
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
	public List<Schedule> getScheduleList() { 
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		
		List<Integer> scheduleCodeList = screeningTableDAO.getScheduleCodeList(this.movieNo, this.branchNo);
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
	public int selectScreen(int schNo) {
		System.out.println("-----------------선택한 지점,영화, 날짜, 시간에 대한 상영관---------------");
		
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		List<Integer> screenList = screeningTableDAO.getScreenList(this.movieNo, this.branchNo, schNo);
		
		for(int i=0; i< screenList.size(); i++) {
			System.out.print((i+1) +". 상영관 " +screenList.get(i) +"\t");
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int selected = sc.nextInt();
		
		return screenList.get(selected - 1);
	}
	
	// 선택한 영화, 영화관, 상영관, 상영 날짜, 상영 시간에 대한 상영표 코드 얻어오기
	public int getScreeningTableNo(int schNo) {
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		
		int screeningTableNo = screeningTableDAO.getScreeningTableNo(this.branchNo, this.screenNum, this.movieNo, schNo);
		
		return screeningTableNo;
	}
	
	
	// 예약할 티켓 수에 대한 좌석 선택하기 (예약할 좌석 수 , 상영표 코드)를 인자로 받는다.
	public List<Integer> selectSeat(int seat_count){
		ReservationDAO reservationDAO = new ReservationDAO();		
		ScreenDAO screenDAO = new ScreenDAO();
		
		
		List<Integer> seat_list = reservationDAO.getReservedSeatList(this.movieSchedule);
		int total_seat = screenDAO.getTotalSeat(this.screenNum, this.branchNo);
		List<Integer> selected_seat_list = new ArrayList<>();
		
		System.out.println("-------------------좌석 현황-----------------");
		System.out.println("                   Screen                 ");
		for(int seat = 1; seat <= total_seat; seat ++) {
			
			if(seat_list.contains(seat)) {
				System.out.format("%4s", "-");
			}else {
				System.out.format("%4s", seat);
			}
			
			
			if(seat % 10 == 0) {			// 한줄에 10좌석
				System.out.println();
			}
		}
		
		if( (total_seat % 10) != 0) {
			System.out.println();
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < seat_count; i++) {
			selected_seat_list.add(sc.nextInt());
		}
		
		return selected_seat_list;
	}
	
	public int selectPaymentMethod(){
	
		return 0;
	}	
	
	
	public boolean doReservation(List<Integer> seat_list) {
		boolean success = true;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-kk-mm-ss", Locale.KOREA);
		Date date = new Date();
		String time = simpleDateFormat.format(date);
		

		this.bookingTime = time.split("-")[3] + ":" + time.split("-")[4] + ":" + time.split("-")[5];
		this.bookingDay = time.split("-")[0] + "-" + time.split("-")[1] + "-" + time.split("-")[2];
		this.ispayment = "false";
		
		ReservationDAO reservationDAO = new ReservationDAO();
	
		
		int reservation_count = reservationDAO.getFinalResNo() + 1;					// 예매 번호에서 가운데 위치하는 부분. 
		int seat_count = 1;			// 한 회원이 한번에 여러장 예매했을 경우. 예매 번호 맨뒷자리로 이를 식별
		for(int seat : seat_list) {
			// "년월일-예약번호-회원이 여러장 예매했을때 구분하는 숫자"
			this.resNo = time.split("-")[0] + time.split("-")[1] + time.split("-")[2]
							+ "-" + reservation_count
							+ "-" + seat_count;
			this.seatNo = seat;
			
			// 예약
			if(!reservationDAO.doReservation(this)) {
				success = false;
			}
			
	
			seat_count++;
		}
		
		return success;
	}
	
	public void getReservationHisotry() {
		ReservationDAO reservationDAO = new ReservationDAO();
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		MovieDAO movieDAO = new MovieDAO();
		TheaterDAO theaterDAO = new TheaterDAO();
		
		List<Reservation> reservationHistory = reservationDAO.getPaymentListOfID(this.id);
		
		
		System.out.println("---------------- 예약 내역 -------------------");
		System.out.format("%20s", "예매 번호"); System.out.format("%20s", "영화명"); 	System.out.format("%10s", "영화관명");   System.out.format("%10s", "상영관");
		System.out.format("%10s", "좌석번호");  System.out.format("%10s", "상영 일자"); System.out.format("%10s", "시작 시각"); 	System.out.format("%10s", "종료 시각");
		System.out.format("%10s", "금액"); System.out.format("%10s", "결제 여부");
		System.out.println();
		
		for(Reservation reservation : reservationHistory) {
			int schNo = screeningTableDAO.getSchNo_about_screeningtableNo(reservation.getMovieSchedule());	// 상영표 번호에 대한 일정 코드
			Schedule schedule = scheduleDAO.getScheduleList(schNo);
			
			
			System.out.format("%20s", reservation.getResNo()); System.out.format("%20s", movieDAO.getMovieName(reservation.getMovieNo())); 	
			System.out.format("%10s", theaterDAO.getBranchName(reservation.getBranchNo())); System.out.format("%10s", reservation.getScreenNum());
			System.out.format("%10s", reservation.getSeatNo()); System.out.format("%10s", schedule.getScreeningDate()); 
			System.out.format("%10s", schedule.getStartTime()); System.out.format("%10s", schedule.getEndTime());
			System.out.format("%10s", reservation.getPrice()); System.out.format("%10s", reservation.getIspayment());
			System.out.println();
		}
		
		System.out.println();
	}
	
	// 예매 취소
	public void cancelReservation() {
		
	}
	
}
