package component.entity.WFCustomizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WFCustomizeSpecification {
	private String scriptName;
	List<WFCustomizeAction> actions = new LinkedList<WFCustomizeAction>();
	
	public WFCustomizeSpecification(String script){
		this.scriptName = script;
	}
	
	public String getScriptName(){
		return this.scriptName;
	}
	
	public WFCustomizeSpecification add(WFCustomizeAction action){
		this.actions.add(action);
		return this;
	}
	
	public List<WFCustomizeAction> getActionsOfType(WFCustomizeActionType type){
		List<WFCustomizeAction> result = new ArrayList<WFCustomizeAction>();
		for(WFCustomizeAction action:actions){
			if(action.getType()==type){
				result.add(action);
			}
		}
		return result;
	}
	
}
