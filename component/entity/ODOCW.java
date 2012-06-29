package component.entity;

import com.sybase.automation.framework.widget.DOF;

import component.entity.model.IWizardEntity;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;
import component.wizard.page.WebServiceDefinitionPage;

public class ODOCW extends OCW {

	@Override
	public void start(String parameter) {
		DOF.getMboFigure(DOF.getRoot(), parameter).click(RIGHT);
		DOF.getContextMenu().click(atPath("New->Operation"));
	}

	@Override
	public void setParameterDefaultValue(String str) {
		super.setParameterDefaultValue(str);
	}

	@Override
	public void verifyParameterDefaultValue(VerificationCriteria vc) {
		super.verifyParameterDefaultValue(vc);
	}

	@Override
	public void setName(String string) {
		super.setName(string);
	}
	
	public void setMethod(String str){
		WebServiceDefinitionPage.setMethod(dialog(), str);
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
	public void setParameterValue(String str) {
		// TODO Auto-generated method stub
		super.setParameterValue(str);
	}

	@Override
	public void setType(String type) {
		super.setType(type);
	}
	
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setDataSource")){
			return 0;
		}
		if(operation.equals("setConnectionProfile")){
			return 0;
		}
		if(operation.equals("setSqlQuery")){
			return 1;
		}
		if(operation.equals("setMethod")){
			return 1;
		}
		if(operation.equals("setXSLT")){
			return 1;
		}
		if(operation.equals("setParameterValue")){
			return 2;
		}
		if(operation.equals("setParameterDefaultValue")){
			return 2;
		}
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}


	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

	public void create(IWizardEntity entity, WizardRunner runner) {
		// TODO Auto-generated method stub
		
	}

}
