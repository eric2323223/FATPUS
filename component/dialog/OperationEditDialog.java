package component.dialog;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TabFolderHelper;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.entity.model.OperationParameter;
import component.entity.model.RestWSOperation;
import component.entity.model.ResultChecker;
import component.view.properties.attributes.OperationCacheUpdatePolicyTab;
import component.view.properties.attributes.OperationParameterTab;
import component.wizard.page.ResultCheckerDefinitionPage;

/**
 * Description   : Functional Test Script
 * @author Eric
 */
public class OperationEditDialog extends RationalTestScript 
{
	String dialogName;
	public OperationEditDialog(String string) {
		this.dialogName = "Operation '" +string+"'";
	}
	/**
	 * Script Name   : <b>OperationEditDialog</b>
	 * Generated     : <b>Aug 9, 2010 2:16:00 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/08/09
	 * @author Eric
	 */
	
	public void testMain(Object[] args) 
	{
		openTabFolder("create","Parameter");
	}
//	public void setDTformultiPara(){
//		tree().click(atPath("id"));
//		tree().click(SHIFT_LEFT,atPath("lname"));
//		tree().click(RIGHT, atCell(atRow(atPath("id")), atColumn("Datatype")));
//		cComboWithTooltip().click(RIGHT, atPoint(70,5));
//		cComboWithTooltip().click(atPoint(70,5));
//		list().click(atText("STRING"));
//		tree().click(atPoint(163,192));			
//	}
	
	public void openTabFolder(String operation,String tab){
		DOF.getTabFolder(DOF.getDialog("Operation '"+operation+"'"),"{Data Source,Definition,Parameter,Roles,Cache Update Policy }",true).setState(SINGLE_SELECT, atText(tab));
	}
	
	public void setOperPropertyParamTreeValue(String operation, String node, String column,String input){			
		DOF.getTree(DOF.getDualHeadersTree(DOF.getDialog("Operation '"+operation+"'"))).click(atCell(atRow(atPath(node)), atColumn(column)));
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(input);
		w.inputKeys("{ENTER}");
	}
	
	public void setOperPropertyArgumentDataTypeValue(String operation, String node, String input){	
		DOF.getTree(DOF.getDualHeadersTree(DOF.getDialog("Operation '"+operation+"'"))).click(atCell(atRow(atPath(node)), atColumn("Argument")));
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{TAB}");
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(input);
		w.inputKeys("{ENTER}");
	}
	public static void setUpdateCache(TopLevelTestObject dialog, boolean v) {
//		DOF.getTabFolder(dialog, "Cache Policy ").setState(SINGLE_SELECT, atText("Cache Policy "));
		DOF.getTabFolder(dialog, "Cache Update Policy ").setState(SINGLE_SELECT, atText("Cache Update Policy "));
		OperationCacheUpdatePolicyTab.setApplyResultToCache(dialog, v);
		closeRefreshPrompt();
	}
	public static void ok(TopLevelTestObject dialog) {
		DOF.getButton(dialog, "OK").click();
	}
	
