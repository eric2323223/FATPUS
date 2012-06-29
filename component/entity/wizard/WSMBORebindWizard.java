package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.LongOperationMonitor;
import component.entity.WSMBOCreationWizard;

public class WSMBORebindWizard extends WSMBOCreationWizard implements IWizard {
	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setDataSourceType")){
			return 0;
		}
		if(operation.equals("setConnectionProfile")){
			return 0;
		}
		if(operation.equals("setMethod")){
			return 1;
		}
		if(operation.equals("setResultChecker")){
			return 1;
		}
		if(operation.equals("setNewResultChecker")){
			return 1;
		}
		if(operation.equals("setJavaNature")){
			return 1;
		}
		if(operation.equals("setExistingResultChecker")){
			return 1;
		}
		if(operation.equals("setParameter")){
			return 2;
		}
		if(operation.equals("setParameterValue")){
			return 2;
		}
		if(operation.equals("setXslt")){
			return 3;
		}
		throw new RuntimeException("Unknown operation: "+operation);
	}
	
	public void start(String str){
		
	}
	
	@Override
	public void setParameterValue(String str) {
		// TODO Auto-generated method stub
		super.setParameterValue(str);
	}

	public void setXslt(String str){
		DOF.getSybaseCCombo(dialog()).click();
		DOF.getPoppedUpList().click(atText(str));
		LongOperationMonitor.waitForProgressBarVanish(dialog());
	}
	
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("Bind Data Source");
	}

	@Override
	public void setConnectionProfile(String string) {
		super.setConnectionProfile(string);
	}

	@Override
	public void setDataSourceType(String string) {
		// TODO Auto-generated method stub
		super.setDataSourceType(string);
	}

	@Override
	public void setExistingResultChecker(String str) {
		super.setExistingResultChecker(str);
	}

	@Override
	public void setJavaNature(String str) {
		super.setJavaNature(str);
	}

	@Override
	public void setMethod(String str) {
		super.setMethod(str);
	}

	@Override
	public void setNewResultChecker(String str) {
		super.setNewResultChecker(str);
	}

	@Override
	public void setParameter(String str) {
		super.setParameter(str);
	}

	@Override
	public void setResultChecker(String str) {
		super.setResultChecker(str);
	}

}
