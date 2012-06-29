package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.model.DbMbo;
import component.entity.model.IWizardEntity;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

public abstract class ACW extends RationalTestScript implements IWizard {
	public static final String NOT_BIND = "not_bind";
	protected boolean canContinue = true;

	public void setConnectionProfile(String string){
		DOF.getCombo(dialog(), "Connection profile:").click();
		DOF.getCombo(dialog(), "Connection profile:").click(RationalTestScript.atText(string));
	}
	
	public void setAttribute(String str){
		if(!str.trim().equals("")){
			//bla bla bla
		}
	}
	
	public void setDataSourceType(String string) {
		if(string.equals("NA")){
			DOF.getButton(dialog(), "Bind data source &later").click();
		} else {
			DOF.getCombo(dialog(), "Data source type:").click();
			DOF.getCombo(dialog(), "Data source type:").click(
					RationalTestScript.atText(string));
		}
	}
	
	public void setSqlQuery(String string){
		DOF.getButton(dialog(), "SQL quer&y statement").click();
		DOF.getTextField(DOF.getGroup(dialog(), "&SQL Query")).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
	}
	
	public void setProjectName(String string){
		DOF.getTree(dialog()).click(RationalTestScript.atPath(string));
	}
	
	public void setName(String string){
		TopLevelTestObject dialog = dialog();
		DOF.getTextField(dialog, "Name:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(string);
	}
	
	/* (non-Javadoc)
	 * @see component.wizard.IWizard#next()
	 */
	public void next() {
		DOF.getButton(dialog(), "&Next >").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog());
	}

	protected TopLevelTestObject dialog() {
		if (DOF.getDialog("New Mobile Business Object") != null) {
			return DOF.getDialog("New Mobile Business Object");
		} else {
			return DOF.getDialog("New Attributes");
		}
	}
	
	/* (non-Javadoc)
	 * @see component.wizard.IWizard#finish()
	 */
	public void finish(){
		RationalTestScript.sleep(1);
		DOF.getButton(dialog(), "&Finish").click();
		while(true){
			if(dialog()!=null){
				sleep(1);
			}else{
				break;
			}
		}
		MainMenu.saveAll();
	}
	
	public void setStoredProcedure(String text){
		DOF.getButton(dialog(), "St&ored procedure").click();
		DOF.getTextField(DOF.getGroup(dialog(), "&SQL Query")).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(text);
	}
	
	public void setFilter(String name){
		DOF.getLabel(dialog(), "Result Set Filters").click();
		DOF.getButton(dialog(), "&Add...").click();
		RationalTestScript.sleep(1);
		DOF.getDialog("Select Result Set Filter Class").inputChars(name);
		DOF.getButton(DOF.getDialog("Select Result Set Filter Class"), "OK").click();
		RationalTestScript.sleep(5);
//		// Frame: New Attributes
//		resultSetFilters().click();
//		_Add().click();
//		
//		// Frame: Select Result Set Filter Class
//		text().click(atPoint(41,14));
//		selectResultSetFilterClass().inputChars("SampleFilger");
//		text().dragToScreenPoint(atPoint(80,9), selectResultSetFilterClass().getScreenPoint(atPoint(3,76)));
//		selectResultSetFilterClass().inputChars("ff");
//		ok().click();
	}
	
	/* (non-Javadoc)
	 * @see component.wizard.IWizard#getPageIndexOfOperation(java.lang.String)
	 */
	public int getPageIndexOfOperation(String operation){
		if(operation.equals("setProjectName"))
			return 0;
		if(operation.equals("setName"))
			return 0;
		if(operation.equals("setConnectionProfile"))
			return 1;
		if(operation.equals("setDataSourceType"))
			return 1;
		if(operation.equals("setAttribute"))
			return 2;
		if(operation.equals("setSqlQuery"))
			return 2;
		if(operation.equals("setStoredProcedure"))
			return 2;
		if(operation.equals("setFilter"))
			return 2;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	}
	

	public abstract void start(String string);


	public void create(IWizardEntity mbo, WizardRunner wr) {
		wr.run(mbo, this);
	}

	public String preview(int row, int column){
		DOF.getButton(dialog(), "Pre&view...").click();
		DOF.getButton(DOF.getDialog("Preview"), "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
		TestObject table = DOF.getTable(DOF.getDialog("Preview"),"Preview Result:");
		return TableHelper.getCellValue((GuiSubitemTestObject) table,1,1);
	}

	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}
	
	public IWizard canContinue(boolean b){
		this.canContinue = b;
		return this;
	}

	public boolean canContinue() {
		return this.canContinue;
	}

}
