package component.wizard.page;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.dialog.DefineXSLTDialog;

public class WebServiceDefinitionPage extends RationalTestScript{

	private static void setSingleXslt(String file, TopLevelTestObject dialog) {
		DOF.getButton(dialog, "&Add...").click();
		DefineXSLTDialog.loadFromFile(file);
		DOF.getButton(DOF.getDialog("Define XSLT"), "OK").click();
	}

	public static void setXslt(String file, TopLevelTestObject dialog){
		DOF.getButton(dialog, "Delete A&ll").click();
		DOF.getButton(DOF.getDialog("Confirm Delete"), "OK").click();
		for(String f:file.split("\\|")){
			setSingleXslt(f, dialog);
		}
	}
	
	public static void setMethod(TopLevelTestObject dialog, String str) {
		DOF.getCombo(dialog, "Method:").click();
		DOF.getCombo(dialog, "Method:").click(atText(str));
		
	}

	public static void setPassword(TopLevelTestObject dialog, String str) {
		DOF.getCombo(dialog, "Password:").click(atPoint(20,10));
		sleep(1);
		DOF.getCombo(dialog, "Password:").click();
//		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(str);
		((GuiTestObject)DOF.getTextFieldByAncestorLine(dialog, "Composite->Shell->Shell")).click();
		
	}

	public static void setUserName(TopLevelTestObject dialog, String str) {
		// TODO Auto-generated method stub((ToggleGUITestObject)DOF.getButton(dialog(), "HTTP Basic Aut&hentication:")).clickToState(SELECTED);
		DOF.getCombo(dialog, "User name:").click(atPoint(20,10));
		sleep(1);
		DOF.getCombo(dialog, "User name:").click(atPoint(20,10));
//		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(str);
	}
	
}
