package component.entity.model;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.entity.OCW;
import component.entity.wizard.IWizard;

public class ODOCW extends OCW {

	@Override
	public void start(String parameter) {
		DOF.getMboFigure(DOF.getObjectDiagramCanvas(), parameter).click(
				RationalTestScript.RIGHT, RationalTestScript.atPoint(5,5) );
		DOF.getContextMenu().click(RationalTestScript.atPath("New->Operation"));
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}
	
	public void setName(String str){
		super.setName(str);
	}
	
	public void setSqlQuery(String str){
		super.setSqlQuery(str);
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
	public IWizard canContinue(boolean b) {
		this.canContinue  = b;
		return this;
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		// TODO Auto-generated method stub
		
	}
	
	

}
