package component.entity.model;

public class RestWSMbo extends AbstractModel implements IWizardEntity{
	private String name;
	private String dataSourceType;
	private String connectionProfile;
	private String resourceBaseUrl;
	private String resourceUriTemplate;
	private String httpMethod;
	private String expectedStatusCode;
	private String response;
	private String project;
	private String userName;
	private String password;
	private String header;
	private String resultChecker;
	private String parameterValue;
	private String existingResultChecker;
	private String newResultChecker;
	private String javaNature;
	
	private String resultFilter;
	private VerificationCriteria verifyNewResultChecker;
	private VerificationCriteria verifyJavaNaturePrompt;
	private VerificationCriteria verifyResponse;
	
	public RestWSMbo newResultChecker(String str){
		this.newResultChecker = str;
		return this;
	}
	
	public RestWSMbo existingResultChecker(String str){
		this.existingResultChecker = str;
		return this;
	}
	
	public String getJavaNature(){
		return this.javaNature;
	}

	public String getExistingResultChecker() {
		return existingResultChecker;
	}
	
	public String getHttpMethod() {
		return httpMethod;
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

	public String getResourceBaseUrl() {
		return resourceBaseUrl;
	}

	public String getResourceUriTemplate() {
		return resourceUriTemplate;
	}


	public String getExpectedStatusCode() {
		return expectedStatusCode;
	}

	public String getResponse() {
		return response;
	}
	
	public String getNewResultChecker() {
		return newResultChecker;
	}

	@Override
	public String sp() {
		return this.project;
	}

	@Override
	public RestWSMbo startParameter(String str) {
		this.project = str;
		return this;
	}

	public RestWSMbo name(String string) {
		this.name = string;
		return this;
	}

	public RestWSMbo dataSourceType(String string) {
		this.dataSourceType = string;
		return this;
	}
	
	public String getResultChecker(){
		return this.resultChecker;
	}

	public RestWSMbo connectionProfile(String string) {
		this.connectionProfile = string;
		return this;
	}

	public RestWSMbo resourceBaseUrl(String string) {
		this.resourceBaseUrl = string;
		return this;
	}

	public RestWSMbo resourceUriTemplate(String string) {
		this.resourceUriTemplate = string;
		return this;
	}

	public RestWSMbo httpMethod(String string) {
		this.httpMethod = string;
		return this;
	}

	public RestWSMbo expectedStatusCode(String string) {
		this.expectedStatusCode = string;
		return this;
	}

	public RestWSMbo response(String string) {
		this.response = string;
		return this;
	}

	public RestWSMbo userName(String string) {
		this.userName=string;
		return this;
	}
	
	public RestWSMbo password(String str){
		this.password = str;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public RestWSMbo header(String string) {
		if(this.header==null){
			this.header=string;
		}else{
			this.header=this.header+":"+string;
		}
		return this;
	}
	
	public String getHeader(){
		return this.header;
	}

	public RestWSMbo resultChecker(String string) {
		this.resultChecker=string;
		return this;
	}
	
	public String getParameterValue(){
		return this.parameterValue;
	}

	public RestWSMbo parameterValue(String string) {
		if(this.parameterValue==null){
			this.parameterValue = string;
		}else{
			this.parameterValue = this.parameterValue + ":"+string;
		}
		return this;
	}

	public RestWSMbo javaNature(String string) {
		this.javaNature = string;
		return this;
	}

	public RestWSMbo verifyNewResultChecker(String string, boolean b) {
		this.verifyNewResultChecker = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyNewResultChecker(){
		return this.verifyNewResultChecker;
	}
	
	public RestWSMbo verifyJavaNaturePrompt(String str, boolean b){
		this.verifyJavaNaturePrompt = new VerificationCriteria(str, b);
		return this;
	}
	
	public VerificationCriteria verifyJavaNaturePrompt(){
		return this.verifyJavaNaturePrompt;
	}

	public RestWSMbo resultFilter(String string) {
		this.resultFilter = string;
		return this;
	}
	
	public String getResultFilter(){
		return this.resultFilter;
	}

	public RestWSMbo verifyResponse(String string, boolean b) {
		this.verifyResponse = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyResponse(){
		return this.verifyResponse;
	}

}
