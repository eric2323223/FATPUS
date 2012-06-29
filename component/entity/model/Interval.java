package component.entity.model;

public class Interval {
	private String hour;
	private String minute;
	private String second;
	public String getHour() {
		return hour;
	}
	public String getMinute() {
		return minute;
	}
	public String getSecond() {
		return second;
	}
	
	public Interval(int hour, int minute, int second){
		this.hour = new Integer(hour).toString();
		this.minute = new Integer(minute).toString();
		this.second = new Integer(second).toString();
	}

}
