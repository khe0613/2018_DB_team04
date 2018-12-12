package theater;

import java.util.List;
import java.util.Scanner;

import dao.ReservationDAO;

public class Ticketing {
	Scanner sc = new Scanner(System.in);
	String inputMenu = "";
	private String id;
	private String resNo;
	private List<Reservation> resList;
	private String isPayment;
	
	public void start() {
		Print.printMessage("--------------- 영 화 티 켓 발 행 ---------------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 티켓 발행 (결제 완료한 고객)  2: 현장 결제 후 티켓 발행");
		inputMenu = sc.next();
		menu(inputMenu);
	}

	private void menu(String menu) {
		ReservationDAO reservationdao = new ReservationDAO();
		switch(menu) {
		case "1":
			Print.printMessage("-> 티켓 발행");
			Print.printMessage("-> 티켓을 수령하려는 ID를 입력하세요.");
			id = sc.next();
			// 해당 아이디에 대한 예매 내역 전부 출력
			resList = reservationdao.getPaymentListOfID(id);
			for(Reservation res : resList) {
				Print.printMessage("resNo	memberId	  movieNo		movieSchedule	bookingTime	bookingDay	screenNum	price	ispayment");
				Print.printMessage(res.getResNo()+"	"+res.getId()+"		"+res.getMovieNo()+"		"+res.getMovieSchedule()
				+"		"+res.getBookingTime()+"		"+res.getBookingDay()+"		"+res.getScreenNum()+"		"+res.getPrice()+"	"+res.getIspayment());
			}
			Print.printMessage("-> 티켓을 받으려는 예매번호를 입력해주세요.");
			resNo = sc.next();
			isPayment = reservationdao.isPayment(resNo);
			if(isPayment.equals("true")) {
				Print.printMessage("결제가 완료된 예매티켓입니다.");
				Print.printMessage("!! 영화 티켓 수령 성공");
			}
			else {
				Print.printMessage("결제가 완료되지 않은 예매티켓입니다.");
				Print.printMessage("!! 영화 티켓 수령 실패");
				Print.printMessage("!! 현장 결제 후 티켓 발행을 실행해주세요.");
			}
			break;
		case "2":
			payment payment = new payment();
			Print.printMessage("-> 현장 결제 후 티켓 발행");
			Print.printMessage("-> 티켓을 결제하려는 ID를 입력하세요.");
			id = sc.next();
			// 해당 아이디에 대한 예매 내역 전부 출력
			resList = reservationdao.getPaymentListOfID(id);
			for(Reservation res : resList) {
				Print.printMessage("resNo	memberId	movieNo		movieSchedule	bookingTime	bookingDay	screenNum	price	ispayment");
				Print.printMessage(res.getResNo()+"	"+res.getId()+"		"+res.getMovieNo()+"		"+res.getMovieSchedule()
				+"		"+res.getBookingTime()+"		"+res.getBookingDay()+"		"+res.getScreenNum()+"		"+res.getPrice()+"	"+res.getIspayment());
			}
			Print.printMessage("-> 티켓을 결제하려는 예매번호를 입력해주세요.");
			resNo = sc.next();
			isPayment = reservationdao.isPayment(resNo);
			if(isPayment.equals("false")) {
				if(payment.onSitePayment(id, resNo)) {
					reservationdao.EndPayment(resNo); // true로 변경
					Print.printMessage("!! 현장 결제 성공");
					Print.printMessage("--------------- 영 화 티 켓 발 행 ---------------");
				}
				else {
					Print.printMessage("!! 현장 결제 실패");
				}   
			}else {
				Print.printMessage("이미 결제가 완료된 티켓입니다.");
				Print.printMessage("티켓 발행을 하러 가세요.");
			}
			break;
		default:
			System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
	}
		
}
}
