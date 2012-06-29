package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.dialog.EditDefaultValueDialog;
import component.dialog.SAPOperationSelectionDialog;
import component.entity.model.VerificationCriteria;
import component.wizard.page.LoadParameterPage;
import component.wizard.page.OperationParameterPage;
import component.wizard.page.ParameterPage;

public class SAPMBOCreationWizard extends ACW{
	
	@Override
	public void start(String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(string)));
		DOF.getContextMenu().click(atPath("New->Mobile Business Object"));
	}
	
	public void setBapiOperation(String str){
		SAPOperationSelectionDialog.setBapiOperation(str, dialog());
	}
	
	public void verifyParameterDefaultValue(VerificationCriteria vc){
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(dialog()));
		String parameter = vc.getExpected().split(",")[0];
		String expected = vc.getExpected().split(",")[1];
		String value = TreeHelper.getCellValue(tree, parameter, "Default Value");
		if(expected.equals(value) && vc.isContinueWhenMatch()){
			this.canContinue = true;
			logInfo("VP passed");
		}else{
			this.canContinue = false;
			if(!expected.equals(value)){
				logError("VP Fails. Expected=["+expected+"] Actual=["+value+"]");
			}
		}
	}
	
	public void setActivateVerify(String str){
		//do nothing, just a trigger.
	}
	
	public int getPageIndexOfOperation(String operation){
		if(operation.equals("setName"))
			return 0;
		if(operation.equals("setConnectionProfile"))
			return 1;
		if(operation.equals("setDataSourceType"))
			return 1;
		if(operation.equals("setAttribute"))
			return 2;
		if(operation.equals("setOperation"))
			return 2;
		if(operation.equals("setBapiOperation"))
			return 2;
		if(operation.equals("setParameter"))
			return 2;
		if(operation.equals("setActivateVerify"))
			return 3;
		if(operation.equals("setParameterValue"))
			return 3;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setActivateVerify")){
			return "verifyParameterDefaultValue";
		}
		return null;
	}
	
	public void setOperation(String str){
		SAPOperationSelectionDialog.setOperation(str, dialog());
	}
	
	public void setAttribute(String str){
		super.setAttribute(str);
	}
	
	public void setParameter(String str){
		SAPOperationSelectionDialog.setParameter(str, dialog());
	}
	
	public void setParameterValue(String str) {
//		OperationParameterPage.setParameterDefaultValue(str, dialog());
		for(String entry:str.split(":")){
			LoadParameterPage.setParameterDefaultValue(entry, dialog());
		}
	}

	@Override
	public void setConnectionProfile(String string) {
		super.setConnectionProfile(string);
	}

	@Override
	public void setDataSourceType(String string) {
		super.setDataSourceType(string);
	}

	@Override
	public void setName(String string) {
		super.setName(string);
	}
	
	public void finish(){
		RationalTestScript.sleep(1);
		DOF.getButton(dialog(), "&Finish").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog());
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
		while(true){
			if(dialog()!=null){
				sleep(1);
			}else{
				break;
			}
		}
		MainMenu.saveAll();
	}

}
