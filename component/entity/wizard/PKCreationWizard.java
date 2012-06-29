package component.entity.wizard;
import com.sybase.automation.framework.widget.eclipse.*;
import com.sybase.automation.framework.widget.interfaces.*;
import com.sybase.automation.framework.widget.interfaces.IWindow;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.interfaces.IButton;
import com.sybase.automation.framework.widget.interfaces.IComboBox;
import com.sybase.automation.framework.widget.interfaces.ITextBox;
import com.sybase.automation.framework.widget.interfaces.ICheckBox;

import component.dialog.EditDefaultValueDialog;
import component.entity.ACW;
import component.entity.WN;
import component.entity.model.IWizardEntity;
import component.entity.model.PK;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.verifier.Verifier;

/**
 * Description   : Functional Test Script
 * @author yongliu, yongli,Eric
 */
public class PKCreationWizard extends ACW
{
	/**
	 * Script Name   : <b>PKCreationWizard</b>
	 * Generated     : <b>Apr 24, 2008 2:33:52 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/04/24
	 * @author yongliu
	 */
	public void testMain(Object[] args) 
	{
//		generateComponent();
		
		new PKCreationWizard().create(new PK()
			.startParameter(WN.projectNameWithVersion("db_ase"))
			.name("PK2")
			.type("INT")
			.nullable("false")
			.defaultValue("75000038")
			.storage("Client")
			, new WizardRunner());
	}
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Personalization Key");
	}

	public void finish(){
		DOF.getButton(dialog(), "&Finish").click();
		sleep(3);
	}
	
	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setDescription")){
			return 0;
		}
		if(operation.equals("setNullable")){
			return 0;
		}
		if(operation.equals("setProtect")){
			return 0;
		}
		if(operation.equals("setDefaultValue")){
			return 0;
		}
		if(operation.equals("setStorage")){
			return 0;
		}
		else{
			throw new RuntimeException("unknown operation: "+operation);
		}
	}

	public void setDescription(String str){
		DOF.getTextField(dialog(), "Description:").click();
		dialog().inputChars(str);
	}

	public void setName(String name){
		DOF.getTextField(dialog(),"Name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(name);
	}
	
	public void setType(String string){
		DOF.getCombo(dialog(), "Type:").click();
//		DOF.getCombo(dialog(), "Type:").click(atText(string));
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
		DOF.getCombo(dialog(), "Type:").click();
	}
	
	public void setNullable(String string){
		if(string.equalsIgnoreCase("true")){
			((ToggleGUITestObject)DOF.getButton(dialog(), "N&ullable")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog(), "N&ullable")).clickToState(NOT_SELECTED);
		}
	}
	
	public void setProtect(String string){
		if(string.equalsIgnoreCase("true")){
			((ToggleGUITestObject)DOF.getButton(dialog(), "P&rotected")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog(), "P&rotected")).clickToState(NOT_SELECTED);
		}
	}
	
	public void setDefaultValue(String string){
		if(string.contains("->")){
			setComplexTypeDefaultValue(string);
		}else if(string.startsWith("[DATE]")){
			setDateTypeDefaultValue(string.replace("[DATE]", ""));
		}else{
			setSimpleDefaultValue(string);
		}
	}
	
	private void setDateTypeDefaultValue(String value) {
		DOF.getCCombo(dialog(), "Default value(s):").click();
		DOF.getButton(dialog(), "...").click();
		sleep(1);
		TopLevelTestObject dialog = DOF.getDialog("Date");
		DOF.getTextField(dialog, "Value:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(value);
		DOF.getButton(dialog, "OK").click();
	}

	private void setComplexTypeDefaultValue(String str){
		DOF.getTextField(dialog(), "Name:").click();
		DOF.getButton(dialog(), "...").click();
		EditDefaultValueDialog.add(DOF.getDialog("Edit Values"));
		EditDefaultValueDialog.setDefaultValue(str, DOF.getDialog("Edit Values"));
		
	}
	
	
	private void setSimpleDefaultValue(String string){
		DOF.getCCombo(dialog(), "Default value(s):").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
		
	}
	
	public void setStorage(String string){
//		DOF.getCComboWithTooltip(dialog()).click();
		DOF.getCComboTip(dialog(),"Storage:").click(atPoint(39,11));
		DOF.getPoppedUpList().click(atText(string));
	}

	@Override
	public void start(String parameter) {
		if (parameter != null) {
			DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(parameter)));
			DOF.getContextMenu().click(atPath("New->Personalization Key"));
		} else {
			throw new RuntimeException("Not specify project name");
		}
	}
	
	public void create(PK pk, WizardRunner wr){
		wr.run(pk, this);
	}
	
	public void verifyDefaultValue(VerificationCriteria vc){
		String actual = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		Verifier.verifyEquals("vpDefaultValue", this, vc, actual).perform();
	}

	public String getDependOperation(String operation) {
		if(operation.equals("setName")){
			return "verifyName";
		}
		if(operation.equals("setDefaultValue")){
			return "verifyDefaultValue";
		}
		return null;
	}
	
	public void verifyName(VerificationCriteria vc){
		String actual = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		Verifier.verifyEquals("PKName", this, vc, actual).perform();
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		create((PK)entity, runner);
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

}
