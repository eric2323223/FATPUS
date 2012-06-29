package component.entity;

import com.sybase.automation.framework.widget.DOF;
import component.entity.model.IWizardEntity;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

public class WNOCW extends OCW {
	protected boolean canContintue = true;

	@Override
	public void start(String parameter) {
		if(parameter!=null){
			DOF.getWNTree().doubleClick(atPath(parameter));
			DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
			DOF.gettabbedlistElement(DOF.getRoot(), "2").click();
			DOF.getButton(DOF.getRoot(), "&Add...").click();
		}
		
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}

	@Override
	public void setName(String string) {
		super.setName(string);
	}

	@Override
	public void setParameterValue(String str) {
		super.setParameterValue(str);
	}

	@Override
	public void setParameterDefaultValue(String str) {
		super.setParameterDefaultValue(str);
	}

	@Override
	public void setSqlQuery(String text) {
		super.setSqlQuery(text);
	}

	@Override
	public void setStoredProcedure(String text) {
		super.setStoredProcedure(text);
	}

	@Override
	public void setType(String type) {
		super.setType(type);
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		runner.run(entity, this);
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContintue = b;
		return this;
	}
	
	

}
