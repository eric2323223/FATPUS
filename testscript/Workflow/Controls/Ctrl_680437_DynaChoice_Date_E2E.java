package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_680437_DynaChoice_Date_E2EHelper;
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
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WFChoice;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_680437_DynaChoice_Date_E2E extends Ctrl_680437_DynaChoice_Date_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_680437_DynaChoice_Date_E2E</b>
	 * Generated     : <b>Nov 1, 2011 2:32:46 AM</b>
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
				.subject("c=1")
				.subjectMatchingRule("c=")
				);
		
		WorkFlowEditor.deleteLink(WorkFlow.SP_SERVER_INIT,"T_olc");
		WorkFlowEditor.addScreen("myscreen");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"myscreen");
		WorkFlowEditor.link("myscreen","T_olc");
		
		DOF.getWFScreenFigure(DOF.getRoot(), "myscreen").doubleClick();
		WFScreenDesigner.showPropertiesView();
		PropertiesView.setNewKeyBindMBOQueryRequest("key2,list,T_olc,c");
		
		WorkFlowEditor.addWidget("myscreen", new WFChoice().label("c:")
				.newKey("c,string")
				.option("Dynamic,key2.key1,key2.key1"));
		
		MainMenu.saveAll();
		
		//TPlan:
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("c=1")
			.to("dt")
			.unwiredServer("My Unwired Server"));

		////1.used to Android:
//	TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_680437_DynaChoice_Date.Script");
//	vpManual("DeviceTest", true, result.isPass()).performTest();
		
		//2.used to BB: passed
	TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_680437_DynaChoice_Date.Script");
	vpManual("DeviceTest", true, resultBB.isPass()).performTest();
	}	
}

