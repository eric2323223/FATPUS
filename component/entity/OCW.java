package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.dialog.SAPOperationSelectionDialog;
import component.entity.model.Operation;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.verifier.Verifier;
import component.entity.wizard.IWizard;
import component.wizard.page.OperationParameterPage;

public abstract class OCW extends RationalTestScript implements IWizard{
	protected boolean canContinue = true;
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Operation");
	}
	
	public void verifyValidationInformation(VerificationCriteria vc){
		DOF.getButton(dialog(), "Validate Synta&x").click();
		String actual = DOF.getTextField(DOF.getDialog("Validation Information")).getProperty("text").toString();
		Verifier.verifyEquals("validationInformation", this, vc, actual).perform();
		DOF.getButton(DOF.getDialog("Validation Information"), "OK").click();
	}
	
	public String getDependOperation(String operation) {
		if(operation.equals("setParameterDefaultValue")){
			return "verifyParameterDefaultValue";
		}
		if(operation.equals("setSqlQuery")){
			return "verifyValidationInformation";
		}
		return null;
	}
	
	public void setDataSource(String str){
		if(str.equals("NA")){
			DOF.getButton(dialog(), "Bind data source &later").click();
		} else {
			DOF.getCombo(dialog(), "Data source type:").click();
			DOF.getCombo(dialog(), "Data source type:").click(
					RationalTestScript.atText(str));
		}
	}
	
	public void setConnectionProfile(String str){
		DOF.getCombo(dialog(), "Connection profile:").click();
		DOF.getCombo(dialog(), "Connection profile:").click(RationalTestScript.atText(str));
	}

	private void save(){
		DOF.getMenu().click(RationalTestScript.atPath("File->Save All"));
	}

	
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		while(true){
			if(dialog()!=null){
				sleep(1);
			}else{
				break;
			}
		}
		save();
	}
	
	public void setName(String string){
		DOF.getTextField(dialog(), "Operation name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
	}
	
	public void setType(String type){
		DOF.getCombo(dialog(), "Operation type:").click();
		DOF.getCombo(dialog(), "Operation type:").click(RationalTestScript.atText(type));
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

	public void setParameterDefaultValue(String str){
		//Nothing more than a verification trigger.
	}
	
	public void setParameter(String str){
		SAPOperationSelectionDialog.setParameter(str, dialog());
	}
	
	public void setParameterValue(String str){
		OperationParameterPage.setParameterDefaultValue(str, dialog());
	}
	
	public void verifyParameterDefaultValue(VerificationCriteria vc){
		String exp = vc.getExpected();
		boolean canGo = vc.isContinueWhenMatch();
		for(String expected:exp.split(":")){
			doSingleParameterDefaultValueVerification(expected, canGo);
		}
	}
	
	public void doSingleParameterDefaultValueVerification(String expected, boolean canGo){
		String parameter = expected.split(",")[0];
		String defaultValue = expected.split(",")[1];
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(dialog()));
		String type = TreeHelper.getCellValue(tree, parameter, "Datatype");
		String actualValue;
		if(type.equalsIgnoreCase("date")){
			actualValue= TreeHelper.getDateCellValue(tree, parameter, "Default Value");
		}else{
			actualValue= TreeHelper.getCellValue(tree, parameter, "Default Value");
		}
		if(actualValue.equals(defaultValue)){
			if(canGo==false){
				this.canContinue = false;
			}
		}else{
			logError("Default Value does not match:expected["+defaultValue+"]\tactual["+actualValue+"]");
		}
	}
	
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setDataSource")){
			return 0;
		}
		if(operation.equals("setConnectionProfile")){
			return 0;
		}
		if(operation.equals("setSqlQuery")){
			return 1;
		}
		if(operation.equals("setStoredProcedure")){
			return 1;
		}
		if(operation.equals("setParameterValue")){
			return 2;
		}
		if(operation.equals("setParameterDefaultValue")){
			return 2;
		}
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}
	
	public void next() {
		DOF.getButton(dialog(),"&Next >").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog());
	}

	
	public abstract void start(String parameter);

	public void create(Operation op, WizardRunner wr){
		wr.run(op, this);
	}
	
	public void cancel(){
		DOF.getButton(dialog(), "Cancel").click();
	}

	public boolean canContinue() {
		return this.canContinue;
	}
}
