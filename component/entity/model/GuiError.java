package component.entity.model;

public class GuiError {
	private String description;
	private String resource;
	private String path;
	private String location;
	private String type;
	
	public GuiError(String desc, String resource, String path, String location, String type){
		this.description = desc;
		this.resource = resource;
		this.path = path;
		this.location = location;
		this.type = type;
	}

	public GuiError(String error) {
		this.description = error;
	}

	public String getDescription() {
		return description;
	}

	public String getResource() {
		return resource;
	}

	public String getPath() {
		return path;
	}

	public String getLocation() {
		return location;
	}

	public String getType() {
		return type;
	}
	
	

}
