package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;
import component.entity.model.JavaClass;
import component.entity.model.VerificationCriteria;
import component.entity.model.WSMBO;
import component.entity.model.WizardRunner;
import component.entity.verifier.Verifier;
import component.wizard.page.AttributesMappingPage;
import component.wizard.page.LoadParameterPage;
import component.wizard.page.ResultCheckerDefinitionPage;
import component.wizard.page.ResultFilterDefinitionPage;
import component.wizard.page.WebServiceDefinitionPage;

public class WSMBOCreationWizard extends ACW{
	protected String javaNature;
	private String javaNaturePrompt = "no";
	private VerificationCriteria verifyNewResultChecker = null;
	
	public void setJavaNature(String str){
		this.javaNature = str;
	}

	public void create(WSMBO mbo, WizardRunner wizardRunner) {
		wizardRunner.run(mbo, this);
	}
	
	public void setResultChecker(String str){
		ResultCheckerDefinitionPage.setResultChecker(str, dialog());
		this.javaNaturePrompt = ResultCheckerDefinitionPage.getJavaNaturePrompt();
	}
	
	public void setNewResultChecker(String str){
		DOF.getLabel(dialog(), "Result Checker").click();
		JavaClass klass = new JavaClass(str);
		DOF.getButton(dialog(), "C&ustom").click();
		DOF.getButton(dialog(), "&Create...").click();
		sleep(1);
		if(DOF.getDialog("Add Java Nature")!=null){
			javaNaturePrompt = "yes";
			if(this.javaNature!=null && this.javaNature.equalsIgnoreCase("false")){
				DOF.getButton(DOF.getDialog("Add Java Nature"), "&No").click();
			}else{
				DOF.getButton(DOF.getDialog("Add Java Nature"), "&Yes").click();
			}
		}
		TopLevelTestObject dialog = DOF.getDialog("New Java Class");
		if(klass.getSourceFolder()!=null){
			DOF.getTextField(dialog, "Source folder:").click();
			dialog.inputKeys(SpecialKeys.CLEARALL);
			dialog.inputChars(klass.getSourceFolder());
		}
		DOF.getTextField(dialog, "Name:").click();
		dialog.inputChars(klass.getName());
		if(this.verifyNewResultChecker!=null){
			String actual = DOF.getTextFieldByAncestorLine(dialog, "Composite->Shell->Shell").getProperty("text").toString();
			Verifier.verify("javaNature", this.verifyNewResultChecker.getExpected(), actual).perform();
			if(Verifier.verify("javaNature", this.verifyNewResultChecker.getExpected(), actual).isPass()){
				if(!this.verifyNewResultChecker.isContinueWhenMatch()){
					DOF.getButton(dialog, "Cancel").click();
					this.canContinue = false;
				}
			}else{
				DOF.getButton(dialog, "Cancel").click();
				this.canContinue = false;
				
			}
		}else{
			DOF.getButton(dialog, "&Finish").click();
			sleep(3);
		}
	}
	
	public void setExistingResultChecker(String str){
		DOF.getLabel(dialog(), "Result Checker").click();
		DOF.getButton(dialog(), "C&ustom").click();
		DOF.getButton(dialog(), "Bro&wse...").click();
		TopLevelTestObject dialog = DOF.getDialog("Select Web Service Result Checker Class");
		dialog.inputChars(str);
		sleep(1);
		dialog.inputKeys(SpecialKeys.DOWN);
		dialog.inputKeys(SpecialKeys.ENTER);
	}
	
	public void setParameter(String str){
		for(String para:str.split(":")){
			setSingleParameter(para);
		}
	}
	
	public void setSingleParameter(String str){
		String[] pair = str.split(",");
		GuiSubitemTestObject tree = DOF.getTree(DOF.getDualHeadersTree(dialog()));
		if (!pair[0].contains("->")) {
			TreeHelper.setTextCellValue(tree, pair[0], "Default Value", pair[1]);
		}else{
			String complextPara = pair[0].substring(0, pair[0].indexOf("->"));
			String dataType = TreeHelper.getCellValue(tree, complextPara, "Datatype");
			tree.click(atCell(atRow(atPath(complextPara)), atColumn("Default Value")));
			sleep(1);
			DOF.getButton(tree, "...").click();
			TopLevelTestObject dialog = DOF.getDialog("Edit Values");
			GuiSubitemTestObject tree2 = DOF.getTree(dialog);
			String path = pair[0].replace(complextPara, dataType);
			TreeHelper.setTextCellValue(tree2, path, "Values", pair[1]);
			DOF.getButton(dialog, "OK").click();
		}
	}
	
	public void verifyJavaNaturePrompt(VerificationCriteria vc){
		if(vc.getExpected().equalsIgnoreCase(javaNaturePrompt)){
			if(!vc.isContinueWhenMatch()){
				canContinue = false;
			}
		}else{
			logError("Java Nature Prompt verification failed!\nExpected:["+vc.getExpected()+"]\tActual:["+javaNaturePrompt+"]");
		}
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setNewResultChecker")){
			return "verifyJavaNaturePrompt";
		}
//		if(operation.equals("setName")){
//			return "verifyNewResultChecker";
//		}
		return null;
	}
	
	public void verifyNewResultChecker(VerificationCriteria vc){
		this.verifyNewResultChecker  = vc;
	}
	
	public void setMethod(String str){
		WebServiceDefinitionPage.setMethod(dialog(), str);

	}
	
	public void setUserName(String str){
		WebServiceDefinitionPage.setUserName(dialog(), str);
	}
	
	public void setResultFilter(String str){
		ResultFilterDefinitionPage.setResultFilter(str, dialog());
	}
	
	public void setPassword(String str){
		WebServiceDefinitionPage.setPassword(dialog(), str);
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setDataSourceType")){
			return 1;
		}
		if(operation.equals("setConnectionProfile")){
			return 1;
		}
		if(operation.equals("setMethod")){
			return 2;
		}
		if(operation.equals("setUserName")){
			return 2;
		}
		if(operation.equals("setPassword")){
			return 2;
		}
		if(operation.equals("setXslt")){
			return 2;
		}
		if(operation.equals("setResultChecker")){
			return 2;
		}
		if(operation.equals("setResultFilter")){
			return 2;
		}
		if(operation.equals("setNewResultChecker")){
			return 2;
		}
		if(operation.equals("setJavaNature")){
			return 2;
		}
		if(operation.equals("setExistingResultChecker")){
			return 2;
		}
		if(operation.equals("setParameter")){
			return 3;
		}
		if(operation.equals("setParameterValue")){
			return 3;
		}
		if(operation.equals("setAttributeName")){
			return 4;
		}
		return 0;
	}
	
	public void setAttributeName(String str){
		String original = str.split(",")[0];
		String newName = str.split(",")[1];
		AttributesMappingPage.setAttributeName(dialog(), original, newName);
	}
	
	public void setName(String str){
		super.setName(str);
	}
	
	public void setXslt(String file){
		WebServiceDefinitionPage.setXslt(file, dialog());
	}
	
	public void setParameterValue(String str){
		for(String value:str.split(":")){
			setSingleParameterValue(value);
		}
	}
	
	private void setSingleParameterValue(String str){
		LoadParameterPage.setParameterDefaultValue(str, dialog());
	}


	@Override
	public void setConnectionProfile(String string) {
		super.setConnectionProfile(string);
	}



	@Override
	public void setDataSourceType(String string) {
		super.setDataSourceType(string);
	}

	@Override
	public void start(String parameter) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(parameter)));
		DOF.getContextMenu().click(atPath("New->Mobile Business Object"));
	}

}
