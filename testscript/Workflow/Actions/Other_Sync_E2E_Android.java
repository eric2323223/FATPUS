package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.Other_Sync_E2E_AndroidHelper;
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
import com.sybase.automation.framework.common.CDBUtil;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Operation;
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Other_Sync_E2E_Android extends Other_Sync_E2E_AndroidHelper
{
	/**
	 * Script Name   : <b>Other_Sync_E2E_Android</b>
	 * Generated     : <b>Oct 26, 2011 8:23:18 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/26
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
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ASA (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "ASA");
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "ASA"))
				.name("O_HParas")
				.sqlQuery("DELETE FROM sampledb.dba.ASA WHERE a = :a"));
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "ASA"))
				.name("O_NParas")
				.sqlQuery("INSERT INTO sampledb.dba.ASA (a, b, c) VALUES (9, 9.0, 9.0)"));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "ASA", "O_HParas");
		WorkFlowEditor.dragOperation(Cfg.projectName, "ASA", "O_NParas");
		WorkFlowEditor.link("Start Screen", "ASA_O_HParas");
		WorkFlowEditor.link("Start Screen", "ASA_O_NParas");
		WorkFlowEditor.setMenuItem("ASA_O_HParas", new WFScreenMenuItem()
			.name("O_HParas")
			.type("Online Request")
			.defaultSuccessScreen("Start Screen"));
		WorkFlowEditor.setMenuItem("ASA_O_NParas", new WFScreenMenuItem()
			.name("O_NParas")
			.type("Online Request")
			.defaultSuccessScreen("Start Screen")
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
		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=1"));
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("a=1");
		clause.add("LOGICAL_DEL=1");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("flv-vmpc", "wf", "ASA", clause)).performTest();
		//
		clause.clear();
		clause.add("a=9");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("flv-vmpc", "wf", "ASA", clause)).performTest();
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("ASA_O_HParas").throughMenuItem("Open ASA_O_HParas");
		s.screen("ASA_O_HParas").setData("ASA_O_HParas_a_paramKey", "1");
		s.screen("ASA_O_HParas").moveTo("Start_Screen").throughMenuItem("O_HParas");
//		s.screen("ASA_create").moveTo("Start_Screen").throughMenuItem("Cancel");
		//
		s.screen("Start_Screen").moveTo("ASA_O_NParas").throughMenuItem("Open ASA_O_NParas");
		s.screen("ASA_O_NParas").moveTo("Start_Screen").throughMenuItem("O_NParas");
		s.screen("Start_Screen").moveTo("ASA_O_NParas").throughMenuItem("Open ASA_O_NParas");
		s.screen("ASA_O_NParas").moveTo("ErrorList").throughMenuItem("O_NParas");
		s.screen("ErrorList").getListItemsCount();

		return s;
	}
}
