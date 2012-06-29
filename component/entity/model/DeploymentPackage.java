package component.entity.model;

public class DeploymentPackage implements IWizardEntity{
	private String fileName;
	private String packageName;
	private String mbo;
	private String project;
	private String jar;
	private String checkJars;
	private VerificationCriteria verifyBannerMessage;
	private VerificationCriteria verifyJars;
	
	public String getCheckJars(){
		return this.checkJars;
	}
	
	public DeploymentPackage checkJars(String str){
		this.checkJars = str;
		return this;
	}
	
	
	public VerificationCriteria verifyJars(){
		return this.verifyJars;
	}
	
	public DeploymentPackage verifyJars(String expected, boolean b){
		this.verifyJars = new VerificationCriteria(expected, b);
		return this;
	}

	public DeploymentPackage jar(String jar){
		this.jar = jar;
		return this;
	}
	
	public String getJar(){
		return this.jar;
	}
	
	public String getFileName() {
		return fileName;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getMbo() {
		return mbo;
	}
	
	public DeploymentPackage fileName(String str){
		this.fileName = str;
		return this;
	}
	
	public DeploymentPackage packageName(String str){
		this.packageName = str;
		return this;
	}
	
	public DeploymentPackage mbo(String str){
		this.mbo = str;
		return this;
	}

	@Override
	public String sp() {
		return this.project;
	}

	@Override
	public DeploymentPackage startParameter(String str) {
		this.project = str;
		return this;
	}

	public DeploymentPackage verifyBannerMessage(String string, boolean b) {
		this.verifyBannerMessage = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyBannerMessage(){
		return this.verifyBannerMessage;
	}

}
