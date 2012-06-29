package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.ActivationKey_E2E_3_2Helper;
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
import component.entity.GlobalConfig;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Module;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class ActivationKey_E2E_3_2 extends ActivationKey_E2E_3_2Helper
{
	/**
	 * Script Name   : <b>ActivationKey_E2E_3__3</b>
	 * Generated     : <b>Oct 27, 2011 11:32:22 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/27
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
//		// Note:there are 2 records in testtable:
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Keys/setup/createTable.sql");

		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->testtable (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		//1.the first WF:
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF1")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE)
				);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Testtable");
		PropertiesView.editModule(new Module().activeKey("Mykey1"));
		
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Testtable")
				.objectQuery("findAll")
				.defaultSuccessScreen("Testtable"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF1"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript()
//			, 
//			testscript.Workflow.cfg.Cfg.tplanScript_client_1
			);
	
		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=2"));
		
		//2.the other WF with different active Key:
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF_Activation_2")
				WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF2")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE)
				);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Testtable");
		PropertiesView.editModule(new Module().activeKey("Mykey2"));
		
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Testtable")
				.objectQuery("findAll")
				.defaultSuccessScreen("Testtable"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
//			.startParameter(WN.filePath(Cfg.projectName, "myWF_Activation_2"))
			.startParameter(WN.filePath(Cfg.projectName, "myWF2"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript()
//			, 
//			testscript.Workflow.cfg.Cfg.tplanScript_client_2
			);
	
		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=2"));
	}
	
		private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Activate").moveTo("Start").throughMenuItem("Submit Workflow");
			s.screen("Start").moveTo("Testtable").throughMenuItem("findAll");
			s.screen("Testtable").getListItemsCount();
			return s;
		}
}

//passed