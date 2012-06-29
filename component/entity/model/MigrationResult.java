package component.entity.model;

import java.util.ArrayList;
import java.util.List;

public class MigrationResult {
	List<String> results;
	
	public MigrationResult(List<String> result){
		this.results = result;
	}
	
	public List<String> errors(){
		List<String> errorList = new ArrayList<String>();
		for(String result:results){
			if(result.toLowerCase().contains("error")
					||result.toLowerCase().contains("does not")
					||result.toLowerCase().contains("fail")){
				errorList.add(result);
			}
		}
		return errorList;
	}
	
	private List<String> extractMessages(String text){
		List<String> result = new ArrayList<String>();
		for(String str:results){
			if(str.contains(text)){
				result.add(str);
			}
		}
		return result;
	}

	public List<String> fileMboMessages() {
		return extractMessages("file mobile business objects");
	}
	
	public List<String> relationshipMessages(){
		return extractMessages("Migrate the relationship");
	}

	public List<String> syncGroupMessages() {
		return extractMessages("synchronization group");
	}

}
