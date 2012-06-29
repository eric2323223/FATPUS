package component.entity;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;
import component.entity.model.WSCP;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

public class WSCPCW extends AbstractCPCreationWizard {

	public void setName(String str){
//		DOF.getTextField(dialog(),"Name:").click();
//		dialog().inputChars(str);
		WO.setTextField(dialog(), DOF.getTextField(dialog(), "Name:"), str);
	}
	
	public void setWsdl(String str){
		DOF.getButton(dialog(), "From &local file").click();
		DOF.getTextFieldByIndex(dialog(), 5).click();
//		DOF.getTextFieldByBound(dialog(), new Rectangle(10,28,270,19)).click();
		dialog().inputChars(str);
//		dialog().inputKeys(SpecialKeys.ENTER);
	}
	
	public void setUrl(String str){
		DOF.getButton(dialog(), "From &URL").click();
		DOF.getTextFieldByIndex(dialog(), "2").click();
		dialog().inputChars(str);
//		dialog().inputKeys(SpecialKeys.ENTER);
	}
	
	
	protected TopLevelTestObject dialog(){
		if(DOF.getDialog("New Connection Profile")!=null){
			return DOF.getDialog("New Connection Profile");
		}else{
			return DOF.getDialog("New Web Service Connection Profile");
		}
	}

	public void setUserName(String str){
//		((ToggleTestObject)DOF.getButton(dialog(), "Enable HTTP &authentication ")).setState(SELECTED);
		DOF.getButton(dialog(), "Enable HTTP &authentication ").click();
		sleep(3);
		DOF.getTextField(dialog(), "User name:").click();
		dialog().inputChars(str);
	}
	
	public void setPassword(String str){
		DOF.getTextField(dialog(), "Password:").click();
		dialog().inputChars(str);
		
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setUrl")){
			return 1;
		}
		if(operation.equals("setWsdl")){
			return 1;
		}
		if(operation.equals("setUserName")){
			return 1;
		}
		if(operation.equals("setPassword")){
			return 1;
		}
		throw new RuntimeException("Unkonw operation: "+operation);
	}

	@Override
	public void start(String parameter) {
		DOF.getEETree().click(RIGHT, atPath("Web Services"));
		DOF.getContextMenu().click(atPath("New..."));
	}

	public void create(WSCP cp, WizardRunner wr) {
		wr.run(cp, this);
		
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

}
