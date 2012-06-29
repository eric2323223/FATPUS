package component.entity.model;

import java.util.ArrayList;
import java.util.List;

public class DUMbo {
	private String name;
	private List<String> attributes= new ArrayList<String>();
	private List<String> operations= new ArrayList<String>();
	private List<String> indexes= new ArrayList<String>();
	
	public List<String> getIndexes(){
		return this.indexes;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAttributes() {
		return attributes;
	}
	public List<String> getOperations() {
		return operations;
	}
	
	public void addOperation(String str){
		this.operations.add(str);
	}
	
	public void addAttribute(String str){
		this.attributes.add(str);
	}
	
	public void addIndex(String str){
		this.indexes.add(str);
	}
	
}
