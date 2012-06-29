package component.entity.model;

public class Module extends AbstractModel{
	String name;
	String version;
	String discription;
	String displayName;
	String credentialCacheKey;
	String activeKey;
	boolean deleteMessage;
	boolean markMessage;

	public String getName() {
		return this.name;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public String getDiscription() {
		return this.discription;
	}
	
	public String getCredentialCacheKey() {
		return this.credentialCacheKey;
	}
	
	public String getActiveKey() {
		return this.activeKey;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public boolean getDeleteMessage() {
		return this.deleteMessage;
	}
	
	public boolean getMarkMessage() {
		return this.markMessage;
	}
	
	public Module name(String name) {
		this.name = name;
		return this;
	}
	
	public Module version(String version) {
		this.version = version;
		return this;
	}
	
	public Module discription(String discription) {
		this.discription = discription;
		return this;
	}
	
	public Module displayName(String displayName) {
		this.displayName = displayName;
		return this;
	}
	
	public Module deleteMessage(boolean deleteMessage) {
		this.deleteMessage = deleteMessage;
		return this;
	}
	public Module markMessage(boolean markMessage) {
		this.markMessage = markMessage;
		return this;
	}
	public Module credentialCacheKey(String credentialCacheKey) {
		this.credentialCacheKey = credentialCacheKey;
		return this;
	}
	public Module activeKey(String activeKey) {
		this.activeKey = activeKey;
		return this;
	}
}
