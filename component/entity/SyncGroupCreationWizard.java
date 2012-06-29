package component.entity;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import component.entity.wizard.IWizard;

public class SyncGroupCreationWizard extends ACW implements IWizard{
	private boolean canContinue = true;

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Synchronization Group");
	}
	
	public void finish(){
		DOF.getButton(dialog(), "&Finish").click();
		sleep(1);
	}

	@Override
	public String getDependOperation(String dependOperation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setName(String str){
		DOF.getTextField(dialog(),"Name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setHour(String str){
		DOF.getSpinner(dialog(), "Input change detection interval in hours, which defines how frequently the data changes of the synchronization group can get server's attention.").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setMinute(String str){
		DOF.getSpinner(dialog(), "Input change detection interval in minutes, which defines how frequently the data changes of the synchronization group can get server's attention.").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setSecond(String str){
		DOF.getSpinner(dialog(), "Input change detection interval in seconds, which defines how frequently the data changes of the synchronization group can get server's attention.").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if (operation.equals("setName")) {
			return 0;
		}
		if (operation.equals("setHour")) {
			return 0;
		}
		if (operation.equals("setMinute")) {
			return 0;
		}
		if (operation.equals("setSecond")) {
			return 0;
		}
		throw new RuntimeException("Unknown operation: "+operation);
	}

	@Override
	public void start(String parameter) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(parameter)+"->Synchronization Groups"));
		DOF.getContextMenu().click(atPath("New->Synchronization Group"));

	}

}
