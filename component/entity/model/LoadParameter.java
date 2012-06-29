package component.entity.model;

public class LoadParameter {
	private String name;
	private String defaultValue;
	private String type;
	private String propagateTo;
	private String pk;
	private String syncParameter;
	
	public String getName(){
		return this.name;
	}

	public LoadParameter name(String string) {
		this.name = string;
		return this;
	}
	
	public String getDefaultValue(){
		return this.defaultValue;
	}
	
	public LoadParameter defaultValue(String str){
		this.defaultValue = str;
		return this;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getPropagateTo(){
		return this.propagateTo;
	}
	
	public LoadParameter type(String str){
		this.type = str;
		return this;
	}
	
	public LoadParameter propagateTo(String str){
		this.propagateTo = str;
		return this;
	}
	
	public String toString(){
		String text = "[name]"+this.name;
		if(this.defaultValue!=null){
			text = text+",[dv]"+this.defaultValue;
		}
		return text;
	}

	public LoadParameter pk(String string) {
		this.pk = string;
		return this;
	}
	
	public String getPk(){
		return this.pk;
	}

	public LoadParameter syncParameter(String string) {
		this.syncParameter = string;
		return this;
	}
	
	public String getSyncParameter(){
		return this.syncParameter;
	}

}
