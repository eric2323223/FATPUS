package component.entity.model;

public class UnwiredServer extends AbstractModel implements IWizardEntity{

	private String name;
	private String host;
	private String domain;
	private String userName;
	private String password;
	
	private String sp;

	public UnwiredServer name(String string) {
		this.name=string;
		return this;
	}

	public UnwiredServer host(String string) {
		this.host=string;
		return this;
	}

	public UnwiredServer domain(String string) {
		this.domain=string;
		return this;
	}

	public UnwiredServer userName(String string) {
		this.userName=string;
		return this;
	}

	public UnwiredServer password(String string) {
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
	public String sp() {
		return this.sp;
	}

	@Override
	public IWizardEntity startParameter(String str) {
		this.sp = str;
		return this;
	}

}
