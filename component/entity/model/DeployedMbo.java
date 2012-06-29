package component.entity.model;

public class DeployedMbo {
	private String connectionProfile;
	private String domain;
	private String pkg;
	private String name;
	
	public DeployedMbo connectionProfile(String str){
		this.connectionProfile = str;
		return this;
	}
	
	public DeployedMbo domain(String str){
		this.domain = str;
		return this;
	}
	
	public DeployedMbo pkg(String str){
		String pkg = str.substring(0,1).toLowerCase()+str.substring(1, str.length());
		this.pkg = pkg;
		return this;
	}
	
	public DeployedMbo name(String str){
		this.name = str;
		return this;
	}
	
	public String getConnectionProfile() {
		return connectionProfile;
	}
	public String getDomain() {
		return domain;
	}
	public String getPkg() {
		return pkg;
	}
	public String getName() {
		return name;
	}

}
