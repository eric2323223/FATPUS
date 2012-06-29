package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Listview_OverManyCellLines_E2EHelper;
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
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.WFEditBox;
import component.entity.model.WFNewscreen;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Ctrl_Listview_OverManyCellLines_E2E extends Ctrl_Listview_OverManyCellLines_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_Listview_OverManyCellLines_Android</b>
	 * Generated     : <b>Sep 2, 2011 3:56:33 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/02
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->employee (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("Employee")
				.objectQuery("findAll")
				.subject("emp=1")
				.subjectMatchingRule("emp="));
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("DT")
			.verifyResult("Successfully deployed the workflow", true));
		
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("emp=4")
			.to("DT")
			.unwiredServer("My Unwired Server"));
		
		//1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_Listview_OverManyCellLines.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		//2.used to BB:passed
		TestResult result = Robot.run("tplan.Workflow.Controls.BB.Ctrl_Listview_OverManyCellLines.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
		
	}
}
//passed with Tplan
