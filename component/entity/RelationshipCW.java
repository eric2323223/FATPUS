package component.entity;

import java.awt.Rectangle;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.MapHelper;
import component.entity.model.IWizardEntity;
import component.entity.model.Relationship;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

public abstract class RelationshipCW extends RationalTestScript implements IWizard{
	protected boolean canContinue = true;
	
	@Override
	public boolean canContinue() {
		return this.canContinue;
	}
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Relationship");
	}
	
	public void setName(String name){
		DOF.getTextFieldByToolTip(dialog(), "This name is used as the relationship's name and also represents the relationship as an attribute in source mobile business object ").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(name);
	}
	
	public void setComposite(String str){
		if(str.equalsIgnoreCase("true")){
			((ToggleGUITestObject)DOF.getButton(dialog(), "Compo&site")).setState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog(), "Compo&site")).setState(NOT_SELECTED);
		}
	}
	
	public void setBidirectional(String str){
		if(str.equalsIgnoreCase("true")){
			((ToggleGUITestObject)DOF.getButton(dialog(), "B&i-directional")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog(), "B&i-directional")).clickToState(NOT_SELECTED);
		}
	}

	@Override
	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}

	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		sleep(1);
		if(DOF.getDialog("Confirm")!=null){
			DOF.getButton(DOF.getDialog("Confirm"), "&Yes").click();
			sleep(1);
		}
		MainMenu.saveAll();
	}

	@Override
	public String getDependOperation(String dependOperation) {
		if(dependOperation.equals("setMapping")){
			return "verifyMapping";
		}
		return null;
	}
	
	public void setTarget(String str){
		DOF.getCCombo(dialog(),"Target object:").click();
		DOF.getPoppedUpList().click(atText(str));
		sleep(2);
	}
	
	public void setType(String type){
		if(type.equals(Relationship.TYPE_MTO)){
			DOF.getButton(dialog(), "&Many to one").click();
		}else if(type.equals(Relationship.TYPE_OTM)){
			DOF.getButton(dialog(), "&One to many     ").click();
		}else{
			DOF.getButton(dialog(), "O&ne to one").click();
		}
	}
	
	public void setMapping(String mapping){
		if(mapping.contains(":")){
			String[] map = mapping.split(":");
			for(String m:map){
				setSingleMapping(m);
			}
		}else{
			setSingleMapping(mapping);
		}
	}
	
	public void verifyMapping(VerificationCriteria vc){
		TestObject errorDialog = DOF.getDialog("Error");
		if(errorDialog!=null){
			String actual = DOF.getLabel(DOF.getDialog("Error")).invoke("getText").toString();
			if(!vc.getExpected().equals(actual)){
				logError("Expected: "+vc.getExpected()+"\tActual: "+actual);
			}else{
				if(!vc.isContinueWhenMatch()){
					this.canContinue = false;
				}
			}
			DOF.getButton(DOF.getDialog("Error"), "OK").click();
		}else{
			String message = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
//			String message = DOF.getTextFieldByBound(dialog(), new Rectangle(10,30,571,36)).getProperty("text").toString();
			if(!message.equals(vc.getExpected())){
				logError("Expected: "+vc.getExpected()+"\tActual: "+message);
			}else{
				if(!vc.isContinueWhenMatch()){
					this.canContinue = false;
				}
			}
		}
	}
	
	private void setSingleMapping(String mapping){
		String[] map = mapping.split(",");
		MapHelper.map(DOF.getMapperFigureContorol(dialog()), map[0], map[1]);
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		return 0;
	}

	@Override
	public void next() {
		//only one page
	}

	@Override
	public abstract void start(String parameter) ;
	
	public void create(IWizardEntity entity, WizardRunner wr){
		wr.run(entity, this);
	}
	

}
