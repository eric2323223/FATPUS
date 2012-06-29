package component.entity.model;

import component.entity.IEditable;

public class WFSignature extends Widget implements IEditable {
	
	@Override
	public WFSignature  newKeyBindMbo(String string) {
		// TODO Auto-generated method stub
		 super.newKeyBindMbo(string);
		 return this;
	}

	public WFSignature label(String str){
		super.label(str);
		return this;
	}
	
	public WFSignature labelPosition(String str){
		super.labelPosition(str);
		return this;
	}
	public WFSignature newKey(String str){
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
