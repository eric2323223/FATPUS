package component.entity.customJsGenerator;

/**
 * @author eric
 *
 */
public class CheckLabelOperation implements IScreenOperation{
	private String labelName;
	private String screenName;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	@Override
	public String toJavaScriptCode() {
		return "checkLabel(\""+screenName+"\", \""+labelName+"\");\n";
	}

	@Override
	public String getScreenName() {
		return this.screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	

}
