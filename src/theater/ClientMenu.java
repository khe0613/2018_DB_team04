package theater;

public enum ClientMenu {
	회원정보관리, 영화정보검색, 영화예약, 영화결제, 영화포인트,
	영화관정보관리, 영화정보관리, 상영영화정보관리, 다시입력하세요;
	
	static ClientMenu got(String ch) {
		switch(ch) {
		case "1":		return 회원정보관리;
		case "2":		return 영화정보검색;
		case "3":		return 영화예약;
		case "4":		return 영화결제;
		case "5":		return 영화포인트;
		case "6":		return 영화관정보관리;
		case "7":		return 영화정보관리;
		case "8":		return 상영영화정보관리;
		default: 		return 다시입력하세요;
			}
		}
}


