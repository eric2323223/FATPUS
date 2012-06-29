package component.entity.customJsGenerator;

public class SubmitMenuItemOperation implements IScreenOperation{
	private String screenName;
	private String anchorObject;

	public String getAnchorObject() {
		return anchorObject;
	}

	public void setAnchorObject(String anchorObject) {
		this.anchorObject = anchorObject;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@Override
	public String toJavaScriptCode() {
		return "uploadData(\"EOM=true\");\n" +
				"clickMenu(\""+screenName+"\",\""+anchorObject+"\");\n";
	}

}
