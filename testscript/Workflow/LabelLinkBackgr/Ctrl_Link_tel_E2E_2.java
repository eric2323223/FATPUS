package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.Ctrl_Link_tel_E2E_2Helper;
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
public class Ctrl_Link_tel_E2E_2 extends Ctrl_Link_tel_E2E_2Helper
{
	/**
	 * Script Name   : <b>Ctr_Link_tel_E2E_2</b>
	 * Generated     : <b>Oct 27, 2011 2:50:41 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/27
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//need to add the record "12,123,722" in Department
		WN.useProject(Cfg.projectName);
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//		.type("Sybase_ASA_12.x").name("My Sample Database"), 
//		GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/LabelLinkBackgr/setup/add_record3.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				);
		
		PropertiesView.jumpStart(new WorkFlow()
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("dept=1")
				.subjectMatchingRule("dept=")
				.setParameterValue("dept_id,Subject,dept=")
				);
		
		WorkFlowEditor.addScreen("myscreen");
		WorkFlowEditor.addWidget("myscreen", new WFLink().label("link")
					.newKey("link,string")
					.logicalType("Phone Number"));
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"myscreen");
		
		PropertiesView.bindKeyInScreen("myscreen","link","link,string,Department,dept_name");
		
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("dept=12")
			.to("dt")
			.unwiredServer("My Unwired Server"));

////1.used to Android:

//2.used to BB6T:PASSED
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_Link_tel_E2E_2.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();	
	}	
}
//passed 20120201
