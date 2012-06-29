package component.entity;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.model.VerificationCriteria;
import component.entity.wizard.IWizard;

public class PaletteRelationshipCW extends RelationshipCW {

	@Override
	public void start(String parameter) {
		DOF.getPaletteRoot().click(atPath("Mobile Application Diagram->Relationship"));
		GefEditPartTestObject source = DOF.getMboNameFigure(DOF.getObjectDiagramCanvas(), parameter.split(",")[0]);
		GefEditPartTestObject target = DOF.getMboNameFigure(DOF.getObjectDiagramCanvas(), parameter.split(",")[1]);
		source.dragToScreenPoint(atPoint(5,5),target.getScreenPoint(atPoint(5,5)));
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
	
	public void verifyMapping(VerificationCriteria vc){
		super.verifyMapping(vc);
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
