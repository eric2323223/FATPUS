package component.entity.model;

public class SynchronizationParameter {
	private String name;
	private String datatype;
	private String defaultValue;
	private String mapTo;
	private String pk;
	
	public String getName() {
		return name;
	}
	public String getDatatype() {
		return datatype;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public String getMapTo() {
		return mapTo;
	}
	public String getPk() {
		return pk;
	}
	
	public SynchronizationParameter name(String str){
		this.name = str;
		return this;
	}
	
	public SynchronizationParameter datatype(String str){
		this.datatype = str;
		return this;
	}
	
	public SynchronizationParameter defaultValue(String str){
		this.defaultValue = str;
		return this;
	}
	
	public SynchronizationParameter mapTo(String str){
		this.mapTo = str;
		return this;
	}
	
	public SynchronizationParameter pk(String str){
		this.pk = str;
		return this;
	}

}
