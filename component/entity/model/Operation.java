package component.entity.model;

public class Operation implements IWizardEntity {
	
	public static final String CREATE = "CREATE";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";
	public static final String OTHER = "OTHER";
	private String name;
	private String type;
	private String sqlQuery;
	private String storedProcedure;
	private String path;
	private String parameterDefaultValue;
	private String method;
	private String parameter;
	private String dataSource;
	private String connectionProfile;
	
	
	private VerificationCriteria verifyParameterDefaultValue;
	private String parameterValue;
	private String updateCache;
	private String role;
	private String resultChecker;
	private VerificationCriteria verifyValidationInformation ;
	
	public String getRole(){
		return this.role;
	}

	
	public String getParameter(){
		return this.parameter;
	}

	public String getDataSource(){
		return this.dataSource;
	}
	
	public String getMethod(){
		return this.method;
	}
	
	public String getConnectionProfile(){
		return this.connectionProfile;
	}
	
	public Operation dataSource(String str){
		this.dataSource = str;
		return this;
	}
	
	public Operation conenctionProfile(String str){
		this.connectionProfile = str;
		return this;
	}
	
	public Operation name(String str){
		this.name = str;
		return this;
	}
	
	public Operation parameterDefaultValue(String str){
		this.parameterDefaultValue = str;
		return this;
	}
	
	public Operation type(String str){
		this.type = str;
		return this;
	}
	
	public Operation method(String str){
		this.method = str;
		return this;
	}
	
	public Operation sqlQuery(String str){
		this.sqlQuery = str;
		return this;
	}
	
	public Operation storedProcedure(String str){
		this.storedProcedure = str;
		return this;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public String getStoredProcedure() {
		return storedProcedure;
	}
	
	public String getParameterDefaultValue(){
		return this.parameterDefaultValue;
	}

	@Override
	public String sp() {
		return this.path;
	}

	@Override
	public Operation startParameter(String str) {
		this.path  = str;
		return this;
	}

	public Operation verifyParameterDefaultValue(String expected, boolean canGo) {
		if(this.verifyParameterDefaultValue==null){
			this.verifyParameterDefaultValue = new VerificationCriteria(expected, canGo);
		}else{
			String exp = this.verifyParameterDefaultValue.getExpected()+":"+expected;
			this.verifyParameterDefaultValue = new VerificationCriteria(exp, canGo);
		}
		return this;
	}
	
	public VerificationCriteria verifyParameterDefaultValue(){
		return this.verifyParameterDefaultValue;
	}

	public Operation parameterValue(String string) {
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

	public Operation setResultToCache(String str) {
		this.updateCache = str;
		return this;
	}
	
	public String getUpdateCache() {
		return updateCache;
	}

	public Operation updateCache(String str){
		this.updateCache = str;
		return this;
	}

	public String getResultToCache(){
		return this.updateCache;
	}

	public Operation role(String string) {
		this.role = string;
		return this;
	}

	public Operation connectionProfile(String string) {
		this.connectionProfile = string;
		return this;
	}

	public Operation resultChecker(String string) {
		this.resultChecker = string;
		return this;
	}
	
	public String getResultChecker(){
		return this.resultChecker;
	}

	public Operation parameter(String string) {
		if(this.parameter==null){
			this.parameter = string;
		}else{
			this.parameter = this.parameter+":"+string;
		}
		return this;
	}
	
	public Operation verifyValidationInformation(String str, boolean b){
		this.verifyValidationInformation = new VerificationCriteria(str,b);
		return this;
	}
	
	public VerificationCriteria verifyValidationInformation(){
		return this.verifyValidationInformation;
	}

}
