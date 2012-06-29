package component.entity;
import java.awt.Rectangle;
import java.util.Arrays;

import testscript.Workflow.cfg.Cfg;


import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.ObjectMarshaller;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.TreeHelper;

import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.IWizardEntity;
import component.entity.model.Jar;
import component.entity.model.UnwiredServer;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.verifier.Verifier;
import component.entity.wizard.IWizard;
import component.entity.wizard.JarCreationWizard;
import component.entity.wizard.UnwiredServerCreationWizard;
import component.wizard.page.ServerConnectionMappingPage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DeployWizard extends ACW
{
	/**
	 * Script Name   : <b>BasicDeployWizard</b>
	 * Generated     : <b>Aug 16, 2010 6:32:13 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/08/16
	 * @author eric
	 */
	protected boolean result;
	protected boolean canContinue = true;
	protected boolean checkResult;
	protected VerificationCriteria verifyResult;
	protected boolean overWriteExistingJar;
	
	public void testMain(Object[] args) 
	{
//		setMode(DeployOption.MODE_UPDATE);
//		setSingleServerConnectionMapping("ASA,sampledb");
//		setSingleServerConnectionMapping("My Sample Database,sampledb");
//		setServerConnectionMapping("My Sample Database,sampledb:ASA,sampledb");
//		next();
//		setMapRole("nonrole,None");
//		finish();
		start(Cfg.projectName);
	}

	public void setMode(String string){
		if(string.equals(DeployOption.MODE_UPDATE)){
			DOF.getButton(dialog(), "&Update").click();
		}
		else if(string.equals(DeployOption.MODE_NO_OVERWRITE)){
			DOF.getButton(dialog(), "No Over&write").click();
		}
		else if(string.equals(DeployOption.MODE_REPLACE)){
			DOF.getButton(dialog(), "&Replace").click();
		}
		else if(string.equals(DeployOption.MODE_VERIFY)){
			DOF.getButton(dialog(), "&Verify").click();
		}else{
			throw new RuntimeException("Invalid mode: "+string);
		}
	}
	
	public void setPackageName(String string){
		DOF.getTextField(dialog(), "Package name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(string);
	}
	
	public void setMboNames(String string){
		if(string.equalsIgnoreCase("all")){
			
		}else{
			String[] names = string.split(",");
			String pre = "Mobile Business Objects->Default->";
			TestObject tree = DOF.getTree(dialog(), "Select Mobile Business Objects:");
			uncheckAll();
			for (String name : names) {
				TreeHelper.checkNode((GuiSubitemTestObject) tree, pre + string);
			}
		}
	}
	
	public void setTargetVersion(String str){
		DOF.getTextField(dialog(),"Target version:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	private void uncheckAll() {
		TestObject tree = DOF.getTree(dialog(), "Select Mobile Business Objects:");
		TreeHelper.unchecknode((GuiSubitemTestObject)tree, "Mobile Business Objects");
	}

	public void setServer(String string){
		String[] servers = TreeHelper.getNodesOfLevel(DOF.getTree(dialog()), 0);
		if(Arrays.asList(servers).contains(string)){
			useServer(string);
		}else{
			UnwiredServer server = (UnwiredServer)ObjectMarshaller.deserialize(string, UnwiredServer.class);
			if(Arrays.asList(servers).contains(server.getName())){
				useServer(server.getName());
				//should throw RuntimeException here?
			}else{
				DOF.getButton(dialog(), "Crea&te...").click();
				new UnwiredServerCreationWizard().create(server, new WizardRunner());
			}
		}
		
	}
	
	private void useServer(String str){
		DOF.getTree(dialog(), "Available servers:").click(atPath(str));
		sleep(1);
		connectServer(str);
		
	}
	
	public void setOverWriteExistingJar(String str){
		if(str.equalsIgnoreCase("true")){
			this.overWriteExistingJar = true;
		}else{
			this.overWriteExistingJar = false;
		}
	}

	private void connectServer(String string) {
		TestObject button = DOF.getButton(dialog(),"&Next >");
		boolean isConnected = new Boolean(button.invoke("isEnabled").toString()).booleanValue();
		if(!isConnected){
//			DOF.getButton(dialog(), "&Refresh").click();
//			DOF.getButton(dialog(), "&Connect").click();
			DOF.getTree(dialog()).doubleClick(atPath(string));
			sleep(1);
			LongOperationMonitor.waitForProgressBarVanish(dialog());
		}
	}

	public boolean getResult(){
		return this.result;
	}
	
	public void setServerConnectionMapping(String connection){
		if(connection.contains(":")){
			String[] connections = connection.split(":");
			for(String con:connections){
				ServerConnectionMappingPage.setSingleServerConnectionMapping(con, dialog());
			}
		}else{
			ServerConnectionMappingPage.setSingleServerConnectionMapping(connection, dialog());
		}
		DOF.getTableByColumnsNames(dialog(), new String[]{"Property", "Value"}).click(atCell(atRow(0), atColumn(1)));
	}

//	private void setSingleServerConnectionMapping(String connection, TopLevelTestObject dialog) {
//		
//		String[] connections = connection.split(",");
//		GuiSubitemTestObject table = DOF.getTableByColumnsNames(dialog, new String[]{"Connection profile", "Server connection"});
//		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Connection profile", connections[0]);
//		try{
//			TableHelper.setCComboCellValue(table, row, "Server connection", connections[1]);
//		}catch(Exception e){
//			TableHelper.setCComboCellValue(table, row, "Server connection", "<New Server Connection ...>");
//			sleep(1);
//			TopLevelTestObject dialog2 = DOF.getDialog("Create New Server Connection");
//			DOF.getTextField(dialog2, "Server connection name:").click();
//			dialog2.inputKeys(SpecialKeys.CLEARALL);
//			dialog2.inputChars(connections[1]);
//			DOF.getButton(dialog2, "OK", true).click();
//			LongOperationMonitor.waitForDialogToVanish("Progress Information");
//		}
//		table.click(atCell(atRow(0),atColumn(0)), atPoint(10,10));
//	}

	@Override
	public void finish() {
		DOF.getButton(dialog(), "&Finish").click();
		sleep(1);
		if(DOF.getDialog("Overwrite Existing Package File")!=null){
			if(this.overWriteExistingJar){
				DOF.getButton(DOF.getDialog("Overwrite Existing Package File"), "&Yes").click();
			}else{
				DOF.getButton(DOF.getDialog("Overwrite Existing Package File"), "&No").click();
			}
		}
		if(DOF.getDialog("Problem Occurred")!=null){
			this.result = false;
			String errorMsg = null;
			if(DOF.getTextFieldByAncestorLine(DOF.getDialog("Deployment Status"), "Composite->Shell->Shell")!=null){
				errorMsg = DOF.getTextFieldByAncestorLine(DOF.getDialog("Deployment Status"), "Composite->Shell->Shell").getProperty("text").toString();
			}
			Verifier.verifyEquals("deployResult", this, this.verifyResult, errorMsg).perform();
			DOF.getButton(DOF.getDialog("Problem Occurred"),"OK").click();
		}else{
			LongOperationMonitor.waitForDialog("Deployment Status");
	//		text().click(atPoint(22,8));
			
			String string = DOF.getTextFieldByAncestorLine(DOF.getDialog("Deployment Status"), "Composite->Shell->Shell").getProperty("text").toString();
	//		String string = text().getProperty("text").toString();
			if(this.checkResult){
				Verifier.verifyEquals("deployResult", this, this.verifyResult, string).perform();
			}
			this.result = string.startsWith("Success")? true: false;
			DOF.getButton(DOF.getDialog("Deployment Status"), "OK").click();
		}
	}
	
	private String getProperty(String prop){
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(dialog()), "Property", prop);
		if(row!=-1){
			return TableHelper.getCellValue(DOF.getTable(dialog()), row, "Value");
		}else{
			throw new RuntimeException("Property "+prop +" not found!");
		}
	}
	
	public void setCheckJars(String str){}
	
	public void setCheckResult(String str){
		this.checkResult = true;
	}
	
	public void verifyJars(VerificationCriteria vc){
		String actual = getProperty("JARs for User-defined Classes");
		Verifier.verifyEquals("propertyJar", this, vc, actual).perform();
	}
	
	public void verifyResult(VerificationCriteria vc){
		this.verifyResult=vc;
//		String actual = DOF.getTextFieldByAncestorLine(DOF.getDialog("Deployment Status"), "Composite->Shell->Shell").getProperty("text").toString();
//		Verifier.verify("deployResult", this, vc, actual).perform();
	}
	
	public IWizard canContinue(boolean b)
	{
		this.canContinue = b;
		return this;
	}
	
	
	@Override
	public int getPageIndexOfOperation(String operation) {
		if(operation.equals("setTargetVersion"))
			return 0;
		if(operation.equals("setMode"))
			return 0;
		if(operation.equals("setType"))
			return 0;
		if(operation.equals("setPackageName"))
			return 0;
		if(operation.equals("setOverWriteExistingJar"))
			return 0;
		if(operation.equals("setMboNames"))
			return 1;
		if(operation.equals("setJar"))
			return 2;
		if(operation.equals("setServer"))
			return 3;
		if(operation.equals("setServerConnectionMapping"))
			return 5;
		if(operation.equals("setMapRole"))
			return 6;
//		if(operation.equals("setCheckProperty")){
//			return 7;
//		}
//		if(operation.equals("setCheckResult")){
//			return 0;
//		}
		if(operation.equals("setSaveAsDeploymentProfile")){
			return 7;
		}
		
		else
			throw new RuntimeException("Unkonwn operation: "+operation);
	}
	
	public void setSaveAsDeploymentProfile(String str){
		((ToggleGUITestObject)DOF.getButton(dialog(), "&Save the deployment settings as a deployment profile")).clickToState(SELECTED);
		sleep(1);
		DOF.getTextField(dialog(), "File name:").click();
		dialog().inputKeys(SpecialKeys.CLEARALL);
		dialog().inputChars(str);
	}
	
	public void setJar(String str){
		Jar jar = new Jar(str);
		if(jar.getType().equals(Jar.TYPE_NEW)){
			new JarCreationWizard().create(jar, new WizardRunner());
		}else if(jar.getType().equals(Jar.TYPE_EXISTING)){
			
		}else{
			
		}
	}
	
	public void setMapRole(String string){
		if(string.contains(":")){
			String[] maps = string.split(":");
			for(String map:maps){
				mapSingleRole(map);
			}
		}else{
			mapSingleRole(string);
		}
	}

	private void mapSingleRole(String string){
		String[] roles = string.split(",");
		GuiSubitemTestObject table = DOF.getTable(dialog(), "Role mappings:");
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Logical roles", roles[0]);
		try {
			TableHelper.setCComboCellValue(table, row, "Physical roles", roles[1]);
		} catch (Exception e) {
			TableHelper.setCComboCellValue(table, row, "Physical roles","<Map Role ...>");
			sleep(2);
			GuiSubitemTestObject physicalTable=DOF.getTable(DOF.getDialog("Role Mapping - "+roles[0]), "Available physical roles:");
			physicalTable.click(atText(roles[1]), atPoint(67,10));
			DOF.getButton(DOF.getDialog("Role Mapping - "+roles[0]), "&Add >").click();
			DOF.getButton(DOF.getDialog("Role Mapping - "+roles[0]), "OK").click();
		}
	}

	@Override
	public void next() {
		DOF.getButton(dialog(), "&Next >").click();
		sleep(0.5);
		if(DOF.getDialog("Incrementing Project Version")!=null){
			DOF.getButton(DOF.getDialog("Incrementing Project Version"), "OK").click();
		}
		LongOperationMonitor.waitForProgressBarVanish(dialog());
	}

	protected TopLevelTestObject dialog(){
		return DOF.getDialog("Deploy Mobile Application Project");
	}
	
	public void setType(String str){
		if(str.equals(DeployOption.TYPE_RBS)){
//			DOF.getButton(dialog(), "Replication base synchronization").click();
			DOF.getButton(dialog(), "Repli&cation-based").click();
		}else{
//			DOF.getButton(dialog(), "Message base synchronization").click();
			DOF.getButton(dialog(), "&Message-based").click();
		}
	}

	@Override
	public void start(String string) {
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(string)));
		DOF.getContextMenu().click(atPath("Deploy Project..."));
		LongOperationMonitor.waitForDialog("Deploy Mobile Application Project",10);
//		sleep(3);
	}

	public void deploy(DeployOption server, WizardRunner wizardRunner) {
		wizardRunner.run(server, this);
	}

	@Override
	public boolean canContinue() {
		return this.canContinue;
	}

	@Override
	public String getDependOperation(String operation) {
		if(operation.equals("setServerConnectionMapping")){
			return "verifyServerConnectionMapping";
		}
		
		
		//////////////ff
		if(operation.equals("setMboNames")){
			return "verifySelectMbo";
		}
		////////ff
		
		
		if(operation.equals("setCheckJars")){
			return "verifyJars";
		}
		if(operation.equals("setCheckResult")){
			return "verifyResult";
		}
		return null;
//		throw new RuntimeException("Not find dependant verification for operation: "+operation);
	}
	
	public void verifyServerConnectionMapping(VerificationCriteria vc){
		String expected = vc.getExpected();
		vpManual("verifyServerMapping", expected, getBannerMessage()).performTest();
		if(!vc.isContinueWhenMatch()){
			canContinue = false;
		}
	}
	///////////////////////////ff
	public void verifySelectMbo(VerificationCriteria vc){
//		String expected = vc.getExpected();
//		vpManual("verifySelectMbo", expected, getBannerMessage()).performTest();
//		if(!vc.isContinueWhenMatch()){
//			canContinue = false;
//		}
		Verifier.verifyEquals("verifySelectMbo", this, vc, getBannerMessage()).perform();
	}
	//////////////ff
	
	
	private String getBannerMessage(){
		return DOF.getTextFieldByAncestorLine(dialog(), "Composite->Shell->Shell").getProperty("text").toString();
//		return DOF.getTextFieldByBound(dialog(), new Rectangle(21,30,570,36)).getProperty("text").toString();
	}

	@Override
	public void cancel() {
		DOF.getButton(dialog(), "Cancel").click();
		
	}

	@Override
	public void create(IWizardEntity entity, WizardRunner runner) {
		deploy((DeployOption)entity, runner);
	}
	
}

