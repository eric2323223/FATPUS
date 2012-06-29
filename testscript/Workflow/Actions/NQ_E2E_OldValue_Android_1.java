package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_E2E_OldValue_Android_1Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class NQ_E2E_OldValue_Android_1 extends NQ_E2E_OldValue_Android_1Helper
{
	/**
	 * Script Name   : <b>NQ_E2E_OldValue_Android_1</b>
	 * Generated     : <b>Oct 31, 2011 7:22:25 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/31
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/ASA_ddl.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/ASA_data.sql");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ASA (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "ASA");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("ASA")
				.objectQuery("findAll")
				.setGenerateOldVal(false)
				.defaultSuccessScreen("ASA")
		);
		WorkFlowEditor.setMenuItem("ASA_update_instance", new WFScreenMenuItem()
				.name("Update")
				.generateErrorScreen(true));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("supAdmin")
				.unwiredServer("My Unwired Server")
				.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
				customTestScript(), 
				"tplan.Workflow.common.StartWF_android");
		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=1|found=true"));
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("ASA").throughMenuItem("findAll");
		s.screen("ASA").moveTo("ASADetail").throughListItem("1");
		s.screen("ASADetail").moveTo("ASA_update_instance").throughMenuItem("Open ASA_update_instance");
		//
		s.screen("ASA_update_instance").moveTo("ErrorList").throughMenuItem("Update");
		s.screen("ErrorList").getListItemsCount();
		
		return s;
	}
}