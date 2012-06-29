package component.entity.model;

import component.entity.resource.IResource;

public class WSCP implements IWizardEntity{
	private String name;
	private String wsdl;
	private String url;
	private String userName;
	private String password;
	

	public WSCP(){
		
	}
	
	public WSCP userName(String str){
		this.userName = str;
		return this;
	}
	
	public WSCP password(String str){
		this.password = str;
		return this;
	}
	
	public WSCP(IResource resource){
		Object wsdl = resource.getProperty("wsdl");
		this.wsdl = wsdl==null? null:wsdl.toString();
		Object url = resource.getProperty("url");
		this.url = url==null? null:url.toString();
		Object userName = resource.getProperty("userName");
		this.userName = userName==null? null:userName.toString();
		Object password = resource.getProperty("password");
		this.password = password==null? null:password.toString();
	}

	public WSCP name(String string) {
		this.name = string;
		return this;
	}

	public WSCP wsdl(String string) {
		this.wsdl = string;
		return this;
	}

	public WSCP url(String string) {
		this.url = string;
		return this;
	}

	public String getName() {
		return this.name;
	}
	
	public String getWsdl(){
		return this.wsdl;
	}
	
	public String getUrl(){
		return this.url;
	}

	@Override
	public String sp() {
		return null;
	}

	@Override
	public IWizardEntity startParameter(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
}
