package component.entity.model;

public class WSMBO implements IWizardEntity{
	private String parameter;
	private String resultChecker;
	private String name;
	private String dataSourceType;
	private String connectionProfile;
	private String project;
	private String existingResultChecker;
	private String newResultChecker;
	private String javaNature;
	private String sourceFolder;
	private String method;
	private String attributeName;
	
	public String getAttributeName(){
		return this.attributeName;
	}
	
	public WSMBO attributeName(String str){
		this.attributeName = str;
		return this;
	}

	private VerificationCriteria verifyJavaNaturePrompt;
	private String xslt;
	private String parameterValue;
	private String password;
	private String userName;
	private String operationParameter;
	
	public String getMethod(){
		return this.method;
	}
	
	public WSMBO dataSourceType(String str){
		this.dataSourceType = str;
		return this;
	}
	
	public String getJavaNature(){
		return this.javaNature;
	}
	
	public WSMBO connectionProfile(String str){
		this.connectionProfile = str;
		return this;
	}
	
	public String getName() {
		return name;
	}

	public String getDataSourceType() {
		return dataSourceType;
	}

	public String getConnectionProfile() {
		return connectionProfile;
	}

	public String getParameter() {
		return parameter;
	}

	public String getResultChecker() {
		return resultChecker;
	}

	public WSMBO startParameter(String string) {
		this.project = string;
		return this;
	}

	public WSMBO resultChecker(String string) {
		this.resultChecker = string;
		return this;
	}

	public WSMBO parameter(String string) {
		if(this.parameter==null){
			this.parameter = string;
		}else{
			this.parameter = this.parameter+":"+string;
		}
		return this;
	}

	@Override
	public String sp() {
		return this.project;
	}

	public WSMBO name(String string) {
		this.name = string;
		return this;
	}

	public WSMBO newResultChecker(String string) {
		this.newResultChecker = string;
		return this;
	}

	public String getExistingResultChecker() {
		return existingResultChecker;
	}

	public String getNewResultChecker() {
		return newResultChecker;
	}

	public WSMBO existingResultChecker(String string) {
		this.existingResultChecker = string;
		return this;
	}

	public WSMBO javaNature(String string) {
		this.javaNature = string;
		return this;
	}

	public WSMBO sourceFolder(String string) {
		this.sourceFolder = string;
		return this;
	}
	
	public String getSourceFolder(){
		return this.sourceFolder;
	}

	public WSMBO verifyJavaNaturePrompt(String string, boolean b) {
		this.verifyJavaNaturePrompt = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyJavaNaturePrompt(){
		return this.verifyJavaNaturePrompt;
	}

	public WSMBO method(String string) {
		this.method = string;
		return this;
	}

	public WSMBO xslt(String string) {
		if(this.xslt==null){
			this.xslt = string;
		}else{
			this.xslt = this.xslt + "|" + string;
		}
		return this;
	}
	
	public String getXslt(){
		return this.xslt;
	}

	public WSMBO parameterValue(String string) {
		if(this.parameterValue==null){
			this.parameterValue=string;
		}else{
			this.parameterValue=this.parameterValue+":"+string;
		}
		return this;
	}
	
	public String getParameterValue(){
		return this.parameterValue;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getPassword(){
		return this.password;
	}
	

	public WSMBO password(String string) {
		this.password= string;
		return this;
	}

	public WSMBO userName(String string) {
		this.userName=string;
		return this;
	}

	public WSMBO operationParameter(String string) {
		if(this.operationParameter == null){
			this.operationParameter=string;
		}else{
			operationParameter = operationParameter + ":"+string;
		}
		return this;
	}
	
	public String operationParameter(){
		return this.operationParameter();
	}
	
	public String getOperationParameter(){
		return operationParameter;
	}

}
