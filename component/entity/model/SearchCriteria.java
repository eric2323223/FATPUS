package component.entity.model;

import java.util.HashMap;

public class SearchCriteria {
	
	public HashMap<String, String> properties = new HashMap<String, String>();
	
	public SearchCriteria property(String key, String value){
		properties.put(key, value);
		return this;
	}
	
	public String getProperty(String key){
		return properties.get(key);
	}

}
