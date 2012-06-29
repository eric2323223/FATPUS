package component.dialog;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.interfaces.IButton;
import com.sybase.automation.framework.widget.interfaces.ILabel;
import com.sybase.automation.framework.widget.interfaces.ITree;
import component.entity.model.PK;

/**
 * Description   : Functional Test Script
 * @author yongliu
 */
public class PKPropertiesDialog extends RationalTestScript
{
	/**
	 * Script Name   : <b>PKPropertiesDialog</b>
	 * Generated     : <b>May 28, 2008 10:56:53 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2008/05/28
	 * @author yongliu
	 */
	
	public static void setPK(TopLevelTestObject dialog, PK key) {
		if(key.getDefaultValue()!=null){
			setDefaultValue(dialog, key.getDefaultValue());
		}
		////////8.8 fanfei  add code:>>>>>>>>>>>>
		if(key.getName()!=null){
			setPkname(dialog, key.getName());
		}
		if(key.getType()!=null){
			setPktype(dialog, key.getType());
		}
		//<<<<<<</8.8 fanfei  add code:
		ok(dialog);
	}
	
	private static void setDefaultValue(TopLevelTestObject dialog, String defaultValue) {
		if (defaultValue.contains("->")) {
			DOF.getButton(dialog, "...").click();
			EditDefaultValueDialog.setDefaultValue(defaultValue, DOF
					.getDialog("Edit Values"));
		}
		else{
			DOF.getTextField(dialog, "Default value(s):").click();
			dialog.inputChars(defaultValue);
		}
	}
	public static void ok(TopLevelTestObject dialog){
		DOF.getButton(dialog, "OK").click();
	}
	//8.8 fanfei add code>>>>>>>>>>>>>>>>:
	private static void setPkname(TopLevelTestObject dialog,String name){
		DOF.getTextField(dialog,"Name:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(name);
	}
	private static void setPktype(TopLevelTestObject dialog, String type) {
		DOF.getCombo(dialog, "Type:").click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(type);
		DOF.getCombo(dialog, "Type:").click();;
	
	}
	//<<<<<<<<<<<<<<<<<<<<<<8.8 fanfei add code
}

