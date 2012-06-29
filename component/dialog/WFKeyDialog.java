package component.dialog;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.WFCustomizer.CallBackMethod;
import component.entity.model.WFKey;
import component.entity.verifier.Verifier;

public class WFKeyDialog extends RationalTestScript{
	private String errorMessage;
	
	private static TopLevelTestObject dialog(){
		return DOF.getDialog("Key");
	}

	public static void key(String newKey) {
//		TopLevelTestObject dialog = DOF.getDialog("Key");
		if(newKey.contains(",")){
			WO.setTextField(dialog(), DOF.getTextField(dialog(), "Name:"), newKey.split(",")[0]);
			WO.setCCombo(DOF.getCCombo(dialog(), "Type:"), newKey.split(",")[1]);
		}else{
			WO.setTextField(dialog(), DOF.getTextField(dialog(), "Name:"), newKey);
			
		}
		DOF.getButton(dialog(), "OK").click();
	}
	
	public static void key(WFKey key){
		WO.setTextField(dialog(), DOF.getTextField(dialog(), "Name:"), key.getName());
		sleep(1);
		if(key.verifyName()!=null){
//			String actual = "";
			//yanxu1121
			String actual = getErrorMessageLabel().getProperty("text").toString();
			System.out.println(actual);
			Verifier.verifyEquals("vpName", key.verifyName(), actual, cancelMethod()).perform();
		}
		if(Verifier.getResult()==null || !Verifier.getResult().isPass()){
			WO.setCCombo(DOF.getCCombo(dialog(), "Type:"), key.getType());
			setSentByServer(key.getSentByServer());
			setMbo(key.getMbo());
			setMboRelationship(key.getMboRelationship());
			DOF.getButton(dialog(), "OK").click();
		}
	}
	
	private static void setSentByServer(String sentByServer) {
		if(sentByServer!=null && sentByServer.equals("true")){
			((ToggleGUITestObject)DOF.getButton(dialog(), "&Sent by server")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog(), "&Sent by server")).clickToState(NOT_SELECTED);
		}
	}

	private static void setMboRelationship(String relationship) {
		if(relationship!=null){
			DOF.getButton(dialog(),"Mobile business object &relationship").click();
			WO.setCCombo(DOF.getCCombo(DOF.getGroup(dialog(), "Input Data Binding"),1), relationship);
		}
	}

	private static void setMbo(String mbo){
		if(mbo!=null){
			WO.setCCombo(DOF.getCCombo(dialog(), "Mobile business object:"), mbo);
		}
	}
	
	private static CallBackMethod cancelMethod(){
		return new CallBackMethod().receiver(WFKeyDialog.class).methodName("cancel");
	}

	public static void cancel(){
		DOF.getButton(dialog(), "Cancel").click();
	}

	public static void setKey(WFKey key) {
		if(key.getSentByServer()!=null){
			setSentByServer(key.getSentByServer());
		}
		DOF.getButton(dialog(), "OK").click();
	}
	
	public static GuiTestObject getErrorMessageLabel(){
		return DOF.getLabelByAncestorLine(dialog(), "Composite->Composite->Shell->Shell");
	}
	
}
