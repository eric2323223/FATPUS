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
import component.entity.LongOperationMonitor;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SAPOperationSelectionDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>SAPOperationSelectionDialog</b>
	 * Generated     : <b>Jan 14, 2011 10:48:35 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/01/14
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		SAPOperationSelectionDialog.setParameter("SALESDOCUMENT,out", DOF.getDialog("New Operation"));
	}
	
	public static void setOperation(String str, TopLevelTestObject dialog){
		String path = str.split(",")[0];
		String name = str.split(",")[1];
		DOF.getButton(dialog, "B&rowse...").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog);
		TestObject browseDialog = DOF.getDialog("Browse Operation");
		GuiSubitemTestObject tree = DOF.getTree(browseDialog);
//		TreeHelper.expandTreePath(tree, path);
		tree.click(atPath(path));
		GuiSubitemTestObject table = DOF.getTable(browseDialog);
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", name);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(browseDialog, "OK").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog);
	}
	
	public static void setParameter(String str, TopLevelTestObject dialog){
		DOF.getButton(dialog, "&Deselect All").click();
		for(String para:str.split(":")){
			setSingleParameter(para, dialog);
		}
	}
	
	public static void setBapiOperation(String str, TopLevelTestObject dialog){
		String bapi = str.split(",")[0];
		String operation = str.split(",")[1];
		DOF.getButton(dialog, "B&rowse...").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog);
		TopLevelTestObject browseDialog = DOF.getDialog("Browse Operation");
		DOF.getTextFieldByToolTip(browseDialog, "Input a regex-style regular expression").click();
		browseDialog.inputChars(bapi);
		DOF.getButton(browseDialog, "&Search BAPIs/RFCs").click();
		LongOperationMonitor.waitForProgressBarVanish(browseDialog);
		GuiSubitemTestObject table = DOF.getTable(browseDialog);
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", operation);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(browseDialog, "OK").click();
		LongOperationMonitor.waitForProgressBarVanish(dialog);
		
	}
	
	private static void setSingleParameter(String str, TopLevelTestObject dialog){
		String name = str.split(",")[0];
		String type = str.split(",")[1];
		if(type.equalsIgnoreCase("in")){
			setSingleInParameter(name, dialog);
		}else{
			setSingleOutParameter(name, dialog);
		}
	}
	
	private static void setSingleInParameter(String str, TopLevelTestObject dialog){
		GuiSubitemTestObject tree = DOF.getTree(dialog);
		tree.click(atCell(atRow(atPath(str)), atColumn("In")));
	}
	
	private static void setSingleOutParameter(String str, TopLevelTestObject dialog){
		GuiSubitemTestObject tree = DOF.getTree(dialog);
		tree.click(atCell(atRow(atPath(str)), atColumn("Out")));
	}

}

