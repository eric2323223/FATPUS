package component.dialog;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description   : Functional Test Script
 * @author yangg
 */
public class ResultCheckerDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>ResultCheckerDialog</b>
	 * Generated     : <b>Dec 2, 2010 2:04:33 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/12/02
	 * @author yangg
	 */
	
	TestObject parent=null;
	public void testMain(Object[] args) 
	{
//		System.out.println(DOF.getRadioButton(DOF.getDialog("New Attributes"), "Defau&lt").getProperties());
		this.errormessage();
	}
	
	public void ResultCheckerDialog()
	{
//		parent=DOF.getDialog("New Attributes");
	}
	
	public boolean getselectRadio(String text)
	{
		System.out.println(text);
		System.out.println(DOF.getRadioButton(DOF.getDialog("New Attributes"), text).getProperties());
		if(DOF.getRadioButton(DOF.getDialog("New Attributes"), text).getProperty("selection").toString().equals("true"))
		{
			return true;
		}
		return false;
	}
	
	public String propertiesofradio(String text,String propertyName)
	{
		return DOF.getRadioButton(DOF.getDialog("New Attributes"), text).getProperty(propertyName).toString();
	}
	
	public void checkRadio(String text)
	{
		DOF.getRadioButton(DOF.getDialog("New Attributes"), text).click();
	}
	
	public void OpenRSPanel()
	{
		DOF.getLabel(DOF.getDialog("New Attributes"),"Result Checker").click();
	}
	
	public boolean errormessage()
	{
//		 System.out.println(DOF.getTextField(DOF.getDialog("New Attributes")).getText());
//		 System.out.println(DOF.getTextFieldByText(DOF.getDialog("New Attributes"), " Custom result checker has not been defined.").getProperties());
		 try{
			 DOF.getTextFieldByText(DOF.getDialog("New Attributes"), " Custom result checker has not been defined.");
			 return true;
		 }
		 catch(Exception e){
		 return false;
		 }
	}
	
    public void createbtn()
    {
    	DOF.getButton(DOF.getDialog("New Attributes"), "&Create...").click();
    	try{
    		confirmJavaNature();
    	}catch(Exception e)
    	{
    		
    	}
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
    
    public void createnewChecker(String packagename,String Filtername)
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
}

