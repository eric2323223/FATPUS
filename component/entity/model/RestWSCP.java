package component.entity.model;

public class RestWSCP implements IWizardEntity{

	private String resourceUriTemplate;
	private String resourceBaseUrl;
	private String name;
	private VerificationCriteria verifyName;
	private VerificationCriteria verifyUrl;

	public RestWSCP name(String string) {
		this.name=string;
		return this;
	}

	public RestWSCP resourceBaseUrl(String string) {
		this.resourceBaseUrl=string;
		return this;
	}

	public RestWSCP resourceUriTemplate(String string) {
		if(this.resourceUriTemplate==null){
			this.resourceUriTemplate = string;
		}else{
			this.resourceUriTemplate = this.resourceUriTemplate+":"+string;
		}
		return this;
	}
	
	public String getName(){
		return this.name;
	}

	public String getResourceUriTemplate() {
		return resourceUriTemplate;
	}

	public String getResourceBaseUrl() {
		return resourceBaseUrl;
	}

	@Override
	public String sp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizardEntity startParameter(String str) {
		
		return null;
	}

	public RestWSCP verifyName(String string, boolean b) {
		this.verifyName = new VerificationCriteria(string, b);
		return this;
	}

	public RestWSCP verifyUrl(String string, boolean b) {
		this.verifyUrl = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyName(){
		return this.verifyName;
	}
	
	public VerificationCriteria verifyUrl(){
		return this.verifyUrl;
	}

	 
}
