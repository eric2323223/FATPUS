package component.entity.model;

public class UnwiredServerCP implements IWizardEntity{
	private String name;
	private String host;
	private String domain;
	private String userName;
	private String password;
	
	private String sp;

	public UnwiredServerCP name(String string) {
		this.name=string;
		return this;
	}

	public UnwiredServerCP host(String string) {
		this.host=string;
		return this;
	}

	public UnwiredServerCP domain(String string) {
		this.domain=string;
		return this;
	}

	public UnwiredServerCP userName(String string) {
		this.userName=string;
		return this;
	}

	public UnwiredServerCP password(String string) {
		this.password=string;
		return this;
	}

	public String getName() {
		return name;
	}

	public String getHost() {
		return host;
	}

	public String getDomain() {
		return domain;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public IWizardEntity startParameter(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sp() {
		// TODO Auto-generated method stub
		return null;
	}

}
