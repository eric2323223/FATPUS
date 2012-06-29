package component.wizard;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

import com.sybase.automation.framework.common.ClipBoardUtil;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;

import component.dialog.ResultSetFilterDialog;
import component.entity.ACW;

/**
 * Description   : Functional Test Script
 * @author yangg
 */
public class SAPDataBindWizard extends ACW
{
	/**
	 * Script Name   : <b>SAPDataBindWizard</b>
	 * Generated     : <b>Mar 17, 2009 9:08:38 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/03/17
	 * @author yangg
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		
		
		// Frame: New Attributes
//		cComboWithTooltip().click(atPoint(232,12));
//		
//		// Frame: 
//		list().click(atText("STATUSINFO"));
	//	selectResultSet("");
		SAPDataBindWizard sp=new SAPDataBindWizard("Bind Data Source");
	//	sp.disabledTree();
		sp.showalltabledata();
	
	}
	
	public static TestObject parent=null;
	public SAPDataBindWizard()
	{
		parent=DOF.getDialog("New Attributes");
	}
	
	public SAPDataBindWizard(String caption)
	{
		parent=DOF.getDialog(caption);
	}
	public void selectparent(String path)
	{
		DOF.getTree(DOF.getDialog("New Mobile Business Object"), "Select the parent folder:").click(atPath(path));
	}
	
	public void setname(String name)
	{
		TextScrollTestObject text=(TextScrollTestObject)DOF.getTextField(DOF.getDialog("New Mobile Business Object"), "Name:");
		text.setText(name);
	}
	
	public void Next_click()
	{
		DOF.getButton(DOF.getDialog("New Mobile Business Object"), "&Next >");
	}
	
	public void specifyDSRadio()
	{
		DOF.getRadioButton(parent, "Specify a &data source").click();
	}
	public void SelectDSType(String type)
	{
		
		DOF.getCombo(parent, "Data source type:").click();
		DOF.getCombo(parent, "Data source type:").click(atText(type));
	}
	
	public void selectCP(String cp)
	{
		DOF.getCombo(parent, "Connection profile:").click();
		DOF.getCombo(parent, "Connection profile:").click(atText(cp));
	}
	
	public void BDSLater()
	{
		DOF.getRadioButton(parent, "Bind data source &later").click();
	}
	
	public void Next_btn()
	{
		DOF.getButton(parent, "&Next >").click();
	}
	
	
	public void Browse_btn()
	{
		DOF.getButton(parent, "B&rowse...").click();
	}
	
	public void BAPI_select(String path,String name)
	{
	//	text2().setText(SAPBO);
		DOF.getTree(DOF.getDialog("Browse Operation")).click(atPath(path));
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getallTable(DOF.getDialog("Browse Operation"))[0];
		table.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
		int row=0;
		for(int i=0;i<dataTable.getRowCount();i++)
		{
			if(dataTable.getCell(i, 0).equals(name))
			{
				row=i;
			}
		}
		Cell cell = new Cell(new Row(new Index(row)),
				new Column(new Index(0)));
		table.click(cell);
		DOF.getButton(DOF.getDialog("Browse Operation"), "OK").click();
	}
	
	public void addparameter(String name, String type){
		DOF.getButton(parent, "&Add").click();
		TestObject[] trees=DOF.getallTree(parent);
		ScrollGuiSubitemTestObject tree=null;
		int count=trees.length;
		while(count>0){
		 
			tree=(ScrollGuiSubitemTestObject)trees[trees.length-count];
			try{
			tree.click(atCell(atRow(atPath("parameter1")), 
                       atColumn("Name")));
			IWindow w = RationalTestScript.getScreen().getActiveWindow();
			w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
			w.inputChars(name);
			w.inputKeys("{TAB}");
			w.inputChars(type);
			sleep(2);
			tree.click(atCell(atRow(atPath(name)), 
                    atColumn("Argument")));
			sleep(2);
			DOF.getCCombo(parent).click(atPoint(78,10));
			sleep(2);
			WO.setCombo(DOF.getCombo(DOF.getRoot(),"Name"), name);
			break;
//			System.out.println(DOF.getList(DOF.getRoot()).getProperties());
//			DOF.getList(DOF.getRoot()).click(atText(name));
			}
			catch(Exception e)
			{
				count=count-1;
			}
		}
	}
	
	public void setdefaultvalueD(String column,String input)
	{
		TestObject[] trees=DOF.getallTree(parent);
		ScrollGuiSubitemTestObject tree=null;
		int count=trees.length;
		while(count>0){
			tree=(ScrollGuiSubitemTestObject)trees[trees.length-count];
			try{
			tree.click(atCell(atRow(atPath(column)), 
                       atColumn("Default Value")));
			IWindow w = RationalTestScript.getScreen().getActiveWindow();
			w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
			w.inputChars(input);
			break;
			
			}
			catch(Exception e)
			{
				count=count-1;
			}
		}
		
	
	}
	
	public void setStructdefaultvalue(String column,String rootnode,String[][] input)
	{
		TestObject[] trees=DOF.getallTree(parent);
		ScrollGuiSubitemTestObject tree=null;
		int count=trees.length;
		while(count>0){
			tree=(ScrollGuiSubitemTestObject)trees[trees.length-count];
			System.out.println("total "+count+" trees");
			try{
				count=count-1;
				System.out.println("This is the tree "+(trees.length-count));
			tree.click(atCell(atRow(atPath(column)), 
                       atColumn("Default Value")));
			sleep(2);
			DOF.getButton(tree, "...").click();
			TestObject parenttmp=DOF.getDialog("Edit Values");
			for(int j=0;j<input[0].length;j++)
			{
				if(!input[1][j].equals(""))
				{
//			DOF.getTree(parenttmp).click(atCell(atRow(atPath("[0]->"+input[0][j])), atColumn("Values")));
			DOF.getTree(parenttmp).click(atCell(atRow(atPath(rootnode+"->"+input[0][j])), atColumn("Values")));
			IWindow w = RationalTestScript.getScreen().getActiveWindow();
			w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
			w.inputChars(input[1][j]);
				}
			}
			sleep(2);
			DOF.getButton(parenttmp, "OK").click();

			break;
			}
			catch(Exception e)
			{
				
			}
		}
		
	
	}
	
	public void expendtree(String path)
	{
		DOF.getTree(parent, "Select parameters for input arguments or choose parameters to be mapped as output attributes").click(atPath(path+"->Location(PLUS_MINUS)"));
	//	tree5().click(atPath("ORDER_HEADER_IN->Location(PLUS_MINUS)"));
	}
	
	public void checkInput(String[][] para)
	{
		for(int i=0;i<para.length;i++)
		{
			DOF.getTree(parent, "Select parameters for input arguments or choose parameters to be mapped as output attributes").click(atCell(atRow(atPath(para[i][0])), 
                       atColumn(para[i][1])));
		}
	}
	
	public boolean disabledTree()
	{
//		TestObject[] para=DOF.getallTree(parent);
//		if(para!=null)
//		{
//		System.out.println(para[0].getProperties());
//		}
		 if(DOF.getTree(parent, "Select parameters for input arguments or choose parameters to be mapped as output attributes").getProperty("enabled").equals("false"))
		 {
			 return true;
		 }
		 else return false;
	}
	
//	public void setdefaultvalue(int row,String input)
//	{
//		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getallTable(parent)[2];
//		Cell cell = new Cell(new Row(new Index(row)),
//				new Column(new Index(6)));
//		table.getObjectReference();
//		
//		table.click(cell);
//		table.click(cell);
//		IWindow w = RationalTestScript.getScreen().getActiveWindow();
//		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
//		w.inputChars(input);
//		table.click();	
//	}
	
	
	
	public void showalltabledata()
	{
		TestObject[] tables=DOF.getallTable(parent);
		int num=tables.length;
		GuiSubitemTestObject table=null;
		for(int e=0;e<num;e++)
		{
		table=(GuiSubitemTestObject)tables[e];
		TestObject[] columns = (TestObject[]) table.invoke("getColumns");
		
		for (int j = 0; j < columns.length; j++) {
			String column = columns[j].invoke("getText").toString();
			System.out.println("Column "+j+" is \\"+column+"\\");
		}
		System.out.println("table:"+e+"********************************************");
		System.out.println(table.getProperties());
		table.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
		for(int i=0;i<dataTable.getRowCount();i++)
		{
			for(int j=0;j<dataTable.getColumnCount();j++)
			{
			System.out.println("cell["+i+"]["+j+"]:"+dataTable.getCell(i, j)+"|");
			}
			
		}
		}
	}
	
	public void Finishwizard()
	{
		DOF.getButton(parent, "&Finish").click();
	}
	
	/**
	 * General info of operation
	 */
	
