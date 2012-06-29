package component.entity.model;

public class WFAuthentication {
	
	public static final String TYPE_STATIC = "static";
	public static final String TYPE_DOMAIN = "domain";
	
	String type;
	String supCPAuthentication;
	
	
	public WFAuthentication type(String str) {
		this.type = str;
		return this;
	}

	public WFAuthentication supCPAuthentication(String string) {
		this.supCPAuthentication = string;
		return this;
	}
	
	public String getType(){
		return type;
	}
	
	public String getSupCPAuthentication(){
		return supCPAuthentication;
	}
}
