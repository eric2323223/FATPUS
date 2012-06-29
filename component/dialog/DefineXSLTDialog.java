package component.dialog;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.interfaces.*;

/**
 * Description   : Functional Test Script
 * @author yangg
 */
public class DefineXSLTDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>DefineXSLTDialog</b>
	 * Generated     : <b>Jun 30, 2008 12:31:20 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/06/30
	 * @author yangg
	 */
	public void testMain(Object[] args) 
	{
//		DefineXSLTDialog ss=new DefineXSLTDialog();
//		ss.generateXSLT();
//		getScreen().getActiveWindow().inputChars("hello");
	}
	
	/**
	 * Added for 1.5.3
	 */
	
	TestObject parent=null;
	public DefineXSLTDialog()
	{
		parent=DOF.getDialog("Define XSLT");
	}
	
	public DefineXSLTDialog(String title)
	{
		parent=DOF.getDialog(title);
	}
	
	public static void setXsltName(String name)
	{
		DOF.getTextField(dialog(), "Name").click(atPoint(10,10));
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(name);
	}
	
	private static TopLevelTestObject dialog(){
		return DOF.getDialog("Define XSLT");
	}
	
	public void generateXSLT()
	{
		sleep(2);
		DOF.getRadioButton(parent, "&Generate XSLT from response message elements selection").click();
		sleep(2);
	//	DOF.getButton(parent, "&Generate XSLT from response message elements selection").click();
		DOF.getButton(DOF.getDialog("Confirm XSLT Replace"), "OK").click();
		System.out.println("Check status");
	}
	
	public void definemanually()
	{
		DOF.getButton(parent, "&Define XSLT manually").click();
	}
	
	public void OKbtn()
	{
		DOF.getButton(parent, "OK").click();
	}
	
	public void changenode(String path)
	{
		DOF.getTree(parent).click(atPath(path+"->Location(CHECKBOX)"));
	}

	public static void loadFromFile(String file) {
		DOF.getButton(dialog(), "&Browse...").click();
//		sleep(1);
//		DOF.getDialog("Select a XSLT file").inputChars(file);
//		DOF.getDialog("Select a XSLT file").inputKeys(SpecialKeys.ENTER);
		sleep(1);
		getScreen().getActiveWindow().inputChars(file);
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
	}
	
	
}