	public void setoperationname(String name)
	{
//		TextScrollTestObject text=(TextScrollTestObject)DOF.getTextField(parent, "&Operation name:");
//		text.setText(name);
		ClipBoardUtil.setClipboardText(name);
		/* paste the contents into textbox */
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("^v");
	}
	
	public void selecttype(String type)
	{
		DOF.getCombo(parent, "Operation type:").click();
		DOF.getCombo(parent, "Operation type:").click(atText(type));
		
	}
	
	public void Opexpendtree(String path)
	{
		DOF.getTree(parent, "Select input parameters for the operation and choose the output as the result of test execution").click(atPath(path+"->Location(PLUS_MINUS)"));
	//	tree5().click(atPath("ORDER_HEADER_IN->Location(PLUS_MINUS)"));
	}
	
	public void OpcheckInput(String[][] para)
	{
		for(int i=0;i<para.length;i++)
		{
			DOF.getTree(parent, "Select input parameters for the operation and choose the output as the result of test execution").click(atCell(atRow(atPath(para[i][0])), 
                       atColumn(para[i][1])));
		}
		
	}
	
	
	/**
	 * No DS
	 */
	
	public void addattribute()
	{
		DOF.getButton(parent, "&Add").click();
	}
	
	public void changeattribute(String name,String type)
	{
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getallTable(parent)[0];
		table.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
		int row=-1;
		for(int i=0;i<dataTable.getRowCount();i++)
		{
			if(dataTable.getCell(i, 1).equals("attribute1"))
			{
				row=i;
			}
		}
		Cell cell = new Cell(new Row(new Index(row)),
				new Column(new Index(1)));
		table.getObjectReference();
		table.click(cell);
		table.click(cell);
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(name);
		table.click();	
		cell = new Cell(new Row(new Index(row)),
				new Column(new Index(2)));
		table.click(cell);
		table.click(cell);
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(type);
		table.click();	
	}
	
