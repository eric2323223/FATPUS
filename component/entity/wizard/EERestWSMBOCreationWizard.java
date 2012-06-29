package component.entity.wizard;

import java.awt.Point;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.OD;
import component.entity.model.VerificationCriteria;
import component.entity.wizard.RestWSMboCreationWizard;
import component.wizard.page.LoadParameterPage;

public class EERestWSMBOCreationWizard extends RestWSMboCreationWizard {

	@Override
	public void setExistingResultChecker(String str) {
		DOF.getLabel(dialog(), "Result Checker").click();
		DOF.getButton(dialog(), "C&ustom").click();
		DOF.getButton(dialog(), "Bro&wse...").click();
		TopLevelTestObject dialog = DOF.getDialog("Select REST Web Service Result Checker Class");
		dialog.inputChars(str);
		sleep(1);
		dialog.inputKeys(SpecialKeys.DOWN);
		dialog.inputKeys(SpecialKeys.ENTER);
	}

	@Override
	public void setResultFilter(String str) {
		// TODO Auto-generated method stub
		super.setResultFilter(str);
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
	public void setRequest(String str) {
		// TODO Auto-generated method stub
		super.setRequest(str);
	}

	@Override
	public void setResultChecker(String str) {
		// TODO Auto-generated method stub
		super.setResultChecker(str);
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setNewResultChecker")){
			return "verifyJavaNaturePrompt";
		}
		if(operation.equals("setHttpMethod")){
			return "verifyNewResultChecker";
		}
		return null;
	}

	@Override
	public void setHeader(String str) {
		// TODO Auto-generated method stub
		super.setHeader(str);
	}
	
	public void setParameterValue(String str){
		super.setParameterValue(str);
	}
	
	public void verifyJavaNaturePrompt(VerificationCriteria vc){
		super.verifyJavaNaturePrompt(vc);
	}
	
	public void verifyNewResultChecker(VerificationCriteria vc){
		super.verifyNewResultChecker(vc);
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
			return 0;
		if(operation.equals("setHttpMethod"))
			return 0;
		if(operation.equals("setExpectedStatusCode"))
			return 0;
		if(operation.equals("setResponse"))
			return 0;
		if(operation.equals("setUserName"))
			return 0;
		if(operation.equals("setPassword"))
			return 0;
		if(operation.equals("setHeader"))
			return 0;
		if(operation.equals("setParameterValue"))
			return 1;
		if(operation.equals("setNewResultChecker"))
			return 0;
		if(operation.equals("setJavaNature"))
			return 0;
		if(operation.equals("setResultChecker"))
			return 0;
		if(operation.equals("setResultFilter"))
			return 0;
		if(operation.equals("setExistingResultChecker"))
			return 0;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	
	}
	
	@Override
	public void setAttribute(String str) {
		// TODO Auto-generated method stub
		super.setAttribute(str);
	}

	@Override
	public void setConnectionProfile(String string) {
		// TODO Auto-generated method stub
		super.setConnectionProfile(string);
	}

	@Override
	public void setDataSourceType(String string) {
		// TODO Auto-generated method stub
		super.setDataSourceType(string);
	}

	@Override
	public void setExpectedStatusCode(String str) {
		// TODO Auto-generated method stub
		super.setExpectedStatusCode(str);
	}

	@Override
	public void setFilter(String name) {
		// TODO Auto-generated method stub
		super.setFilter(name);
	}

	@Override
	public void setHttpMethod(String str) {
		// TODO Auto-generated method stub
		super.setHttpMethod(str);
	}

	@Override
	public void setName(String string) {
		// TODO Auto-generated method stub
		super.setName(string);
	}

	@Override
	public void setProjectName(String string) {
		// TODO Auto-generated method stub
		super.setProjectName(string);
	}

	@Override
	public void setResourceBaseUrl(String str) {
		// TODO Auto-generated method stub
		super.setResourceBaseUrl(str);
	}

	@Override
	public void setResourceUriTemplate(String str) {
		// TODO Auto-generated method stub
		super.setResourceUriTemplate(str);
	}

	@Override
	public void setResponse(String str) {
		// TODO Auto-generated method stub
		super.setResponse(str);
	}

	@Override
	public void setSqlQuery(String string) {
		// TODO Auto-generated method stub
		super.setSqlQuery(string);
	}

	@Override
	public void setStoredProcedure(String text) {
		// TODO Auto-generated method stub
		super.setStoredProcedure(text);
	}

	@Override
	public void start(String parameter) {
		String cp = parameter.split(",")[0];
		String method = parameter.split(",")[1];
		EE.connectConnectionProfile(cp);
		Point validPoint = OD.getValidPoint();
		DOF.getEETree().dragToScreenPoint(atPath("REST Web Services->"+cp+"->"+method), 
				DOF.getObjectDiagramCanvas().getScreenPoint(atPoint(validPoint.x, validPoint.y)));
		sleep(1);
		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "OK").click();
	
	}

	@Override
	public void setPassword(String str) {
		// TODO Auto-generated method stub
		super.setPassword(str);
	}

	@Override
	public void setUserName(String str) {
		// TODO Auto-generated method stub
		super.setUserName(str);
	}
	

}
