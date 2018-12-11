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
	private String id;						// ����� ���̵�
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
		   Print.printMessage("------------------ ��ȭ������� ------------------");
		      Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		      Print.printMessage("1: ����  2: ���೻����ȸ  3: ���");
		      
		      Scanner sc = new Scanner(System.in);
		      String menu = sc.next();
		      
		      // ��ȭ ����
		      if(menu.equals("1")) {
		    	  // ��� id �ʿ�
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
		int movieNo = selectMovie();
		
		System.out.println("��ȭ���� �������ּ���.");
		int branchNo = selectTheater();
		
		// ������ ��ȭ��, ��ȭ�� ���� ���� ����Ʈ �޾ƿ���
		List<Schedule> scheduleList = getScheduleList(movieNo, branchNo);
		
		System.out.println("�󿵳�¥�� �������ּ���.");
		String screeningDate = selectDate(scheduleList);
		
		System.out.println("�󿵽ð��� �������ּ���.");
		String time = selectTime(scheduleList, screeningDate);
		
		// ������ ��¥, �ð��� ���� ���� �ڵ� ������
		int schNo = getScheduleCode(scheduleList, screeningDate, time);	
		
		
		System.out.println("�󿵰��� �������ּ���.");
		int screenNo = selectScreen(movieNo, branchNo, schNo);
		
		// ������ ��ȭ, ��ȭ��, �󿵰�, �� ��¥, �� �ð��� ���� ��ǥ �ڵ� ������
		int screeningtableNo = getScreeningTableNo(branchNo, screenNo, movieNo, schNo);
		
		
		System.out.println(screeningtableNo);
		System.out.print("������ �¼� ���� �Է����ּ��� : ");
		int seat_count = sc.nextInt();
		
		System.out.println("������ �¼��� ������ �ּ���");
		//List<Integer> seat_list = 		//������ �˼� ����Ʈ
		
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
	public List<Schedule> getScheduleList(int movieNo, int branchNo) { 
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		
		List<Integer> scheduleCodeList = screeningTableDAO.getScheduleCodeList(movieNo, branchNo);
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
	public int selectScreen(int movieNo, int branchNo, int schNo) {
		System.out.println("-----------------������ ����,��ȭ, ��¥, �ð��� ���� �󿵰�---------------");
		
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		List<Integer> screenList = screeningTableDAO.getScreenList(movieNo, branchNo, schNo);
		
		for(int i=0; i< screenList.size(); i++) {
			System.out.print((i+1) +". �󿵰� " +screenList.get(i) +"\t");
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int selected = sc.nextInt();
		
		return screenList.get(selected - 1);
	}
	
	// ������ ��ȭ, ��ȭ��, �󿵰�, �� ��¥, �� �ð��� ���� ��ǥ �ڵ� ������
	public int getScreeningTableNo(int branchNo, int screenNo, int movieNo, int schNo) {
		ScreeningTableDAO screeningTableDAO = new ScreeningTableDAO();
		
		int screeningTableNo = screeningTableDAO.getScreeningTableNo(branchNo, screenNo, movieNo, schNo);
		
		return screeningTableNo;
	}
	
	// ������ Ƽ�� ���� ���� �¼� �����ϱ� (������ �¼� �� , ��ǥ �ڵ�)�� ���ڷ� �޴´�.
	public List<Integer> selectSeat(int seat_count, int screeningtableNo){
		
	}
	
	
}
