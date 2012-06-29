package component.entity.model;

import component.entity.IEditable;

public class WFLabel extends Widget implements IEditable {
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openInPropertiesView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	private String name;
	private String key;
	private String defaultvalue;

	public WFLabel name(String string) {
		this.name = string;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public WFLabel defaultvalue(String string) {
		this.defaultvalue = string;
		return this;
	}
	
	public String getDefaultvalue() {
		return this.defaultvalue;
	}
	
	public WFLabel key(String key) {
		this.key = key;
		return this;
	}
	
	public String getKey() {
		return this.key;
	}
	
}
