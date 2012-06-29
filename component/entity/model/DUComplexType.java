package component.entity.model;

import java.util.ArrayList;
import java.util.List;

public class DUComplexType {
	private String name;
	List<String> subTypes = new ArrayList<String>();

	public DUComplexType(String name) {
		this.name = name;
	}

	public void setSubType(String str){
		subTypes.add(str);
	}
	
	public String getSubType(String type){
		for(String line:subTypes){
			if(line.split(",")[0].equals(type)){
				return line;
			}
		}
		return null;
	}
	
	public String getSubTypeType(String name){
		String line = getSubType(name);
		return line.split(",")[1];
	}
	
	public int getSubTypeLength(String name){
		return new Integer(getSubType(name).split(",")[2]).intValue();
	}

}
