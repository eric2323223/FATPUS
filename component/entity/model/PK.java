package component.entity.model;

public class PK implements IWizardEntity {
	
	private String name;
	private String type;
	private String nullable;
	private String protect;
	private String defaultValue;
	private String projectName;
	private String storage;
	private VerificationCriteria verifyName;
	private String description;
	private VerificationCriteria verifyDefaultValue;

	public PK startParameter(String proj){
		this.projectName = proj;
		return this;
	}
	
	public PK storage(String sto){
		this.storage = sto;
		return this;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getNullable() {
		return nullable;
	}
	public String getProtect() {
		return protect;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public String getStorage() {
		return this.storage;
	}
	
	public PK name(String name){
		this.name = name;
		return this;
	}
	public PK type(String type){
		this.type = type;
		return this;
	}
	public PK nullable(String nullable){
		this.nullable = nullable;
		return this;
	}
	
	public PK protect(String protect){
		this.protect = protect;
		return this;
	}
	
	public PK defaultValue(String defaultValue){
		this.defaultValue = defaultValue;
		return this;
	}

	@Override
	public String sp() {
		return this.projectName;
	}

	public PK verifyName(String string, boolean b) {
		this.verifyName=new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyName(){
		return this.verifyName;
	}

	public PK description(String string) {
		this.description = string;
		return this;
	}

	public String getDescription(){
		return this.description;
	}

	public PK verifyDefaultValue(String string, boolean b) {
		this.verifyDefaultValue = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyDefaultValue(){
		return this.verifyDefaultValue;
	}

}
