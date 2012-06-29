package component.entity.model;

public class DeployedWorkFlow {
	String unwiredServer;
	String name;
	String version;
	public String getUnwiredServer() {
		return unwiredServer;
	}
	public String getName() {
		return name;
	}
	public String getVersion() {
		return version;
	}
	
	public DeployedWorkFlow name(String str){
		this.name = str;
		return this;
	}
	
	public DeployedWorkFlow unwiredServer(String str){
		this.unwiredServer = str;
		return this;
	}
	
	public DeployedWorkFlow version(String str){
		this.version = str;
		return this;
	}

}
