package component.entity;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import component.entity.model.CGOption;
import component.entity.model.IWizardEntity;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.wizard.IWizard;

public class CodeGenerationWizard extends RationalTestScript implements	IWizard {
	private boolean canContinue=true;
	
	private TopLevelTestObject dialog(){
		return DOF.getDialog("Generate Code");
	}
	
	public void setPageSize(String size){
		DOF.getCombo(dialog(), "Page size:").click();
		DOF.getCombo(dialog(), "Page size:").click(atText(size));
	}
	
	public void setConfiguration(String str){
		DOF.getButton(dialog(), "Select an &existing configuration").click();
		if(ListHelper.hasItem(DOF.getList(dialog()), str)){
			DOF.getList(dialog()).click(atText(str));
		}else{
			DOF.getTextField(dialog(),"Name:").click();
			dialog().inputKeys(SpecialKeys.CLEARALL);
			dialog().inputChars(str);
		}
	}
	
	public void verifyConfiguration(VerificationCriteria cr){
		boolean hasConfig = ListHelper.hasItem(DOF.getList(dialog()), cr.getExpected());
		if(hasConfig){
			if(!cr.isContinueWhenMatch()){
				this.canContinue = false;
			}
		}else{
			logError("Does not find config "+cr.getExpected());
		}
	}
	
	public void setLanguage(String str){
		DOF.getCombo(dialog(), "Language:").click();
		DOF.getCombo(dialog(), "Language:").click(atText(str));
	}
	
	public void setPlatform(String str){
		DOF.getCombo(dialog(), "Platform:").click();
		DOF.getCombo(dialog(), "Platform:").click(atText(str));

	}
	
	public void setUnwiredServer(String str){
		DOF.getCombo(dialog(), "Unwired server:").click();
		DOF.getCombo(dialog(), "Unwired server:").click(atText(str));
	}
	
	public void setServerDomain(String str){
		DOF.getCombo(dialog(), "Server domain:").click();
		DOF.getCombo(dialog(), "Server domain:").click(atText(str));
	}
	
	public void setPackageName(String str){
		DOF.getTextFieldByToolTip(dialog(),"Specify the package to generate code.").click();
//		DOF.getTextField(dialog(),"Page size:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setProjectPath(String str){
		DOF.getButton(dialog(), "Pro&ject path:").click();
		DOF.getTextFieldByToolTip(dialog(),"Specify project path to generate code to.").click();
//		DOF.getTextField(dialog(),"Project path:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setFileSystemPath(String str){
		DOF.getButton(dialog(), "F&ile system path:").click();
		DOF.getTextFieldByToolTip(dialog(),"Specify file system path to generate code to.").click();
//		DOF.getTextField(dialog(),"File system path:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	 
	public void setCleanUp(String str){
		if(!str.equalsIgnoreCase("false")){
			((ToggleGUITestObject)DOF.getButton(dialog(), "Clean &up destination before code generation")).clickToState(SELECTED);
			DOF.getButton(DOF.getDialog("Confirm"),"&Yes").click();
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog(), "Clean &up destination before code generation")).clickToState(NOT_SELECTED);
			
		}
	}
	
	public void setGenerateMetaData(String str){
		if(!str.equalsIgnoreCase("false")){
			((ToggleGUITestObject)DOF.getButton(dialog(), "Gener&ate metadata classes")).clickToState(SELECTED);
		}else{
			((ToggleGUITestObject)DOF.getButton(dialog(), "Gener&ate metadata classes")).clickToState(NOT_SELECTED);
		}
	}
	
	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		LongOperationMonitor.waitForDialog("Success");
		DOF.getButton(DOF.getDialog("Success"), "&OK").click();
