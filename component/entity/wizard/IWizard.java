package component.entity.wizard;

import component.entity.model.IWizardEntity;
import component.entity.model.WizardRunner;

public interface IWizard {

	public abstract void next();

	public abstract void finish();

	public abstract int getPageIndexOfOperation(String operation);

	public abstract void start(String parameter);

	public abstract String getDependOperation(String dependOperation);

	public abstract boolean canContinue();
	
	public abstract IWizard canContinue(boolean b);

	public abstract void cancel();
	
	//updated
	public abstract void create(IWizardEntity entity, WizardRunner runner);
}