package component.dialog;
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
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DeployedMboPropertiesDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>DeployedMboPropertiesDialog</b>
	 * Generated     : <b>Aug 25, 2010 10:45:25 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/08/25
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		System.out.println(getAttributeRoleMap("nonrole"));
	}
	
	public TopLevelTestObject dialog(){
		return DOF.getDialog("Properties Dialog");
	}
	
	public GuiSubitemTestObject tree(){
		return DOF.getTree(dialog());
	}
	
	public String getAttributeRoleMap(String logicRole){
		tree().click(atPath("Roles"));
		GuiSubitemTestObject table = DOF.getTable(dialog());
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "LogicalRole", logicRole);
		return TableHelper.getCellValue(table, row, 1);
	}
	
	public String getOperationRoleMap(String operation, String logicRole){
		tree().click(atPath("Operations"));
		DOF.getList(dialog(),"Operations").click(atText(operation));
		GuiSubitemTestObject table = DOF.getTable(dialog(),"Roles assigned to the object are:");
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "LogicalRole", logicRole);
		return TableHelper.getCellValue(table, row, 1);
	}
}

