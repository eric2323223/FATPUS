package component.entity.model;

public class LocalMboAttribute {
	private String name;
	private String type;
	private boolean isNullable;
	private boolean isPrimary;
	
	public LocalMboAttribute(String name, String type, boolean nullable, boolean primary){
		this.name = name;
		this.type = type;
		this.isNullable = nullable;
		this.isPrimary = primary;
	}
	
	public LocalMboAttribute(String string){
		String[] parts = string.split(",");
		this.name = parts[0];
		this.type = parts[1];
		this.isNullable = parts[2].equals("true")? true: false;
		this.isPrimary = parts[3].equals("true")? true: false;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public String toString(){
		String str = this.name+","+this.type+",";
		str = str + (this.isNullable? "true": "false");
		str = str +",";
		str = str + (this.isPrimary? "true":"false");
		System.out.println(str);
		return str;
	}
	
	
	public static void main(String[] args){
		String text = new LocalMboAttribute("a","b",true,true).toString();
		System.out.println(new LocalMboAttribute(text).getName());
		System.out.println(new LocalMboAttribute(text).getType());
	}

}
