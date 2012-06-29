package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S676421_editbox_position_E2EHelper;
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
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S676421_editbox_position_E2E extends S676421_editbox_position_E2EHelper
{
	/**
	 * Script Name   : <b>S676421_editbox_position_E2E</b>
	 * Generated     : <b>Mar 13, 2012 8:14:21 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/13
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("left:")
				.newKey("c1,int")
				.labelPosition("LEFT"));
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("top:")
				.newKey("c2,int")
				.labelPosition("TOP"));
		sleep(0.5);
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("right:")
				.newKey("c3,int")
				.labelPosition("RIGHT"));
		
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(Cfg.projectName+"->"+Cfg.wfName)
			.unwiredServer("My Unwired Server")
			.assignToUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true));
		
////////2.used to BB6:passed
		TestResult resultBB = Robot.run("tplan.Workflow.Supplement.BB.S676421_editbox_position.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
//////2.used to Android:passed
		TestResult resultAndroid = Robot.run("tplan.Workflow.Supplement.Android.S676421_editbox_position_Android.Script");
		vpManual("DeviceTest", true, resultAndroid.isPass()).performTest();
	}
}
//passed  BB6T,Android 20120314
