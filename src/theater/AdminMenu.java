package theater;

public enum AdminMenu {
	�󿵿�ȭ��������, VIP������, ��ȭƼ�Ϲ���, �ٽ��Է��ϼ���;
	
	static AdminMenu got(String ch) {
		switch(ch) {
		case "1":		return �󿵿�ȭ��������;
		case "2":		return VIP������;
		case "3":		return ��ȭƼ�Ϲ���;
		default:		return �ٽ��Է��ϼ���;
			}
		}
}


