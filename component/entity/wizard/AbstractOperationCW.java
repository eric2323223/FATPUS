package component.entity.wizard;

import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import component.entity.ACW;

public abstract class AbstractOperationCW extends ACW {

	@Override
	public abstract void start(String string);

	@Override
	public String getDependOperation(String dependOperation) {
		return null;
	}

	@Override
	public void setConnectionProfile(String string) {
		super.setConnectionProfile(string);
	}

	@Override
	public void setDataSourceType(String string) {
		super.setDataSourceType(string);
	}
	
	public void setType(String str){
		DOF.getCombo(dialog(), "Operation type:").click();
		DOF.getCombo(dialog(), "Operation type:").click(atText(str));
	}
	
	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		throw new RuntimeException("Unknown operation: "+operation);
	}

	@Override
	public void setName(String string) {
		DOF.getTextField(dialog(), "Operation name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
	}
	
	protected TopLevelTestObject dialog(){
		return DOF.getDialog("New Operation");
	}

}
