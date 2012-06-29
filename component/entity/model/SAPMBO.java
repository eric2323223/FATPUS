package component.entity.model;

import component.entity.resource.IResource;

public class SAPMBO implements IWizardEntity {
	private DbMbo mbo = new DbMbo();
	
	private String operation;
	private String parameter;
	private String parameterValue;
	private String project;
	private String bapiOperation;
	private String attribute;

	private String outputTable;

	private VerificationCriteria verifyParameterDefaultValue;

	private String activateVerify;
	
	public String getAttribute(){
		return this.attribute;
	}
	
	public SAPMBO attribute(String str){
		if(this.attribute==null){
			this.attribute = str;
		}else{
			this.attribute = this.attribute+":"+str;
		}
		return this;
	}

	public String getOperation(){
		return this.operation;
	}
	
	public String getBapiOperation(){
		return this.bapiOperation;
	}
	
	public SAPMBO bapiOperation(String str){
		this.bapiOperation = str;
		return this;
	}
	
	public String getParameter(){
		return this.parameter;
	}
	
	public SAPMBO operation(String str){
		this.operation = str;
		return this;
	}
	
	public SAPMBO parameter(String str){
		if(this.parameter==null){
			this.parameter = str;
		}else{
			this.parameter = this.parameter +":"+str;
		}
		return this;
	}

	@Override
	public String sp() {
		return this.project;
	}

	@Override
	public SAPMBO startParameter(String str) {
		this.project = str;
		return this;
	}

	public SAPMBO connectionProfile(String string) {
		mbo.connectionProfile(string);
		return this;
	}

	public SAPMBO dataSourceType(String string) {
		mbo.dataSourceType(string);
		return this;
	}

	public SAPMBO name(String string) {
		mbo.name(string);
		return this;
	}
	
	public String getName(){
		return mbo.getName();
	}
	
	public String getDataSourceType(){
		return mbo.getDataSourceType();
	}
	
	public String getConnectionProfile(){
		return mbo.getConnectionProfile();
	}

	public SAPMBO parameterValue(String string) {
		if(this.parameterValue==null){
			this.parameterValue = string;
		}else{
			this.parameterValue = this.parameterValue+":"+string;
		}
		return this;
	}
	
	public String getParameterValue(){
		return this.parameterValue;
	}

	public SAPMBO outputTable(String string) {
		this.outputTable= string;
		return this;
	}
	
	public String getOutputTable(){
		return this.outputTable;
	}

	public SAPMBO verifyParameterDefaultValue(String string, boolean b) {
		this.verifyParameterDefaultValue = new VerificationCriteria(string,b);
		return this;
	}
	
	public VerificationCriteria verifyParameterDefaultValue(){
		return this.verifyParameterDefaultValue;
	}

	public SAPMBO activateVerify(String string) {
		this.activateVerify = string;
		return this;
	}
	
	public String GetActivateVerify(){
		return this.activateVerify;
	}
}
