package component.entity.customJsGenerator;
/**
 * @author eric
 *
 */
public class SetDataOperation implements IScreenOperation {
	private String elementId;
	private String value;
	private String screenName;
	

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toJavaScriptCode(){
//		return "$(getScreen(\""+screenName+"\")).find(\"#"+elementId+"\").val(\""+value+"\");\n";
		return "document.forms[getCurrentScreen()+'Form']."+elementId+".value=\""+value+"\";\n";
//		return "document.getElementById(\""+elementId+"\").value = \""+value+"\";\n";
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	

}