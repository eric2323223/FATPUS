package component.entity.wizard;

import java.awt.Point;

import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import component.entity.EE;
import component.entity.LongOperationMonitor;
import component.entity.OD;

public class EEWSMBOOperationWizard extends WSOperationCW {

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
	public void setName(String string) {
		// TODO Auto-generated method stub
		super.setName(string);
	}

	@Override
	public void setType(String str) {
		// TODO Auto-generated method stub
		super.setType(str);
	}

	@Override
	public void setMethod(String str) {
		// TODO Auto-generated method stub
		super.setMethod(str);
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

	@Override
	public void setOperationParameter(String str) {
		super.setOperationParameter(str);
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setMethod")){
			return 0;
		}
		if(operation.equals("setOperationParameter")){
			return 1;
		}
		throw new RuntimeException("Unknown operation: "+operation);
	}

}
