package theater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dao.VIPDAO;

public class VIPSearch {
	String memberID;
	int resNo;
	HashMap<String, Integer> hashmap;
	
	public String getMemberID() {
		return memberID;
	}
	
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getResNo() {
		return resNo;
	}
	public void setResNo(int resNo) {
		this.resNo = resNo;
	}
	
	public VIPSearch() {}
	
	public VIPSearch(int no, String id) {
		this.resNo = no;
		this.memberID = id;
	}
	
	/* 해당 String 문자열 내부에 숫자가 존재하는지 체크 */
	public static boolean isNumber(String string) {
		for(int i = 0 ; i < string.length(); i ++)
	    {    
	        // 48 ~ 57은 아스키 코드로 0~9이다.
	        if(!(48 <= string.charAt(i) && string.charAt(i) <= 57))
	            return false;
	    }
		return true;
	}
	
	/* 날짜 입력 시 6자리 체크 */
	private static boolean isLength(String string) {
		return string.length() == 6;
	}
	
	/* 시작 */
	public void start() {
		Print.printMessage("---------- VIP 고 객 관 리 ----------");
		Print.printMessage("-> VIP 고객 검색을 시작합니다.");
		Print.printMessage("-> 검색하려는 기간의 시작 날짜를 입력하세요.");
		Scanner sc = new Scanner(System.in);
		String startTime = sc.next();

		Print.printMessage("-> 검색하려는 기간의 종료 날짜를 입력하세요.");
		String endTime = sc.next();

		VIPDAO vipdao = new VIPDAO();
		ArrayList<VIPSearch> result = vipdao.getReservationList(startTime, endTime); // 예약 테이블에 저장된 정보 가져오기
		if(result != null) {
			vipResearch(result);
		}
	}
	
	/* VIP 탐색 */
	public void vipResearch(ArrayList<VIPSearch> arrayList) {
		Print.printMessage("-> DB 접속 완료! 결과를 출력합니다.");
		hashmap = vipSortByID(arrayList);
		List<String> keySet = SearchMovie.sortByValue(hashmap);
		
		Scanner sc = new Scanner(System.in);
		int count = 0;
		while(true) {
			Print.printMessage("-> 결과를 몇 등 까지 표시합니까 ?(최대 10등)");
			count = sc.nextInt();
			if(count > 10 ) {
				System.out.println("최대 10등까지 확인 가능합니다. VIP는 예매 횟수 top10 고객을 의미합니다.");
			}else {
				break;
			}
		}
		
		
		// 사용자가 입력한 등수가 총 등수보다 큰 값을 가지는 경우
		if(count == 0) {
			Print.printMessage("아무 정보도 등록되지 않았습니다.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		if(count > hashmap.size()) {
			Print.printMessage("-> 입력하신 등수보다 적은 인원이 VIP로 등록되어 있습니다.");
			Print.printMessage("-> 전체 등수를 출력합니다.");
			count = hashmap.size();
		}

		// 해당 등수까지 출력
		Iterator<String> keys = keySet.iterator(); // 이터레이터 사용
		
		for(int i = 1; i <= count; i++) {
			String key = keys.next();	
			Print.printMessage("등수	아이디	예약횟수");
			Print.printMessage(i+"등	" + key + "	" + hashmap.get(key));
		}
		Print.printMessage("-----------------------------------------------------");
	}
	
	/* 아이디에 따른 값 정렬 */
	public HashMap<String, Integer> vipSortByID(ArrayList<VIPSearch> temp) {
		HashMap<String, Integer> result = new HashMap<>();
		int i = 0;
		// 예약 테이블에 존재하던 모든 예약 정보의 id값을 가져와 HashMap에 담고, 카운트를 셈.
		while(i<temp.size() && temp.get(i)!=null) {
			VIPSearch search = temp.get(i);
			if(result.containsKey(search.getMemberID())) { // 만약 id값이 이미 들어와있는 경우
				int a = result.get(search.getMemberID()) + 1;
				result.replace(search.getMemberID(), a);
			}
			else {
				result.put(search.getMemberID(), 1);
			}
			i++;
		}
		return result;
	}
}
