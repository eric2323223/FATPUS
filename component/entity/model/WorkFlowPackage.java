package component.entity.model;

public class WorkFlowPackage implements IWizardEntity {
	String startParameter;
	private String unwiredServer;
	private String assignToUser;
	private VerificationCriteria verifyResult;
	private String deployToServer;
	private String externalFolder;
	private String validateControls;
	private String saveConfiguration;
	private String selectConfiguration;
	private String assignToSelectedUser;
	private String continueWithError;
	private VerificationCriteria verifyMultiUserMessage;
	

	public String getContinueWithError(){
		return this.continueWithError;
	}
	
	public WorkFlowPackage continueWithError(String str){
		this.continueWithError = str;
		return this;
	}
	
	public String getUnwiredServer() {
		return unwiredServer;
	}
	
	public String getExternalFolder(){
		return this.externalFolder;
	}
	
	public WorkFlowPackage externalFolder(String str){
		this.externalFolder = str;
		return this;
	}
	
	
	public String getDeployToServer(){
		return this.deployToServer;
	}
	
	public WorkFlowPackage deployToServer(String str){
		this.deployToServer = str;
		return this;
	}

	public String getAssignToUser() {
		return assignToUser;
	}

	@Override
	public String sp() {
		return startParameter;
	}

	@Override
	public WorkFlowPackage startParameter(String str) {
		startParameter = str;
		return this;
	}

	public WorkFlowPackage unwiredServer(String string) {
		unwiredServer = string;
		return this;
	}

	public WorkFlowPackage assignToUser(String string) {
		assignToUser = string;
		return this;
	}

	public WorkFlowPackage verifyResult(String string, boolean b) {
		this.verifyResult = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyResult(){
		return this.verifyResult;
	}
	
	public WorkFlowPackage clone(){
		WorkFlowPackage pkg =new WorkFlowPackage();
		pkg.assignToUser = this.assignToUser;
		pkg.deployToServer = this.deployToServer;
		pkg.unwiredServer = this.unwiredServer;
		pkg.startParameter = this.startParameter;
		pkg.verifyResult = this.verifyResult;
		pkg.continueWithError = this.continueWithError;
		return pkg;
	}

	public WorkFlowPackage validateControls(String string) {
		this.validateControls = string;
		return this;
	}

	public String getValidateControls(){
		return this.validateControls;
	}

	public WorkFlowPackage saveConfiguration(String string) {
		this.saveConfiguration = string;
		return this;
	}

	public WorkFlowPackage selectConfiguration(String string) {
		this.selectConfiguration = string;
		return this;
	}
	
	public String getSelectConfiguration(){
		return this.selectConfiguration;
	}

	public WorkFlowPackage assignToSelectedUser(String string) {
		this.assignToSelectedUser = string;
		return this;
	}
	
	public String getAssignToSelectedUser(){
		return this.assignToSelectedUser;
	}

	public WorkFlowPackage verifyMultiUserMessage(String string, boolean b) {
		this.verifyMultiUserMessage = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyMultiUserMessage(){
		return this.verifyMultiUserMessage;
	}

	public String getSaveConfiguration(){
		return this.saveConfiguration;
	}
}
