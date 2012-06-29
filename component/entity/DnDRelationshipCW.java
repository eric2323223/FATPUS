package component.entity;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.wizard.IWizard;

public class DnDRelationshipCW extends RelationshipCW {

	@Override
	public void start(String parameter) {
		GefEditPartTestObject sourceAttr = DOF.getMboAttributeFigure(DOF.getMboFigure(DOF.getRoot(), getSourceMbo(parameter)), getSourceAttribute(parameter));
		GefEditPartTestObject targetAttr = DOF.getMboAttributeFigure(DOF.getMboFigure(DOF.getRoot(), getTargetMbo(parameter)), getTargetAttribute(parameter));
		OD.unhighlightAllMbo();
		sourceAttr.click();
		sleep(1);
		sourceAttr.click();
		sleep(1);
		sourceAttr.dragToScreenPoint(atPoint(5,5),targetAttr.getScreenPoint(atPoint(5,5)));
	}
	
	@Override
	public void setComposite(String str) {
		super.setComposite(str);
	}

	@Override
	public void setMapping(String mapping) {
		super.setMapping(mapping);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public void setTarget(String str) {
		super.setTarget(str);
	}

	@Override
	public void setType(String type) {
		super.setType(type);
	}

	protected String getTargetAttribute(String parameter) {
//		System.out.println( targetPart(parameter).split("\\[")[1].replace("]",""));
		return targetPart(parameter).split("\\[")[1].replace("]","");
	}

	protected String targetPart(String parameter) {
		if(parameter.contains(",")){
			return parameter.split(",")[1];
		}else{
			throw new RuntimeException(errorMessage());
		}
	}

	protected String getSourceAttribute(String parameter) {
		return sourcePart(parameter).split("\\[")[1].replace("]","");

	}

	protected String getSourceMbo(String parameter ){
		return sourcePart(parameter).split("\\[")[0];
	}
	
	protected String sourcePart(String parameter) {
		if(parameter.contains(",")){
			return parameter.split(",")[0];
		}else{
			throw new RuntimeException(errorMessage());
		}
	}

	protected String getTargetMbo(String parameter){
//		System.out.println( targetPart(parameter).split("\\[")[0]);
		return targetPart(parameter).split("\\[")[0];
	}
	
	protected String errorMessage(){
		return "DndRelationshipCW should start with parameter looks like this: \"Department[dept_id],Employee[dept_id]\"";
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue  = b;
		return this;
	}
	 
}
