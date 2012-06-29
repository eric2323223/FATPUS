package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.Ctrl_Link_showUtrlInBrowser_E2E_2Helper;
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
import com.sybase.automation.framework.widget.DOF;

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
import component.entity.model.WFLink;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_Link_showUtrlInBrowser_E2E_2 extends Ctrl_Link_showUtrlInBrowser_E2E_2Helper
{
	/**
	 * Script Name   : <b>Ctrl_Link_showUtrlInBrowser_E2E_2</b>
	 * Generated     : <b>Oct 27, 2011 4:15:37 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/27
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//Need to add the record "10,http://www.google.com,26" in Department table
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				);
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("dept=1")
				.subjectMatchingRule("dept=")
				.setParameterValue("dept_id,Subject,dept="));
		
		WorkFlowEditor.addScreen("myscreen");
		WorkFlowEditor.addWidget("myscreen", new WFLink().label("link")
					.newKey("link,string")
					.logicalType("Open External URL"));
		
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"myscreen");
		
		PropertiesView.bindKeyInScreen("myscreen","link","link,string,Department,dept_name");
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("dept=10")
			.to("dt")
			.unwiredServer("My Unwired Server"));
		
		//Verify: when click the link "http://www.google.com",show the home page of google
		//1.used to Android:


		//2.used to BB6T:PASSED
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.Ctrl_Link_showUtrlInBrowser_E2E_2.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();	
	}
}
//passed 20120201
