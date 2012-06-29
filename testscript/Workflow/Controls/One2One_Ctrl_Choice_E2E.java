package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.One2One_Ctrl_Choice_E2EHelper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.WFChoice;
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
public class One2One_Ctrl_Choice_E2E extends One2One_Ctrl_Choice_E2EHelper
{
	/**
	 * Script Name   : <b>One2One_Ctrl_Choice_E2E_Android</b>
	 * Generated     : <b>Sep 1, 2011 10:57:29 AM</b>
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
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_department (dba)"), 10, 10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_employee (dba)"), 100, 10);
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Ff_wf_department"))
				.name("employees")
				.mapping("dept_id,dept_id")
				.composite("true")
				.target("Ff_wf_employee")
				.type(Relationship.TYPE_OTO));
		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
		vpManual("deploy", true, EE.ifMboDeployed(new DeployedMbo()
					.connectionProfile("My Unwired Server")
					.domain("default")
					.name("Ff_wf_department")
					.pkg(Cfg.projectName+":1.0"))).performTest();
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wfOtochoice")
				.option(WorkFlow.SP_SERVER_INIT));
		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Ff_wf_department")
				.objectQuery("findByPrimaryKey")
				.subject("dept=0")
				.subjectMatchingRule("dept=")
				.setParameterValue("dept_id,Subject,dept="));

		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
		PropertiesView.setNewKeyBindMBORelationship("Screen Design,key2,string,Ff_wf_department,employees,emp_name");
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfOtochoice.xbw", "linktoemail", new WFChoice().label("emp_name:")
				.newKey("emp_name,string")
				.option("Dynamic,key2.key1,key2.key1"));

		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "wfOtochoice"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "wfOtochoice.xbw", new Email()
				.subject("dept=1")
				.to("dt")
				.unwiredServer("My Unwired Server"));
		
		//1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.One2One_Ctrl_Choice.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
	////	2.used to BB:passed
		TestResult result = Robot.run("tplan.Workflow.Controls.BB.One2One_Ctrl_Choice.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
		
	}
}

