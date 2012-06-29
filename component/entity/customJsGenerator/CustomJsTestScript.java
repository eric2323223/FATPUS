package component.entity.customJsGenerator;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.sybase.ua.util.FileUtil;


/**
 * @author eric
 *
 */
public class CustomJsTestScript {
	private String currentScreen;
	private String lastScreen;
	private ScreenTransitionAction currentTransitionAction;
	
	private String rftScriptName;
	
	private List<ScreenTransitionAction> transitions = new LinkedList<ScreenTransitionAction>();
	private List<GlobalAction> globalActions = new LinkedList<GlobalAction>();
	private boolean hasConditionScreen;
	
	private String mode = "interactive"; //mode could be batch or interactive, default is interactive
	
	public CustomJsTestScript(String scriptName){
		this.rftScriptName = scriptName;
	}
	
	public CustomJsTestScript(){}
	
	public String getCurrentScreen(){
		return this.currentScreen;
	}
	
	public void setMode(String str){
		this.mode = str;
	}
	
	public void reportTo(String uri){
		//TBD
	}

	public CustomJsTestScript screen(String screen) {
		if(transitions.size()>0){
			ScreenTransitionAction lastAction = transitions.get(transitions.size()-1);
//			if(lastAction.getTargetScreen()!=null && !lastAction.getTargetScreen().equals(screen)){
//				throw new RuntimeException("Invalid screen order, current screen should be: "+lastAction.getTargetScreen());
//			}
		}
		if(currentScreen!=screen){
			currentScreen = screen;
			int index = transitions.size();
			currentTransitionAction = new ScreenTransitionAction(index);
			if(hasConditionScreen){
				currentTransitionAction.setIgnoreInvalidScreenFlow(true);
			}else{
				currentTransitionAction.setIgnoreInvalidScreenFlow(false);
			}
			currentTransitionAction.setScreen(screen);
			currentTransitionAction.setLastScreen(lastScreen);
			transitions.add(currentTransitionAction);
		}
		return this;
	}
	
	public void verifyData(String vpName, String itemId, String expectedValue){
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size()-1);
			DataVerificationOperation op = new DataVerificationOperation();
			op.setScreenName(currentScreen);
			op.setVpName(vpName);
			if(this.rftScriptName!=null){
				op.setRftScriptName(this.rftScriptName);
			}else{
				throw new RuntimeException("You need to use parameterized contructor to set RFT script name");
			}
			op.setElementId(itemId);
			op.setExpectedValue(expectedValue);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("verifyData() should be all after screen().");
		}
	}
	
	public void getData(String id) {
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size()-1);
			GetDataOperation op = new GetDataOperation();
			op.setScreenName(currentScreen);
			op.setElementId(id);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("getData() should be all after screen().");
		}
	}
	
	public void menuItem(String item){
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size()-1);
			MenuItemOperation op = new MenuItemOperation();
			op.setScreenName(currentScreen);
			op.setAnchorObject(item);
