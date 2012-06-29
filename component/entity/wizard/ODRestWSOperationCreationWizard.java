package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;

import component.entity.WN;
import component.entity.model.RestWSOperation;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.verifier.Verifier;

public class ODRestWSOperationCreationWizard extends RestWSMboCreationWizard{

	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Operation");
	}
	
	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName"))
			return 0;
		if(operation.equals("setType"))
			return 0;
		if(operation.equals("setConnectionProfile"))
			return 0;
		if(operation.equals("setDataSourceType"))
			return 0;
		if(operation.equals("setResourceBaseUrl"))
			return 1;
		if(operation.equals("setResourceUriTemplate"))
			return 1;
		if(operation.equals("setHttpMethod"))
			return 1;
		if(operation.equals("setExpectedStatusCode"))
			return 1;
		if(operation.equals("setResponse"))
			return 1;
		if(operation.equals("setRequest"))
			return 1;
		if(operation.equals("setUserName"))
			return 1;
		if(operation.equals("setPassword"))
			return 1;
		if(operation.equals("setHeader"))
			return 1;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	}
	
	public void setType(String str){
		DOF.getCombo(dialog(), "Operation type:").click();
		DOF.getCombo(dialog(), "Operation type:").click(atText(str));
		
	}
	
	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setRequest")){
			return "verifyRequest";
		}
		return super.getDependOperation(operation);
	}
	
	public void verifyRequest(VerificationCriteria vc){
		String actual = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		Verifier.verifyEquals("vpRequest", this, vc, actual).perform();
	}

	public void setName(String str){
		DOF.getTextField(dialog(),"Operation name:").click();
		dialog().inputChars(str);
	}
	
	public void setRequest(String str){
		super.setRequest(str);
	}

	@Override
	public void setAttribute(String str) {
		super.setAttribute(str);
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
	public void setExpectedStatusCode(String str) {
		super.setExpectedStatusCode(str);
	}

	@Override
	public void setHeader(String str) {
		super.setHeader(str);
	}

	@Override
	public void setHttpMethod(String str) {
		super.setHttpMethod(str);
	}

	@Override
	public void setPassword(String str) {
		super.setPassword(str);
	}

	@Override
	public void setProjectName(String string) {
		super.setProjectName(string);
	}

	@Override
	public void setResourceBaseUrl(String str) {
		super.setResourceBaseUrl(str);
	}

	@Override
	public void setResourceUriTemplate(String str) {
		super.setResourceUriTemplate(str);
	}

	@Override
	public void setResponse(String str) {
		super.setResponse(str);
	}

	@Override
	public void setSqlQuery(String string) {
		super.setSqlQuery(string);
	}

	@Override
	public void setStoredProcedure(String text) {
		super.setStoredProcedure(text);
	}

	@Override
	public void setUserName(String str) {
		super.setUserName(str);
	}

	public void create(RestWSOperation op, WizardRunner wr) {
		new WizardRunner().run(op, this);
	}
	
	public void start(String str){
		DOF.getWNTree().doubleClick(atPath(str));
		String mbo=str.split("->")[2];
		DOF.getMboNameFigure(DOF.getObjectDiagramCanvas(), mbo).click(RIGHT);
		DOF.getContextMenu().click(atPath("New->Operation"));
	}

}
