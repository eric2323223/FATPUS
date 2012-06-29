package component.dialog.properties;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.wizard.page.ServerConnectionMappingPage;

public class DeploymentPackageConfigurationDialog extends RationalTestScript{

	public static void setSeverConnectionMapping(
			String mapping, TopLevelTestObject dialog) {
		DOF.getTree(dialog).click(atPath("Server Connection Mapping"));
		sleep(3);
		for(String item:mapping.split(":")){
			ServerConnectionMappingPage.setSingleServerConnectionMapping(mapping, dialog);
		}
	}

	public static void ok(TopLevelTestObject dialog) {
		DOF.getButton(dialog, "OK").click();
	}
	
	

}
