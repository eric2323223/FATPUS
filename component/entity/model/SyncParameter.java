package component.entity.model;

public class SyncParameter {
	private String mapTo=null;
	private String name= null;
	
	public SyncParameter mappedTo(String mt){
		this.mapTo = mt;
		return this;
	}
	
	public SyncParameter name(String name){
		this.name = name;
		return this;
	}
	
	public String getMappedTo(){
		return this.mapTo;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
}
