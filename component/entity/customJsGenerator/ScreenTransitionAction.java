package component.entity.customJsGenerator;
import java.util.LinkedList;
import java.util.List;


/**
 * @author eric
 *
 */
public class ScreenTransitionAction {
	protected int index;
	protected String screen ;
	protected String targetScreen;
	protected String transitionMethod;
	protected String anchorObject;
	protected String lastScreen;
	protected boolean isIgnoreInvalidScreenFlow = false;

	public List<IScreenOperation> operations = new LinkedList<IScreenOperation>();
	
	public ScreenTransitionAction(int index) {
		this.index = index;
	}
	
	public int getIndex(){
		return this.index;
	}

	public boolean isIgnoreInvalidScreenFlow() {
		return isIgnoreInvalidScreenFlow;
	}

	public void setIgnoreInvalidScreenFlow(boolean isIgnoreInvalidScreenFlow) {
		this.isIgnoreInvalidScreenFlow = isIgnoreInvalidScreenFlow;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getTargetScreen() {
		return targetScreen;
	}

	public void setTargetScreen(String targetScreen) {
		this.targetScreen = targetScreen;
	}

	public String getTransitionAction() {
		return this.transitionMethod;
	}

	public void setTransitionMethod(String action) {
		this.transitionMethod = action;
	}

	public String getAnchorObject() {
		return anchorObject;
	}

	public void setAnchorObject(String anchorObject) {
		this.anchorObject = anchorObject;
	}

	public String getScreen() {
		return screen;
	}

	public String getLastScreen() {
		return lastScreen;
	}

	public void setLastScreen(String str) {
		if(str==null){
			lastScreen = "";
		}else{
			lastScreen = str;
		}
	}

	public String getCallBackMethod() {
		return "customAfterShowScreen";
	}

	public String getCondition() {
		return "else if(screenToShow == \""+screen+"\" && screenToHide==\""+lastScreen+"\" && step=="+index+")";
	}
	
	public String getFirstCondition(){
		return "if(screenToShow == \""+screen+"\" && screenToHide==\""+lastScreen+"\" && step=="+index+")";
	}

	public boolean hasConditionInMethod(String body) {
		return body.contains(getCondition());
	}
	
	public String toJavaScriptCode(){
		return toJavaScriptCode(false, false);
	}
	
	public String toJavaScriptCode(boolean isFirst, boolean isEnd) {
		String str = "";
		if(isFirst){
			str = getFirstCondition()+"\n";
		}else{
			str = getCondition()+"\n";
		}
		str = str+"{\n";
		str = str+ body(isEnd)+"\n";
		str = str+"step++;\n";
		str = str+"}\n";
		if(isEnd && !isIgnoreInvalidScreenFlow){
			str = str+ "else{\n // uploadData(\"ERROR=Unable to handle screen flow from \"+screenToHide +\" to \"+screenToShow);\nend();\n}";
		}
		return str;
	}

	public String body(boolean isEnd) {
		String body = "setTimeout(function(){\n";
		for(IScreenOperation operation: operations){
			body = body+ operation.toJavaScriptCode()+"\n";
		}
		if(anchorObject!=null){
//			body = body + "setTimeout(function(){menuItemCallback"+screen+"Open_Screen_"+anchorObject+"();}, 5000);\n";
			body = body + transitionJavascriptCode();
//			body = body + "setTimeout(function(){clickMenu(\""+screen+"\",\""+anchorObject+"\");}, 3000);\n";
		}
		if(isEnd){
			body = body + "end();\n";
		}
		body = body + "}, 5000);\n";
		return body;
	}

	public String transitionJavascriptCode() {
		if(this.transitionMethod.equals("MenuItem")){
			return "clickMenu(\""+screen+"\",\""+anchorObject+"\");\n";
//			return "setTimeout(function(){clickMenu(\""+screen+"\",\""+anchorObject+"\");}, 3000);\n";
		}
		if(this.transitionMethod.equals("CustomAction")){
			return "clickMenu(\""+screen+"\",\""+anchorObject+"\");\n";
//			return "setTimeout(function(){clickMenu(\""+screen+"\",\""+anchorObject+"\");}, 3000);\n";
		}
		if(this.transitionMethod.equals("ListItem")){
			if(anchorObject.contains("->")){
				String data = anchorObject.split("->")[0];
				String index = anchorObject.split("->")[1];
				return "clickListItemByData(\""+targetScreen+"\",\""+data+"\", "+index+");\n";
			}else{
				return "clickListItem(\""+targetScreen+"\",\""+anchorObject+"\");\n";
			}
		}else{
			throw new RuntimeException("Unknown transition method: "+this.transitionMethod);
		}
	}

	public void addScreenOperation(IScreenOperation op) {
		operations.add(op);
	}

	public boolean isLastAction(CustomJsTestScript script) {
		return this.index == script.getTransitions().size()-1;
	}
	
	public boolean isFirstAction(){
		return this.index==0;
	}
	
}