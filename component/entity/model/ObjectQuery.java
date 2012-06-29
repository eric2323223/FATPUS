package component.entity.model;

public class ObjectQuery implements IWizardEntity {
	private String path;
	private String name;
	private String parameter;
	private String returnType;
	private String queryDefinition;
	private String createIndex;
	private String verifyAutoChangeWarning;
	/////////
//	private String generate;
//	public ObjectQuery generate(String str){
//		this.generate = str;
//		return this;
//	}
//	
//	public String getGenerate(){
//		return generate;
//	}
	//////////////
	public String getVerifyAutoChangeWarning(){
		return this.verifyAutoChangeWarning;
	}
	
	public ObjectQuery returnType(String str){
		this.returnType = str;
		return this;
	}
	
	public String getReturnType(){
		return this.returnType;
	}
	
	public String getQueryDefinition(){
		return this.queryDefinition;
	}
	
	public ObjectQuery name(String str){
		this.name = str;
		return this;
	}
	
	public ObjectQuery parameter(String str){
		this.parameter = str;
		return this;
	}

	public String getName() {
		return name;
	}

	public String getParameter() {
		return parameter;
	}
	
	public String getCreateIndex(){
		return this.createIndex;
	}

	@Override
	public String sp() {
		return this.path;
	}

	@Override
	public ObjectQuery startParameter(String str) {
		this.path = str;
		return this;
	}

	public ObjectQuery queryDefinition(String string) {
		this.queryDefinition = string;
		return this;
	}

	public ObjectQuery createIndex(String string) {
		this.createIndex = string;
		return this;
	}

	public ObjectQuery verifyAutoChangeWarning(String string) {
		this.verifyAutoChangeWarning = string;
		return this;
	}

}
