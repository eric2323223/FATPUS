package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_LView_OnEmptyList_2_E2EHelper;
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
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WFLview;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Ctrl_LView_OnEmptyList_2_E2E extends Ctrl_LView_OnEmptyList_2_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_LView_OnEmptyList_2_Android</b>
	 * Generated     : <b>Aug 23, 2011 9:39:00 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/23
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				);
		PropertiesView.jumpStart(new WorkFlow()
				.mbo("T_olc")
				.objectQuery("findByPrimaryKey")
				.subject("t=1")
				.subjectMatchingRule("t=")
				.setParameterValue("a,Subject,t="));
		WorkFlowEditor.dragMbo(Cfg.projectName, "T_olc");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "T_olc");
		
		DOF.getWFScreenFigure(DOF.getRoot(), "T_olc").doubleClick();
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click();
		PropertiesView.maximize();
		PropertiesView.clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "On empty list:"), "~!@#$%^&*()_+:&^%");
		MainMenu.saveAll();
		PropertiesView.restore();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("t=2")
			.to("dt")
			.unwiredServer("My Unwired Server"));
		
//      //1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_LView_OnEmptyList_2.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
      //1.used to BB:
		TestResult result = Robot.run("tplan.Workflow.Controls.BB.Ctrl_LView_OnEmptyList_2.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
	}
}
//passed with TPlan

