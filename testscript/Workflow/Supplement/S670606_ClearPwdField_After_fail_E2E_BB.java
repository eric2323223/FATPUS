package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S670606_ClearPwdField_After_fail_E2E_BBHelper;
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
import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S670606_ClearPwdField_After_fail_E2E_BB extends S670606_ClearPwdField_After_fail_E2E_BBHelper
{
	/**
	 * Script Name   : <b>S670606_ClearPwdField_After_fail_E2E_BB</b>
	 * Generated     : <b>Mar 11, 2012 8:30:35 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/12
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)",10,10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findall")
				.project(Cfg.projectName)
				.type("Online Request")
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		PropertiesView.setAuthentication("supAdmin","s3pAdmin");
		MainMenu.saveAll();
		
		//used to BB6T:
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"),
			customTestScript(), 
			testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
		WFCustomizer.verifyResult(new WFClientResult().data(
			"id=cc_username,value=sup|"+
			"id=cc_password,value= "));
		
		
		//used to Android:
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"),
			customTestScript(), 
			"testscript.Workflow.cfg.Cfg.tplanScript_Android_client");

		WFCustomizer.verifyResult(new WFClientResult().data(
			"id=cc_username,value=sup|"+
			"id=cc_password,value= ")); 
		
}

private CustomJsTestScript customTestScript() {
	CustomJsTestScript s = new CustomJsTestScript();
	s.screen("Credential_Request").setData("cc_username","sup");
	s.screen("Credential_Request").setData("cc_password","wrongPW");
	s.screen("Credential_Request").moveTo("Start").throughMenuItem("Save");
	s.screen("Start").moveTo("Credential_Request").throughMenuItem("findall");
	s.screen("Credential_Request").getData("cc_username");
	s.screen("Credential_Request").getData("cc_password");
	return s;
	}
}
//need to run on device 20120312.not end
