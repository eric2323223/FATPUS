package component.entity.model;

public class ObjectQueryParameter{
	private String name;
	private String type;
	private String nullable;
	private String mapTo;

	public ObjectQueryParameter(String str) {
		String[] data = str.split(",");
		this.name = data[0];
		this.type = data[1];
		this.nullable = data[2];
		this.mapTo = data[3];
	}
	
	public String toString(){
		return this.name+","+this.type+","+this.nullable+","+this.mapTo;
	}
	
	public String getNullable(){
		return this.nullable;
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

	public String getMapTo() {
		return this.mapTo;
	}


}
