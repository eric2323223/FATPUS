package component.entity.wizard;

import java.awt.Point;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.EE;
import component.entity.LongOperationMonitor;
import component.entity.OD;
import component.entity.model.VerificationCriteria;
import component.entity.verifier.Verifier;

public class EEOperationRestWSMboCreationWizard extends ODRestWSOperationCreationWizard{
	
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
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setResourceBaseUrl"))
			return 0;
		if(operation.equals("setResourceUriTemplate"))
			return 0;
		if(operation.equals("setHttpMethod"))
			return 0;
		if(operation.equals("setExpectedStatusCode"))
			return 0;
		if(operation.equals("setResponse"))
			return 0;
		if(operation.equals("setRequest"))
			return 0;
		if(operation.equals("setUserName"))
			return 0;
		if(operation.equals("setPassword"))
			return 0;
		if(operation.equals("setHeader"))
			return 0;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	}

	@Override
	public void setExpectedStatusCode(String str) {
		// TODO Auto-generated method stub
		super.setExpectedStatusCode(str);
	}

	@Override
	public void setHeader(String str) {
		// TODO Auto-generated method stub
		super.setHeader(str);
	}

	@Override
	public void setHttpMethod(String str) {
		// TODO Auto-generated method stub
		super.setHttpMethod(str);
	}

	@Override
	public void setName(String str) {
		// TODO Auto-generated method stub
		super.setName(str);
	}

	@Override
	public void setPassword(String str) {
		// TODO Auto-generated method stub
		super.setPassword(str);
	}

	@Override
	public void setProjectName(String string) {
		// TODO Auto-generated method stub
		super.setProjectName(string);
	}

	@Override
	public void setRequest(String str) {
		// TODO Auto-generated method stub
		super.setRequest(str);
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
	public void setType(String str) {
		// TODO Auto-generated method stub
		super.setType(str);
	}

	@Override
	public void setUserName(String str) {
		// TODO Auto-generated method stub
		super.setUserName(str);
	}
	
	public void verifyRequest(VerificationCriteria vc){
		String actual = DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
		Verifier.verifyEquals("vpRequest", this, vc, actual).perform();
	}

	@Override
	public void start(String str) {
		Point point = OD.getValidPoint();
		EE.dndResource(str, point.x, point.y);
		sleep(1);
		LongOperationMonitor.waitForDialog("New Mobile Business Object Options");
		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "Operation").click();
		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "OK").click();
	}

}
