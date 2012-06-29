package component.entity;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;


import component.entity.model.IWizardEntity;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

public abstract class AbstractCPCreationWizard extends RationalTestScript implements IWizard {
	protected boolean canContinue = true;
	
	protected abstract TopLevelTestObject dialog();


	@Override
	public boolean canContinue() {
		return this.canContinue;
	}
	
	protected void setTextField(String priorLabel, String value){
		DOF.getTextField(dialog(), priorLabel).click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(value);
	}

	@Override
	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}

	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		sleep(1);
	}

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}

	@Override
	public abstract int getPageIndexOfOperation(String operation);

	@Override
	public void next() {
		DOF.getButton(dialog(), "&Next >").click();
	}

	@Override
	public abstract void start(String parameter);

	public void create(IWizardEntity entity, WizardRunner wr){
		wr.run(entity, this);
	}
}
