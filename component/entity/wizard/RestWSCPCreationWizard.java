package component.entity.wizard;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import component.entity.AbstractCPCreationWizard;
import component.entity.model.VerificationCriteria;
import component.entity.verifier.Verifier;
import component.wizard.page.RestWSCPDefinitionPage;

public class RestWSCPCreationWizard extends AbstractCPCreationWizard {
	private boolean canGo = true;
	
	@Override
	public boolean canContinue() {
		return canGo;
	}

	@Override
	protected TopLevelTestObject dialog() {
		return DOF.getDialog("New REST Web Service Connection Profile");
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setName")){
			return "verifyName";
		}
		if(operation.equals("setResourceBaseUrl")){
			return "verifyUrl";
		}
		return null;
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setResourceBaseUrl")){
			return 1;
		}
		if(operation.equals("setResourceUriTemplate")){
			return 1;
		}
		throw new RuntimeException("Unable to find operation: "+operation);
	}
	
	
	
	public void verifyName(VerificationCriteria vc){
		Verifier.verify("verifyName", vc.getExpected(), getBannerMessage()).perform();
		if(Verifier.verify("verifyName", vc.getExpected(), getBannerMessage()).isPass()){
			if(!vc.isContinueWhenMatch()){
				this.canGo = false;
			}
		}else{
			this.canGo = false;
		}
	}
	
	public void verifyUrl(VerificationCriteria vc){
		Verifier.verify("verifyUrl", vc.getExpected(), getBannerMessage()).perform();
		if(Verifier.verify("verifyUrl", vc.getExpected(), getBannerMessage()).isPass()){
			if(!vc.isContinueWhenMatch()){
				this.canGo = false;
			}
		}else{
			this.canGo = false;
		}
		
	}
	
	public String getBannerMessage(){
		return DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
	}

	@Override
	public void start(String parameter) {
		DOF.getEETree().click(RIGHT, atPath("REST Web Services"));
		DOF.getContextMenu().click(atPath("New..."));
	}
	
	public void setName(String str){
		DOF.getTextField(dialog(),"Name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setResourceBaseUrl(String str){
		RestWSCPDefinitionPage.setResourceBaseUrl(str, dialog());
	}
	
	public void setResourceUriTemplate(String str){
		RestWSCPDefinitionPage.setResourceUriTemplate(str, dialog());
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}



}
