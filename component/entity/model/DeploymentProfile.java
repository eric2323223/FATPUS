package component.entity.model;

public class DeploymentProfile extends AbstractModel implements IWizardEntity {
	String project;
	String fileName;
	String pkg;
	String target;
	private VerificationCriteria verifyName;

	public String getFileName() {
		return fileName;
	}

	public String getPkg() {
		return pkg;
	}

	public String getTarget() {
		return target;
	}

	public DeploymentProfile fileName(String str){
		this.fileName = str;
		return this;
	}
	
	public DeploymentProfile pkg(String str){
		if(pkg==null){
			pkg = str;
		}else{
			pkg = pkg+":"+str;
		}
		return this;
	}
	
	public DeploymentProfile target(String str){
		if(this.target ==null){
			this.target = str;
		}else{
			this.target = this.target+":"+str;
		}
		return this;
	}
	
	@Override
	public String sp() {
		return project;
	}

	@Override
	public DeploymentProfile startParameter(String str) {
		project = str;
		return this;
	}

	public DeploymentProfile verifyName(String string, boolean b) {
		this.verifyName = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyName(){
		return this.verifyName;
	}
	

}
