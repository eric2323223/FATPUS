package component.entity.model;

public class Role implements IWizardEntity {
	private String name;
	private String comment;
	private String project;

	@Override
	public String sp() {
		return this.project;
	}
	
	public Role name(String str){
		this.name = str;
		return this;
	}
	public Role comment(String str){
		this.comment=str;
		return this;
	}
	public String getName(){
		return this.name;
	}
	public String getComment(){
		return this.comment;
	}
	public Role startParameter(String str){
		this.project = str;
		return this;
	}

}
