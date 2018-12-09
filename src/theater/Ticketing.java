package theater;

import java.util.Scanner;

import dao.MovieInfoSettingDAO;
import dao.PaymentDAO;
import dao.ReservationDAO;

public class Ticketing {
	Scanner sc = new Scanner(System.in);
	String inputMenu = "";
	private String id ;
	public void start() {
		Print.printMessage("--------------- 영 화 티 켓 발 행 ---------------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 티켓 발행 (결제 완료한 고객)  2: 현장 결제 후 티켓 발행");
		inputMenu = sc.next();
		menu(inputMenu);
	}

	private void menu(String menu) {
		switch(menu) {
		case "1":
			ReservationDAO reservationdao = new ReservationDAO();
			Print.printMessage("-> 티켓 발행");
			Print.printMessage("-> 티켓을 수령하려는 ID를 입력하세요.");
			id = sc.next();
			String isPayment = reservationdao.isPaymentID(id);
			if(isPayment.equals("true")) {
				Print.printMessage("!! 영화 티켓 수령 성공");
			}
			else {
				Print.printMessage("!! 영화 티켓 수령 실패");
				Print.printMessage("!! 현장 결제 후 티켓 발행을 실행해주세요.");
			}
			break;
		case "2":
			PaymentDAO paymentdao = new PaymentDAO();
			Print.printMessage("-> 현장 결제 후 티켓 발행");
			
			if(paymentdao.onSitePayment(id, resNo)) {
				Print.printMessage("!! 현장 결제 성공");
				Print.printMessage("--------------- 영 화 티 켓 발 행 ---------------");
			}
			else {
				Print.printMessage("!! 현장 결제 실패");
			}
			break;
		default:
			System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
	}
		
}
}
