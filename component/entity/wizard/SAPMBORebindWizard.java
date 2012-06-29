package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.LongOperationMonitor;
import component.entity.SAPMBOCreationWizard;

public class SAPMBORebindWizard extends SAPMBOCreationWizard implements IWizard {

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setConnectionProfile"))
			return 0;
		if(operation.equals("setDataSourceType"))
			return 0;
		if(operation.equals("setOperation"))
			return 1;
		if(operation.equals("setParameter"))
			return 1;
		if(operation.equals("setParameterValue"))
			return 2;
		if(operation.equals("setOutputTable"))
			return 3;
		else
			throw new RuntimeException("Unknown operation name: "+operation);
	}
	
	public void setOutputTable(String str){
		DOF.getSybaseCCombo(dialog()).click();
		DOF.getPoppedUpList().click(atText(str));
		LongOperationMonitor.waitForProgressBarVanish(dialog());
	}
	
	public void start(String str){
		
	}
	
	protected TopLevelTestObject dialog() {
		return DOF.getDialog("Bind Data Source");
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
	public void setOperation(String str) {
		super.setOperation(str);
	}

	@Override
	public void setParameter(String str) {
		super.setParameter(str);
	}

	@Override
	public void setParameterValue(String str) {
		super.setParameterValue(str);
	}
	

}
