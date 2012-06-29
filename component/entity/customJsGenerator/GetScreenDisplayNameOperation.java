package component.entity.customJsGenerator;

/**
 * @author eric
 *
 */
public class GetScreenDisplayNameOperation implements IScreenOperation {
	String screenName;
	
	public void setScreenName(String screen){
		this.screenName = screen;
	}

	@Override
	public String toJavaScriptCode() {
		return "getScreenDisplayName(\""+screenName+"\");\n";
	}

	@Override
	public String getScreenName() {
		return this.screenName;
	}

}
