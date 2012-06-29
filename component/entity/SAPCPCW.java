package component.entity;

import java.awt.Rectangle;

import com.rational.test.ft.object.interfaces.ToggleTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;
import component.entity.model.SAPCP;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

public class SAPCPCW extends AbstractCPCreationWizard{

	@Override
	public int getPageIndexOfOperation(String operation) {
		if (operation.equals("setName")) {
			return 0;
		}
		if (operation.equals("setPropertyFile")) {
			return 1;
		}
		if (operation.equals("setApplicationServer")) {
			return 1;
		}
		if (operation.equals("setSystemNumber")) {
			return 1;
		}
		if (operation.equals("setSystemId")) {
			return 1;
		}
		if (operation.equals("setClientId")) {
			return 1;
		}
		if (operation.equals("setUserName")) {
			return 1;
		}
		if (operation.equals("setPassword")) {
			return 1;
		}
		throw new RuntimeException("Unknown operation: "+operation);
	}
	
	public void setName(String name){
//		DOF.getTextField(dialog(), "Name:").click();
//		dialog().inputChars(name);
		WO.setTextField(dialog(), DOF.getTextField(dialog(), "Name:"), name);
	}
	
	public void setPropertyFile(String file){
		ToggleTestObject button = (ToggleTestObject)DOF.getButton(dialog(), "Create form pr&operties file:");
		button.setState(SELECTED);
		DOF.getTextFieldByBound(dialog(), new Rectangle(5, 28, 346, 19)).click();
		dialog().inputChars(file);
		dialog().inputKeys(SpecialKeys.ENTER);
		sleep(1);
	}
	
	public void setApplicationServer(String str){
		DOF.getTextField(dialog(), "Application server:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setSystemNumber(String str){
		DOF.getTextField(dialog(), "System number:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setSystemId(String str){
		DOF.getTextField(dialog(), "System ID:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setClientId(String str){
		DOF.getTextField(dialog(), "Client ID:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setUserName(String str){
		DOF.getTextField(dialog(), "User name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setPassword(String str){
		DOF.getTextField(dialog(), "Password:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}

	@Override
	public void start(String parameter) {
		DOF.getEETree().click(RIGHT, atPath("SAP Servers"));
		DOF.getContextMenu().click(atPath("New..."));
		
	}
	
	protected TopLevelTestObject dialog(){
		if(DOF.getDialog("New Connection Profile")!=null){
			return DOF.getDialog("New Connection Profile");
		}else{
			return DOF.getDialog("New SAP Connection Profile");
		}
	}
	
	public void create(SAPCP entity, WizardRunner wr){
		wr.run(entity, this);
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

}
