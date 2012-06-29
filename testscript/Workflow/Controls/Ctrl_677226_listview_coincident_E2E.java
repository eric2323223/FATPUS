package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_677226_listview_coincident_E2EHelper;
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
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_677226_listview_coincident_E2E extends Ctrl_677226_listview_coincident_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_677226_listview_coincident_E2E</b>
	 * Generated     : <b>Dec 13, 2011 10:05:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/13
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
		        .objectQuery("findAll")
		        .subject("dept=1")
		        .subjectMatchingRule("dept=")
				);
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
				.unwiredServer("My Unwired Server")
				.assignToUser("dt")
				.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
				.subject("dept=1")
				.to("dt")
				.unwiredServer("My Unwired Server"));
		
		////1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_677226_listview_coincident.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		////2.used to BB:passed
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_677226_listview_coincident.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
	}
}

