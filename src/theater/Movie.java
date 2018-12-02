package theater;

import java.util.Scanner;

public class Movie {
	static boolean isLogin = false;
	static boolean isAdmin = false;
	private static Scanner sc = new Scanner(System.in);
	static String input_loginmenu;
	
	public enum ClientMenu {
		ȸ����������, ��ȭ�����˻�, ��ȭ����, ��ȭ����, ��ȭ����Ʈ,
		��ȭ����������, �ٽ��Է��ϼ���;
		
		static ClientMenu got(String ch) {
			switch(ch) {
			case "1":		return ȸ����������;
			case "2":		return ��ȭ�����˻�;
			case "3":		return ��ȭ����;
			case "4":		return ��ȭ����;
			case "5":		return ��ȭ����Ʈ;
			case "6":		return ��ȭ����������;
			default: 		return �ٽ��Է��ϼ���;
				}
			}
	}
	
	public enum AdminMenu {
		�󿵿�ȭ��������, VIP������, ��ȭƼ�Ϲ���, �ٽ��Է��ϼ���;
		
		static AdminMenu got(String ch) {
			switch(ch) {
			case "1":		return �󿵿�ȭ��������;
			case "2":		return VIP������;
			case "3":		return ��ȭƼ�Ϲ���;
			default:		return �ٽ��Է��ϼ���;
				}
			}
	}
	
	private static void loginMenuPrint() {
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
		if(!isLogin) {
			loginMenuPrint();	
		}
		//isAdmin = AdminCheck(id); // id �����ؼ� �����ڸ� true, �ƴϸ� false �����ؼ� �ֱ�.
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		if(!isAdmin) {
			Print.printMessage("1: ȸ����������    2: ��ȭ�����˻�    3: ��ȭ����    4: ��ȭ����");
			Print.printMessage("5: ��ȭ����Ʈ    6: ��ȭ����������");
		}
		if(isAdmin) {
			Print.printMessage("1: �󿵿�ȭ��������    2: VIP ������    3: ��ȭƼ�� ����  ");
		}
	}
	
	public static void main(String args[]) {
		
		while(true) {
			
			String input_menu = "";
			mainMenuPrint(); // ���θ޴�
			input_menu = sc.next(); // �Է¹ޱ�
			if(input_menu.equals("quit")) { // ����
				return;
			}
			if(!isAdmin) {
				ClientMenu menu = ClientMenu.got(input_menu); // enum���� ������.
				clientMenu(menu); // switch�� ���� ������ MainMenu�� �� �޴��� �°� �з���.	
			}
			if(isAdmin) {
				AdminMenu menu = AdminMenu.got(input_menu);
				adminMenu(menu);
 			}
		}
	}
	
	public static void loginMenu(String menu) {
		if(menu.equals("1")) {
			Print.printMessage("�α��� ���");
		}
		if(menu.equals("2")) {
			Member member = new Member();
			
			if(!member.register()) {	// ȸ�� ���� ����
				member = null;
			}
			
			//Print.printMessage("ȸ������ ���");
		}
	}
	
	public static void clientMenu(ClientMenu menu) {
		switch(menu) {
		case ȸ����������:
			Print.printMessage(menu);
			break;
		case ��ȭ�����˻�:
			Print.printMessage(menu);
			break;
		case ��ȭ����:
			Print.printMessage(menu);
			break;
		case ��ȭ����:
			Print.printMessage(menu);
			break;
		case ��ȭ����Ʈ:
			Print.printMessage(menu);
			break;
		case ��ȭ����������:
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
	
	public static void adminMenu(AdminMenu menu) {
		switch(menu) {
		case �󿵿�ȭ��������:
			Print.printMessage(menu);
			break;
		case VIP������:
			Print.printMessage(menu);
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


