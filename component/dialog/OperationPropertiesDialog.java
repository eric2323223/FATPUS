package component.dialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import com.sybase.automation.framework.widget.interfaces.IButton;
import com.sybase.automation.framework.widget.interfaces.ICheckBox;
import com.sybase.automation.framework.widget.interfaces.IComboBox;
import com.sybase.automation.framework.widget.interfaces.ITabFolder;
import com.sybase.automation.framework.widget.interfaces.ITable;
import com.sybase.automation.framework.widget.interfaces.ITextBox;
import com.sybase.automation.framework.widget.interfaces.ITree;

/**
 * Description   : Functional Test Script
 * @author yangg
 */
public class OperationPropertiesDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>OperationPropertiesDialog</b>
	 * Generated     : <b>Jun 24, 2008 5:04:16 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/06/24
	 * @author yangg
	 */
	public void testMain(Object[] args) 
	{
	}
	
	private String opName;
	
	public OperationPropertiesDialog(String name){
		this.opName = name;
	}
	
	public OperationPropertiesDialog(){
		
	}
	/**
	 * common info
	 * @return
	 */
	
//	public static TopLevelTestObject dialog(){
//		return DOF.getDialog("Operation '"+this.opName+"'");
//	}
	
	public static void setConnectionProfile(String cp, TopLevelTestObject dialog){
		DOF.getTabFolder(dialog, "Data Source").setState(SINGLE_SELECT, atText("Data Source"));
		DOF.getButton(dialog, "C&hange Connection Profile...").click();
		DOF.getCombo(dialog,"Connection profile:").click();
		DOF.getCombo(dialog,"Connection profile:").click(atText(cp));
		DOF.getButton(dialog,"OK").click();
	}
	
	public static void addRole(String role, TopLevelTestObject dialog){
		DOF.getTabFolder(dialog, "Data Source").setState(SINGLE_SELECT, atText("Roles"));
		GuiSubitemTestObject leftTable = DOF.getTable(dialog,"Available Roles:");
		int row = TableHelper.getRowIndexOfRecordInColumn(leftTable, "Name", role);
		leftTable.click(atCell(atRow(row), atColumn("Name")),atPoint(10,10));
		sleep(1);
		DOF.getButton(dialog, "A&dd >>").click();
		sleep(1);
		DOF.getButton(dialog,"OK").click();
	}
	
	public static List<String> getParameters(TopLevelTestObject dialog){
//		DOF.getTabFolder(dialog, "Operation Parameters").setState(SINGLE_SELECT, atText("Operation Parameters"));
		DOF.getTabFolder(dialog, "Parameter").setState(SINGLE_SELECT, atText("Parameter"));
		GuiSubitemTestObject tree = DOF.getTree(dialog);
		String[] nodes = TreeHelper.getNodesOfLevel(tree, 0);
		cancel(dialog);
		return new ArrayList<String>(Arrays.asList(nodes));
	}
	
	private static void ok(TopLevelTestObject dialog){
		DOF.getButton(dialog, "OK").click();
	}
	
	private static void cancel(TopLevelTestObject dialog){
		DOF.getButton(dialog, "Cancel").click();
	}

	public static String getPreviewData(TopLevelTestObject dialog, int row, int column) {
		DOF.getTabFolder(dialog, "Definition").setState(SINGLE_SELECT, atText("Definition"));
		DOF.getButton(dialog, "Test E&xecute...").click();
		String data = PreviewDialog.getPreviewData(DOF.getDialog("Test Execute"), row, column);
		DOF.getButton(dialog, "Cancel").click();
		return data;
	}
}

