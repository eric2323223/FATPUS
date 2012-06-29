package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Locale_specific_E2EHelper;
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
import component.entity.GlobalConfig;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.ObjectQuery;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Locale_specific_E2E extends Locale_specific_E2EHelper
{
	/**
	 * Script Name   : <b>Locale_specific_E2E</b>
	 * Generated     : <b>Nov 1, 2011 1:02:09 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/01
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		//new record "1,1,10:31,2011-10-31 12:12:00" in table T_olc
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/crete_table.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("T_olc")
				.objectQuery("findAll")
				.subject("t=1")
				.subjectMatchingRule("t=")
				);
		
		WorkFlowEditor.resetWidgetInScreen("T_olcDetail",new WFEditBox().label("C:"),"Locale_Specific");
		WorkFlowEditor.resetWidgetInScreen("T_olcDetail",new WFEditBox().label("D:"),"Locale_Specific");
	
		MainMenu.saveAll();
		
	WN.createWorkFlowPackage(new WorkFlowPackage()
		.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
		.unwiredServer("My Unwired Server")
		.assignToUser("dt")
		.verifyResult("Successfully deployed the workflow", true));
	
	WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
		.subject("t=1")
		.to("dt")
		.unwiredServer("My Unwired Server"));
	
//////1.Used to Android:
//	TestResult result = Robot.run("tplan.Workflow.Controls.android.Locale_specific.Script");
//	vpManual("DeviceTest", true, result.isPass()).performTest();
	
////2.Used to BB6T: passed
	TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Locale_specific.Script");
	vpManual("DeviceTest", true, resultBB.isPass()).performTest();
	}
}

