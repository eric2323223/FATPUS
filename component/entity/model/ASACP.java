package component.entity.model;

import component.entity.resource.IResource;

public class ASACP implements IWizardEntity {
	private String type;
	private String name;
	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String password;
	private String driverName;
	
	private DBDriver driver;
	
	public ASACP(IResource resource){
		this.host = resource.getProperty("host").toString();
		this.port = resource.getProperty("port").toString();
		this.dbName = resource.getProperty("dbName").toString();
		this.userName = resource.getProperty("login").toString();
		this.password = resource.getProperty("password").toString();
		this.driverName = resource.getProperty("driverName").toString();
	}
	
	public ASACP() {
		// TODO Auto-generated constructor stub
	}

	public ASACP driver(DBDriver d){
		this.driver = d;
		return this;
	}

	public DBDriver getDriver(){
		return this.driver;
	}
	
	private VerificationCriteria verifyName;


	@Override
	public String sp() {
		return null;
	}

	@Override
	public IWizardEntity startParameter(String str) {
		return null;
	}
	
	public ASACP verifyName(String exp, boolean canContinue){
		this.verifyName = new VerificationCriteria(exp, canContinue);
		return this;
	}
	
	public VerificationCriteria verifyName(){
		return this.verifyName;
	}
	
	public ASACP type(String str){
		this.type = str;
		return this;
	}
	
	public ASACP name(String str){
		this.name = str;
		return this;
	}
	
	public ASACP host(String str){
		this.host = str;
		return this;
	}
	
	public ASACP port(String str){
		this.port = str;
		return this;
	}
	
	public ASACP dbName(String str){
		this.dbName = str;
		return this;
	}
	
	public ASACP userName(String str){
		this.userName = str;
		return this;
	}
	
	public ASACP password(String str){
		this.password = str;
		return this;
	}

	public String getDriverName(){
		return this.driverName;
	}
	
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getDbName() {
		return dbName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	

}
