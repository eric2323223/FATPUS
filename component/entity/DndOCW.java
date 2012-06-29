package component.entity;

import component.entity.model.IWizardEntity;
import component.entity.model.Operation;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

public class DndOCW extends OCW {

	@Override
	public void setName(String string) {
		// TODO Auto-generated method stub
		super.setName(string);
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		super.setType(type);
	}

	@Override
	public void start(String parameter) {
		EE.dndOperationAsOperation(parameter, 10, 10);
	}
	
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setSqlQuery")){
			return 0;
		}
		if(operation.equals("setStoredProcedure")){
			return 0;
		}
		if(operation.equals("setParameterDefaultValue")){
			return 0;
		}
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}

	/*
	 * The override set methods is obligatory, because WizardRunner
	 * will looking for method on "this", which is this subclass.
	 *
	*/
	public void setSqlQuery(String string){
		super.setSqlQuery(string);
	}
	
	public void setStoredProcedure(String string){
		super.setStoredProcedure(string);
	}
	
	public void finish(){
		super.finish();
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		runner.run(entity, this);
		
	}

}
