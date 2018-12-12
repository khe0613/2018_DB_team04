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
	
	/* 저장된 일정 정보 목록 출력 */
	public void getScheduleInfoList() {
		ArrayList<Schedule> arrayList = new ArrayList<Schedule>();
		arrayList = new ScheduleDAO().getScheduleInfoListSQL();
		if(arrayList == null) {
			Print.printMessage("아무 정보도 등록되지 않았습니다.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		Print.printMessage("일정코드\t시작시간\t종료시간\t상영 날짜");
		int i = 0;
		while(i < arrayList.size()) {
			Schedule temp = arrayList.get(i);
			Print.printMessage(temp.getSchNo() + "\t" + temp.getStartTime() + "\t" + temp.getEndTime()+ "\t" +temp.getScreeningDate());
			
			i++;
		}
		Print.printMessage("총 " + (i) + "개의 일정 정보가 저장되어있습니다.");
		Print.printMessage("-----------------------------------------------------");
	}
}