	public void defaultvalueofope(String[][] para)
	{
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getallTable(parent)[1];
		table.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
		
		Cell cell=null;
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		for(int i=0;i<dataTable.getRowCount();i++)
		{
			for(int j=0;j<para.length;j++)
			{
				System.out.println(dataTable.getCell(i, 1).toString());
				if(dataTable.getCell(i, 1).equals(para[j][0]))
				{
					if(dataTable.getCell(i, 1).toString().indexOf("[]")>0)
					{
						table.click(atCell(atRow(atIndex(i)), atColumn("Default Value")), atPoint(69,8));
						DOF.getButton(parent, "...").click();
						setdefaultvalue(para[j][0],para[j][1]);
					}
					else 
					{
						cell = new Cell(new Row(new Index(j)),
								new Column(new Index(7)));
						table.click(cell);
						table.click(cell);
						w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
						w.inputChars(para[j][1]);
						table.click();
					}
				}
			}
		}
	}
	
	
	private void setdefaultvalue(String colu,String value)
	{
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTable(DOF.getDialog("Default Value"));
		table.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
		String columnname=colu.substring(colu.indexOf("[]")+3, colu.length());
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		String column="";
	//	int number=-1;
		for(int i=0;i<dataTable.getColumnCount();i++)
		{
			if(dataTable.getColumnHeader(i).toString().indexOf("[")>0)
			{
			column=dataTable.getColumnHeader(i).toString().substring(0, dataTable.getColumnHeader(i).toString().indexOf("["));
//			 System.out.println("columnname is:"+columnname+", and column is:"+column);
			if(column.equals(columnname))
			{
//		        System.out.println("columnname is:"+columnname+", and column is:"+column);
//		        System.out.println(dataTable.getCell(0, i));
				table.click(atCell(atRow(atIndex(0)), atColumn(atIndex(i))));
				w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
				w.inputChars(value);
				table.click();
				
			}
			}
		}
		DOF.getButton(DOF.getDialog("Default Value"), "OK").click();
		
	}
	
	public void valuefortableOpe()
	{
//		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getallTable(parent)[1];
//		table.getObjectReference();
//		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
//	//	Cell cell=null;
////		IWindow w = RationalTestScript.getScreen().getActiveWindow();
//		for(int i=0;i<dataTable.getRowCount();i++)
//		{
//			if(dataTable.getCell(i, 1).toString().indexOf("[]")>0)
//			{
//			System.out.println(dataTable.getCell(i, 1).toString().substring(dataTable.getCell(i, 1).toString().indexOf("[]")+3));
//			}
//		}
		ScrollGuiSubitemTestObject table=(ScrollGuiSubitemTestObject)DOF.getTable(DOF.getDialog("Default Value"));
		table.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
	//	String columnname=colu.substring(colu.indexOf("[]")+3);
	//	IWindow w = RationalTestScript.getScreen().getActiveWindow();
		String column="";
		int number=-1;
		for(int i=0;i<dataTable.getColumnCount();i++)
		{
	//		System.out.println("Table have column number:"+dataTable.getColumnCount());
			System.out.println(dataTable.getColumnHeader(i).toString());
			if(dataTable.getColumnHeader(i).toString().indexOf("[")>0)
			{
			column=dataTable.getColumnHeader(i).toString().substring(0, dataTable.getColumnHeader(i).toString().indexOf("["));
			System.out.println(column);
			}
		}
	}
	
