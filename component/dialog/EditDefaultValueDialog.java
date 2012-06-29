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
import com.sybase.automation.framework.widget.helper.TreeHelper;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class EditDefaultValueDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>EditDefaultValueDialog</b>
	 * Generated     : <b>Jan 20, 2011 8:33:38 PM</b>
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
	
	public static void setDefaultValue(String str, TopLevelTestObject dialog){
		for(String pair:str.split(":")){
			setSingleDefaultValue(str, dialog);
		}
		DOF.getButton(dialog, "OK").click();
	}
	
	public static void setSingleDefaultValue(String str, TopLevelTestObject dialog){
		String parameter = str.split(",")[0];
		String value = str.split(",")[1];
		String root = TreeHelper.getFirstItem((ScrollGuiSubitemTestObject)editTree(dialog));
		parameter = parameter.replace(parameter.substring(0,parameter.indexOf("->")), root);
		System.out.println(parameter);
		editTree(dialog).click(atCell(atRow(atPath(parameter)), atColumn("Values")));
		dialog.inputChars(value);
	}
	
	private static GuiSubitemTestObject editTree(TopLevelTestObject dialog){
		return DOF.getTree(dialog);
	}

	public static void add(TopLevelTestObject dialog) {
		DOF.getButton(dialog, "&Add").click();
		
	}
	
	public static void addListOfValues(String[] values, TopLevelTestObject dialog){
		for(int i=0;i<values.length;i++){
			addSingleValue(values[i], dialog, i);
		}
//		for(String str:values){
//			addSingleValue(str, dialog);
//		}
		DOF.getButton(dialog, "OK").click();
	}

	private static void addSingleValue(String str, TopLevelTestObject dialog, int index) {
		if(index==0){
			DOF.getTable(dialog).click(atCell(atRow(0), atColumn("Value")));
		}else{
			DOF.getButton(dialog, "&Add").click();
		}
		dialog.inputChars(str);
	}
}

