package component.entity.model;

public class CachePolicy {
	public static String ONDEMAND= "on_demand";
	public static String SCHEDULED="scheduled";
	public static String ONLINE = "online";
	public static String DCN = "DCN";
	
	private String type;
	private Interval interval;
	private boolean partition;
	
	public CachePolicy(String type, Interval interval, boolean partition) {
		this.type = type;
		this.interval = interval;
		this.partition = partition;
	}

	public String getType(){
		return this.type;
	}

	public Interval getInterval() {
		return this.interval;
	}
	
	public CachePolicy type(String str){
		this.type = str;
		return this;
	}
	
	public CachePolicy interval(Interval interval){
		this.interval = interval;
		return this;
	}
	
	public boolean getPartition(){
		return this.partition;
	}

}
