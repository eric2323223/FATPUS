package component.dialog;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.Cell;
import com.rational.test.ft.script.Column;
import com.rational.test.ft.script.Index;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Row;
import com.rational.test.ft.vp.ITestDataTable;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.LongOperationMonitor;

/**
 * Description   : Functional Test Script
 * @author yangg, Eric
 */
public class PreviewDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>PreviewDialog</b>
	 * Generated     : <b>Mar 9, 2009 3:07:11 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/03/09
	 * @author yangg
	 */
	TestObject parent=null;
	public PreviewDialog()
	{
		parent=getParent("Preview");
	}
	
	public PreviewDialog(String title)
	{
		parent=getParent(title);
		System.out.println(parent);
	}
	
	private TestObject getParent(String title)
	{
		return DOF.getDialog(title);
	}
	
	public void setparametervalue(int row, String input)
	{
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getallTable(parent)[0];
		Cell cell = new Cell(new Row(new Index(row)),
				new Column(new Index(4)));
		System.out.println(table);
		table.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
		for(int i=0;i<dataTable.getRowCount();i++)
		{
			for(int j=0;j<dataTable.getColumnCount();j++)
			System.out.println("cell["+i+"]["+j+"]:"+dataTable.getCell(i, j)+"|");
		}
	//	dataTable.setCell(row, col, input);
		table.click(cell);
		table.click(cell);
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(input);
		table.click();
	}
	
	public void setmultivalue(int startrow,int endrow, String input)
	{
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getallTable(parent)[0];
		Cell cell = new Cell(new Row(new Index(endrow)),
				new Column(new Index(4)));
		table.getObjectReference();
		table.click(atCell(atRow(atIndex(startrow)),atColumn(atIndex(4))));
		table.click(SHIFT_LEFT,atCell(atRow(atIndex(endrow)), 
                atColumn(atIndex(4))));
		
		table.click(cell);
	
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(input);
		table.click();	
	}
	
	public void setStructureValue(String column,String rootnoded,String title,String[][] input)
	{
		String[] columnNames={"","Argument Name","Datatype","Nullable","Required","Value"};
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTableByColumnsNames(parent, columnNames);
		int row=0;
		System.out.println(table);
		table.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
		for(int i=0;i<dataTable.getRowCount();i++)
		{
			System.out.println("value:"+dataTable.getCell(i, 1));
			if(dataTable.getCell(i, 1).equals(column))
			{
				row=i;
				System.out.println("Row is:"+row);
			}
			for(int j=0;j<dataTable.getColumnCount();j++)
				System.out.println("cell["+i+"]["+j+"]:"+dataTable.getCell(i, j)+"|");
			
		}
		Cell cell = new Cell(atRow(atIndex(row)),
				atColumn(atIndex(5)));
	//	dataTable.setCell(row, col, input);
		table.click();
		table.click(cell);
		DOF.getButton(parent, "...").click();
		TestObject parenttmp=DOF.getDialog("Edit Values");
		for(int j=0;j<input[0].length;j++)
		{
			if(!input[1][j].equals(""))
			{
//		DOF.getTree(parenttmp).click(atCell(atRow(atPath("[0]->"+input[0][j])), atColumn("Values")));
		DOF.getTree(parenttmp).click(atCell(atRow(atPath(rootnoded+"->"+input[0][j])), atColumn("Values")));
		try{
			DOF.getButton(parenttmp, "...").click();
			DOF.getTextField(DOF.getDialog(title),"Value:").click();
			IWindow w = RationalTestScript.getScreen().getActiveWindow();
			w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
			w.inputChars(input[1][j]);
			DOF.getButton(DOF.getDialog(title), "OK").click();
		}
		catch(Exception e){
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(input[1][j]);
		}
			}
		}
		sleep(2);
		DOF.getButton(parenttmp, "OK").click();
	}
	
	public void previewresult()
	{
		DOF.getButton(parent, "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
	}
	
	public void OK_btn()
	{
		DOF.getButton(parent, "OK").click();
	}
	
	public void savedefaultvalue(boolean flog)
	{
		ToggleGUITestObject df=(ToggleGUITestObject)DOF.getButton(parent, "Save as de&fault values");
		if(flog)
		{
			df.clickToState(SELECTED);
		}
		else
		{
			df.clickToState(NOT_SELECTED);
		}
	}

	public static String getPreviewData(TopLevelTestObject dialog, int row, int column) {
		DOF.getButton(dialog, "Test E&xecute").click();
		DOF.getButton(DOF.getDialog("Warning"),"OK").click();
		LongOperationMonitor.waitForDialogToVanish("Progress Information");
		String data = TableHelper.getCellValue(DOF.getTable(dialog, "Preview Result:"), row, column);
		DOF.getButton(dialog, "OK").click();
		return data;
	}
	
	public static String getMessage(TopLevelTestObject dialog, String parameter){
		setupParameter(dialog, parameter);
		sleep(1);
		return DOF.getTextFieldByAncestorLine(dialog, "Composite->Shell->Shell").getProperty("text").toString();
	}
	
	private static void setupParameter(TopLevelTestObject dialog, String parameter){
		for(String entry:parameter.split(":")){
			setupSingleParameter(dialog, parameter);
		}
		GuiSubitemTestObject paraTable = DOF.getTableByColumnsNames(dialog,new String[]{"","Argument Name", "Datatype", "Nullable", "Required", "Value"});
		paraTable.click(atCell(atRow(0), atColumn("Argument Name")));
	}
	
	private static void setupSingleParameter(TopLevelTestObject dialog, String parameter){
		String name = parameter.split(",")[0];
		String value = parameter.split(",")[1];
		GuiSubitemTestObject paraTable = DOF.getTableByColumnsNames(DOF.getDialog("Test Execute"),new String[]{"","Argument Name", "Datatype", "Nullable", "Required", "Value"});
//		GuiSubitemTestObject paraTable = DOF.getTableByColumnsNames(dialog,new String[]{"","Argument Name", "Datatype", "Nullable", "Required", "Value"});
		int row = TableHelper.getRowIndexOfRecordInColumn(paraTable, "Argument Name",name);
		paraTable.click(atCell(atRow(row), atColumn("Value")));
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(value);
	}
}

