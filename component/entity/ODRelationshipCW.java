package component.entity;

import com.sybase.automation.framework.widget.DOF;
import component.entity.wizard.IWizard;

public class ODRelationshipCW extends RelationshipCW {

	@Override
	public void start(String parameter) {
		DOF.getMboFigure(DOF.getObjectDiagramCanvas(), parameter).click(RIGHT);
		DOF.getContextMenu().click(atPath("New->Relationship"));
		sleep(1);
	}

	@Override
	public void setMapping(String mapping) {
		super.setMapping(mapping);
	}

	@Override
	public void setTarget(String str) {
		super.setTarget(str);
	}

	@Override
	public void setType(String type) {
		super.setType(type);
	}
	
	public void setName(String str){
		super.setName(str);
	}
	
	public void setComposite(String str){
		super.setComposite(str);
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

}