	public static void cancel(TopLevelTestObject dialog){
		DOF.getButton(dialog, "Cancel").click();
	}
	public boolean hasTab(String string) {
		boolean result = TabFolderHelper.hasItem(DOF.getTabFolder(DOF.getDialog(this.dialogName),"Data Source"), string);;
		cancel(DOF.getDialog(this.dialogName));
		return result;
	}
	public static void setRole(TopLevelTestObject dialog, String role) {
		if(role!=null){
			DOF.getTabFolder(dialog, "Role").setState(SINGLE_SELECT, atText("Roles"));
			GuiSubitemTestObject leftTable = DOF.getTableByColumnData(dialog, 1, new String[]{role});
			int row = TableHelper.getRowIndexOfRecordInColumn((GuiSubitemTestObject) leftTable, "Name", role);
			leftTable.click(atCell(atRow(row), atColumn("Name")));
			DOF.getButton(dialog, "A&dd >>").click();
			closeRefreshPrompt();
		}
	}
	public static void setName(String name, TopLevelTestObject dialog) {
		if(name!=null){
			DOF.getTextField(dialog, "Operation name:").click();
			dialog.inputKeys(SpecialKeys.CLEARALL);
			dialog.inputChars(name);
			closeRefreshPrompt();
		}
	}
	public static void setConnectionProfile(String connectionProfile, TopLevelTestObject dialog) {
		if(connectionProfile!=null){
			DOF.getTabFolder(dialog, "Data Source").setState(SINGLE_SELECT, atText("Data Source"));
			DOF.getButton(dialog, "C&hange Connection Profile...").click();		
			TopLevelTestObject dsDialog = DOF.getDialog("Data Source");
			DOF.getCombo(dsDialog, "Connection profile:").click();
			DOF.getCombo(dsDialog, "Connection profile:").click(atText(connectionProfile));
			DOF.getButton(dsDialog, "OK").click();
			closeRefreshPrompt();
		}
	}
	public static void setResultChecker(String string, TopLevelTestObject dialog) {
		if(string!=null){
			DOF.getTabFolder(dialog, "Definition").setState(SINGLE_SELECT, atText("Definition"));
			DOF.getButton(dialog, "&Edit...").click();
			sleep(1);
			TopLevelTestObject defDialog = DOF.getDialog("Definition");
			ResultCheckerDefinitionPage.setResultChecker(string, defDialog);	
			ResultCheckerDefinitionPage.ok(defDialog);
			closeRefreshPrompt();
		}
	}
	public static RestWSOperation getOperation(TopLevelTestObject dialog) {
		DOF.getTabFolder(dialog, "Definition").setState(SINGLE_SELECT, atText("Definition"));
		String resultCheckerType = DOF.getTextFieldByAncestorLine(dialog, "Composite->Composite->Composite->Composite->Composite->TabFolder->Composite->Composite->Composite->Composite->Shell->Shell").getProperty("text").toString();
		ResultChecker rc = new ResultChecker().type(resultCheckerType);
		return new RestWSOperation().resultChecker(rc.toString());
	}
	public static String getParameterDefaultValue(String parameter,	TopLevelTestObject dialog) {
//		DOF.getTabFolder(dialog, "Operation Parameters").setState(SINGLE_SELECT, atText("Operation Parameters"));
		DOF.getTabFolder(dialog, "Parameter").setState(SINGLE_SELECT, atText("Parameter"));
		GuiSubitemTestObject tree = DOF.getTree(dialog);
		return TreeHelper.getCellValue(tree, parameter, "Default Value");
	}
	public static void setParameter(String parameter, TopLevelTestObject dialog) {
		if (parameter == null) return;
		OperationParameter op = new OperationParameter(parameter);
		DOF.getTabFolder(dialog, "Operation Parameters").setState(SINGLE_SELECT, atText("Operation Parameters"));
//		GuiSubitemTestObject tree = DOF.getTree(dialog);
//		if(op.getPk()!=null){
//			TreeHelper.setCComboCellValue(tree, op.getName(), "Personalization Key", op.getPk());
//		}
		OperationParameterTab.set(op, dialog);
		closeRefreshPrompt();
	}
	
	//setDeifinition
	public static void setDeifinition(String sql, TopLevelTestObject dialog) {
		DOF.getTabFolder(dialog, "Definition").setState(SINGLE_SELECT, atText("Definition"));
		if(sql !=null){
			DOF.getButton(dialog, "&Edit...").click();
			TopLevelTestObject defDialog = DOF.getDialog("Definition");
			sleep(0.5);
			DOF.getTextFieldByToolTip(DOF.getGroup(defDialog, "&SQL Query"), "Input SQL statements here for definition ").click();
			sleep(0.5);
			defDialog.inputKeys(SpecialKeys.CLEARALL);
			sleep(0.5);
		//	defDialog.inputKeys(sql);
			defDialog.inputChars(sql);

			sleep(0.5);
			DOF.getButton(defDialog, "OK").click();
			closeRefreshPrompt();
		}
	}
	
	public static void closeRefreshPrompt(){
		sleep(1);
		TopLevelTestObject promptDialog = DOF.getDialog("Refresh Prompt");
		if(promptDialog!=null){
			DOF.getButton(promptDialog, "&Yes").click();
		}
	}

}

