package theater;

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


