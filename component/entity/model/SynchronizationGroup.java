package component.entity.model;

public class SynchronizationGroup implements IWizardEntity {
	private String project;
	private String name;
	private String hour;
	private String minute;
	private String second;

	@Override
	public String sp() {
		return this.project;
	}

	@Override
	public SynchronizationGroup startParameter(String str) {
		this.project = str;
		return this;
	}
	
	public SynchronizationGroup name(String str){
		this.name = str;
		return this;
	}
	
	public SynchronizationGroup hour(String str){
		this.hour = str;
		return this;
	}
	
	public SynchronizationGroup minute(String str){
		this.minute = str;
		return this;
	}
	
	public SynchronizationGroup second(String str){
		this.second = str;
		return this;
	}
	
	public String getName(){
		return this.name;
		
	}

	public String getHour() {
		return hour;
	}

	public String getMinute() {
		return minute;
	}

	public String getSecond() {
		return second;
	}

}
