package component.entity.model;

import component.entity.IEditable;



public class WFChoice extends Widget implements IEditable{
	private String item;
	//yanxu
	private String validationMessage;
	private String numberofdecimal;
	
//end
	public WFChoice Numberofdecimal(String string) {
		numberofdecimal = string;
		return this;
	}
	//end
	public WFChoice label(String str){
		super.label(str);
		return this;
	}
	
	public WFChoice labelPosition(String str){
		super.labelPosition(str);
		return this;
	}
	
	public WFChoice logicalType(String str){
		super.logicalType(str);
		return this;
	}
	
	public WFChoice newKey(String str){
		super.newKey(str);
		return this;
	}
	
	public WFChoice item(String str){
		if(item==null){
			item = str;
		}else{
			item = item+":"+str;
		}
		return this;
	}
	
	public String getItem(){
		return item;
	}

	public WFChoice ifReadonly(boolean value){
		super.ifReadonly(value);
		return this;
	}
	
	public WFChoice ifRequired(boolean value){
		super.ifRequired(value);
		return this;
	}

	public WFChoice option(String str){
		if(this.option == null){
			this.option = str;
		}else{
			this.option = this.option+":"+str;
		}
		return this;
	}
	
	@Override
	public void openInPropertiesView() {
		// TODO Auto-generated method stub
		
	}

	public String getOption() {
		// TODO Auto-generated method stub
		return option;
	}

	//add by yanxu
	public WFChoice validationMessage(String string) {
		validationMessage = string;
		return this;
	}
	
	public String getValidationMessage() {
		return validationMessage;
	}
	
	public String getNumberofdecimal() {
		return numberofdecimal;
	}
	//end
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
