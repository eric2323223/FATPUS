package component.entity.wizard;

import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.sybase.automation.framework.common.ObjectMarshaller;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.entity.DBCPCreationWizard;
import component.entity.model.DBDriver;
import component.entity.model.VerificationCriteria;
import component.entity.resource.DB2;

public class DB2CPCreationWizard extends DBCPCreationWizard {

	@Override
	public void setDbName(String dbName) {
		DOF.getTextField(dialog(), "Location:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(dbName);
//		super.setDbName(dbName);
	}
	
//////////fanfei>>>>>>>>>//////////////
	public void verifyName(VerificationCriteria vc){
		super.verifyName(vc);
	}
////////////fanfei<<<<<<<<<<<<///////////
	
	public void setHost(String str){
		DOF.getTextField(dialog(), "Host:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setType")){
			return 0;
		}
		if (operation.equals("setName")) {
			return 0;
		}
		if (operation.equals("setDriver")) {
			return 1;
		}
		if (operation.equals("setDbName")) {
			return 1;
		}
		if (operation.equals("setPort")) {
			return 1;
		}
		if (operation.equals("setHost")) {
			return 1;
		}
		if (operation.equals("setUserName")) {
			return 1;
		}
		if (operation.equals("setPassword")) {
			return 1;
		} else {
			throw new RuntimeException("Unknown operation: " + operation);
		}
	}

	@Override
	public void setDriver(String driverStr) {

		DBDriver driver = new DBDriver(driverStr);
//		DBDriver driver = (DBDriver)ObjectMarshaller.deserialize(driverStr, DBDriver.class);
		DOF.getToolBar(dialog(), "New Driver Definition").click();
		driverTree().click(atCell(atRow(atPath("Database->"+driver.getTemplate())), atColumn("Name")));
		if(driver.getName()!=null){
			DOF.getTextField(newDriverDialog(), "Driver name:").click();
			newDriverDialog().inputKeys(SpecialKeys.CLEARALL);
			newDriverDialog().inputChars(driver.getName());
		}
		DOF.getTabFolder(newDriverDialog(), "Jar List").setState(SINGLE_SELECT, atText("Jar List"));
		DOF.getButton(newDriverDialog(), "&Clear All").click();
		for(String jarFile:driver.getJarList()){
			DOF.getButton(newDriverDialog(), "&Add JAR/Zip...").click();
			sleep(1);
			System.out.println("file name: "+ jarFile);
			getScreen().getActiveWindow().inputChars(jarFile);
			getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		}
		
			DOF.getTabFolder(newDriverDialog(), "Properties").setState(SINGLE_SELECT, atText("Properties"));
			TreeHelper.setTextCellValue(propertiesTree(newDriverDialog()), "General->Connection URL", "Value", new DB2().connectionString());
		
		
			DOF.getTabFolder(newDriverDialog(), "Properties").setState(SINGLE_SELECT, atText("Properties"));
			propertiesTree(newDriverDialog()).click(atCell(atRow(atPath("General->Driver Class")), atColumn("Value")));
			newDriverDialog().inputKeys(SpecialKeys.CLEARALL);
			newDriverDialog().inputChars(new DB2().driverClass());
		
			DOF.getTabFolder(newDriverDialog(), "Properties").setState(SINGLE_SELECT, atText("Properties"));
			TreeHelper.setTextCellValue(propertiesTree(newDriverDialog()), "General->Database Name", "Value", "mockDB");
		
			propertiesTree(newDriverDialog()).click(atPath("General"));
		DOF.getButton(newDriverDialog(), "OK").click();
	
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	public void setPassword(String password) {
		setTextField("Password:", password);
		((ToggleGUITestObject)DOF.getButton(dialog(), "Sa&ve password")).clickToState(SELECTED);
	}

	@Override
	public void setPort(String port) {
		// TODO Auto-generated method stub
		super.setPort(port);
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		super.setType(type);
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		super.setUserName(userName);
	}

}
