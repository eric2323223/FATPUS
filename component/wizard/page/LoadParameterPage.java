package component.wizard.page;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import component.entity.DefaultValueTreeHelper;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class LoadParameterPage extends RationalTestScript
{
	/**
	 * Script Name   : <b>LoadParameterPage</b>
	 * Generated     : <b>Jan 20, 2011 6:55:27 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/01/20
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
	}
	
	public static void setParameterDefaultValue(String str, TopLevelTestObject dialog){
		if(str.contains("->")){
			setComplexTypeParameterDafaultValue(str, dialog);
		}else{
			setSimpleParameterDefaultValue(str, dialog);
		}
	}

	private static void setSimpleParameterDefaultValue(String str,
			TopLevelTestObject dialog) {
		String parameter = str.split(",")[0];
		String value = str.split(",")[1];
		DefaultValueTreeHelper.setDefaultValue(parameterTree(dialog), parameter, value);
	}

	public static void setComplexTypeParameterDafaultValue(String str,TopLevelTestObject dialog) {
		String parameter = str.split(",")[0];
		String value = str.split(",")[1];
		String pName = parameter.substring(0, str.indexOf("->"));
		String restPart = parameter.replace(pName, "");
		parameterTree(dialog).click(atCell(atRow(atPath(pName)),atColumn("Default Value")));
		DOF.getButton(dialog, "...").click();
		String root = TreeHelper.getFirstItem((ScrollGuiSubitemTestObject)editTree());
		String newPath = root+restPart;
		editTree().click(atCell(atRow(atPath(newPath)), atColumn("Values")));
		editDialog().inputChars(value);
		DOF.getButton(editDialog(), "OK").click();
	}
	
	private static GuiSubitemTestObject parameterTree(TestObject parent){
		if(DOF.getDualHeadersTree(parent)!=null){
			return DOF.getTree(DOF.getDualHeadersTree(parent));
		}else{
			return DOF.getTree(parent);
		}
	}
	
	private static TopLevelTestObject editDialog(){
		return DOF.getDialog("Edit Values");
	}
	
	private static GuiSubitemTestObject editTree(){
		return DOF.getTree(editDialog());
	}
	
}

