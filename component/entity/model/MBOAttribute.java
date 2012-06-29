package component.entity.model;

public class MBOAttribute {
	private String name;
	private String type;
	private String mapTo;
	private boolean nullable;
	private boolean primaryKey;
	
	public String getMapTo(){
		return this.mapTo;
	}
	
	public boolean isPrimaryKey(){
		return this.primaryKey;
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public boolean isNullable() {
		throw new RuntimeException("Can not get check box status in table:(");
	}
	
	public MBOAttribute(String name, String type, boolean nullable, boolean pk){
		this.name = name;
		this.type = type;
		this.nullable = nullable;
		this.primaryKey = pk;
	}
	
	public MBOAttribute(){}
	
	public MBOAttribute name(String str){
		this.name = str;
		return this;
	}
	
	public MBOAttribute type(String str){
		this.type = str;
		return this;
	}
	
	public MBOAttribute primaryKey(boolean b){
		this.primaryKey = b;
		return this;
	}
	
	public MBOAttribute mapTo(String str){
		this.mapTo = str;
		return this;
	}
	
}
