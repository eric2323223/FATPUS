package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;

public class NotificationProcessingWizard extends WorkFlowCreateionWizard {
	
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("Notification Processing Wizard");
	}
	
	public boolean setObjectQuery(String str){
		return super.setObjectQuery(str);
	}
	
	public void start(String str){
		DOF.getButton(DOF.getRoot(), "Jumpstart...").click();
	}
	
	public void setType(String str){
		
	}

	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setParentFolder")){
			return 0;
		}
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setOption")){
			return 0;
		}
		if(operation.equals("setMbo")){
			return 0;
		}
		if(operation.equals("setObjectQuery")){
			return 0;
		}
		if(operation.equals("setFrom")){
			return 1;
		}
		if(operation.equals("setSubject")){
			return 1;
		}
		if(operation.equals("setTo")){
			return 1;
		}
		if(operation.equals("setCc")){
			return 1;
		}
		if(operation.equals("setBody")){
			return 1;
		}
		if(operation.equals("setFromMatchingRule")){
			return 4;
		}
		if(operation.equals("setSubjectMatchingRule")){
			return 4;
		}
		if(operation.equals("setToMatchingRule")){
			return 4;
		}
		if(operation.equals("setCcMatchingRule")){
			return 4;
		}
		if(operation.equals("setBodyMatchingRule")){
			return 4;
		}
		if(operation.equals("setParameterValue")){
			return 5;
		}
		throw new RuntimeException("Unknown operaton: "+ operation);
	}
}
