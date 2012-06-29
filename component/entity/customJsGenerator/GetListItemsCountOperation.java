package component.entity.customJsGenerator;

/**
 * @author eric
 *
 */
public class GetListItemsCountOperation implements IScreenOperation{
	String screenName;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@Override
	public String toJavaScriptCode() {
		return "getListItemsCount(\""+screenName+"\");\n";
	}

}
