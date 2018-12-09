package theater;

import java.util.Scanner;

import dao.MemberDAO;

public class Movie {
	static boolean isLogin = false;
	static boolean isAdmin = false;
	private static Scanner sc = new Scanner(System.in);
	static String input_loginmenu;
	public static Member member = new Member();

	public enum ClientMenu {
		ȸ����������, ��ȭ�����˻�, ��ȭ����, ��ȭ����, �ٽ��Է��ϼ���;

		static ClientMenu got(String ch) {
			switch (ch) {
			case "1":
				return ȸ����������;
			case "2":
				return ��ȭ�����˻�;
			case "3":
				return ��ȭ����;
			case "4":
				return ��ȭ����;
			default:
				return �ٽ��Է��ϼ���;
			}
		}
	}

	public enum AdminMenu {
		��ȭ��������, ��ȭ����������, �󿵰���������, �󿵿�ȭ��������, VIP������, ��ȭƼ�Ϲ���, �ٽ��Է��ϼ���;

		static AdminMenu got(String ch) {
			switch (ch) {
			case "1":
				return ��ȭ��������;
			case "2":
				return ��ȭ����������;
			case "3":
				return �󿵰���������;
			case "4":
				return �󿵿�ȭ��������;
			case "5":
				return VIP������;
			case "6":
				return ��ȭƼ�Ϲ���;
			default:
				return �ٽ��Է��ϼ���;
			}
		}
	}

	public static boolean isLogin() {
		return isLogin;
	}

	public static void setLogin(boolean isLogin) {
		Movie.isLogin = isLogin;
	}

	public static boolean isAdmin() {
		return isAdmin;
	}

	public static void setAdmin(boolean isAdmin) {
		Movie.isAdmin = isAdmin;
	}

	public static void loginMenuPrint() {
		input_loginmenu = "";
		Print.printMessage("!! �α����� �Ǿ����� �ʽ��ϴ�.");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: �α���    2: ȸ������ ");
		input_loginmenu = sc.next();
		loginMenu(input_loginmenu);
	}

	private static void mainMenuPrint() {
		Print.printMessage("------------- Database TermProject 04�� -------------");
		Print.printMessage("---------------- ��  ȭ  ��  ��  ��  ��  ��  -------------------");
		if (!isLogin) {
			loginMenuPrint();
		}
		isAdmin = member.AdminCheck(member.getId()); // id �����ؼ� �����ڸ� true, �ƴϸ� false �����ؼ� �ֱ�.
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		if (!isAdmin) {
			Print.printMessage("1: ȸ����������    2: ��ȭ�����˻�    3: ��ȭ����    4: ��ȭ����");
		}
		if (isAdmin) {
			Print.printMessage("1: ��ȭ��������    2: ��ȭ����������  3: �󿵰���������  ");
			Print.printMessage("4: �󿵿�ȭ��������  5: VIP ������  6: ��ȭƼ�Ϲ���  ");
		}
	}

	public static void main(String args[]) {

		while (true) {

			String input_menu = "";
			mainMenuPrint(); // ���θ޴�
			input_menu = sc.next(); // �Է¹ޱ�
			if (input_menu.equals("quit")) { // ����
				return;
			}
			if (!isAdmin) {
				ClientMenu menu = ClientMenu.got(input_menu); // enum���� ������.
				clientMenu(menu); // switch�� ���� ������ MainMenu�� �� �޴��� �°� �з���.
			}
			if (isAdmin) {
				AdminMenu menu = AdminMenu.got(input_menu);
				adminMenu(menu);
			}
		}
	}

	public static void loginMenu(String menu) {

		if (menu.equals("1")) {
			Print.printMessage("�α��� ���");
			Member temp = member.login();
			if (temp != null) {
				member.setId(temp.getId());
				member.setPw(temp.getPw());
				isLogin = true;
			} else {
				loginMenuPrint();
			}

		}
		if (menu.equals("2")) {
			Print.printMessage("ȸ������ ���");
			Member temp = member.register();
			if (temp == null) { // ȸ�� ���� ����
				 loginMenuPrint();
			}else {
				member.setId(temp.getId());
				member.setPw(temp.getPw());
				setLogin(true);
			}
		
		}
	}

	public static void clientMenu(ClientMenu menu) {
		switch (menu) {
		case ȸ����������:
			Print.printMessage(menu);
			member.MemberModifyStart();
			break;
		case ��ȭ�����˻�:
			Print.printMessage(menu);
			new SearchMovie().getChart();
			break;
		case ��ȭ����:
			Print.printMessage(menu);
			 new Reservation(member.getId()).ReservationStart();
			break;
		case ��ȭ����:
			Print.printMessage(menu);
			new payment().Start(member.getId());
			break;
		default:
			Print.printMessage(menu);
			Print.printMessage("");
			Print.printMessage("");
			Print.printMessage("");
			Print.printMessage("");
			Print.printMessage("");
			break;
		}
	}

	public static void adminMenu(AdminMenu menu) {
		switch (menu) {
		case ��ȭ��������:
			Print.printMessage(menu);
			new MovieInfoSetting().start();
			break;
		case ��ȭ����������:
			Print.printMessage(menu);
			new TheaterInfo().start();
			break;
		case �󿵿�ȭ��������:
			Print.printMessage(menu);
			new ScreeningMovieInfoSetting().start();
			break;
		case �󿵰���������:
			Print.printMessage(menu);
			new Screen().start();
			break;
		case VIP������:
			Print.printMessage(menu);
			new VIPSearch().start();
			break;
		case ��ȭƼ�Ϲ���:
			Print.printMessage(menu);
			
			break;
		default:
			Print.printMessage(menu);
			Print.printMessage("");
			Print.printMessage("");
			Print.printMessage("");
			Print.printMessage("");
			Print.printMessage("");
			break;
		}
	}
}
