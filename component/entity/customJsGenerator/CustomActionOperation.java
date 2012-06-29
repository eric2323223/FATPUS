package component.entity.customJsGenerator;

public class CustomActionOperation implements IScreenOperation {
	private String name;
	private String screenName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@Override
	public String getScreenName() {
		return screenName;
	}

	@Override
	public String toJavaScriptCode() {
		return "menuItemCallback"+screenName+name+"();\n";
	}

}
