package Therter;

import java.util.Scanner;

public class Movie {
	
	public static void printMessage(String str) {
		System.out.println(str);
	}
	
	private static void mainMenuPrint() {
		printMessage("------------- Database TermProject 04조 -------------");
		printMessage("---------------- 영  화  관  리  시  스  템  -------------------");
		printMessage("-> 원하시는 메뉴를 선택하세요.");
		printMessage("1: 회원정보관리    2: 영화정보검색    3: 영화예약    4: 영화결제");
		printMessage("5: 영화포인트    6: 영화관정보관리    7: 영화정보관리    8: 상영영화정보관리");
		
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String menu;
		mainMenuPrint(); // 메인메뉴
		menu = sc.next(); // 입력받기
		
		MainMenu a = MainMenu.got(menu);
		
	}
}