	public void BAPISearch(String bapi, String method)
	{
		TestObject parent1=DOF.getDialog("Browse Operation");
		DOF.getGuiObjectCloseToGuiObject(parent1,"org.eclipse.swt.widgets.Text",DOF.getButton(parent1, "&Search BAPIs/RFCs"),DOF.RELATIVE_DIRECTION_LEFT).click(atPoint(10,10));
		
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(bapi);
		DOF.getButton(parent1, "&Search BAPIs/RFCs").click();
		sleep(5);
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTable(parent1, "Choose a BAPI or RFC operation for the definition:");
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
		int row=0;
		for(int i=0;i<dataTable.getRowCount();i++)
		{
			if(dataTable.getCell(i, 0).equals(method))
			{
				row=i;
			}
		}
		Cell cell = new Cell(new Row(new Index(row)),
				new Column(new Index(0)));
		table.click(cell);
		DOF.getButton(parent1, "OK").click();
	}
	
	public void selectResultSet(String outtable)
	{
		DOF.getCComboTip(parent, "Select an output table to map attributes:").click(atPoint(510,10));
		DOF.getPoppedUpList().click(atText(outtable));
	}
	
	public void Addbtn_click(String name)
	{
		DOF.getButton(parent, "&Add").click();
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
	
	public void OKbtn_click()
	{
		DOF.getButton(parent, "OK").click();
	}
	
	public void mapAttribute(String name, String type, String mapname)
	{
		addattribute();
		String[] para={"","Name","Datatype","Nullable","Map to","Datatype","Nullable"};
		GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTableByColumnsNames(parent, para);
		table.getObjectReference();
		ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
		int row=0;
		int count=dataTable.getRowCount();
		int i=0;
		
			while(count>0){
			if(dataTable.getCell(i, 1).equals(name))
			{
				row=i;
			}
			count=count-1;
			i++;
		}
		Cell cell = new Cell(new Row(new Index(row)),
				new Column(new Index(1)));
		table.click(cell);
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(name);
		w.inputKeys("{TAB}");
		w.inputChars(type);
		table.click(new Cell(new Row(new Index(row)),
				atColumn("Map to")));
		DOF.getCCombo(parent).click(atPoint(130,10));
		sleep(2);
		WO.setCCombo(DOF.getCCombo(parent), mapname);
		
	}
	
	public void changeopeType(String type)
	{
		DOF.getCombo(parent, "Operation type:").click();
		DOF.getCombo(parent, "Operation type:").click(atText(type));
	}
	
	public void selecttab(String name)
	{
//		DOF.getTabFolder(parent).setState(SINGLE_SELECT, atText(name));
	}
	
	public boolean okbtn_status()
	{
		if(DOF.getButton(parent, "OK").getProperty("enabled").equals("false"))
		{
			return true;
		}
		else
			return false;
	}
	
	public void cancelbtn_click()
	{
		DOF.getButton(parent, "Cancel").click();
	}
	
	public void setcommit(boolean flog)
	{
		ToggleGUITestObject btn=(ToggleGUITestObject)DOF.getButton(parent, "Commit &SAP Operation");
		if(flog==true)
		{			
			btn.clickToState(SELECTED);
		}
		else
		{
			btn.clickToState(NOT_SELECTED);
		}
	}
	
	
	public void checker(String name)
	{
		if(name.equals("None"))
			DOF.getButton(parent, "N&one").click();
		else if(name.equals("Default"))
			DOF.getButton(parent, "Defau&lt").click();
		else
			DOF.getButton(parent, "C&ustom").click();
	}
	
	public void newchecker(String packagename,String checkername)
	{
		DOF.getGuiObjectCloseToGuiObject(parent, "org.eclipse.swt.widgets.Button", DOF.getButton(parent, "Bro&wse..."), DOF.RELATIVE_DIRECTION_LEFT).click();
		try{
			ResultSetFilterDialog rd= new ResultSetFilterDialog();
			rd.confirmJavaNature();
		}
		catch(Exception e)
		{
			
		}
		TestObject parent1=DOF.getDialog("New Java Class");
		if(packagename!=null&packagename!=""){
		TextScrollTestObject tt=(TextScrollTestObject)DOF.getTextField(parent1, "Package:");
		tt.click(atPoint(10,10));
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(packagename);
		}
		if(checkername!=null&checkername!="")
		{
			TextScrollTestObject tt=(TextScrollTestObject)DOF.getTextField(parent1, "Name:");
			tt.click(atPoint(10,10));
			IWindow w = RationalTestScript.getScreen().getActiveWindow();
			w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
			w.inputChars(checkername);
		}		
		DOF.getButton(parent1, "&Finish").click();
	}
	
	
	
	public void selectfilterclass(String filtername)
	{
		TestObject parent1=DOF.getDialog("Select SAP Result  Class");
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
	
	
	public void expandRC()
	{
		DOF.getLabel(parent,"Runtime Data Source Credential and Connection Properties").click();
	}
	
	public void setusername(String user)
	{
		DOF.getCombo(parent, "User name:").click(atPoint(10,10));
		try{
			DOF.getCombo(parent, "User name:").click(atText(user));
		}catch(Exception e){
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(user);
		}
		DOF.getCombo(parent, "User name:").click();
	}
	
	public void setpsw(String psw)
	{
		DOF.getCombo(parent, "Password:").click(atPoint(10,10));
		try{
			DOF.getCombo(parent, "Password:").click(atText(psw));
		}catch(Exception e){
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(psw);
		}
	}
	
	public void setconnpro(String name, String value)
	{
		DOF.getTable(parent, "Connection Properties:").click(atCell(atRow(atIndex(0)), atColumn("Value")), 
              atPoint(106,13));
		sleep(2);
		DOF.getTable(parent, "Connection Properties:").click(atCell(atRow(atIndex(0)), atColumn("Value")), 
	              atPoint(106,13));
		try{
			DOF.getCCombo(parent).click(atPoint(236,7));
			DOF.getPoppedUpList().click(atText(value));
		}
		catch(Exception e)
		{
			IWindow w = RationalTestScript.getScreen().getActiveWindow();
			w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
			w.inputChars(value);
		}
	}
	
	public void backbtn_click()
	{
		DOF.getButton(parent, "< &Back").click();
	}
	
	public void Previewdialog()
	{
		DOF.getButton(parent, "Pre&view...").click();
	}
	
	
	public void setDedaultvalue(String para,String rootnode,String[][] input)
	{
		if(para!=null)
		{
			TestObject parent1=DOF.getDialog("Preview");
			String[] columns={"","Argument Name","Datatype","Nullable","Required","Value"};
			GuiSubitemTestObject table=(GuiSubitemTestObject)DOF.getTableByColumnsNames(parent1, columns);
			table.getObjectReference();
			ITestDataTable dataTable=(ITestDataTable)table.getTestData("contents");
			int row=0;
			int count=dataTable.getRowCount();
			int i=0;
			
				while(count>0){
				if(dataTable.getCell(i, 1).equals(para))
				{
					row=i;
				}
				count=count-1;
				i++;
			}
			Cell cell = new Cell(new Row(new Index(row)),
					atColumn("Value"));
			table.click(cell);
			DOF.getButton(parent1, "...").click();
			
			TestObject parenttmp=DOF.getDialog("Edit Values");
			for(int j=0;j<input[0].length;j++)
			{
				if(!input[1][j].equals(""))
				{
//			DOF.getTree(parenttmp).click(atCell(atRow(atPath("[0]->"+input[0][j])), atColumn("Values")));
			DOF.getTree(parenttmp).click(atCell(atRow(atPath(rootnode+"->"+input[0][j])), atColumn("Values")));
			IWindow w = RationalTestScript.getScreen().getActiveWindow();
			w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
			w.inputChars(input[1][j]);
				}
			}
			sleep(2);
//			saveasdefault(true);
			
			DOF.getButton(parenttmp, "OK").click();
		}
	}
	
	public void saveasdefault(boolean flog)
	{
		TestObject parent1=DOF.getDialog("Preview");
		ToggleGUITestObject btn=(ToggleGUITestObject)DOF.getButton(parent1, "Save as de&fault values");
		if(flog==true)
		{			
			btn.clickToState(SELECTED);
		}
		else
		{
			btn.clickToState(NOT_SELECTED);
		}
		DOF.getButton(parent1, "OK").click();
		
		
	}

	@Override
	public void start(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}
}

