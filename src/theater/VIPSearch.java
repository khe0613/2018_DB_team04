package theater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	
	/* �ش� String ���ڿ� ���ο� ���ڰ� �����ϴ��� üũ */
	public static boolean isNumber(String string) {
		for(int i = 0 ; i < string.length(); i ++)
	    {    
	        // 48 ~ 57�� �ƽ�Ű �ڵ�� 0~9�̴�.
	        if(!(48 <= string.charAt(i) && string.charAt(i) <= 57))
	            return false;
	    }
		return true;
	}
	
	/* ��¥ �Է� �� 6�ڸ� üũ */
	private static boolean isLength(String string) {
		return string.length() == 6;
	}
	
	/* ���� */
	public void start() {
		Print.printMessage("---------- VIP �� �� �� �� ----------");
		Print.printMessage("-> VIP �� �˻��� �����մϴ�.");
		Print.printMessage("-> �˻��Ϸ��� �Ⱓ�� ���� ��¥�� �Է��ϼ���.");
		Scanner sc = new Scanner(System.in);
		String startTime = sc.next();
		// ���ڰ� ���ԵǴ� ����ó��
		if(!(isNumber(startTime)) || !(isLength(startTime))) {
			start();
			return;
		}
		Print.printMessage("-> �˻��Ϸ��� �Ⱓ�� ���� ��¥�� �Է��ϼ���.");
		String endTime = sc.next();
		// ���ڰ� ���ԵǴ� ����ó��
		if(!(isNumber(endTime)) || !(isLength(endTime))) {
			start();
			return;
		}
		
		VIPDAO vipdao = new VIPDAO();
		ArrayList<VIPSearch> result = vipdao.getReservationList(startTime, endTime); // ���� ���̺� ����� ���� ��������
		if(result != null) {
			vipResearch(result);
		}
	}
	
	/* VIP Ž�� */
	public void vipResearch(ArrayList<VIPSearch> arrayList) {
		Print.printMessage("-> DB ���� �Ϸ�! ����� ����մϴ�.");
		hashmap = vipSortByID(arrayList);
		SearchMovie.sortByValue(hashmap);
		Print.printMessage("-> ����� �� �� ���� ǥ���մϱ� ?");
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		// ����ڰ� �Է��� ����� �� ������� ū ���� ������ ���
		if(count == 0) {
			Print.printMessage("�ƹ� ������ ��ϵ��� �ʾҽ��ϴ�.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		if(count > hashmap.size()) {
			Print.printMessage("-> �Է��Ͻ� ������� ���� �ο��� VIP�� ��ϵǾ� �ֽ��ϴ�.");
			Print.printMessage("-> ��ü ����� ����մϴ�.");
			count = hashmap.size();
		}
		
		// �ش� ������� ���
		Iterator<String> keys = hashmap.keySet().iterator(); // ���ͷ����� ���
		
		for(int i = 0; i < count; i++) {
			String key = keys.next();
			Print.printMessage("���	���̵�	����Ƚ��");
			Print.printMessage(i+"��	" + key + "	" + hashmap.get(key));
		}
		Print.printMessage("-----------------------------------------------------");
	}
	
	/* ���̵� ���� �� ���� */
	public HashMap<String, Integer> vipSortByID(ArrayList<VIPSearch> temp) {
		HashMap<String, Integer> result = new HashMap<>();
		int i = 0;
		// ���� ���̺� �����ϴ� ��� ���� ������ id���� ������ HashMap�� ���, ī��Ʈ�� ��.
		while(temp.get(i) != null) {
			VIPSearch search = temp.get(i);
			if(result.containsKey(search.getMemberID())) { // ���� id���� �̹� �����ִ� ���
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
