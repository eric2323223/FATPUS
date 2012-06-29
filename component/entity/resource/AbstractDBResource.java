package component.entity.resource;

import java.util.HashMap;

public abstract class AbstractDBResource implements IResource{
	private HashMap properties = new HashMap();

	@Override
	public Object getProperty(String name) {
		return this.properties.get(name);
	}
	
	@Override
	public void setProperty(String key, Object value){
		this.properties.put(key, value);
	}

	@Override
	public abstract boolean isActive() ;

	@Override
	public boolean match(Criteria criteria){
		return criteria.match(this.properties);
	}
	
	public abstract String connectionString();
	
	public abstract String driverClass();

}
