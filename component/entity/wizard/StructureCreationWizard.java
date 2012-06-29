package component.entity.wizard;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.ACW;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.model.IWizardEntity;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class StructureCreationWizard extends ACW
{
	/**
	 * Script Name   : <b>StructureCreationWizard</b>
	 * Generated     : <b>Sep 7, 2010 10:52:42 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/09/07
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Structure");
	}

	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		MainMenu.saveAll();
	}
	
	public void setName(String string){
		DOF.getTextField(dialog(), "Name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
	}
	
	public void setParameter(String string){
		if(string.contains(":")){
			String[] paras = string.split(":");
			for(String para:paras){
				addSingleParameter(para);
			}
		}else{
			addSingleParameter(string);
		}
	}

	private void addSingleParameter(String string) {
		String[] data = string.split(",");
		DOF.getButton(dialog(), "&Add").click();
		GuiSubitemTestObject table = DOF.getTable(dialog());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", "attribute1");
		TableHelper.setTextCellValue(table, row, "Name", data[0]);
		TableHelper.setTextCellValue(table, row, "Datatype", data[1]);
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setParameter")){
			return 1;
		}
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}

	@Override
	public void next() {
		DOF.getButton(dialog(), "&Next >").click();
	}

	@Override
	public void start(String parameter) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(parameter)));
		DOF.getContextMenu().click(atPath("New->Structure"));
//		DOF.getPaletteRoot().click(atText("Structure"));
//		DOF.getObjectDiagramCanvas().click(atPoint(10,10));
	}
	
	public void create(IWizardEntity entity, WizardRunner wr){
		wr.run(entity, this);
	}

	@Override
	public boolean canContinue() {
		return canContinue;
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}


	@Override
	public StructureCreationWizard canContinue(boolean b) {
		canContinue = b;
		return this;
	}
}

