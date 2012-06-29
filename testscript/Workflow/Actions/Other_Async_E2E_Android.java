package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.Other_Async_E2E_AndroidHelper;
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
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Other_Async_E2E_Android extends Other_Async_E2E_AndroidHelper
{
	/**
	 * Script Name   : <b>Other_Async_E2E_Android</b>
	 * Generated     : <b>Oct 31, 2011 2:58:11 AM</b>
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
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ASA (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "ASA");
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "ASA"))
				.name("O_HParas")
				.sqlQuery("DELETE FROM sampledb.dba.ASA WHERE a = :a"));
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "ASA"))
				.name("O_NParas")
				.sqlQuery("INSERT INTO sampledb.dba.ASA (a, b, c) VALUES (8, 8.0, 8.0)"));
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
			.type("Submit Workflow"));
		WorkFlowEditor.setMenuItem("ASA_O_NParas", new WFScreenMenuItem()
			.name("O_NParas")
			.type("Submit Workflow"));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("DT")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android");
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("a=2");
		clause.add("LOGICAL_DEL=1");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("flv-vmpc", "wf", "ASA", clause)).performTest();
		//
		clause.clear();
		clause.add("a=8");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("flv-vmpc", "wf", "ASA", clause)).performTest();
//
	}
	
	private java.util.List<CustomJsTestScript> customTestScript() {
		java.util.List<CustomJsTestScript> list = new ArrayList<CustomJsTestScript>();
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("ASA_O_HParas").throughMenuItem("Open ASA_O_HParas");
		s.screen("ASA_O_HParas").setData("ASA_O_HParas_a_paramKey", "2");
		s.screen("ASA_O_HParas").menuItem("O_HParas");
		list.add(s);
		//
		CustomJsTestScript s1 = new CustomJsTestScript();
		s1.screen("Start_Screen").moveTo("ASA_O_NParas").throughMenuItem("Open ASA_O_NParas");
		s1.screen("ASA_O_NParas").menuItem("O_NParas");
		list.add(s1);

		return list;
	}
}
