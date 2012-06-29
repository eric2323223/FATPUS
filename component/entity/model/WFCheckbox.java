package component.entity.model;

import component.entity.IEditable;

public class WFCheckbox extends Widget implements IEditable {
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	//add by yanxu
	private String defaultvalue;
	private String validationMessage;
	
	public WFCheckbox defaultValue(String str){
		defaultvalue = str;
		return this;
	}
	
	public WFCheckbox validationMessage(String string) {
		validationMessage = string;
		return this;
	}
	//and
	public WFCheckbox label(String str){
		super.label(str);
		return this;
	}

	public WFCheckbox labelPosition(String str){
		super.labelPosition(str);
		return this;
	}

	public WFCheckbox newKey(String str){
		super.newKey(str);
		return this;
	}
	
	public WFCheckbox ifReadonly(boolean value){
		super.ifReadonly(value);
		return this;
	}
	
	public WFCheckbox ifRequired(boolean value){
		super.ifRequired(value);
		return this;
	}

	//add by yanxu
	public String getValidationMessage() {
		return validationMessage;
	}
	
	public String getdefaultValue() {
		return defaultvalue;
	}
	
	//end
	@Override
	public void openInPropertiesView() {
		// TODO Auto-generated method stub

	}

}
