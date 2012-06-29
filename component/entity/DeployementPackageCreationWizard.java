package component.entity;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import component.dialog.MboSelectionDialog;
import component.entity.model.DeploymentPackage;
import component.entity.model.IWizardEntity;
import component.entity.model.Jar;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.verifier.Verifier;
import component.entity.wizard.IWizard;
import component.entity.wizard.JarCreationWizard;

public class DeployementPackageCreationWizard extends RationalTestScript implements IWizard {
	boolean canContinue = true;

	public void create(DeploymentPackage deploymentPackage,
			WizardRunner wizardRunner) {
		wizardRunner.run(deploymentPackage, this);
		
	}
	
	public void setJar(String str){
		Jar jar = new Jar(str);
		if(jar.getType().equals(Jar.TYPE_NEW)){
			new JarCreationWizard().create(jar, new WizardRunner());
		}else if(jar.getType().equals(Jar.TYPE_EXISTING)){
			
		}else{
			
		}
	}
	
	private TopLevelTestObject dialog(){
		return DOF.getDialog("New Mobile Deployment Package");
	}

	@Override
	public boolean canContinue() {
		return true;
	}

	@Override
	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}
	
	public void setFileName(String str){
		DOF.getTextField(dialog(), "File name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setPackageName(String str){
		DOF.getTextField(dialog(), "Package name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setMbo(String str){
//		if(str.equalsIgnoreCase("all")){
//			TreeHelper.checkNode(DOF.getTree(dialog()), "Mobile Business Objects");
////			DOF.getTree(dialog()).click(RationalTestScript.atPath("Mobile Business Objects->Location(CHECKBOX)"));
//		}else{
//			for(String string:str.split(",")){
//				setSingleMbo("Mobile Business Objects->Default->"+string);
//			}
//		}
		MboSelectionDialog.selectMbo(dialog(), str);
	}
	
//	public void setSingleMbo(String str){
////		DOF.getTree(dialog()).click(RationalTestScript.atPath("Mobile Business Objects->Default->"+str+"->Location(CHECKBOX)"));
//		TreeHelper.exclusiveCheckNode(DOF.getTree(dialog()), str);
//		//TBD
//	}
	
	public void verifyBannerMessage(VerificationCriteria vc){
		String message = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		Verifier.verify("bannerMessage", vc.getExpected(), message).perform();
		if(!Verifier.verify("bannerMessage", vc.getExpected(), message).isPass()&&!vc.isContinueWhenMatch()){
			this.canContinue = false;
		}
	}

	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		RationalTestScript.sleep(2);
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setMbo")){
			return "verifyBannerMessage";
		}
		if(operation.equals("setFileName")){
			return "verifyBannerMessage";
		}
		if(operation.equals("setCheckJars")){
			return "verifyJars";
		}
		return null;
	}
	
	public void setCheckJars(String str){
		
	}
	
	public void verifyJars(VerificationCriteria vc){
		String actual = getProperty("JARs for User-defined Classes");
		System.out.println(actual);
		Verifier.verifyEquals("jar", this, vc, actual).perform();
	}
	

	private String getProperty(String string) {
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(dialog()), "Property", string);
		return TableHelper.getCellValue(DOF.getTable(dialog()), row, "Value");
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setFileName")){
			return 0;
		}
		if(operation.equals("setPackageName")){
			return 1;
		}
		if(operation.equals("setMbo")){
			return 2;
		}
		if(operation.equals("setJar")){
			return 3;
		}
		if(operation.equals("setCheckJars")){
			return 4;
		}
		throw new RuntimeException("Unknown operation: "+operation);
	}

	@Override
	public void next() {
		DOF.getButton(dialog(), "&Next >").click();
	}

	@Override
	public void start(String parameter) {
		DOF.getWNTree().click(RationalTestScript.RIGHT, RationalTestScript.atPath( WN.projectNameWithVersion(parameter)));
		DOF.getContextMenu().click(RationalTestScript.atPath("Create Mobile Deployment Package..."));
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		throw new RuntimeException("It does not make any sense to call this method!!!!");
		
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

}
