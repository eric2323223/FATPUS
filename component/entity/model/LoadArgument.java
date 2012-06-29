package component.entity.model;

public class LoadArgument extends AbstractModel {
	private String name;
	private String defaultValue;
	private String type;
	private String propagateTo;
	private String pk;
	
	public LoadArgument name(String str){
		this.name = str;
		return this;
	}
	
	public LoadArgument defaultValue(String str){
		this.defaultValue = str;
		return this;
	}
	
	public LoadArgument type(String str){
		this.type = str;
		return this;
	}

	public String getName() {
		return name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getType() {
		return type;
	}
	
	public String getPropagateTo(){
		return this.propagateTo;
	}

	public LoadArgument propagateTo(String string) {
		this.propagateTo = string;
		return this;
	}

	public LoadArgument pk(String string) {
		this.pk = string;
		return this;
	}
	
	public String getPk(){
		return this.pk;
	}
	

}
