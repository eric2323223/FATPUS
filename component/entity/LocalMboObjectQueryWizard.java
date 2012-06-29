package component.entity;

import com.sybase.automation.framework.widget.DOF;
import component.entity.wizard.ObjectQueryWizard;

public class LocalMboObjectQueryWizard extends ObjectQueryWizard {
	@Override
	public void start(String parameter) {
		DOF.getWNTree().doubleClick(atPath(parameter));
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		DOF.gettabbedlistElement(DOF.getRoot(), "1").click();
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Object Queries"));
		DOF.getButton(DOF.getRoot(), "&Add...").click();
	}

	@Override
	public void setCreateIndex(String str) {
		// TODO Auto-generated method stub
		super.setCreateIndex(str);
	}

	@Override
	public void setName(String str) {
		// TODO Auto-generated method stub
		super.setName(str);
	}

	@Override
	public void setParameter(String str) {
		// TODO Auto-generated method stub
		super.setParameter(str);
	}

	@Override
	public void setQueryDefinition(String string) {
		// TODO Auto-generated method stub
		super.setQueryDefinition(string);
	}

	@Override
	public void setReturnType(String str) {
		// TODO Auto-generated method stub
		super.setReturnType(str);
	}
	
	

}
