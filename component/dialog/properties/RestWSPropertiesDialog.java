package component.dialog.properties;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.entity.model.RestWSCP;
import component.wizard.page.RestWSCPDefinitionPage;

public class RestWSPropertiesDialog extends RationalTestScript{

	public static void setRestWSCP(RestWSCP cp, TopLevelTestObject dialog) {
		DOF.getTree(dialog).click(atPath("Connection Details"));
		if(cp.getResourceBaseUrl()!=null){
			RestWSCPDefinitionPage.setResourceBaseUrl(cp.getResourceBaseUrl(), dialog);
		}
		if(cp.getResourceUriTemplate()!=null){
			DOF.getButton(dialog, "D&eleteAll").click();
			DOF.getButton(DOF.getDialog("Confirm Resource URI template Deleting"), "OK").click();
			RestWSCPDefinitionPage.setResourceUriTemplate(cp.getResourceUriTemplate(), dialog);
		}
	}

	public static void ok(TopLevelTestObject dialog) {
		DOF.getButton(dialog, "OK").click();
	}

	
}
