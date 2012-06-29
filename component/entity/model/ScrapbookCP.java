package component.entity.model;

public class ScrapbookCP {
	private String type;
	private String name;
	private String database;
	
	public ScrapbookCP type(String str){
		this.type = str;
		return this;
	}
	
	public ScrapbookCP name(String str){
		this.name = str;
		return this;
	}
	
	public ScrapbookCP database(String str){
		this.database = str;
		return this;
	}

	public String getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public String getDatabase() {
		return this.database;
	}

}
