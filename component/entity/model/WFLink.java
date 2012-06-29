package component.entity.model;

import component.entity.IEditable;

public class WFLink extends Widget implements IEditable{
	private String label;
	private String key;
	private String defaultvalue;
	private String logicaltype;
	private String prefix;
	private String suffix;

	public WFLink label(String string) {
		this.label = string;
		return this;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public WFLink key(String string) {
		this.key = string;
		return this;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public WFLink defaultValue(String string) {
		this.defaultvalue = string;
		return this;
	}
	
	public String getDefaultValue() {
		return this.defaultvalue;
	}
	
	public WFLink logicalType(String string) {
		this.logicaltype = string;
		return this;
	}
	
	public String getLogicalType() {
		return this.logicaltype;
	}

	public WFLink prefix(String string) {
		this.prefix = string;
		return this;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public WFLink suffix(String string) {
		this.suffix = string;
		return this;
	}
	
	public String getSuffix() {
		return this.suffix;
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
	public void openInPropertiesView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