//		DOF.getToolBar(DOF.getRoot(), "Save (Ctrl+S)").click(atToolTipText("Save (Ctrl+S)"));
//		DOF.getMenu().click(atPath("File->Save All"));
		MainMenu.saveAll();
	}
	
	public void setDeleteConfig(String str){
		DOF.getList(dialog()).click(atText(str));
		DOF.getButton(dialog(), "&Delete").click();
		DOF.getButton(DOF.getDialog("Confirm"), "&Yes").click();
	}
	
	private List<String> getAllMbo(){
		String[]nodeArray = TreeHelper.getNodesOfLevel(DOF.getTree(dialog()), 2);
		return arrayToList(nodeArray);
	}
	
	private List<String> arrayToList(String[] array){
		List<String> result = new ArrayList<String>();
		for(String node:array){
			result.add(node);
		}
		return result;
	}
	
	private List<String> getLeftOver(List<String> total, List<String> substract){
		List<String> result = new ArrayList<String>();
		for(String e: total){
			if(!substract.contains(e)){
				result.add(e);
			}
		}
		return result;
	}
	
	public void setSelectMbo(String mbo){
		if(mbo.equalsIgnoreCase("all")){
			TreeHelper.checkNode(DOF.getTree(dialog()), "Mobile Business Objects");
		}else{
			List<String> unselectedMbos = getLeftOver(getAllMbo(), arrayToList(mbo.split(",")));
			for(String m:unselectedMbos){
				uncheckMbo(m);
			}
		}
	}
	
	public void verifySelectMbo(VerificationCriteria vc){
		String text = DOF.getTextFieldByBound(dialog(), new Rectangle(21,30,334,36)).getProperty("text").toString();
		if(text.equals(vc.getExpected())){
			logInfo("Warning message matched.");
			if(!vc.isContinueWhenMatch()){
				this.canContinue = false;
			}
		}else{
			logError("Not match: Actual["+text+"] Expected["+vc.getExpected()+"]");
			this.canContinue = false;
		}
	}
	
	public void verifyHeaderMessage(VerificationCriteria vc){
		verifySelectMbo(vc);
	}
	
	private void uncheckMbo(String mbo){
		String path = "Mobile Business Objects->";
		path = path+TreeHelper.getNodesOfLevel(DOF.getTree(dialog()), 1)[0];
		path = path +"->"+ mbo +"->Location(CHECKBOX)";
		DOF.getTree(dialog()).click(atPath(path));
//		DOF.getTree(dialog()).click(atPath("Mobile Business Objects->Default->Employee->Location(CHECKBOX)"));
	}
	
	public void verifyDeleteConfig(VerificationCriteria vc){
		boolean hasConfig = ListHelper.hasItem(DOF.getList(dialog()), vc.getExpected());
		if(!hasConfig){
			if(!vc.isContinueWhenMatch()){
				this.canContinue = false;
			}
		}else{
			logError("Config ["+vc.getExpected()+"] does not deleted");
		}
	}

	public void setMboType(String str){
		if(str.equals(CGOption.MBS)){
			DOF.getButton(dialog(), "Repli&cation-based").click();
		}else{
			DOF.getButton(dialog(), "&Message-based").click();
		}
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setConfiguration")){
			return 0;
		}
		if(operation.equals("setLanguage")){
			return 2;
		}
		if(operation.equals("setPlatform")){
			return 2;
		}
		if(operation.equals("setMboType")){
			return 2;
		}
		if(operation.equals("setUnwiredServer")){
			return 2;
		}
		if(operation.equals("setServerDomain")){
			return 2;
		}
		if(operation.equals("setPackageName")){
			return 2;
		}
		if(operation.equals("setProjectPath")){
			return 2;
		}
		if(operation.equals("setFileSystemPath")){
			return 2;
		}
		if(operation.equals("setCleanUp")){
			return 2;
		}
		if(operation.equals("setGenerateMetaData")){
			return 2;
		}
		if(operation.equals("setPageSize")){
			return 2;
		}
		if(operation.equals("setNewConfig")){
			return 0;
		}
		if(operation.equals("setExistConfig")){
			return 0;
		}
		if(operation.equals("setDeleteConfig")){
			return 0;
		}
		if(operation.equals("setSelectMbo")){
			return 1;
		}
		
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}

	@Override
	public void next() {
		DOF.getButton(dialog(), "&Next >").click();
	}

	@Override
	public void start(String parameter) {
		DOF.getWNTree().click(RIGHT,atPath(WN.projectNameWithVersion(parameter)));
		DOF.getContextMenu().click(atPath("Generate Code... "));
	}

	public void generate(CGOption option, WizardRunner wr){
		wr.run(option, this);
	}

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}

	@Override
	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setConfiguration")){
			return "verifyConfiguration";
		}
		if(operation.equals("setDeleteConfig")){
			return "verifyDeleteConfig";
		}
		if(operation.equals("setSelectMbo")){
			return "verifySelectMbo";
		}
		if(operation.equals("setFileSystemPath")){
			return "verifyHeaderMessage";
		}
		if(operation.equals("setProjectPath")){
			return "verifyHeaderMessage";
		}
		if(operation.equals("setPackageName")){
			return "verifyHeaderMessage";
		}
		return null;
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		this.generate((CGOption)entity, runner);
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

}
