package component.wizard.page;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.LongOperationMonitor;
import component.entity.model.ResultFilter;

public class ResultSetFilterDefinitionDefinitionPage extends RationalTestScript{

	public static void setResultSetFilter(ResultFilter filter,TopLevelTestObject dialog) {
		if(filter.getType().equals(ResultFilter.TYPE_NEW)){
			newFilter(filter.getJavaClass(), dialog);
		}else{
//			existingFilter();
		}
	}

	public static void ok(TopLevelTestObject dialog) {
		DOF.getButton(dialog, "OK").click();
		sleep(5);
		if(DOF.getDialog("Refresh Prompt")!=null){
			DOF.getButton(DOF.getDialog("Refresh Prompt"), "&Yes").click();
			LongOperationMonitor.waitForDialog("Progress Information");
		}
	}
	
	private static void newFilter(String name, TopLevelTestObject dialog){
		String title = dialog.getProperty("text").toString();
		DOF.getLabel(dialog(title), "Result Set Filters").click();
		DOF.getButton(dialog(title), "&Create...").click();
		sleep(1);
		if(DOF.getDialog("Add Java Nature")!=null){
			DOF.getButton(DOF.getDialog("Add Java Nature"), "&Yes").click();
		}
		TopLevelTestObject javaClassDialog = DOF.getDialog("New Java Class");
		DOF.getTextField(javaClassDialog, "Name:").click();
		javaClassDialog.inputChars(name);
		DOF.getButton(javaClassDialog, "&Finish").click();
		sleep(5);
	}
	
	private static TopLevelTestObject dialog(String title){
		return DOF.getDialog(title);
	}

	public static void deleteResultSetFilter(String filter,	TopLevelTestObject dialog) {
		String title = dialog.getProperty("text").toString();
		DOF.getLabel(dialog(title), "Result Set Filters").click();
		GuiSubitemTestObject table = DOF.getTable(dialog(title));
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", filter);
		table.click(atCell(atRow(row), atColumn("Name")));
		DOF.getButton(dialog(title), "&Delete").click();
	}

}
