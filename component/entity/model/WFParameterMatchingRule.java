package component.entity.model;

import com.sybase.automation.framework.common.ObjectMarshaller;

public class WFParameterMatchingRule extends AbstractModel{
	String field;
	String startTag;
	String endTag;
	private String name;

	public WFParameterMatchingRule(){}
	
	public WFParameterMatchingRule(String string){
		WFParameterMatchingRule mr = (WFParameterMatchingRule)ObjectMarshaller.deserialize(string, WFParameterMatchingRule.class);
		this.field = mr.field;
		this.startTag = mr.startTag;
		this.endTag = mr.endTag;
		this.name = mr.name;
	}
	
	public String getField() {
		return field;
	}

	public String getStartTag() {
		return startTag;
	}

	public String getEndTag() {
		return endTag;
	}

	public WFParameterMatchingRule field(String string) {
		this.field = string;
		return this;
	}

	public WFParameterMatchingRule startTag(String string) {
		this.startTag = string;
		return this;
	}

	public WFParameterMatchingRule endTag(String string) {
		this.endTag = string;
		return this;
	}

	public String getName() {
		return this.name;
	}
	
	public WFParameterMatchingRule name(String str){
		this.name = str;
		return this;
	}

}
