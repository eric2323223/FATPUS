package component.entity.wizard;

import com.sybase.automation.framework.widget.DOF;
import component.entity.WN;
import component.entity.WSMBOCreationWizard;
import component.entity.model.VerificationCriteria;
import component.entity.verifier.Verifier;
import component.wizard.page.LoadParameterPage;
import component.wizard.page.RestWSDefinitionPage;

public class RestWSMboCreationWizard extends WSMBOCreationWizard implements IWizard {

	

	@Override
	public void setResultFilter(String str) {
		// TODO Auto-generated method stub
		super.setResultFilter(str);
	}

	@Override
	public void setExistingResultChecker(String str) {
		// TODO Auto-generated method stub
		super.setExistingResultChecker(str);
	}

	@Override
	public void setJavaNature(String str) {
		// TODO Auto-generated method stub
		super.setJavaNature(str);
	}

	@Override
	public void setNewResultChecker(String str) {
		// TODO Auto-generated method stub
		super.setNewResultChecker(str);
	}

	@Override
	public void setResultChecker(String str) {
		// TODO Auto-generated method stub
		super.setResultChecker(str);
	}
	
	public void verifyResponse(VerificationCriteria vc){
		String actual = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		Verifier.verifyEquals("vpResponse", this, vc, actual).perform();
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setResponse")){
			return "verifyResponse";
		}
		if(operation.equals("setResponse")){
			return "verifyRequest";
		}
		return super.getDependOperation(operation);
	}

	@Override
	public void setConnectionProfile(String string) {
		super.setConnectionProfile(string);
	}

	@Override
	public void setDataSourceType(String string) {
		super.setDataSourceType(string);
	}
	
	public void setParameterValue(String str){
		LoadParameterPage.setParameterDefaultValue(str, dialog());
	}

	@Override
	public void setName(String string) {
		super.setName(string);
	}
	
	public void setResourceBaseUrl(String str){
		RestWSDefinitionPage.setResourceBaseUri(str, dialog());
	}
	
	public void setResourceUriTemplate(String str){
		RestWSDefinitionPage.setResourceUriTemplate(str, dialog());
	}
	
	public void setHttpMethod(String str){
		RestWSDefinitionPage.setHttpMethod(str, dialog());
	}
	
	public void setExpectedStatusCode(String str){
		RestWSDefinitionPage.setExpectedStatusCode(str, dialog());
	}
	
	public void setResponse(String str){
		RestWSDefinitionPage.setResponse(str, dialog());
	}
	
	public void setRequest(String str){
		RestWSDefinitionPage.setRequest(str, dialog());
	}
	
	public void setHeader(String str){
		RestWSDefinitionPage.setHeader(str, dialog());
		
	}
	
	public void setUserName(String str){
		RestWSDefinitionPage.setUserName(str, dialog());
	}
	
	public void setPassword(String str){
		RestWSDefinitionPage.setPassword(str, dialog());
	}
	

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName"))
			return 0;
		if(operation.equals("setConnectionProfile"))
			return 1;
		if(operation.equals("setDataSourceType"))
			return 1;
		if(operation.equals("setResourceBaseUrl"))
			return 2;
		if(operation.equals("setResourceUriTemplate"))
			return 2;
		if(operation.equals("setHttpMethod"))
			return 2;
		if(operation.equals("setExpectedStatusCode"))
			return 2;
		if(operation.equals("setResponse"))
			return 2;
//		if(operation.equals("setRequest"))
//			return 2;
		if(operation.equals("setUserName"))
			return 2;
		if(operation.equals("setPassword"))
			return 2;
		if(operation.equals("setHeader"))
			return 2;
		if(operation.equals("setParameterValue"))
			return 3;
		if(operation.equals("setNewResultChecker"))
			return 2;
		if(operation.equals("setResultFilter"))
			return 2;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	
	}

	@Override
	public void setAttribute(String str) {
		// TODO Auto-generated method stub
		super.setAttribute(str);
	}

	@Override
	public void setFilter(String name) {
		// TODO Auto-generated method stub
		super.setFilter(name);
	}

	@Override
	public void setProjectName(String string) {
		// TODO Auto-generated method stub
		super.setProjectName(string);
	}

	@Override
	public void setSqlQuery(String string) {
		// TODO Auto-generated method stub
		super.setSqlQuery(string);
	}
	
//	public void setRequest(String str){
//		RestWSDefinitionPage.setRequest(str, dialog());
//	}
	

	@Override
	public void setStoredProcedure(String text) {
		// TODO Auto-generated method stub
		super.setStoredProcedure(text);
	}

	@Override
	public void start(String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(string)));
		DOF.getContextMenu().click(atPath("New->Mobile Business Object"));
	}
	
	

}
