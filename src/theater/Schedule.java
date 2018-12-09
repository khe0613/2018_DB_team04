package theater;

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
	
	
}
