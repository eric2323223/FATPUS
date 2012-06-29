package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.One2One_Ctrl_Signature_E2EHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.WFHtmlView;
import component.entity.model.WFSignature;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class One2One_Ctrl_Signature_E2E extends One2One_Ctrl_Signature_E2EHelper
{
	/**
	 * Script Name   : <b>One2One_Ctrl_Signature_E2E</b>
	 * Generated     : <b>Nov 2, 2011 3:16:48 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/02
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_department (dba)"), 10, 10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_employee (dba)"), 100, 10);
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Ff_wf_department"))
				.name("ffWfEmployees")
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
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfOtosignature")
				.option(WorkFlow.SP_SERVER_INIT));

		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Ff_wf_department")
				.objectQuery("findByPrimaryKey")
				.subject("dept=0")
				.subjectMatchingRule("dept=")
				.setParameterValue("dept_id,Subject,dept="));

		WorkFlowEditor.addScreen("myscreen");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "myscreen");
		
		WorkFlowEditor.addWidget("myscreen", new WFSignature().label("emp_sign:")
				.newKeyBindMbo("id,string,Ff_wf_employee,emp_sign"));
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "wfOtosignature"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "wfOtosignature.xbw", new Email()
			.subject("dept=1")
			.to("dt")
			.unwiredServer("My Unwired Server"));
	
		////1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.One2One_Ctrl_Signature.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		//2.used to BB:
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.One2One_Ctrl_Signature.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
	}
}
//passed with TPlan
