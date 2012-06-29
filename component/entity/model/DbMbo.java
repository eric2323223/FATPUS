package component.entity.model;

public class DbMbo implements IWizardEntity{
	private String dataSourceType;
	private String connectionProfile;
	private String sqlQuery;
	private String projectName;
	private String name;
	private String startParameter;
	private String newFilter;
	private String existingFilter;
	private String storedProcedure;
	private String attributeMapping;
	private String parameter;
	private String resultSet;
	private String role;
	
	private VerificationCriteria verifyName;
	private VerificationCriteria verifyPreview;
	private VerificationCriteria verifyAttributeMapping;

	
	public DbMbo verifyName(String expected, boolean isContinueWhenMatch){
		this.verifyName = new VerificationCriteria(expected, isContinueWhenMatch);
		return this;
	}
	
	public VerificationCriteria verifyName(){
		return this.verifyName;
	}
	
	
	public String getAttributeMapping(){
		return this.attributeMapping;
	}

	public String getDataSourceType() {
		return this.dataSourceType;
	}
	
	public String getSqlQuery() {
		return sqlQuery;
	}
	
	public String getStoredProcedure(){
		return this.storedProcedure;
	}
	
	public String getExistingFilter(){
		return this.existingFilter;
	}

	public String getConnectionProfile() {
		return connectionProfile;
	}

	public String getProjectName() {
		return projectName;
	}
	
	public String getName(){
		return this.name;
	}
	
	public DbMbo attributeMapping(String mapping){
		this.attributeMapping = mapping;
		return this;
	}
	
	public DbMbo startParameter(String string){
		this.startParameter = string;
		return this;
	}

	public DbMbo dataSourceType(String string) {
		// TODO Auto-generated method stub
		this.dataSourceType = string;
		return this;
	}

	public DbMbo connectionProfile(String string) {
		this.connectionProfile = string;
		return this;
	}
	
	public String getParameter(){
		return this.parameter;
	}

	public DbMbo sqlQuery(String string) {
		this.sqlQuery = string;
		return this;
	}

	public DbMbo projectName(String proj_name) {
		this.projectName = proj_name;
		return this;
	}
	
	public DbMbo filter(String string){
		this.existingFilter = string;
		return this;
	}

	public DbMbo name(String string) {
		this.name = string;
		return this;
	}
	
	public DbMbo storedProcedure(String sp){
		this.storedProcedure = sp;
		return this;
	}

	@Override
	public String sp() {
		return this.startParameter;
	}

	
//	public static void main(String[] args){
//		DbMbo mbo = new DbMbo();
//		mbo.verifyName("watson");
//		System.out.println(mbo.verifyName());
//	}

	public DbMbo verifyPreview(String string, boolean value ) {
		this.verifyPreview = new VerificationCriteria(string, value);
		return this;
	}
	
	public VerificationCriteria verifyPreview(){
		return this.verifyPreview;
	}

	public DbMbo parameter(String string) {
		if(this.parameter==null){
			this.parameter = string;
		}else{
			this.parameter = this.parameter+":"+string;
		}
		return this;
	}

	public DbMbo resultSet(String string) {
		this.resultSet = string;
		return this;
	}
	
	public String getResultSet(){
		return this.resultSet;
	}

	public DbMbo existingFilter(String string) {
		this.existingFilter = string;
		return this;
	}

	public DbMbo newFilter(String string) {
		this.newFilter = string;
		return this;
	}
	
	public String getNewFilter(){
		return this.newFilter;
	}

	public DbMbo role(String string) {
		if(role==null){
			role = string;
		}else{
			role = role + ":"+ string;
		}
		return this;
	}
	
	public String getRole(){
		return this.role;
	}

	public DbMbo verifyAttributeMapping(String string, boolean b) {
		this.verifyAttributeMapping = new VerificationCriteria(string, b);
		return this;
	}
	
	public VerificationCriteria verifyAttributeMapping(){
		return this.verifyAttributeMapping;
	}
}
