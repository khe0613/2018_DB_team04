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
		String menu;
		mainMenuPrint(); // ���θ޴�
		menu = sc.next(); // �Է¹ޱ�
		
		MainMenu a = MainMenu.got(menu);
		
	}
}


