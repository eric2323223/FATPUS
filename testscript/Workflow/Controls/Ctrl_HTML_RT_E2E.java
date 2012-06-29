package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_HTML_RT_E2EHelper;
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
import component.entity.model.Email;
import component.entity.model.ObjectQuery;
import component.entity.model.StartPoint;
import component.entity.model.WFCheckbox;
import component.entity.model.WFHtmlView;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_HTML_RT_E2E extends Ctrl_HTML_RT_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_HTML_RT_E2E</b>
	 * Generated     : <b>Oct 30, 2011 9:48:52 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/31
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("dept=1")
				.subjectMatchingRule("dept=")
				.setParameterValue("dept_id,Subject,dept=")
				);
		WorkFlowEditor.addScreen("myscreen");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"myscreen");
		WorkFlowEditor.addWidget("myscreen", new WFHtmlView()
				.newKeyBindMbo("key1,string,Department,dept_name"));
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
//			.assignToUser("DT")
			.assignToUser("dt")  //use to BB
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("dept=100")
			.to("dt")
			.unwiredServer("My Unwired Server"));
		
		//1.used to Android:pass
		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_HTML_RT.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		//2.used to BB:pass
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_HTML_RT.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
	}
}

//passed with TPlan
