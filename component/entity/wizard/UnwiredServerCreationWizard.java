package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import component.entity.ACW;

public class UnwiredServerCreationWizard extends ACW {

	@Override
	public void start(String string) {
		DOF.getEETree().click(RIGHT, atPath("Unwired Servers"));
		DOF.getContextMenu().click(atPath("New..."));
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}
	
	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setHost")){
			return 1;
		}
		if(operation.equals("setDomain")){
			return 1;
		}
		if(operation.equals("setUserName")){
			return 1;
		}
		if(operation.equals("setPassword")){
			return 1;
		}
		throw new RuntimeException("Unknown operation: "+operation);
	}

	protected TopLevelTestObject dialog(){
		if(DOF.getDialog("New Connection Profile")!=null){
			return DOF.getDialog("New Connection Profile");
		}
		return DOF.getDialog("New Unwired Server Connection Profile");
	}
	
	public void setName(String str){
		DOF.getTextField(dialog(), "Name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setHost(String str){
		DOF.getTextField(dialog(),"Host:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setDomain(String str){
		DOF.getTextField(dialog(),"IP domain:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setUserName(String str){
		DOF.getTextField(dialog(),"User name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}

	public void setPassword(String str){
		DOF.getTextField(dialog(),"Password:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
		DOF.getButton(dialog(), "&Save password").click();
	}
	
	public void finish(){
		DOF.getButton(dialog(), "&Finish").click();
		sleep(2);
	}
}
