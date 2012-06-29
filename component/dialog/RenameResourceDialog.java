package component.dialog;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

public class RenameResourceDialog extends RationalTestScript {

	public static void setName(String to) {
		DOF.getTextField(dialog()).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(to);
		DOF.getButton(dialog(), "OK").click();
		sleep(3);
	}
	
	public static TopLevelTestObject dialog(){
		return DOF.getDialog("Rename Resource");
	}

}
