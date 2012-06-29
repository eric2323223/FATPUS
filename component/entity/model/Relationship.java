package component.entity.model;

public class Relationship implements IWizardEntity{
	private String type;
//	private String source;
	private String target;
	private String mapping;
	private String parameter;
	private String name;
	private String composite;
	private String bidirectional;
	
	private VerificationCriteria verifyMapping;
	
	public static final String TYPE_OTO = "oto";
	public static final String TYPE_OTM = "otm";
	public static final String TYPE_MTO = "mto";
	
	public Relationship name(String str){
		this.name = str;
		return this;
	}
	
	public Relationship composite(String str){
		this.composite = str;
		return this;
	}
	
	public Relationship verifyMapping(String expected, boolean canGoIfMatch){
		this.verifyMapping = new VerificationCriteria(expected, canGoIfMatch);
		return this;
	}
	
	public VerificationCriteria verifyMapping(){
		return this.verifyMapping;
	}
	
	public String getType() {
		return this.type;
	}

	public String getTarget() {
		return this.target;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Relationship type(String str){
		this.type = str;
		return this;
	}

	
	public Relationship target(String s){
		this.target = s;
		return this;
	}

	@Override
	public String sp() {
		return this.parameter;
	}

	@Override
	public Relationship startParameter(String str) {
		this.parameter = str;
		return this;
	}

	public String getMapping(){
		return this.mapping;
	}
	
	public Relationship mapping(String str){
		this.mapping = str;
		return this;
	}
	
	public String getComposite(){
		return this.composite;
	}

	public Relationship bidirectional(String string) {
		this.bidirectional = string;
		return this;
	}
	
	public String getBidirectional(){
		return this.bidirectional;
	}
}
