package theater;

import java.util.Scanner;

public class Movie {
	static boolean login = false;
	private static Scanner sc = new Scanner(System.in);
	static String input_loginmenu;
	public static void printMessage(String str) {
		System.out.println(str);
		
	}
	public static void printMessage(MainMenu str) {
		System.out.println(str);
	}
	
	private static void loginMenuPrint() {
		input_loginmenu = "";
		printMessage("!! �α����� �Ǿ����� �ʽ��ϴ�.");
		printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		printMessage("1: �α���    2: ȸ������ ");
		input_loginmenu = sc.next();
		loginMenu(input_loginmenu);
	}
	private static void mainMenuPrint() {
		printMessage("------------- Database TermProject 04�� -------------");
		printMessage("---------------- ��  ȭ  ��  ��  ��  ��  ��  -------------------");
		if(!login) {
			loginMenuPrint();	
		}
		printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		printMessage("1: ȸ����������    2: ��ȭ�����˻�    3: ��ȭ����    4: ��ȭ����");
		printMessage("5: ��ȭ����Ʈ    6: ��ȭ����������    7: ��ȭ��������    8: �󿵿�ȭ��������");
		
	}
	
	public static void main(String args[]) {
		
		while(true) {
			
			String input_menu = "";
			mainMenuPrint(); // ���θ޴�
			input_menu = sc.next(); // �Է¹ޱ�
			if(input_menu.equals("quit")) { // ����
				return;
			}
			
			MainMenu menu = MainMenu.got(input_menu); // enum���� ������.
			mainMenu(menu); // switch�� ���� ������ MainMenu�� �� �޴��� �°� �з���.
		}
	}
	
	public static void loginMenu(String menu) {
		if(menu.equals("1")) {
			printMessage("�α��� ���");
		}
		if(menu.equals("2")) {
			printMessage("ȸ������ ���");
		}
	}
	public static void mainMenu(MainMenu menu) {
		switch(menu) {
		case ȸ����������:
			printMessage(menu);
			break;
		case ��ȭ�����˻�:
			printMessage(menu);
			break;
		case ��ȭ����:
			printMessage(menu);
			break;
		case ��ȭ����:
			printMessage(menu);
			break;
		case ��ȭ����Ʈ:
			printMessage(menu);
			break;
		case ��ȭ����������:
			printMessage(menu);
			break;
		case ��ȭ��������:
			printMessage(menu);
			break;
		case �󿵿�ȭ��������:
			printMessage(menu);
			break;
		default:
			printMessage("!! �ش��ϴ� �޴��� �����ϴ�. �ٽ� �Է����ּ���.");
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			break;
		}
	}
}


