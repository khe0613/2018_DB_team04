package Therter;

import java.util.Scanner;

public class Movie {
	
	public static void printMessage(String str) {
		System.out.println(str);
		
	}
	
	private static void mainMenuPrint() {
		printMessage("------------- Database TermProject 04�� -------------");
		printMessage("---------------- ��  ȭ  ��  ��  ��  ��  ��  -------------------");
		printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		printMessage("1: ȸ����������    2: ��ȭ�����˻�    3: ��ȭ����    4: ��ȭ����");
		printMessage("5: ��ȭ����Ʈ    6: ��ȭ����������    7: ��ȭ��������    8: �󿵿�ȭ��������");
		
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
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
	
	public static void mainMenu(MainMenu menu) {
		switch(menu) {
		case ȸ����������:
			System.out.println(menu);
			break;
		case ��ȭ�����˻�:
			System.out.println(menu);
			break;
		case ��ȭ����:
			System.out.println(menu);
			break;
		case ��ȭ����:
			System.out.println(menu);
			break;
		case ��ȭ����Ʈ:
			System.out.println(menu);
			break;
		case ��ȭ����������:
			System.out.println(menu);
			break;
		case ��ȭ��������:
			System.out.println(menu);
			break;
		case �󿵿�ȭ��������:
			System.out.println(menu);
			break;
		default:
			printMessage("�ش��ϴ� �޴��� �����ϴ�. �ٽ� �Է����ּ���.");
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			break;
		}
	}
}


