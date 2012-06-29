package component.entity.model;

import component.entity.IEditable;

public class WFNewscreen extends Widget implements IEditable {
	private String name;
	private String key;
	public WFNewscreen name(String str){
		this.name = str;
		return this;
	}
	public String getName(){
		return name;
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
