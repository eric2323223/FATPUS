package component.dialog;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

import component.entity.model.WSResponse;

public class RESTWSDefinitionDialog extends RationalTestScript {

	public static void setResponse(WSResponse response,	TopLevelTestObject dialog) {
		DOF.getCombo(dialog).click();
		dialog.inputChars(response.getXsdUri());
		DOF.getCombo(dialog).click();
		DOF.getButton(dialog, "Load &Elements").click();
		sleep(3);
		DOF.getCombo(dialog, "Root element:").click();
		DOF.getCombo(dialog, "Root element:").click(atText(response.getRootElement()));
		sleep(3);
		DOF.getButton(dialog, "OK").click();
	}

}
