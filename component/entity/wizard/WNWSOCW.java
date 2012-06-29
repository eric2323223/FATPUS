package component.entity.wizard;

import com.sybase.automation.framework.widget.DOF;

import component.entity.WSMBOCreationWizard;

public class WNWSOCW extends WSMBOCreationWizard {
	
	public void start(String parameter){
		if(parameter!=null){
			DOF.getWNTree().doubleClick(atPath(parameter));
			DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
			DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
			DOF.getButton(DOF.getRoot(), "&Add...").click();
		}
		
	
	}

}
