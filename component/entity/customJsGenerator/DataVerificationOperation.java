package component.entity.customJsGenerator;

/**
 * @author eric
 *
 */
public class DataVerificationOperation implements IScreenOperation{
	private String elementId;
	private String screenName;
	private String expectedValue;
	private String vpName;
	private String rftScriptName;

	public String getRftScriptName() {
		return rftScriptName;
	}

	public void setRftScriptName(String rftScriptName) {
		this.rftScriptName = rftScriptName;
	}

	public String getVpName() {
		return vpName;
	}

	public void setVpName(String vpName) {
		this.vpName = vpName;
	}

	public String getExpectedValue() {
		return expectedValue;
	}

	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}

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
		String str = "var data = getCurrentMessageValueCollection().getData(\""+elementId+"\").getValue();\n";
		str = str + "if(data==\""+this.expectedValue+"\")\n";
		str = str + "{uploadData(\"rftScriptName="+this.rftScriptName+";vpName="+this.vpName+"\");\n}";
		str = str + "else\n}";
		str = str + "{uploadData(\"rftScriptName="+this.rftScriptName+";vpName="+this.vpName+";expected="+this.expectedValue+";actual=\"+data);\n}";
		return str;
	}

	public void setElementId(String id) {
		this.elementId = id;
	}

}
