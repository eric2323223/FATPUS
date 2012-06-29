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
public class WSDSBindDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>WSDSBindDialog</b>
	 * Generated     : <b>Sep 2, 2009 1:53:49 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/09/02
	 * @author yangg
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	TestObject parent=null;
	public WSDSBindDialog()
	{
//		parent=DOF.getDialog("New Attributes");
	}
	
	public void selectOption(String option)
	{ 
		if(option.equals("Attributes"))
		{
			DOF.getRadioButton(DOF.getDialog("New Mobile Business Object Options"), "Attributes").click();
			parent=DOF.getDialog("New Attributes");
		}
		else
		{
			DOF.getRadioButton(DOF.getDialog("New Mobile Business Object Options"), "Operation").click();
			parent=DOF.getDialog("New Operation");
		}
		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "OK").click();
	}
	
	
	
	public void AddXSLT()
	{
		DOF.getButton(parent, "&Add...").click();
	}
	
	public void EditXSLT()
	{
		DOF.getButton(parent, "&Edit...").click();
	}
	
	public void DeleteXSLT(String XSLTname)
	{
		String[] para={"Name","XSLT"};
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
					if(dataTable.getCell(i, 0).equals(XSLTname))
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
		DOF.getButton(table, "&Delete").click();
	}
	
	public void DeleteAllXSLT()
	{
		DOF.getButton(parent, "Delete A&ll").click();
	}
	
	public void NextBtn()
	{
		DOF.getButton(parent, "&Next >").click();
	}
	
	public void FinishBtn()
	{
		DOF.getButton(parent, "&Finish").click();
	}
	
	public void BackBtn()
	{
		DOF.getButton(parent, "< &Back").click();
	}
}

