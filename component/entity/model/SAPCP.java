package component.entity.model;

import component.entity.resource.IResource;

public class SAPCP implements IWizardEntity{
	private String name;
	private String applicationServer;
	private String systemId;
	private String systemNumber;
	private String clientId;
	private String userName;
	private String password;
	private String propertyFile;

	public SAPCP(IResource resource) {
		this.applicationServer = resource.getProperty("appServer").toString();
		if(resource.getProperty("sysID")!=null){
			this.systemId = resource.getProperty("sysID").toString();
		}
		this.systemNumber = resource.getProperty("sysNumber").toString();
		this.clientId = resource.getProperty("clientID").toString();
		this.userName = resource.getProperty("userName").toString();
		this.password = resource.getProperty("password").toString();
	}

	public SAPCP(){}
	
	public String getName() {
		return name;
	}

	public String getApplicationServer() {
		return applicationServer;
	}

	public String getSystemId() {
		return systemId;
	}

	public String getSystemNumber() {
		return systemNumber;
	}

	public String getClientId() {
		return clientId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	public SAPCP setPropertyFile(String str){
		this.propertyFile = str;
		return this;
	}
	
	public String getPropertyFile(){
		return this.propertyFile;
	}

	public SAPCP name(String string) {
		this.name = string;
		return this;
	}

	public SAPCP applicationServer(String string) {
		this.applicationServer = string;
		return this;
	}

	public SAPCP systemId(String string) {
		this.systemId = string;
		return this;
	}

	public SAPCP systemNumber(String string) {
		this.systemNumber = string;
		return this;
	}

	public SAPCP clientId(String string) {
		this.clientId = string;
		return this;
	}

	public SAPCP userName(String string) {
		this.userName = string;
		return this;
	}

	public SAPCP password(String string) {
		this.password = string;
		return this;
	}

	@Override
	public String sp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizardEntity startParameter(String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
