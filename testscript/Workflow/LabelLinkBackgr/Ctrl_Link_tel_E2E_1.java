package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.Ctrl_Link_tel_E2E_1Helper;
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
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WFLabel;
import component.entity.model.WFLink;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_Link_tel_E2E_1 extends Ctrl_Link_tel_E2E_1Helper
{
	/**
	 * Script Name   : <b>Ctrl_Link_tel_E2E_1</b>
	 * Generated     : <b>Oct 26, 2011 10:51:20 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/26
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
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				);
		
		WorkFlowEditor.addScreen("myscreen");
		WorkFlowEditor.addWidget("myscreen", new WFLink().label("link")
			.newKey("link,string")
			.defaultValue("123456")
			.logicalType("Phone Number"));
		
		WorkFlowEditor.link("Start Screen","myscreen");
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
	
////Verify: 1.Android has tip:Unsupported action - that action is not currently supported.
	
//2.used to BB6T:PASSED
	TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_Link_tel_E2E_1.Script");
	vpManual("DeviceTest", true, resultBB.isPass()).performTest();	
	}
}
//passed 20120201
