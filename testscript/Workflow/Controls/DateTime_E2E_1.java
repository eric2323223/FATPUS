package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.DateTime_E2E_1Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DateTime_E2E_1 extends DateTime_E2E_1Helper
{
	/**
	 * Script Name   : <b>DateTime_E2E_1_Android</b>
	 * Generated     : <b>Sep 1, 2011 3:23:00 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/01
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//			.type("Sybase_ASA_12.x").name("My Sample Database"), 
//			GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/crete_table.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("T_olc")
				.objectQuery("findAll")
				.subject("t=1")
				.subjectMatchingRule("t=")
				);
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
				.unwiredServer("My Unwired Server")
				.assignToUser("dt")
				.verifyResult("Successfully deployed the workflow", true));
		
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("t=1")
			.to("dt")
			.unwiredServer("My Unwired Server"));
		
		
//		//1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.DateTime_E2E_1.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		//1.used to BB: passed
		TestResult result = Robot.run("tplan.Workflow.Controls.BB.DateTime_E2E_1.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
		
	}
}

