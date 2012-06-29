package component.wizard.page;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;

public class SQLDefinitionPage {

	public static void setSQLDefinition(TopLevelTestObject dialog, String definition) {	
		DOF.getButton(dialog, "SQL quer&y statement").click();
		WO.setTextField(dialog, DOF.getTextFieldByToolTip(dialog, "Input SQL statements here for definition "), definition);
	}

	public static void setSPDefinition(TopLevelTestObject dialog, String definition) {
		DOF.getButton(dialog, "St&ored procedure").click();
		WO.setTextField(dialog, DOF.getTextFieldByToolTip(dialog, "Input SQL statements here for definition "), definition);
	}

	public static void ok(TopLevelTestObject dialog) {
		DOF.getButton(dialog, "OK").click();
	}

}
