package component.entity;

import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;
import component.entity.wizard.IWizard;

public class WNLocalMBOCW extends LocalMboCreationWizard {

	@Override
	public void start(String parameter) {
		DOF.getWNTree().click(RationalTestScript.RIGHT, atPath(WN.projectNameWithVersion(parameter)));
		DOF.getContextMenu().click(RationalTestScript.atPath("New->Local Business Object"));
	}
	
	public void setName(String s){
		super.setName(s);
	}
	
	public void setAttributes(String s){
		super.setAttributes(s);
	}

	@Override
	public boolean canContinue() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getDependOperation(String dependOperation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}


}