//			op.setScreenName(currentScreen);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("menuItem() should be all after screen().");
		}
	}
	
	public void submitMenuItem(String item){
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size()-1);
			SubmitMenuItemOperation op = new SubmitMenuItemOperation();
			op.setScreenName(currentScreen);
			op.setAnchorObject(item);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("menuItem() should be all after screen().");
		}
	}

	public void setData(String id, String value) {
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size() - 1);
			SetDataOperation op = new SetDataOperation();
			op.setScreenName(currentScreen);
			op.setElementId(id);
			op.setValue(value);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("getData() should be all after screen().");
		}
	}
	
	public void checkLabel(String labelName) {
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size() - 1);
			CheckLabelOperation op = new CheckLabelOperation();
			op.setLabelName(labelName);
			op.setScreenName(currentScreen);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("checkLabel() should be all after screen().");
		}
	}
	
	public void getMenuItemDisplayName(String id) {
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size() - 1);
			GetMenuItemDisplayNameOperation op = new GetMenuItemDisplayNameOperation();
			op.setScreenName(currentScreen);
			op.setMenuItemId(id);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("getMenuItemDisplayName() should be all after screen().");
		}
	}
	
	public void getScreenDisplayName(String id) {
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size() - 1);
			GetScreenDisplayNameOperation op = new GetScreenDisplayNameOperation();
			op.setScreenName(currentScreen);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("getScreenDisplayName() should be all after screen().");
		}
	}
	
	public CustomJsTestScript setMethod(String methodName, String code){
		globalActions.add(new GlobalAction(methodName, code));
		return this;
	}
	
	public CustomJsTestScript addNewMethod(String methodName, String[] methodParameters, String code){
		globalActions.add(new GlobalAction(methodName, methodParameters, code, true));
		return this;
	}
	
	public CustomJsTestScript addDeclaration(String code){
		globalActions.add(new GlobalAction(code));
		return this;
	}
	
	public CustomJsTestScript setMethod(String methodName, File file){
		try{
			String code = FileUtil.readFileAsString(file);
			globalActions.add(new GlobalAction(methodName, code));
			return this;
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException("Failed to setup conditional screen rule to custom.js");
		}
	}
	
	public CustomJsTestScript setConditionScreenRule(String fileName){
		try {
			hasConditionScreen = true;
			setMethod("customConditionalNavigation", FileUtil.readFileAsString(new File(fileName)));
			return this;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to setup conditional screen rule to custom.js");
		}
	}
	
	public List<GlobalAction> getGlobalActions(){
		return this.globalActions;
	}
	
	public CustomJsTestScript moveTo(String string) {
		if (currentScreen != null 
				&& currentTransitionAction!=null && currentTransitionAction.getScreen()!=null
				&& transitions.size()>0) {
			currentTransitionAction = transitions.remove(transitions.size() - 1);
			currentTransitionAction.setTargetScreen(string);
			transitions.add(currentTransitionAction);
			return this;
		} else {
			throw new RuntimeException(
					"moveTo() method should be called after screen() to define target screen.");
		}
	}

	public CustomJsTestScript through(String method, String anchorObject) {
		if (currentTransitionAction != null) {
			if (currentTransitionAction.getScreen() != null
					&& currentTransitionAction.getTargetScreen() != null
					&& !currentTransitionAction.getScreen().equals(currentTransitionAction.getTargetScreen())
					&& transitions.size()>0) {
				currentTransitionAction = transitions.remove(transitions.size() - 1);
				currentTransitionAction.setTransitionMethod(method);
				currentTransitionAction.setAnchorObject(anchorObject);
				lastScreen = currentTransitionAction.getScreen();
				transitions.add(currentTransitionAction);
				return this;
			}else if(currentTransitionAction.getScreen().equals(currentTransitionAction.getTargetScreen())){
				currentTransitionAction = transitions.remove(transitions.size() - 1);
				MenuItemOperation operation = new MenuItemOperation();
				operation.setAnchorObject(anchorObject);
				operation.setScreenName(currentTransitionAction.getScreen());
				currentTransitionAction.addScreenOperation(operation);
				return this;
			}
		} 
		throw new RuntimeException("through() should be called at last to define operation.");
	}
	
	public void checkListItem(String columnData, String columnIndex) {
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size()-1);
			CheckListItemOperation op = new CheckListItemOperation();
			op.setColumnData(columnData);
			op.setColumnIndex(columnIndex);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("checkListItem() should be all after screen().");
		}
	}
	
	public void getListItemsCount() {
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size()-1);
			GetListItemsCountOperation op = new GetListItemsCountOperation();
			op.setScreenName(currentScreen);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("getListItemsCount() should be all after screen().");
		}
	}

	public void customAction(String string) {
		if(currentScreen!=null && currentTransitionAction!=null && transitions.size()>0){
			currentTransitionAction = transitions.remove(transitions.size()-1);
			CustomActionOperation op = new CustomActionOperation();
			op.setScreenName(currentScreen);
			currentTransitionAction.addScreenOperation(op);
			transitions.add(currentTransitionAction);
		}else{
			throw new RuntimeException("customAction() should be all after screen().");
		}
	}

	public void throughMenuItem(String string) {
		through("MenuItem", string);
	}
	
	public void throughListItem(String string) {
		through("ListItem", string);
	}
	
	public void throughCustomAction(String string) {
		through("CustomAction", string);
	}

	public void throughLink(String string) {
		through("Link", string);

	}

	public void throughNavigation(String string) {
		through("Navigation", string);

	}

	public void throughButton(String string) {
		through("Button", string);

	}

	public List<ScreenTransitionAction> getTransitions() {
		return transitions;
	}


}
