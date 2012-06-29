package component.entity.model;

public class LocalMbo implements IWizardEntity {
	private String name;
	private String project;
	private String attributes;
	
	private VerificationCriteria verifyBannerMessage;
	
	public VerificationCriteria verifyBannerMessage(){
		return this.verifyBannerMessage;
	}
	
	public LocalMbo verifyBannerMessage(String expected, boolean canContinue){
		this.verifyBannerMessage = new VerificationCriteria(expected, canContinue);
		return this;
	}

	@Override
	public String sp() {
		return this.project;
	}

	@Override
	public LocalMbo startParameter(String str) {
		this.project = str;
		return this;
	}
	
	public String getName(){
		return this.name;
	}
	
	public LocalMbo name(String str){
		this.name = str;
		return this;
	}
	
	public LocalMbo attributes(LocalMboAttribute[] str){
		this.attributes = "";
		for(LocalMboAttribute att:str){
			this.attributes = this.attributes + att.toString() +"|";
		}
		this.attributes = this.attributes.substring(0, this.attributes.length()-1);
		return this;
	}
	
	public String getAttributes(){
		return this.attributes;
	}

}
