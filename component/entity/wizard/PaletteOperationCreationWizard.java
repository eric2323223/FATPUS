package component.entity.wizard;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.sybase.automation.framework.widget.DOF;

import component.entity.ACW;
import component.entity.OCW;
import component.entity.model.IWizardEntity;
import component.entity.model.WizardRunner;

public class PaletteOperationCreationWizard extends OCW{
	
	@Override
	public void start(String parameter) {
		DOF.getPaletteRoot().click(atPath("Mobile Application Diagram->Operation"));
		GefEditPartTestObject source = DOF.getMboNameFigure(DOF.getObjectDiagramCanvas(), parameter);
		source.click();
	}
	
	@Override
	public void setConnectionProfile(String str) {
		// TODO Auto-generated method stub
		super.setConnectionProfile(str);
	}

	@Override
	public void setDataSource(String str) {
		// TODO Auto-generated method stub
		super.setDataSource(str);
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
		this.canContinue = b;
		return this;
	}
	
}
