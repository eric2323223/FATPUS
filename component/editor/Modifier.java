package component.editor;

public class Modifier {
	public static final String TYPE_ADD="add";
	public static final String TYPE_REMOVE="remove";
	public static final String TYPE_CHANGE="change";
	
	private String property;
	private String orginalValue;
	private String newValue;
	private String type;
	
	public Modifier type(String str){
		this.type = str;
		return this;
	}
	
	public Modifier originalValue(String str){
		this.orginalValue = str;
		return this;
	}
	
	public Modifier newValue(String str){
		this.newValue = str;
		return this;
	}
	
	public Modifier property(String str){
		this.property = str;
		return this;
	}
	
//	public Modifier(String type, String property, String value){
//		this.type = type;
//		this.property = property;
//		this.newValue = value;
//	}
//	
//	public Modifier(String type, String property){
//		this.type = type;
//		this.property = property;
//	}
//	
//	public Modifier(String type, String property, String origin, String newValue){
//		this.type = type;
//		this.property = property;
//		this.orginalValue = origin;
//		this.newValue = newValue;
//	}

	public String getProperty() {
		return property;
	}

	public String getOrginalValue() {
		return orginalValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public String getType() {
		return type;
	}
	
	
	
}
