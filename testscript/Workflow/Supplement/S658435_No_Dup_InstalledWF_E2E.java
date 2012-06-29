package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S658435_No_Dup_InstalledWF_E2EHelper;
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
public class S658435_No_Dup_InstalledWF_E2E extends S658435_No_Dup_InstalledWF_E2EHelper
{
	/**
	 * Script Name   : <b>S658435_No_Dup_InstalledWF_E2E</b>
	 * Generated     : <b>Mar 20, 2012 8:40:07 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/20
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addWidget("Start",new WFEditBox().label("dept_id:")
				.newKey("dept_id,int"));
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findByID")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("DepartmentDetail")
				.parametermapping("dept_id,dept_id"));
		
		MainMenu.saveAll();
		
		//deploy the WorkFlowPackage for the first time:
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(Cfg.projectName+"->"+Cfg.wfName)
			.unwiredServer("My Unwired Server")
			.assignToUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true));
		
		//1.used to Android:the first time:
		TestResult result = Robot.run("tplan.Workflow.Supplement.Android.S668687_validation_for_dynachoice_Android.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		
		//2_1.used to BB:the first time:
		TestResult resultB= Robot.run("tplan.Workflow.Supplement.BB.S668687_validation_for_dynachoice_BB.Script");
		vpManual("DeviceTest", true, resultB.isPass()).performTest();	
		
		//Note:need to modify the workflow icon as airplane..TBD>>>
		
		
		//deploy the WorkFlowPackage for the second time:
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(Cfg.projectName+"->"+Cfg.wfName)
			.unwiredServer("My Unwired Server")
			.assignToUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true));
		
	
		//1_2.used to BB :the second time:
	TestResult resultAndroid = Robot.run("tplan.Workflow.Supplement.Android.S668687_validation_for_dynachoice_2_Android.Script");
	vpManual("DeviceTest", true, resultAndroid.isPass()).performTest();
		
	//2_2.used to BB:passed
	TestResult resultBB= Robot.run("tplan.Workflow.Supplement.BB.S668687_validation_for_dynachoice_2_BB.Script");
	vpManual("DeviceTest", true, resultBB.isPass()).performTest();	
	}
}

