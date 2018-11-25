package theater;

import java.util.Scanner;

public class Movie {
	static boolean isLogin = false;
	static boolean isAdmin = false;
	private static Scanner sc = new Scanner(System.in);
	static String input_loginmenu;
	public static void printMessage(String str) {
		System.out.println(str);
		
	}
	public static void printMessage(ClientMenu str) {
		System.out.println(str);
	}
	public static void printMessage(AdminMenu str) {
		System.out.println(str);
	}
	
	public enum ClientMenu {
		회원정보관리, 영화정보검색, 영화예약, 영화결제, 영화포인트,
		영화관정보관리, 다시입력하세요;
		
		static ClientMenu got(String ch) {
			switch(ch) {
			case "1":		return 회원정보관리;
			case "2":		return 영화정보검색;
			case "3":		return 영화예약;
			case "4":		return 영화결제;
			case "5":		return 영화포인트;
			case "6":		return 영화관정보관리;
			default: 		return 다시입력하세요;
				}
			}
	}
	
	public enum AdminMenu {
		상영영화정보관리, VIP고객관리, 영화티켓발행, 다시입력하세요;
		
		static AdminMenu got(String ch) {
			switch(ch) {
			case "1":		return 상영영화정보관리;
			case "2":		return VIP고객관리;
			case "3":		return 영화티켓발행;
			default:		return 다시입력하세요;
				}
			}
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
		if(!isLogin) {
			loginMenuPrint();	
		}
		//isAdmin = AdminCheck(id); // id 구별해서 관리자면 true, 아니면 false 리턴해서 넣기.
		printMessage("-> 원하시는 메뉴를 선택하세요.");
		if(!isAdmin) {
			printMessage("1: 회원정보관리    2: 영화정보검색    3: 영화예약    4: 영화결제");
			printMessage("5: 영화포인트    6: 영화관정보관리");
		}
		if(isAdmin) {
			printMessage("1: 상영영화정보관리    2: VIP 고객관리    3: 영화티켓 발행  ");
		}
	}
	
	public static void main(String args[]) {
		
		while(true) {
			
			String input_menu = "";
			mainMenuPrint(); // 메인메뉴
			input_menu = sc.next(); // 입력받기
			if(input_menu.equals("quit")) { // 종료
				return;
			}
			if(!isAdmin) {
				ClientMenu menu = ClientMenu.got(input_menu); // enum에서 가져옴.
				clientMenu(menu); // switch를 통해 가져온 MainMenu를 각 메뉴에 맞게 분류함.	
			}
			if(isAdmin) {
				AdminMenu menu = AdminMenu.got(input_menu);
				adminMenu(menu);
 			}
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
	
	public static void clientMenu(ClientMenu menu) {
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
		default:
			printMessage(menu);
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			break;
		}
	}
	
	public static void adminMenu(AdminMenu menu) {
		switch(menu) {
		case 상영영화정보관리:
			printMessage(menu);
			break;
		case VIP고객관리:
			printMessage(menu);
			break;
		case 영화티켓발행:
			printMessage(menu);
			break;
		default:
			printMessage(menu);
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			printMessage("");
			break; 
		}
	}
}


