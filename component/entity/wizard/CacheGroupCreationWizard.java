package component.entity.wizard;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

import component.dialog.CacheGroupEditDialog;
import component.entity.WN;
import component.entity.model.DbMbo;
import component.entity.model.IWizardEntity;
import component.entity.model.Interval;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.model.CacheGroup;
import component.entity.verifier.Verifier;
import component.entity.wizard.IWizard;

/**
 * Description : Functional Test Script
 * 
 * @author YongLi
 */
public class CacheGroupCreationWizard extends RationalTestScript
		implements IWizard {
	/**
	 * Script Name : <b>CacheGroupCreationWizard</b> Generated : <b>Jan 18, 2011
	 * 12:44:07 AM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2011/01/18
	 * @author YongLi, Eric
	 */
	private boolean canContinue = true;

	public void testMain(Object[] args) {
		this.setType("On demand");
		this.finish();
	}
	
	public void setDefaultCacheGroup(String str){
		if(!str.equals("false")){
			ToggleGUITestObject button = (ToggleGUITestObject)DOF.getButton(dialog(), "Use as defa&ult cache group");
			button.clickToState(SELECTED);
		}
	}
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Cache Group");
	}

	public void setName(String name) {
		DOF.getTextField(DOF.getDialog("New Cache Group"), "Name : ").click();
		DOF.getDialog("New Cache Group").inputKeys(SpecialKeys.CLEARALL);
		DOF.getDialog("New Cache Group").inputChars(name);
	}
	
	public void verifyIntervalStatus(VerificationCriteria vc){
		String enabled = CacheGroupEditDialog.getIntervalStatus(dialog())?"enabled":"disabled";
		Verifier.verify("IntervalStatus", vc.getExpected(), enabled).perform();
		if(!Verifier.verify("IntervalStatus", vc.getExpected(), enabled).isPass()&&
				!vc.isContinueWhenMatch()){
			this.canContinue = false;
		}
	}
	
	public void verifyPartitionStatus(VerificationCriteria vc){
		String enabled = CacheGroupEditDialog.getPartitionStatus(dialog())?"enabled":"disabled";
		Verifier.verify("PartitionStatus", vc.getExpected(), enabled).perform();
		if(!Verifier.verify("PartitionStatus", vc.getExpected(), enabled).isPass()&&
				!vc.isContinueWhenMatch()){
			this.canContinue = false;
		}
	}

	public void setType(String str) {
		CacheGroupEditDialog.setType(dialog(), str);
//		if (toogle.equalsIgnoreCase("On demand")) {
//			DOF.getRadioButton(DOF.getDialog("New Cache Group"),
//					"On de&mand   ").click();
//		} else if (toogle.equalsIgnoreCase("Scheduled")) {
//			DOF.getRadioButton(DOF.getDialog("New Cache Group"),
//					"&Scheduled    ").click();
//		} else if (toogle.equalsIgnoreCase("DCN")) {
//			DOF.getRadioButton(DOF.getDialog("New Cache Group"), "&DCN       ")
//					.click();
//		} else if (toogle.equalsIgnoreCase("Online")) {
//			DOF.getRadioButton(DOF.getDialog("New Cache Group"),
//					"&Online      ").click();
//		}
	}

	public void setHour(String hour) {
		CacheGroupEditDialog.setHour(dialog(), hour);

	}

	public void setMinute(String minute) {
		CacheGroupEditDialog.setMinute(dialog(), minute);
		
	}

	public void setSecond(String second) {
		CacheGroupEditDialog.setSecond(dialog(), second);

	}

	public void setPartition(String toogle) {
		if (toogle.equalsIgnoreCase("Y")){
			((ToggleGUITestObject)DOF.getButton(dialog(), "&Partition by requester and device identity")).clickToState(SELECTED);
//			_PartitionByRequesterAndDevice().clickToState(SELECTED);
		}
		else{
			((ToggleGUITestObject)DOF.getButton(dialog(), "&Partition by requester and device identity")).clickToState(NOT_SELECTED);
//			_PartitionByRequesterAndDevice().clickToState(NOT_SELECTED);
		}
	}

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}

	@Override
	public void cancel() {
		DOF.getButton(DOF.getDialog("New Cache Group"), "Cancel").click();
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		create((CacheGroup) entity, runner);
	}

	public void create(CacheGroup cachegroup, WizardRunner wr) {
		wr.run(cachegroup, this);
	}

	@Override
	public void finish() {
		DOF.getButton(DOF.getDialog("New Cache Group"), "&Finish").click();
	}
	
	public void verifyBannerMessage(VerificationCriteria vc){
		String message = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		
		Verifier.verify("BannerMessageVerification", vc.getExpected(), message).perform();
		if(Verifier.verify("BannerMessageVerification", vc.getExpected(), message).isPass()){
			
		}else{
			this.canContinue = false;
		}
	}
	
	public void verifyPartition(VerificationCriteria vc){
		String status = DOF.getButton(dialog(), "&Partition by requester and device identity").getProperty("enabled").toString();
		Verifier.verify("enabled", vc.getExpected(), status).perform();
		if(Verifier.verify("enabled", vc.getExpected(), status).isPass()&&
				!vc.isContinueWhenMatch()){
			this.canContinue = false;
		}
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setName")){
			return "verifyBannerMessage";
		}
		if(operation.equals("setType")){
			return "verifyBannerMessage,verifyPartition,verifyIntervalStatus,verifyPartitionStatus";
		}
		return null;
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if (operation.equals("setName"))
			return 0;
		if (operation.equals("setType"))
			return 0;
		if (operation.equals("setHour"))
			return 0;
		if (operation.equals("setMinute"))
			return 0;
		if (operation.equals("setSecond"))
			return 0;
		if (operation.equals("setPartition"))
			return 0;
		if (operation.equals("setDefaultCacheGroup"))
			return 0;
		else
			throw new RuntimeException("Unknown operation name: " + operation);
	}

	@Override
	public void next() {
		
	}

	@Override
	public void start(String project) {
		DOF.getWNTree().click(RIGHT,
				atPath(WN.projectNameWithVersion(project) + "->Cache Groups"));
		DOF.getContextMenu().click(atPath("New->Cache Group"));
	}

	@Override
	public IWizard canContinue(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
}
