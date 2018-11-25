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
		printMessage("!! 로그인이 되어있지 않습니다.");
		printMessage("-> 원하시는 메뉴를 선택하세요.");
		printMessage("1: 로그인    2: 회원가입 ");
		input_loginmenu = sc.next();
		loginMenu(input_loginmenu);
	}
	private static void mainMenuPrint() {
		printMessage("------------- Database TermProject 04조 -------------");
		printMessage("---------------- 영  화  관  리  시  스  템  -------------------");
		if(!login) {
			loginMenuPrint();	
		}
		printMessage("-> 원하시는 메뉴를 선택하세요.");
		printMessage("1: 회원정보관리    2: 영화정보검색    3: 영화예약    4: 영화결제");
		printMessage("5: 영화포인트    6: 영화관정보관리    7: 영화정보관리    8: 상영영화정보관리");
		
	}
	
	public static void main(String args[]) {
		
		while(true) {
			
			String input_menu = "";
			mainMenuPrint(); // 메인메뉴
			input_menu = sc.next(); // 입력받기
			if(input_menu.equals("quit")) { // 종료
				return;
			}
			
			MainMenu menu = MainMenu.got(input_menu); // enum에서 가져옴.
			mainMenu(menu); // switch를 통해 가져온 MainMenu를 각 메뉴에 맞게 분류함.
		}
	}
	
	public static void loginMenu(String menu) {
		if(menu.equals("1")) {
			printMessage("로그인 기능");
		}
		if(menu.equals("2")) {
			printMessage("회원가입 기능");
		}
	}
	public static void mainMenu(MainMenu menu) {
		switch(menu) {
		case 회원정보관리:
			printMessage(menu);
			break;
		case 영화정보검색:
			printMessage(menu);
			break;
		case 영화예약:
			printMessage(menu);
			break;
		case 영화결제:
			printMessage(menu);
			break;
		case 영화포인트:
			printMessage(menu);
			break;
		case 영화관정보관리:
			printMessage(menu);
			break;
		case 영화정보관리:
			printMessage(menu);
			break;
		case 상영영화정보관리:
			printMessage(menu);
			break;
		default:
			printMessage("!! 해당하는 메뉴가 없습니다. 다시 입력해주세요.");
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			break;
		}
	}
}


