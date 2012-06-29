package component.entity.wizard;

import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.entity.DBCPCreationWizard;
import component.entity.model.DBDriver;
import component.entity.model.VerificationCriteria;
import component.entity.resource.Oracle;

public class OracleCPCreationWizard extends DBCPCreationWizard {

	@Override
	public void setDbName(String dbName) {
		// TODO Auto-generated method stub
		super.setDbName(dbName);
	}
	
	public void verifyName(VerificationCriteria vc){
		super.verifyName(vc);
	}
	
	public void setHost(String str){
		DOF.getTextField(dialog(), "Host:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}

	@Override
	public void setDriver(String driverStr) {

		DBDriver driver = new DBDriver(driverStr);
		if(ListHelper.hasItem(DOF.getCombo(dialog(),"Drivers:"), driver.getName())){
			DOF.getCombo(dialog(),"Drivers:").click();
			DOF.getCombo(dialog(),"Drivers:").click(atText(driver.getName()));
		}else{
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
			sleep(2);
			System.out.println("file name: "+ jarFile);
//			DOF.getDialog("Select the file:").inputChars(jarFile);
//			DOF.getDialog("Select the file:").inputKeys(SpecialKeys.ENTER);
			getScreen().getActiveWindow().inputChars(jarFile);
			getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		}
		if(driver.getConnectionString()!=null){
			DOF.getTabFolder(newDriverDialog(), "Properties").setState(SINGLE_SELECT, atText("Properties"));
			TreeHelper.setTextCellValue(propertiesTree(newDriverDialog()), "General->Connection URL", "Value", driver.getConnectionString());
		}
		
		DOF.getTabFolder(newDriverDialog(), "Properties").setState(SINGLE_SELECT, atText("Properties"));
		propertiesTree(newDriverDialog()).click(atCell(atRow(atPath("General->Driver Class")), atColumn("Value")));
		newDriverDialog().inputKeys(SpecialKeys.CLEARALL);
		newDriverDialog().inputChars(new Oracle().driverClass());
		
		if(driver.getDbName()!=null){
			DOF.getTabFolder(newDriverDialog(), "Properties").setState(SINGLE_SELECT, atText("Properties"));
			TreeHelper.setTextCellValue(propertiesTree(newDriverDialog()), "General->Database Name", "Value", driver.getDbName());
		}
		DOF.getButton(newDriverDialog(), "OK").click();
		}
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		super.setPassword(password);
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
