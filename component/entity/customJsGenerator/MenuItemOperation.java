package component.entity.customJsGenerator;

/**
 * @author eric
 *
 */
public class MenuItemOperation implements IScreenOperation{
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
		return "clickMenu(\""+screenName+"\",\""+anchorObject+"\");\n";
	}

}
