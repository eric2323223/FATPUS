package component.entity.resource;

public abstract class AbstractRelation implements IRelation {
	protected String property;
	protected String value;
	
	@Override
	public String getValue(){
		return this.value;
	}
	
	@Override
	public String getProperty() {
		return this.property;
	}

	@Override
	public abstract boolean match(String key, String value) ;

}
