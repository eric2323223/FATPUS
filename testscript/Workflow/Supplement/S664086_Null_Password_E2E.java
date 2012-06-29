package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S664086_Null_Password_E2EHelper;
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
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S664086_Null_Password_E2E extends S664086_Null_Password_E2EHelper
{
	/**
	 * Script Name   : <b>S664086_Null_Password_BB</b>
	 * Generated     : <b>Mar 9, 2012 1:00:09 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/09
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
		PropertiesView.setAuthentication("supAdmin","");
		MainMenu.saveAll();
		
		//used to BB6:passed
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"),
			customTestScript(), 
			testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
//		//used to Android:passed
//		WFCustomizer.runTest(new WorkFlowPackage()
//			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//			.assignToUser(Cfg.deviceUser)
//			.unwiredServer("My Unwired Server")
//			.deployToServer("true"),
//			customTestScript(), 
//			testscript.Workflow.cfg.Cfg.tplanScript_Android_client);
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"list_items_count=0"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Credential_Request").setData("cc_username","supAdmin");
		s.screen("Credential_Request").setData("cc_password","");
		s.screen("Credential_Request").moveTo("Start").throughMenuItem("Save");
		s.screen("Start").getListItemsCount();
		return s;
	}
}
//passed BB6T and Android  20120309