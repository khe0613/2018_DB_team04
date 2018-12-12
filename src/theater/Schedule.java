package theater;

import java.util.ArrayList;

import dao.ScheduleDAO;

public class Schedule {

	private int schNo;
	private String startTime;
	private String endTime;
	private String screeningDate;
	
	public Schedule() {
		
	}
	
	public int getSchNo() {
		return schNo;
	}
	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getScreeningDate() {
		return screeningDate;
	}
	public void setScreeningDate(String screeningDate) {
		this.screeningDate = screeningDate;
	}
	
	/* ����� ���� ���� ��� ��� */
	public void getScheduleInfoList() {
		ArrayList<Schedule> arrayList = new ArrayList<Schedule>();
		arrayList = new ScheduleDAO().getScheduleInfoListSQL();
		if(arrayList == null) {
			Print.printMessage("�ƹ� ������ ��ϵ��� �ʾҽ��ϴ�.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		Print.printMessage("�����ڵ�\t���۽ð�\t����ð�\t�� ��¥");
		int i = 0;
		while(i < arrayList.size()) {
			Schedule temp = arrayList.get(i);
			Print.printMessage(temp.getSchNo() + "\t" + temp.getStartTime() + "\t" + temp.getEndTime()+ "\t" +temp.getScreeningDate());
			
			i++;
		}
		Print.printMessage("�� " + (i) + "���� ���� ������ ����Ǿ��ֽ��ϴ�.");
		Print.printMessage("-----------------------------------------------------");
	}
}
