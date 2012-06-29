package component.entity;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.entity.model.DBDriver;
import component.entity.model.IWizardEntity;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.resource.MSSQL;

public class DBCPCreationWizard extends ASACPCreationWizard {

	@Override
	public void cancel() {
		super.cancel();
	}
	
	public void setDriver(String driverStr){
		DBDriver driver = new DBDriver(driverStr);
		
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
				TreeHelper.setTextCellValue(propertiesTree(newDriverDialog()), "General->Connection URL", "Value", new MSSQL().connectionString());
			
			
				DOF.getTabFolder(newDriverDialog(), "Properties").setState(SINGLE_SELECT, atText("Properties"));
				propertiesTree(newDriverDialog()).click(atCell(atRow(atPath("General->Driver Class")), atColumn("Value")));
				DOF.getButton(newDriverDialog(), "...").click();
				DOF.getButton(DOF.getDialog("Available Classes from Jar List"), "Type class name").click();
				DOF.getTextField(DOF.getDialog("Available Classes from Jar List")).click();
				DOF.getDialog("Available Classes from Jar List").inputKeys(SpecialKeys.CLEARALL);
				DOF.getDialog("Available Classes from Jar List").inputChars(new MSSQL().driverClass());
	//			DOF.getDialog("Available Classes from Jar List").inputChars(driver.getDriverClass());
				DOF.getButton(DOF.getDialog("Available Classes from Jar List"), "OK").click();
	//			TreeHelper.setTextCellValue(propertiesTree(newDriverDialog()), "General->Driver Class", "Value", driver.getDriverClass());
			
			if(driver.getDbName()!=null){
				DOF.getTabFolder(newDriverDialog(), "Properties").setState(SINGLE_SELECT, atText("Properties"));
				TreeHelper.setTextCellValue(propertiesTree(newDriverDialog()), "General->Database Name", "Value", driver.getDbName());
			}
			propertiesTree(newDriverDialog()).click(atPath("General"));
			DOF.getButton(newDriverDialog(), "OK").click();
		
	}
	
	protected GuiSubitemTestObject propertiesTree(TestObject parent){
		return DOF.getTree(parent);
	}
	
	protected TopLevelTestObject newDriverDialog(){
		return DOF.getDialog("New Driver Definition");
	}
	
	protected GuiSubitemTestObject driverTree(){
		return DOF.getTree(newDriverDialog());
	}

	@Override
	public boolean canContinue() {
		return super.canContinue();
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner wr) {
		super.create(entity, wr);
	}

	@Override
	protected TopLevelTestObject dialog() {
		return super.dialog();
	}

	@Override
	public void finish() {
		super.finish();
	}

	@Override
	public String getDependOperation(String operation) {
		return super.getDependOperation(operation);
	}
	
	public void setUrl(String str){
		DOF.getTextField(dialog(), "URL:").click();
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
		if (operation.equals("setUrl")) {
			return 1;
		}
		if (operation.equals("setDriver")) {
			return 1;
		}
		if (operation.equals("setHost")) {
			return 1;
		}
		if (operation.equals("setDbName")) {
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
	public void next() {
		super.next();
	}

	@Override
	public void setDbName(String dbName) {
		if(DOF.getTextField(dialog(), "Database:")!=null){
			DOF.getTextField(dialog(), "Database:").click();
		}else{
			DOF.getTextField(dialog(), "SID:").click();
		}
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(dbName);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public void setPassword(String password) {
		setTextField("Password:", password);
		((ToggleGUITestObject)DOF.getButton(dialog(), "Sa&ve password")).clickToState(SELECTED);
	}

	@Override
	public void setPort(String port) {
		DOF.getTextField(dialog(), "Port number:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(port);
	}

	@Override
	public void setType(String type) {
		super.setType(type);
	}

	@Override
	public void setUserName(String userName) {
		super.setUserName(userName);
	}

	@Override
	public void start(String parameter) {
		super.start(parameter);
	}

	@Override
	public void verifyName(VerificationCriteria vc) {
		super.verifyName(vc);
	}

	
}
