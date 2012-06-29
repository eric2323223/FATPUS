package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import component.entity.wizard.IWizard;

public class OldASACPCreationWizard extends AbstractCPCreationWizard {

	@Override
	protected TopLevelTestObject dialog() {
		if(DOF.getDialog("New ASA Connection Profile")!=null){
			return DOF.getDialog("New ASA Connection Profile");
		}else{
			return DOF.getDialog("New Connection Profile");
		}
	}
	
	private String getMessage(){
		return DOF.getTextFieldByClientArea(dialog(), 361, 36).getProperty("text").toString();
	}
	
	public void verifyName(String expected){
//		if(!RationalTestScript.vpManual("verifyName", expected, getMessage()).performTest()){
//			this.canContinue = false;
//		}
	}

	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setName")){
			return 0;
		}
		if(operation.equals("setType")){
			return 0;
		}
		if(operation.equals("setHost")){
			return 1;
		}
		if(operation.equals("setPort")){
			return 1;
		}
		if(operation.equals("setDbName")){
			return 1;
		}
		if(operation.equals("setUserName")){
			return 1;
		}
		if(operation.equals("setPassword")){
			return 1;
		}
		else{
			throw new RuntimeException("Unknown operation: "+ operation);
		}
	}
	
	public String getDependOperation(String operation){
		if(operation.equals("setName")){
			return "verifyName";
		}else{
			return null;
		}
	}

	@Override
	public void start(String parameter) {
		DOF.getEETree().click(RationalTestScript.RIGHT,RationalTestScript.atPath("Database Connections"));
		DOF.getContextMenu().click(RationalTestScript.atPath("New..."));
	}
	
	public void setName(String name){
		setTextField("Name:", name);
	}
	
	public void setType(String type){
		GuiSubitemTestObject table = DOF.getTable(dialog());
		table.click(RationalTestScript.atText(type));
	}
	
	public void setHost(String host){
		setTextField("Host:", host);
	}
	
	public void setPort(String port){
		setTextField("Port:", port);
	}
	
	public void setDbName(String dbName){
		setTextField("Database name:", dbName);
	}
	
	public void setUserName(String userName){
		setTextField("User name:", userName);
	}
	
	public void setPassword(String password){
		setTextField("Password:", password);
	}

	@Override
	public IWizard canContinue(boolean b) {
		this.canContinue = b;
		return this;
	}

}
