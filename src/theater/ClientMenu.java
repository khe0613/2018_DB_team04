package theater;

public enum ClientMenu {
	ȸ����������, ��ȭ�����˻�, ��ȭ����, ��ȭ����, ��ȭ����Ʈ,
	��ȭ����������, ��ȭ��������, �󿵿�ȭ��������, �ٽ��Է��ϼ���;
	
	static ClientMenu got(String ch) {
		switch(ch) {
		case "1":		return ȸ����������;
		case "2":		return ��ȭ�����˻�;
		case "3":		return ��ȭ����;
		case "4":		return ��ȭ����;
		case "5":		return ��ȭ����Ʈ;
		case "6":		return ��ȭ����������;
		case "7":		return ��ȭ��������;
		case "8":		return �󿵿�ȭ��������;
		default: 		return �ٽ��Է��ϼ���;
			}
		}
}


