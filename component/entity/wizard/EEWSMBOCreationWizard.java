package component.entity.wizard;

import java.awt.Point;

import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.OD;
import component.entity.WSMBOCreationWizard;

public class EEWSMBOCreationWizard extends WSMBOCreationWizard {
	
	@Override
	public void start(String parameter) {
		String cp = parameter.split(",")[0];
		String method = parameter.split(",")[1];
		EE.connectConnectionProfile(cp);
		Point validPoint = OD.getValidPoint();
		DOF.getEETree().dragToScreenPoint(atPath("Web Services->"+cp+"->PortPortType->"+method), 
				DOF.getObjectDiagramCanvas().getScreenPoint(atPoint(validPoint.x, validPoint.y)));
		DOF.getButton(DOF.getDialog("New Mobile Business Object Options"), "OK").click();
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
			return 0;
		}
		if(operation.equals("setResultChecker")){
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
			return 1;
		}
		if(operation.equals("setAttributeName")){
			return 4;
		}
		return 0;
	
	}

	@Override
	public void setAttributeName(String str) {
		// TODO Auto-generated method stub
		super.setAttributeName(str);
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
	public void setMethod(String str) {
		// TODO Auto-generated method stub
		super.setMethod(str);
	}

	@Override
	public void setName(String str) {
		// TODO Auto-generated method stub
		super.setName(str);
	}

	@Override
	public void setNewResultChecker(String str) {
		// TODO Auto-generated method stub
		super.setNewResultChecker(str);
	}

	@Override
	public void setParameter(String str) {
		// TODO Auto-generated method stub
		super.setParameter(str);
	}

	@Override
	public void setParameterValue(String str) {
		// TODO Auto-generated method stub
		super.setParameterValue(str);
	}

	@Override
	public void setPassword(String str) {
		// TODO Auto-generated method stub
		super.setPassword(str);
	}

	@Override
	public void setResultChecker(String str) {
		// TODO Auto-generated method stub
		super.setResultChecker(str);
	}

	@Override
	public void setSingleParameter(String str) {
		// TODO Auto-generated method stub
		super.setSingleParameter(str);
	}

	@Override
	public void setUserName(String str) {
		// TODO Auto-generated method stub
		super.setUserName(str);
	}

	@Override
	public void setXslt(String file) {
		// TODO Auto-generated method stub
		super.setXslt(file);
	}
	

}
