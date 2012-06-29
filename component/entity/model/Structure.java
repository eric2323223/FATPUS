package component.entity.model;

public class Structure implements IWizardEntity {
	private String name;
	private String parameter;
	private String project;
	
	public String getName(){
		return this.name;
	}
	
	public String getParameter(){
		return this.parameter;
	}
	
	public Structure name(String s){
		this.name = s;
		return this;
	}
	
	public Structure parameter(String s){
		this.parameter = s;
		return this;
	}

	@Override
	public String sp() {
		return this.project;
	}
	

	@Override
	public Structure startParameter(String str) {
		this.project = str;
		return this;
	}

}
