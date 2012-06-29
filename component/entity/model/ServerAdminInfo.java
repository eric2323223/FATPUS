package component.entity.model;

public class ServerAdminInfo {
	private String clusterName;
	private int adminPort;
	private String clusterContextName;
	private String adminLogIn;
	private String adminLogPW;
	
	public String getClusterName() {
		return clusterName;
	}
	public int getAdminPort() {
		return adminPort;
	}
	public String getClusterContextName() {
		return clusterContextName;
	}
	public String getAdminLogIn() {
		return adminLogIn;
	}
	public String getAdminLogPW() {
		return adminLogPW;
	}

	public ServerAdminInfo clusterName(String str){
		this.clusterName = str;
		return this;
	}
	
	public ServerAdminInfo adminPort(int i){
		this.adminPort = i;
		return this;
	}
	
	public ServerAdminInfo clusterContextName(String str){
		this.clusterContextName = str;
		return this;
	}
	
	public ServerAdminInfo adminLogIn(String str){
		this.adminLogIn = str;
		return this;
	}
	
	public ServerAdminInfo adminLogPW(String str){
		this.adminLogPW = str;
		return this;
	}

}
