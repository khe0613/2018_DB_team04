package theater;

import java.util.List;
import java.util.Scanner;

import dao.MovieInfoSettingDAO;
import dao.PaymentDAO;
import dao.ReservationDAO;

public class Ticketing {
	Scanner sc = new Scanner(System.in);
	String inputMenu = "";
	private String id;
	private String resNo;
	private List<Reservation> resList;
	private String isPayment;
	
	public void start() {
		Print.printMessage("--------------- �� ȭ Ƽ �� �� �� ---------------");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: Ƽ�� ���� (���� �Ϸ��� ��)  2: ���� ���� �� Ƽ�� ����");
		inputMenu = sc.next();
		menu(inputMenu);
	}

	private void menu(String menu) {
		ReservationDAO reservationdao = new ReservationDAO();
		switch(menu) {
		case "1":
			Print.printMessage("-> Ƽ�� ����");
			Print.printMessage("-> Ƽ���� �����Ϸ��� ID�� �Է��ϼ���.");
			id = sc.next();
			// �ش� ���̵� ���� ���� ���� ���� ���
			resList = reservationdao.getPaymentListOfID(id);
			for(Reservation res : resList) {
				Print.printMessage("resNo	memberId	payNo		movieNo		movieSchedule	bookingTime	bookingDay	screenNum	price	ispayment");
				Print.printMessage(res.getResNo()+"	"+res.getId()+"		"+res.getPayNo()+"		"+res.getMovieNo()+"		"+res.getMovieSchedule()
				+"		"+res.getBookingTime()+"		"+res.getBookingDay()+"		"+res.getScreenNum()+"		"+res.getPrice()+"	"+res.getIspayment());
			}
			Print.printMessage("-> Ƽ���� �������� ���Ź�ȣ�� �Է����ּ���.");
			resNo = sc.next();
			isPayment = reservationdao.isPayment(resNo);
			if(isPayment.equals("true")) {
				Print.printMessage("������ �Ϸ�� ����Ƽ���Դϴ�.");
				Print.printMessage("!! ��ȭ Ƽ�� ���� ����");
			}
			else {
				Print.printMessage("������ �Ϸ���� ���� ����Ƽ���Դϴ�.");
				Print.printMessage("!! ��ȭ Ƽ�� ���� ����");
				Print.printMessage("!! ���� ���� �� Ƽ�� ������ �������ּ���.");
			}
			break;
		case "2":
			PaymentDAO paymentdao = new PaymentDAO();
			Print.printMessage("-> ���� ���� �� Ƽ�� ����");
			Print.printMessage("-> Ƽ���� �����Ϸ��� ID�� �Է��ϼ���.");
			id = sc.next();
			// �ش� ���̵� ���� ���� ���� ���� ���
			resList = reservationdao.getPaymentListOfID(id);
			for(Reservation res : resList) {
				Print.printMessage("resNo	memberId	payNo		movieNo		movieSchedule	bookingTime	bookingDay	screenNum	price	ispayment");
				Print.printMessage(res.getResNo()+"	"+res.getId()+"		"+res.getPayNo()+"		"+res.getMovieNo()+"		"+res.getMovieSchedule()
				+"		"+res.getBookingTime()+"		"+res.getBookingDay()+"		"+res.getScreenNum()+"		"+res.getPrice()+"	"+res.getIspayment());
			}
			Print.printMessage("-> Ƽ���� �����Ϸ��� ���Ź�ȣ�� �Է����ּ���.");
			resNo = sc.next();
			isPayment = reservationdao.isPayment(resNo);
			if(isPayment.equals("false")) {
				if(paymentdao.onSitePayment(id, resNo)) {
					reservationdao.EndPayment(resNo); // true�� ����
					Print.printMessage("!! ���� ���� ����");
					Print.printMessage("--------------- �� ȭ Ƽ �� �� �� ---------------");
				}
				else {
					Print.printMessage("!! ���� ���� ����");
				}   
			}else {
				Print.printMessage("�̹� ������ �Ϸ�� Ƽ���Դϴ�.");
				Print.printMessage("Ƽ�� ������ �Ϸ� ������.");
			}
			break;
		default:
			System.out.println("�߸��� �Է��Դϴ�. �ٽ� �õ����ּ���.");
	}
		
}
}
