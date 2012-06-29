package component.entity.model;

import com.sybase.automation.framework.common.ObjectMarshaller;

public class AbstractModel {
	public Object buildModel(String str, Class klass){
		return ObjectMarshaller.deserialize(str, klass);
	}
	
	public String serialize(){
		try{
			return new ObjectMarshaller().serialize(this);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("Unable to serialize object.");
		}
	}
	
	public String toString(){
		return this.serialize();
	}

}
