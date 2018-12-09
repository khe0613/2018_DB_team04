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
		Print.printMessage("--------------- �� ȭ Ƽ �� �� �� ---------------");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: Ƽ�� ���� (���� �Ϸ��� ��)  2: ���� ���� �� Ƽ�� ����");
		inputMenu = sc.next();
		menu(inputMenu);
	}

	private void menu(String menu) {
		switch(menu) {
		case "1":
			ReservationDAO reservationdao = new ReservationDAO();
			Print.printMessage("-> Ƽ�� ����");
			Print.printMessage("-> Ƽ���� �����Ϸ��� ID�� �Է��ϼ���.");
			id = sc.next();
			String isPayment = reservationdao.isPaymentID(id);
			if(isPayment.equals("true")) {
				Print.printMessage("!! ��ȭ Ƽ�� ���� ����");
			}
			else {
				Print.printMessage("!! ��ȭ Ƽ�� ���� ����");
				Print.printMessage("!! ���� ���� �� Ƽ�� ������ �������ּ���.");
			}
			break;
		case "2":
			PaymentDAO paymentdao = new PaymentDAO();
			Print.printMessage("-> ���� ���� �� Ƽ�� ����");
			
			if(paymentdao.onSitePayment(id, resNo)) {
				Print.printMessage("!! ���� ���� ����");
				Print.printMessage("--------------- �� ȭ Ƽ �� �� �� ---------------");
			}
			else {
				Print.printMessage("!! ���� ���� ����");
			}
			break;
		default:
			System.out.println("�߸��� �Է��Դϴ�. �ٽ� �õ����ּ���.");
	}
		
}
}
