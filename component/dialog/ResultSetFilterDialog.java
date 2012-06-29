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

/**
 * Description   : Functional Test Script
 * @author yangg
 */
public class ResultSetFilterDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>ResultSetFilterDialog</b>
	 * Generated     : <b>Mar 11, 2010 6:33:16 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/03/11
	 * @author yangg
	 */
	TestObject parent=null;
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	
	public ResultSetFilterDialog(String type)
	{
//		
		if(type.equals("SAP"))
			parent=DOF.getDialog("SAP");
		else
//			parent=DOF.getDialog(type);
			parent=DOF.getDialog("Definition");
	}
	
	public ResultSetFilterDialog()
	{
		parent=DOF.getDialog("New Attributes");
	}
	
	public void OpenRFPanel()
	{
		DOF.getLabel(parent,"Result Set Filters").click();
	}
	
	public void CreateBtn_Click()
	{
		DOF.getEnabledButton(parent, "&Create...").click();
	//	System.out.println(DOF.getGuiObjectCloseToGuiObject(parent, "org.eclipse.swt.widgets.Button", DOF.getLabel(parent,"Result Set Filters"), DOF.RELATIVE_DIRECTION_BELOW));
	//	DOF.getGuiObjectCloseToGuiObject(parent, "org.eclipse.swt.widgets.Button", DOF.getLabel(parent,"Result Set Filters"), DOF.RELATIVE_DIRECTION_BELOW).click();
	}
	
	public void cancelCreate()
	{
		DOF.getButton(DOF.getDialog("New Java Class"), "Cancel").click();
	}
	
	public void AddBtn_click()
	{
		//DOF.getButton(parent, "&Add...").click();
		DOF.getGuiObjectCloseToGuiObject(parent,"org.eclipse.swt.widgets.Button",DOF.getEnabledButton(parent, "&Create..."),DOF.RELATIVE_DIRECTION_RIGHT).click();
	
	}
	
	public void DeleteBtn_click()
	{
		DOF.getEnabledButton(parent, "&Delete").click();
	}
	
	public void DeleteAllBtn_click()
	{
		DOF.getButton(parent, "Delete A&ll").click();
	}
	
	public void UpBtn_click()
	{
		DOF.getButton(parent, "&Up").click();
	}
	
	public void DownBtn_click()
	{
		DOF.getButton(parent, "Do&wn").click();
	}
	
	public void selectfilter(String filtername)
	{
		String[] para={"Name","Path"};
		Cell cell = null;
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTableByColumnsNames(parent, para);
	//	GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTable(parent);
		
		if (table==null)
		{
			System.out.println("I can't catch the table");
		}else
		{
			System.out.println("Found the table");
			table.getObjectReference();
			ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
			for(int i=0;i<dataTable.getRowCount();i++)
			{
				for(int j=0;j<dataTable.getColumnCount();j++)
				{
					if(dataTable.getCell(i, 0).equals(filtername))
					{
						System.out.println(dataTable.getCell(i, 0));
					cell=new Cell(new Row(new Index(i)),
							new Column(new Index(0)));
					}
					System.out.println("cell["+i+"]["+j+"]:"+dataTable.getCell(i, j)+"|");
				}
				
			}
			table.click(cell);
		}
	}
	
	public void getalltable()
	{
		DOF.getallTable(parent);
	}
	
	public void showtableitem()
	{
		String[] para={"Name","Path"};
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTableByColumnsNames(parent, para);
		//.getTable(parent);
//		TestObject[] tableall=(TestObject[])DOF.getallTable(parent);
//		//GuiSubitemTestObject table=null;
//		for(int m=0; m<tableall.length; m++)
//		{
//			table=(GuiSubitemTestObject)tableall[m];
		if (table==null)
		{
			System.out.println("I can't catch the table");
		}else
		{
			TestObject[] columns = (TestObject[]) table.invoke("getColumns");
			
			for (int j = 0; j < columns.length; j++) {
				String column = columns[j].invoke("getText").toString();
				System.out.println("Column "+j+" is \\"+column+"\\");
			}
			table.getObjectReference();
			ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
			for(int i=0;i<dataTable.getRowCount();i++)
			{
				for(int j=0;j<dataTable.getColumnCount();j++)
				System.out.println("cell["+i+"]["+j+"]:"+dataTable.getCell(i, j)+"|");
			}
		}
	//	}
	}
	
	public void confirmJavaNature()
	{
		IWindow[] iw=RationalTestScript.getTopWindows();
		if(iw != null)
		{
			for(int i=0;i<iw.length;i++)
			{
				if (iw[i].getText().equals("Add Java Nature"))
				{
					DOF.getButton(DOF.getDialog("Add Java Nature"), "&Yes").click();
				}
			}
		}
	}
	
	public boolean validateRSFilter()
	{
		boolean flag=true;
		String value=DOF.getButton(DOF.getDialog("Select Result Set Filter Class"), "OK").getProperty("enabled").toString();
		System.out.println("Value is:"+DOF.getButton(DOF.getDialog("Select Result Set Filter Class"), "OK").getProperty("enabled")+"~~");
		if(value.equals("false"))
		{
			flag=false;
		}
		return flag;
	}
	
	
	public void createnewfilter(String packagename,String Filtername)
	{
		TestObject parent1=DOF.getDialog("New Java Class");
		if(packagename!=null&packagename!=""){
		TextScrollTestObject tt=(TextScrollTestObject)DOF.getTextField(parent1, "Package:");
		tt.click(atPoint(10,10));
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(packagename);
		}
		if(Filtername!=null&Filtername!="")
		{
			TextScrollTestObject tt=(TextScrollTestObject)DOF.getTextField(parent1, "Name:");
			tt.click(atPoint(10,10));
			IWindow w = RationalTestScript.getScreen().getActiveWindow();
			w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
			w.inputChars(Filtername);
		}		
		DOF.getButton(parent1, "&Finish").click();
	}
	
	public void OKBtn_click()
	{
		DOF.getButton(parent, "OK").click();
	}
	
	public void confirmrefresh()
	{
		DOF.getButton(DOF.getDialog("Refresh prompt"),"&Yes").click();
	}
	
	
	public void selectfilterclass(String filtername)
	{
		TestObject parent1=DOF.getDialog("Select Result Set Filter Class");
		TextScrollTestObject tt=(TextScrollTestObject)DOF.getTextField(parent1);
		tt.click(atPoint(10,10));
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(filtername);
		sleep(10);
		GuiSubitemTestObject tb=(GuiSubitemTestObject)DOF.getTable(parent1);
		tb.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)tb.getTestData("contents");
		for(int i=0;i<dataTable.getRowCount();i++)
		{
			for(int j=0;j<dataTable.getColumnCount();j++)
			{
			System.out.println("cell["+i+"]["+j+"]:"+dataTable.getCell(i, j)+"|");
			if (dataTable.getCell(i,j).toString().equals(filtername+" - (default package)"))
			{
				Cell cell = new Cell(new Row(new Index(i)),
						new Column(new Index(j)));
				tb.click(cell);
			}
			}
		}
		DOF.getButton(parent1, "OK").click();
	}
	
	
	public boolean previewBtn_click(String culumnchange)
	{
		DOF.getButton(parent, "Pre&view...").click();
		sleep(2);
		TestObject parent1=DOF.getDialog("Preview");
		DOF.getButton(parent1, "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		sleep(30);
		TestObject[] tb1=(TestObject[])DOF.getallTable(parent1);
		TestObject tb=null;
		for(int i=0;i<tb1.length;i++)
		{
			System.out.println(tb1[i].getProperty(".priorLabel"));
			if((tb1[i].getProperty(".priorLabel")!=null)&&(tb1[i].getProperty(".priorLabel").equals("Preview Result:")))
				tb=(GuiSubitemTestObject)tb1[i];
		}
//		tb=(TestObject)DOF.getTable(parent1, "Preview Result:");
		TestObject[] columns = (TestObject[]) tb.invoke("getColumns");
		boolean flag=false;
		if (columns.length != 0){
			for (int j = 1; j < columns.length; j++) {
				String column = columns[j].invoke("getText").toString();
				System.out.println(column);
				if (column.equals(culumnchange)) {
					flag=true;
				} 
			}
		}
		DOF.getButton(parent1, "OK").click();
	return flag;
	}
	
	
	public boolean previewBtn_click(String culumn, String value)
	{
		DOF.getButton(parent, "Pre&view...").click();
		sleep(2);
		TestObject parent1=DOF.getDialog("Preview");
		DOF.getButton(parent1, "Pre&view").click();
		DOF.getButton(DOF.getDialog("Warning"), "OK").click();
		sleep(30);
		TestObject[] tb1=(TestObject[])DOF.getallTable(parent1);
		TestObject tb=null;
		
		for(int i=0;i<tb1.length;i++)
		{
		//	System.out.println(tb1[i].getProperty(".priorLabel"));
			if((tb1[i].getProperty(".priorLabel")!=null)&&(tb1[i].getProperty(".priorLabel").equals("Preview Result:")))
				tb=(GuiSubitemTestObject)tb1[i];
		}
		int col=-1;
		TestObject[] columns = (TestObject[]) tb.invoke("getColumns");
		if (columns.length != 0){
			for (int j = 1; j < columns.length; j++) {
				String column = columns[j].invoke("getText").toString();
				System.out.println(column);
				if (column.equals(culumn)) {
					col=j;
				} 
			}
		}

//		tb=(TestObject)DOF.getTable(parent1, "Preview Result:");
		ITestDataTable dataTable=(ITestDataTable)tb.getTestData("contents");
		boolean flag=true;
		for(int i=0;i<dataTable.getRowCount();i++)
		{
			System.out.println(dataTable.getCell(i, col));
			
				if (!dataTable.getCell(i, col).equals(value))
				{
					flag=false;
				}
	//		System.out.println("cell["+i+"]["+col+"]:"+dataTable.getCell(i, col)+"|");
		
		}
		DOF.getButton(parent1, "OK").click();
	return flag;
	}
	
	public void cancelAdd()
	{
		DOF.getButton(DOF.getDialog("Select Result Set Filter Class"), "Cancel").click();
	}
	
}

