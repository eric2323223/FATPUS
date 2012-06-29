package component.entity.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import component.entity.resource.IRelation;

public class Criteria {
	private List<IRelation> factors;
	
	public Criteria(){
		this.factors = new ArrayList<IRelation>();
	}
	
	public Criteria equal(String property, String value){
		factors.add(new EqualRelation(property,value) );
		return this;
	}
	
	public Criteria include(String property, String value){
		factors.add(new IncludeRelation(property, value));
		return this;
	}

	public boolean match(HashMap properties) {
		Iterator iterator = properties.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next().toString();
			String value = properties.get(key).toString();
			for(IRelation relation:factors){
				if(!hasProperty(properties, relation.getProperty())){
					return false;
				}
				else if(relation.match(relation.getValue(), value)){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean hasProperty(HashMap properties, String property){
		return properties.get(property)==null?false:true;
	}

}
