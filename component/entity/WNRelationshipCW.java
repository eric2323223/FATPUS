package component.entity;

import com.sybase.automation.framework.widget.DOF;
import component.entity.model.VerificationCriteria;
import component.entity.wizard.IWizard;

public class WNRelationshipCW extends RelationshipCW {

	@Override
	public void setMapping(String mapping) {
		super.setMapping(mapping);
	}

	@Override
	public void setTarget(String str) {
		super.setTarget(str);
	}

	@Override
	public void setBidirectional(String str) {
		super.setBidirectional(str);
	}

	@Override
	public void setType(String type) {
		super.setType(type);
	}
	
	public void verifyMapping(VerificationCriteria vc){
		super.verifyMapping(vc);
	}
	
	public String getDependOperation(String operation){
		return super.getDependOperation(operation);
	}

	@Override
	public void setComposite(String str) {
		super.setComposite(str);
	}

	@Override
	public void start(String parameter) {
		DOF.getWNTree().doubleClick(atPath(parameter));
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		DOF.gettabbedlistElement(DOF.getRoot(), "3").click();
		DOF.getButton(DOF.getRoot(), "A&dd").click();
	}
	
	public void setName(String name){
		super.setName(name);
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

}
