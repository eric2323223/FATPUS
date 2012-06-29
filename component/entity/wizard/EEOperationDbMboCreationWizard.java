package component.entity.wizard;

import java.awt.Point;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.EE;
import component.entity.OCW;
import component.entity.OD;
import component.entity.model.IWizardEntity;
import component.entity.model.WizardRunner;

public class EEOperationDbMboCreationWizard extends OCW {

	public void start(String str){
		Point point = OD.getValidPoint();
		EE.dndResource(str, point.x, point.y);
		sleep(1);
		if(DOF.getDialog("Warning")!=null){
			DOF.getButton(DOF.getDialog("Warning"), "&Yes").click();
		}
		sleep(1);
//		LongOperationMonitor.waitForDialog("Quick Create");
		TopLevelTestObject dialog;
		if(DOF.getDialog("Quick Create")!=null){
			dialog = DOF.getDialog("Quick Create");
		}else{
			dialog = DOF.getDialog("New Mobile Business Object Options");
		}
		DOF.getButton(dialog, "Operation").click();
		DOF.getButton(dialog, "OK").click();
		sleep(1);
		if(DOF.getDialog("Warning")!=null){
			DOF.getButton(DOF.getDialog("Warning"), "&Yes").click();
		}
	}
	
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setDataSource")){
			return 0;
		}
		if(operation.equals("setConnectionProfile")){
			return 0;
		}
		if(operation.equals("setSqlQuery")){
			return 1;
		}
		if(operation.equals("setStoredProcedure")){
			return 1;
		}
		if(operation.equals("setParameter")){
			return 0;
		}
		if(operation.equals("setParameterValue")){
			return 1;
		}
		if(operation.equals("setParameterDefaultValue")){
			return 1;
		}
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}

	@Override
	public void setName(String string) {
		// TODO Auto-generated method stub
		super.setName(string);
	}

	@Override
	public void setParameterValue(String str) {
		// TODO Auto-generated method stub
		super.setParameterValue(str);
	}

	@Override
	public void setSqlQuery(String text) {
		// TODO Auto-generated method stub
		super.setSqlQuery(text);
	}

	@Override
	public void setStoredProcedure(String text) {
		// TODO Auto-generated method stub
		super.setStoredProcedure(text);
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		super.setType(type);
	}
	
	public void setParameter(String str){
		super.setParameter(str);
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		runner.run(entity, this);
		
	}

}
