package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_672971_Boolean_Checkbox_E2EHelper;
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
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.WFSignature;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_672971_Boolean_Checkbox_E2E extends Ctrl_672971_Boolean_Checkbox_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_672971_Boolean_Checkbox_E2E</b>
	 * Generated     : <b>Dec 12, 2011 3:46:53 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/12
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//		.type("Sybase_ASA_12.x").name("My Sample Database"), 
//		GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/create_table_ffwf.sql");
		
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_employee (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Ff_wf_employee")
				.objectQuery("findAll")
				.subject("emp=0")
				.subjectMatchingRule("emp=")
				);
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("emp=1")
			.to("dt")
			.unwiredServer("My Unwired Server"));
		
	////1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_672971_Boolean_Checkbox.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		//2.used to BB: passed
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_672971_Boolean_Checkbox.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
	}
}

