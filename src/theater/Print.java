package theater;

import theater.MainClass.AdminMenu;
import theater.MainClass.ClientMenu;

public class Print {
	public static void printMessage(String str) {
		System.out.println(str);
		
	}
	public static void printMessage(ClientMenu str) {
		System.out.println(str);
	}
	public static void printMessage(AdminMenu str) {
		System.out.println(str);
	}
}
