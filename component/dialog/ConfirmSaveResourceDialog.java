package component.dialog;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

public class ConfirmSaveResourceDialog extends RationalTestScript {
	private static TopLevelTestObject dialog(){
		return DOF.getDialog("Save Resource");
		
	}
	
	public static void yes(){
		DOF.getButton(dialog(), "&Yes").click();
	}
	
	public static void no(){
		DOF.getButton(dialog(), "&No").click();
		
	}
}
