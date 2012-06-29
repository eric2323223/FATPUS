package component.dialog;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

public class MoveTargetSelectionDialog extends RationalTestScript {

	public static void setDestinition(String target) {
		
		TopLevelTestObject dialog = DOF.getDialog("Folder Selection");
		DOF.getTextField(dialog).click();
		dialog.inputKeys(SpecialKeys.CLEARALL);
		dialog.inputChars(target);
		DOF.getButton(dialog, "OK").click();
		sleep(1);
	}

}
