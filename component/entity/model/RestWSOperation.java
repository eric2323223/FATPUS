package component.entity.model;

public class RestWSOperation extends AbstractModel implements IWizardEntity{
	private String name;
	private String type;
	private String dataSourceType;
	private String connectionProfile;
	private String resourceBaseUrl;
	private String resourceUriTemplate;
	private String httpMethod;
	private String expectedStatusCode;
	private String request;
	private String response;
	private String resultChecker;
	
	private String mbo;
	private VerificationCriteria verifyRequest;

	public RestWSOperation resultChecker(String str){
		this.resultChecker = str;
		return this;
	}
	
	public String getResultChecker(){
		return this.resultChecker;
	}
	
	public RestWSOperation name(String string) {
		this.name = string;
		return this;
	}

	public RestWSOperation type(String string) {
		this.type = string;
		return this;
	}

	public RestWSOperation datatSourceType(String string) {
		this.dataSourceType = string;
		return this;
	}

	public RestWSOperation connectionProfile(String string) {
		this.connectionProfile = string;
		return this;
	}

	public RestWSOperation resourceBaseUrl(String string) {
		this.resourceBaseUrl = string;
		return this;
	}

	public RestWSOperation resourceUriTemplate(String string) {
		this.resourceUriTemplate = string;
		return this;
	}

	public RestWSOperation httpMethod(String string) {
		this.httpMethod = string;
		return this;
	}

	public RestWSOperation expectedStatusCode(String string) {
		this.expectedStatusCode = string;
		return this;
	}

	public RestWSOperation request(String string) {
		this.request = string;
		return this;
	}
	
	public RestWSOperation response(String str){
		this.response = str;
		return this;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDataSourceType() {
		return dataSourceType;
	}

	public String getConnectionProfile() {
		return connectionProfile;
	}

	public String getResourceBaseUrl() {
		return resourceBaseUrl;
	}

	public String getResourceUriTemplate() {
		return resourceUriTemplate;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getExpectedStatusCode() {
		return expectedStatusCode;
	}

	public String getRequest() {
		return request;
	}
	
	public String getResponse(){
		return response;
	}

	@Override
	public String sp() {
		return this.mbo;
	}

	@Override
	public RestWSOperation startParameter(String str) {
		this.mbo = str;
		return this;
	}

	public RestWSOperation verifyRequest(String str,boolean b) {
		this.verifyRequest=new VerificationCriteria(str,b);
		return this;
	}
	
	public VerificationCriteria verifyRequest(){
		return this.verifyRequest;
	}

	
}
