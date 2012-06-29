package component.entity;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import component.dialog.SAPOperationSelectionDialog;
import component.wizard.page.OperationParameterPage;

public class WNSAPOCW extends WNOCW {
	
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setOperation")){
			return 1;
		}
		if(operation.equals("setBapiOperation")){
			return 1;
		}
		if(operation.equals("setParameter")){
			return 1;
		}
		if(operation.equals("setParameterValue")){
			return 2;
		}
		else{
			throw new RuntimeException("Unknown operation: "+operation);
		}
	}
	
	public void setBapiOperation(String str){
		SAPOperationSelectionDialog.setBapiOperation(str, dialog());
	}
	
	public void setName(String str){
		super.setName(str);
	}
	
	public void setParameterValue(String str){
		OperationParameterPage.setParameterDefaultValue(str, dialog());
	}
	
	public void setType(String type){
		super.setType(type);
	}
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Operation");
	}

	public void setOperation(String str){
		SAPOperationSelectionDialog.setOperation(str , dialog());
	}
	
	public void setParameter(String str){
		SAPOperationSelectionDialog.setParameter(str, dialog());
	}
}
