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
public class PropertiesDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>PropertiesDialog</b>
	 * Generated     : <b>Aug 28, 2010 2:46:30 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/08/28
	 * @author yangg
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	
	TestObject parent=null;
	public PropertiesDialog()
	{
		parent=DOF.getDialog("Properties Dialog");
	}
	
	
	public void selectleftitem(String item)
	{
		//DOF.getTree(parent).click(atText(item));
		DOF.getTreeByRootElement(parent, "Common").click(atPath(item));
	}
	
	public String getpropertiesinfo(String property)
	{
		String[] para={"Property","Value"};
		
		String value="";
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTableByColumnsNames(parent,para );
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
						if(dataTable.getCell(i, 0).equals(property))
						{
							value=(String)dataTable.getCell(i, 1);
						}
						System.out.println("cell["+i+"]["+j+"]:"+dataTable.getCell(i, j)+"|");
					}
					
				}
				
			}
		return value;
	}
	
	public void OKbtn()
	{
		DOF.getButton(parent, "OK").click();
	}
	
	public String getroleinfo(String logicalRole)
	{
		String[] para={"LogicalRole","PhysicalRole"};
		
		String value="";
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTableByColumnsNames(parent,para );
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
						if(dataTable.getCell(i, 0).equals(logicalRole))
						{
							value=(String)dataTable.getCell(i, 1);
						}
						System.out.println("cell["+i+"]["+j+"]:"+dataTable.getCell(i, j)+"|");
					}
					
				}
				
			}
		return value;
	}
}

