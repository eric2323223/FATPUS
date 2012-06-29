package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import component.entity.MBOProperties;
import component.entity.OCW;
import component.entity.model.IWizardEntity;
import component.entity.model.Operation;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OperationCreationWizard extends OCW implements IWizard
{
	/**
	 * Script Name   : <b>OperationCreationWizard</b>
	 * Generated     : <b>Aug 28, 2010 11:53:12 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/08/28
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	
	protected boolean canContinue = true;
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Operation");
	}
	
	private void save(){
		DOF.getMenu().click(atPath("File->Save All"));
	}

	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		save();
	}
	
	public void setName(String string){
		DOF.getTextField(dialog(), "Operation name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
	}
	
	public void setType(String type){
		DOF.getCombo(dialog(), "Operation type").click();
		DOF.getCombo(dialog(), "Operation type").click(atText(type));
	}
	
	public void setSqlQuery(String text){
		DOF.getButton(dialog(), "SQL quer&y statement").click();
		DOF.getTextField(DOF.getGroup(dialog(), "&SQL Query")).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(text);
	}
	
	public void setStoredProcedure(String text) {
		DOF.getButton(dialog(), "St&ored procedure").click();
		DOF.getTextField(DOF.getGroup(dialog(), "&SQL Query")).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(text);
	}
	
	//Nothing but a verification trigger
	public void setParameterDefaultValue(String str){
		
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setSqlQuery")){
			return 1;
		}
		if(operation.equals("setStoredProcedure")){
			return 1;
		}
		if(operation.equals("setParameterDefaultValue")){
			return 2;
		}
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}
	@Override
	public void next() {
		DOF.getButton(dialog(),"&Next >").click();
	}
	
	

	@Override
	public void start(String parameter) {
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		DOF.getWNTree().doubleClick(atPath(parameter));
		sleep(2);
		DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
		DOF.getButton(DOF.getRoot(), "&Add...").click();
	}

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}

	@Override
	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}

	@Override
	public IWizard canContinue(boolean b) {
		canContinue = b;;
		return this;
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		runner.run(entity, this);
	}
}

