package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import component.entity.WN;
import component.entity.model.IWizardEntity;
import component.entity.model.Role;
import component.entity.model.WizardRunner;

/**
 * Description : Functional Test Script
 * 
 * @author yongliu, Eric
 */
public class RoleCreationWizard extends RationalTestScript implements IWizard{

//	private WorkspaceNavigator wn = new WorkspaceNavigator();
	private boolean canContinue = true;

	/**
	 * Script Name : <b>RoleCreationWizard</b> Generated : <b>Apr 24, 2008
	 * 2:27:45 AM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2008/04/24
	 * @author yongliu, Eric
	 * 
	 * @changed to implement IWizard
	 */


//	private boolean roleExist(String path) {
//		boolean exist = false;
//		try {
//			exist = wn.Navigator_tre().doesNodeExist_new(path);
//		} catch (Throwable t) {
//		}
//		return exist;
//	}

	
	public void setName(String name){
		DOF.getTextField(dialog(), "Name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(name);
	}
	public void setComment(String comment){
		DOF.getTextField(dialog(), "Description:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(comment);
	}
	public void start(String sp){
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(sp)));
		DOF.getContextMenu().click(atPath("New->Role"));
	}

	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
	}
	
	public TopLevelTestObject dialog(){
		return DOF.getDialog("New Role");
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setComment")){
			return 0;
		}
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}

	@Override
	public void next() {
		throw new RuntimeException("this is a one-page wiazrd");
	}
	
	public void cancel(){
		DOF.getButton(dialog(), "Cancel").click();
	}
	
	public void create(Role role, WizardRunner wr){
		wr.run(role, this);
	}

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		runner.run((Role)entity, this);
		
	}

	@Override
	public IWizard canContinue(boolean b) {
		canContinue = b;
		return this;
	}

}
