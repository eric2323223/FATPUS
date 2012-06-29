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
public class PropertiesOfCP extends RationalTestScript
{
	/**
	 * Script Name   : <b>PropertiesOfCP</b>
	 * Generated     : <b>Aug 2, 2010 7:15:04 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/08/02
	 * @author yangg
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	TestObject parent=null;
	public PropertiesOfCP(String cpname)
	{
		parent=DOF.getDialog("Properties for "+cpname);
	}
	
	public void selectleft(String item)
	{
		DOF.getTree(parent).click(atPath(item));
	}
	/*
	 * Common Page
	 */
	public void setname(String newname)
	{
		DOF.getTextField(parent, "Name").click(atPoint(10,10));
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(newname);
	}
	
	public void setDescription(String description)
	{
		DOF.getTextField(parent, "Description (optional):").click(atPoint(10,10));
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(description);
	}
	
	public void autocon(boolean flag)
	{
		ToggleGUITestObject ac=(ToggleGUITestObject)DOF.getButton(parent, "Connect every time the workbench is &started");
		if(flag)
		{
			ac.clickToState(SELECTED);
		}
		else
		{
			ac.clickToState(NOT_SELECTED);
		}
	}
	/*
	 * Default Filter Page of properties for CP
	 */
	
	public void BOFilterenabled(boolean flog)
	{
		ToggleGUITestObject df=(ToggleGUITestObject)DOF.getButton(parent, "D&isable filter");
		if(flog)
		{
			df.clickToState(SELECTED);
		}
		else
		{
			df.clickToState(NOT_SELECTED);
		}
	}
	
	
	public void Selectfiltername(String item)
	{
		DOF.getCombo(parent, "Name").click();
		DOF.getCombo(parent, "Name").click(atText(item));
	}
	
	public void setKeyword(String keyword)
	{
		TextScrollTestObject tt=(TextScrollTestObject)DOF.getGuiObjectCloseToGuiObject(parent, "org.eclipse.swt.widgets.Text", DOF.getCombo(parent, "Name"), DOF.RELATIVE_DIRECTION_RIGHT);
//		TextScrollTestObject tt=(TextScrollTestObject)DOF.getTextFieldByIndex(parent,1);
		tt.click(atPoint(10,10));
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(keyword);
	}
	
	/**
	 * Page of Web Service
	 */
	
	public void from(String from, String content)
	{
		if(from.equals("L"))
		{
			DOF.getRadioButton(parent, "From &local file").click();
			
		}
		else if(from.equals("W"))
		{
			DOF.getRadioButton(parent, "From &Workspace").click();
		}
		else{
			DOF.getRadioButton(parent, "From &URL").click();
		}
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{TAB}");
		sleep(1);
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(content);
	}
	
	
	public void Applybtn_click()
	{
		DOF.getButton(parent,"&Apply").click();
	}
	
	public void OKbtn_click()
	{
		DOF.getButton(parent, "OK").click();
	}
	
	/**
	 * Properties of SAP
	 */
	
	public void selTab(String item)
	{
		DOF.getTabFolder(parent, "&Advanced Properties").setState(SINGLE_SELECT, atText(item));
	}
	
	public void defaultcodechebox(boolean flag)
	{
		ToggleGUITestObject df=(ToggleGUITestObject)DOF.getButton(parent, "&Default code page");
		if(flag)
		{
			df.clickToState(SELECTED);
		}
		else
		{
			df.clickToState(NOT_SELECTED);
		}
	}
	
	public void setcodepage(String value)
	{
		DOF.getTextField(parent, "Code page number").click();
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(value);
	}
	
	
	public void setlanguage(String value)
	{
		DOF.getCombo(parent, "Language").click();
		
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(value);
		DOF.getTextField(parent, "Code page number").click();
	}
	
	public void testConn()
	{
		DOF.getButton(parent, "&Test Connection").click();
	}
	
	public boolean testresult()
	{
		
		try{
			DOF.getButton(DOF.getDialog("Error"), "OK").click();
			return false;
		}
		catch(Exception e)
		{
			DOF.getButton(DOF.getDialog("Success"), "OK").click();
			return true;
		}
	}
	
	public void cancel()
	{
		DOF.getButton(parent, "Cancel").click();
	}
	
	public boolean errormessage(String tips)
	{
		try{
		DOF.getTextFieldByToolTip(parent, tips).click();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}

