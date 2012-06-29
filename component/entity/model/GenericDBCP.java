package component.entity.model;

import component.entity.resource.AbstractDBResource;
import component.entity.resource.IDBResource;
import component.entity.resource.IResource;

public class GenericDBCP implements IWizardEntity{
	private String type;
	private String name;
	private String port;
	private String dbName;
	private String userName;
	private String password;
	private String host;
	
	private DBDriver driver;
	private AbstractDBResource resource;
	
	public GenericDBCP(AbstractDBResource resource){
		this.host = resource.getProperty("host").toString();
		this.port = resource.getProperty("port").toString();
		this.dbName = resource.getProperty("dbName").toString();
		this.userName = resource.getProperty("login").toString();
		this.password = resource.getProperty("password").toString();
		this.resource = resource;
	}
	
	public GenericDBCP() {
		// TODO Auto-generated constructor stub
	}

	public GenericDBCP driver(DBDriver d){
		this.driver = d;
		return this;
	}

	public String getDriver(){
		return this.driver.toString();
	}
//	public DBDriver getDriver(){
//		return this.driver;
//	}
	
	private VerificationCriteria verifyName;
	
	public String getUrl(){
		return resource.connectionString();
	}
////////////////////ff
	public GenericDBCP type(String str){
		this.type = str;
		return this;
	}
	///////////////////ff

	@Override
	public String sp() {
		return null;
	}

	@Override
	public IWizardEntity startParameter(String str) {
		return null;
	}
	
	public GenericDBCP verifyName(String exp, boolean canContinue){
		this.verifyName = new VerificationCriteria(exp, canContinue);
		return this;
	}
	
	public VerificationCriteria verifyName(){
		return this.verifyName;
	}
	
	public GenericDBCP name(String str){
		this.name = str;
		return this;
	}
	
	public GenericDBCP port(String str){
		this.port = str;
		return this;
	}
	
	public GenericDBCP dbName(String str){
		this.dbName = str;
		return this;
	}
	
	public GenericDBCP userName(String str){
		this.userName = str;
		return this;
	}
	
	public GenericDBCP password(String str){
		this.password = str;
		return this;
	}

	
	public String getType() {
		return "Generic JDBC";
	}

	public String getName() {
		return name;
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
