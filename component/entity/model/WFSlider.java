package component.entity.model;

import component.entity.IEditable;
import component.entity.model.WFCheckbox;
import component.entity.model.Widget;

public class WFSlider extends Widget implements IEditable {
	//add by yanxu
	private String defaultvalue;
	private String validationMessage;
	
	public WFSlider defaultValue(String str){
		defaultvalue = str;
		return this;
	}
	
	public WFSlider validationMessage(String string) {
		validationMessage = string;
		return this;
	}
	//end
	
	
	public WFSlider label(String str){
		super.label(str);
		return this;
	}

	public WFSlider labelPosition(String str){
		super.labelPosition(str);
		return this;
	}

	public WFSlider newKey(String str){
		super.newKey(str);
		return this;
	}
	public WFSlider maximumValue(String maxvalue){
		super.maximumValue(maxvalue);
		return this;
	}
	public WFSlider minValue(String minvalue){
		super.minValue(minvalue);
		return this;
	}
	public WFSlider ifReadonly(boolean value){
		super.ifReadonly(value);
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


}
