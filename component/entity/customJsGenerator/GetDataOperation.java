package component.entity.customJsGenerator;

/**
 * @author eric
 *
 */
public class GetDataOperation implements IScreenOperation{
	private String elementId;
	private String screenName;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getElementId() {
		return elementId;
	}

	@Override
	public String toJavaScriptCode() {
//		String str = "var data = document.getElementById(\""+this.elementId+"\").value;\n";
//		str = str + "uploadData(\"id="+this.elementId+";value=\"+data);\n";
		
		String str = "var data = getCurrentMessageValueCollection().getData(\""+elementId+"\").getValue();\n";
		str = str + "uploadData(\"id="+this.elementId+";value=\"+data);\n";
		return str;
	}

	public void setElementId(String id) {
		this.elementId = id;
	}

}
