package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_674522_addctr_listview_E2EHelper;
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
import component.entity.model.WFEditBox;
import component.entity.model.WFSignature;
import component.entity.model.WFSlider;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_674522_addctr_listview_E2E extends Ctrl_674522_addctr_listview_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_674522_addctr_listview_E2E</b>
	 * Generated     : <b>Nov 2, 2011 5:09:27 AM</b>
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
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("T_olc")
				.objectQuery("findAll")
				.subject("a=1")
				.subjectMatchingRule("a="));
		
		WorkFlowEditor.addWidget("T_olc", new WFEditBox().label("myEdit:")
				.newKey("key1,string"));
	
		WorkFlowEditor.addWidget("T_olc", new WFSlider().label("mySlider:")
				.maxValue("20")
				.minValue("0")
				.newKey("key2,int"));
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("a=1")
			.to("dt")
			.unwiredServer("My Unwired Server"));
		
		////1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_674522_addctr_listview.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		////2.used to BB:passed
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_674522_addctr_listview.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
	}
}

