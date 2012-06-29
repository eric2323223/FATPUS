package component.entity.model;

public class CacheGroup implements IWizardEntity {
	private String startParameter;
	
	public CacheGroup startParameter(String string){
		this.startParameter = string;
		return this;
	}
	
	@Override
	public String sp() {
		return this.startParameter;
	}
	
	private String name;
	private String type;
	private String hour;
	private String minute;
	private String second;
	private String partition;
	private String defaultCacheGroup;
	private VerificationCriteria verifyBannerMessage;
	private VerificationCriteria verifyPartition;
	private VerificationCriteria verifyIntervalStatus;
	private VerificationCriteria verifyPartitionStatus;
	
	public VerificationCriteria verifyIntervalStatus(){
		return this.verifyIntervalStatus;
	}
	
	public VerificationCriteria verifyPartitionStatus(){
		return this.verifyPartitionStatus;
	}
	
	public CacheGroup verifyPartitionStatus(String expected, boolean canGo){
		this.verifyPartitionStatus = new VerificationCriteria(expected, canGo);
		return this;
	}
	
	public CacheGroup verifyIntervalStatus(String expected, boolean canGo){
		this.verifyIntervalStatus = new VerificationCriteria(expected, canGo);
		return this;
	}

	public String getName(){
		return this.name;
	}
	
	public CacheGroup name(String string){
		this.name=string;
		return this;
	}
	
	public String getType(){
		return this.type;
	}
	
	public CacheGroup type(String string){
		this.type=string;
		return this;
	}

	public String getHour() {
		return this.hour;
	}
	
	public CacheGroup hour(String hour){
		this.hour=hour;
		return this;
	}
	
	public String getMinute() {
		return this.minute;
	}
	
	public CacheGroup minute(String minute){
		this.minute=minute;
		return this;
	}
	
	public String getSecond() {
		return this.second;
	}

	public CacheGroup second(String second){
		this.second=second;
		return this;
	}

	public String getPartition(){
		return this.partition;
	}
	
	public CacheGroup partition(String partition){
		this.partition=partition;
		return this;
	}

	public CacheGroup verifyBannerMessage(String string, boolean canContinue) {
		this.verifyBannerMessage = new VerificationCriteria(string, canContinue);
		return this;
	}
	
	public VerificationCriteria verifyBannerMessage(){
		return this.verifyBannerMessage;
	}

	public CacheGroup defaultCacheGroup(String string) {
		this.defaultCacheGroup = string;
		return this;
	}
	
	public String getDefaultCacheGroup(){
		return this.defaultCacheGroup;
	}

	public CacheGroup verifyPartition(String string, boolean b) {
		this.verifyPartition = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyPartition(){
		return this.verifyPartition;
	}
}
