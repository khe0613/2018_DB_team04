package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.MovieDAO;
import dao.OccupiedSeatDAO;
import dao.ScheduleDAO;
import dao.ScreenDAO;
import dao.ScreeningTableDAO;
import dao.TheaterDAO;

public class Reservation {
	private int resNo;	
	private String id;						// ����� ���̵�
	private int payNo;
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
	}
	
	
	public int getResNo() {
		return resNo;
	}

	public void setResNo(int resNo) {
		this.resNo = resNo;
	}
	
	

	public int getBranchNo() {
		return branchNo;
	}


	public void setBranchNo(int branchNo) {
		this.branchNo = branchNo;
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
		Scanner sc= new Scanner(System.in);
		
		System.out.println("�����Ͻ� ��ȭ�� �������ּ���.");
		this.movieNo = selectMovie();
		
		System.out.println("��ȭ���� �������ּ���.");
		this.branchNo = selectTheater();
		
		// ������ ��ȭ��, ��ȭ�� ���� ���� ����Ʈ �޾ƿ���
		List<Schedule> scheduleList = getScheduleList();
		
		System.out.println("�󿵳�¥�� �������ּ���.");
		String screeningDate = selectDate(scheduleList);
		
		System.out.println("�󿵽ð��� �������ּ���.");
		String time = selectTime(scheduleList, screeningDate);
		
		// ������ ��¥, �ð��� ���� ���� �ڵ� ������
		int schNo = getScheduleCode(scheduleList, screeningDate, time);	
		
		
		System.out.println("�󿵰��� �������ּ���.");
		this.screenNum = selectScreen(schNo);
		
		// ������ ��ȭ, ��ȭ��, �󿵰�, �� ��¥, �� �ð��� ���� ��ǥ �ڵ� ������
		this.movieSchedule = getScreeningTableNo(schNo);
		
		
		System.out.print("������ �¼� ���� �Է����ּ��� : ");
		int seat_count = sc.nextInt();
		
		System.out.println("������ �¼��� ������ �ּ���");
		List<Integer> seat_list = selectSeat(seat_count);		//������ �˼� ����Ʈ
		
		
		System.out.println(seat_list);
	}
	
	public void Reservationd(String id)  { // id�� ���ڷ� �޾ƿ�
		
		/*
	
		
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
	
	
	// ������ ��ȭ, ��ȭ���� ���� ������(����) ����Ʈ ��ȯ
	public List<Schedule> getScheduleList() { 
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		
		List<Integer> scheduleCodeList = screeningTableDAO.getScheduleCodeList(this.movieNo, this.branchNo);
		List<Schedule> scheduleList= new ArrayList<>();	// ������  ����Ʈ
		for(int schNo : scheduleCodeList) {
			Schedule schedule = scheduleDAO.getScheduleList(schNo);
			if(schedule != null) {
				scheduleList.add(schedule);
			}
		}
		
		return scheduleList;
	}
	
	
	// ������ ������ ��ȭ�� ���� �� ��¥ �����ϱ�
	public String selectDate(List<Schedule> scheduleList) {
		List<String> dateList = new ArrayList<String>();
		
		for(Schedule schedule: scheduleList) {
			String date = schedule.getScreeningDate();
			if(!dateList.contains(date)) {
				dateList.add(date);
			}
			
		}
	
		System.out.println("-----------------������ ������ ��ȭ�� ���� �� ��¥---------------");
		for(int i = 0; i<dateList.size(); i++) {
			System.out.print((i+1) +". " + dateList.get(i) +"\t");
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int selected = sc.nextInt();
		
		return dateList.get(selected-1);
	}
	
	
	// ������ ����, ��ȭ, ��¥�� ���� �� �ð� �����ϱ�
	public String selectTime(List<Schedule> scheduleList, String screeningDate) {
		List<String> timeList = new ArrayList<>();
		
		System.out.println("-----------------������ ����, ��ȭ, ��¥�� ���� �� ��¥---------------");
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
	
	// �� ��¥, �� �ð��� ���� ���� �ڵ� ������
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
	
	 // ������ ��ȭ, ��ȭ��, �� ��¥, �� �ð��� ���� �󿵰� �����ϱ�
	public int selectScreen(int schNo) {
		System.out.println("-----------------������ ����,��ȭ, ��¥, �ð��� ���� �󿵰�---------------");
		
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		List<Integer> screenList = screeningTableDAO.getScreenList(this.movieNo, this.branchNo, schNo);
		
		for(int i=0; i< screenList.size(); i++) {
			System.out.print((i+1) +". �󿵰� " +screenList.get(i) +"\t");
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int selected = sc.nextInt();
		
		return screenList.get(selected - 1);
	}
	
	// ������ ��ȭ, ��ȭ��, �󿵰�, �� ��¥, �� �ð��� ���� ��ǥ �ڵ� ������
	public int getScreeningTableNo(int schNo) {
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		
		int screeningTableNo = screeningTableDAO.getScreeningTableNo(this.branchNo, this.screenNum, this.movieNo, schNo);
		
		return screeningTableNo;
	}
	
	
	// ������ Ƽ�� ���� ���� �¼� �����ϱ� (������ �¼� �� , ��ǥ �ڵ�)�� ���ڷ� �޴´�.
	public List<Integer> selectSeat(int seat_count){
		OccupiedSeatDAO occupiedSeatDAO = new OccupiedSeatDAO();		
		ScreenDAO screenDAO = new ScreenDAO();
		
		
		List<Integer> seat_list = occupiedSeatDAO.getOccupiedSeatList(this.movieSchedule);
		int total_seat = screenDAO.getTotalSeat(this.screenNum, this.branchNo);
		List<Integer> selected_seat_list = new ArrayList<>();
		
		System.out.println("-------------------�¼� ��Ȳ-----------------");
		System.out.println("                   Screen                 ");
		for(int seat = 1; seat <= total_seat; seat ++) {
			
			if(seat_list.contains(seat)) {
				System.out.format("%4s", "-");
			}else {
				System.out.format("%4s", seat);
			}
			
			
			if(seat % 10 == 0) {			// ���ٿ� 10�¼�
				System.out.println();
			}
		}
		
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < seat_count; i++) {
			selected_seat_list.add(sc.nextInt());
		}
		
		return selected_seat_list;
	}
	
	
}
