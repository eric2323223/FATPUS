package component.entity.customJsGenerator;

/**
 * @author eric
 *
 */
public class GetMenuItemDisplayNameOperation implements IScreenOperation {
	private String menuItemId;
	private String screenName;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}

	@Override
	public String toJavaScriptCode() {
		return "getMenuItemDisplayName(\""+screenName+"\", \""+menuItemId+"\");\n";
	}

}
