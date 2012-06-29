package component.entity;

import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.model.VerificationCriteria;
import component.entity.verifier.Verifier;

public class WorkFlowPackageCreationWizard extends ACW {
	VerificationCriteria verifyResult;
	boolean isCanceled;
	
	protected TopLevelTestObject dialog(){
//		return DOF.getDialog("New Mobile Workflow Package Generation");
		return DOF.getDialog("New Hybrid App Package Generation");
	}

	@Override
	public void start(String str) {
		String project = str.split("->")[0];
		String wf = str.split("->")[1]+".xbw";
		String path = WN.filePath(project, wf);
		DOF.getWNTree().click(RIGHT, atPath(path));
		
		//ffan modify the following code in sup2.2drop2
//		DOF.getContextMenu().click(atText("Generate Mobile Workflow Package...")); 
//		LongOperationMonitor.waitForDialog("New Mobile Workflow Package Generation");
		DOF.getContextMenu().click(atText("Generate Hybrid App Package..."));
		LongOperationMonitor.waitForDialog("New Hybrid App Package Generation");
	}
	
	public void setContinueWithError(String str){
		sleep(1);
		if(str.equalsIgnoreCase("true")){
			DOF.getButton(DOF.getDialog("Error Detected"), "&Yes").click();
			sleep(3);
		}else{
			DOF.getButton(DOF.getDialog("Error Detected"), "&No").click();
			isCanceled = true;
		}
	}
	
	public void setUnwiredServer(String str){
		//Generate to project by default, when not specifying generate to external folder
		DOF.getButton(dialog(), "Generate into the pro&ject").click();
		
		((ToggleGUITestObject)DOF.getButton(dialog(), "&Deploy to an Unwired Server")).clickToState(SELECTED);
		DOF.getCCombo(dialog(), "Unwired Server profile:").click();
		sleep(1);
		DOF.getPoppedUpList().click(atText(str));
	}
	
	public void setAssignToUser(String str){
		if(!str.equalsIgnoreCase("null")){
			//ffan modify the following code in sup2.2drop2
//			((ToggleGUITestObject)DOF.getButton(dialog(), "&Assign workflow to user(s)")).clickToState(SELECTED);
			((ToggleGUITestObject)DOF.getButton(dialog(), "&Assign hybrid app to user(s)")).clickToState(SELECTED);
	//		DOF.getButton(dialog(), "&Get Users").click();
	//		sleep(3);
	//		DOF.getCCombo(dialog(), "User(s):").click(atPoint(414,8));
	//		DOF.getPoppedUpList().click(atText(str));
			DOF.getCCombo(dialog(), "User(s):").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str);
		}else{
//			((ToggleGUITestObject)DOF.getButton(dialog(), "&Assign workflow to user(s)")).clickToState(NOT_SELECTED);
			((ToggleGUITestObject)DOF.getButton(dialog(), "&Assign hybrid app to user(s)")).clickToState(NOT_SELECTED);
		}
	}
	
	public void verifyMultiUserMessage(VerificationCriteria vc){
		sleep(1);
		String actual =DOF.getLabelByAncestorLine(dialog(), "Composite->Composite->Section->Composite->ScrolledComposite->Composite->Composite->Composite->Composite->Shell->Shell",2)
			.getProperty("text").toString();
		Verifier.verifyEquals("multipleUserMessage", this, vc, actual).perform();
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setUnwiredServer")){
			return "verifyResult";
		}
		if(operation.equals("setAssignToUser")){
			return "verifyMultiUserMessage";
		}
		return null;
	}
	
	public void verifyResult(VerificationCriteria vc){
		verifyResult = vc;
	}
	
	public void setDeployToServer(String str){
		ToggleGUITestObject button = (ToggleGUITestObject)DOF.getButton(dialog(), "&Deploy to an Unwired Server");
		if(str.equalsIgnoreCase("true") || str.equalsIgnoreCase("yes")){
			button.clickToState(SELECTED);
		}else{
			button.clickToState(NOT_SELECTED);
		}
	}
	
	public void setExternalFolder(String str){
		DOF.getButton(dialog(), "Generate to an &external folder").click();
		WO.setTextField(dialog(), DOF.getTextField(dialog(), "Generation folder:"), str);
	}
	
	public void setValidateControls(String str){
		WO.checkButton(DOF.getButton(dialog(), "&Validate controls as soon as the user tries to change focus away from them"), true);
	}
	
	public void setSaveConfiguration(String str){
		DOF.getHyperLink(dialog(), "Favorite Configurations").click();
		WO.setTextField(dialog(), DOF.getCombo(dialog(), "Name:"), str);
		DOF.getButton(dialog(), "&Save").click();
	}
	
	public void setSelectConfiguration(String str){
		DOF.getHyperLink(dialog(), "Favorite Configurations").click();
		sleep(1);
		WO.setCombo(DOF.getCombo(dialog(),"Name:"), str);
	}
	
	public void setAssignToSelectedUser(String str){
//		((ToggleGUITestObject)DOF.getButton(dialog(), "&Assign workflow to user(s)")).clickToState(SELECTED);
		((ToggleGUITestObject)DOF.getButton(dialog(), "&Assign hybrid app to user(s)")).clickToState(SELECTED);
		DOF.getButton(dialog(), "&Get Users").click();
		sleep(3);
		WO.setCCombo(DOF.getCCombo(dialog(), "User(s):"), str);
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setContinueWithError")){
			return 0;
		}
		if(operation.equals("setSaveConfiguration")){
			return 0;
		}
		if(operation.equals("setSelectConfiguration")){
			return 0;
		}
		if(operation.equals("setExternalFolder")){
			return 0;
		}
		if(operation.equals("setUnwiredServer")){
			return 0;
		}
		if(operation.equals("setAssignToUser")){
			return 0;
		}
		if(operation.equals("setAssignToSelectedUser")){
			return 0;
		}
		if(operation.equals("setDeployToServer")){
			return 0;
		}
		if(operation.equals("setValidateControls")){
			return 0;
		}
		throw new RuntimeException("Unknown operaton: "+operation);
	}

	public void finish(){
		if(!isCanceled){
			DOF.getButton(dialog(), "&Finish").click();
			sleep(2);
			if(DOF.getDialog("Note")!=null){
				DOF.getButton(DOF.getDialog("Note"), "OK").click();
			}
			LongOperationMonitor.waitForProgressBarVanish(dialog());
			sleep(1);
			String actual = DOF.getStyledText(DOF.getRoot()).getProperty("text").toString();
			if(verifyResult!=null){
				Verifier.verifyContains("WFdeploy", this, verifyResult, actual).perform();
			}
		}
		
//		if(!verifyResult.isContinueWhenMatch()){
//			cancel();
//		}
	}

}
