package component.entity;

import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import component.entity.model.WFAuthentication;

public class WFProperties extends MBOProperties {

	public WFProperties(String projectName, String wf) {
		super(projectName, wf);
	}
	
	public void selectWorkFlow(){
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		DOF.getWNTree().doubleClick(atPath(WN.wfPath(project, mbo)));
		
	}

	public void setAuthentication(WFAuthentication auth) {
		selectWorkFlow();
		openFlowDesignTab();
		PropertiesTabHelper.clickTabName("Authentication");
		if(auth.getType().equals(WFAuthentication.TYPE_STATIC)){
			((ToggleGUITestObject)DOF.getButton(DOF.getRoot(), "Use static credentials")).clickToState(SELECTED);
			if(auth.getSupCPAuthentication().equals("true")){
				DOF.getButton(DOF.getRoot(), "Use SUP Server connection profile authentication").click();
			}
		}
		MainMenu.saveAll();
	}

	private void openFlowDesignTab() {
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
	}
	
	

}
