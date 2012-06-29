package component.entity.model;

import component.entity.IEditable;

public class WFHtmlView extends Widget implements IEditable{
	

	@Override
	public WFHtmlView newKeyBindMbo(String string) {
		// TODO Auto-generated method stub
		 super.newKeyBindMbo(string);
		 return this;
	}

	public WFHtmlView newKey(String str){
		super.newKey(str);
		return this;
	}
	
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
