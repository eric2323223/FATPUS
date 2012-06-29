package testscript.Workflow.Customization;
import java.io.File;

import resources.testscript.Workflow.Customization.Cust_API_Native_doSubmit_1_E2EHelper;
import testscript.Workflow.Customization.cfg.Config;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Cust_API_Native_doSubmit_1_E2E extends Cust_API_Native_doSubmit_1_E2EHelper
{
	/**
	 * Script Name   : <b>Cust_API_Native_doSubmit_1_E2E</b>
	 * Generated     : <b>Mar 28, 2012 4:01:05 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/28
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//		.type("Sybase_ASA_12.x").name("My Sample Database"), 
//		GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/create_AllDT.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "AllDT","create");
		WorkFlowEditor.link("Start", "AllDTcreate");
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customJsTestScript(), 
			Cfg.tplanScript_client_1);
			
	//1.used to BB6: passed
		WFCustomizer.verifyResult(new WFClientResult().data("PassGetCurrentScreen=AllDTcreate"));
		}
			
	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setMethod("customBeforeMenuItemClick", new File(Config.Cust_API_Native_doSubmit_1_1));
		s.setMethod("customAfterShowScreen", new File(Config.Cust_API_Native_doSubmit_1_2));
		return s;
	}
}
//passed BB6T 20120331
