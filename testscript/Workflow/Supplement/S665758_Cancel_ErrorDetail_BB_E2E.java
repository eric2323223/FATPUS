package testscript.Workflow.Supplement;
import java.util.ArrayList;

import resources.testscript.Workflow.Supplement.S665758_Cancel_ErrorDetail_BB_E2EHelper;
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
import com.sybase.automation.framework.common.CDBUtil;

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
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S665758_Cancel_ErrorDetail_BB_E2E extends S665758_Cancel_ErrorDetail_BB_E2EHelper
{
	/**
	 * Script Name   : <b>S665758_Cancel_ErrorDetail_BB</b>
	 * Generated     : <b>Mar 13, 2012 4:48:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/13
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)",10,10);
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
			    .objectQuery("findAll")
			    .subject("findall")
			    .subjectMatchingRule("findall"));
		
		WorkFlowEditor.link("Department","Departmentcr...");
		WorkFlowEditor.setMenuItem("Departmentcreate",new WFScreenMenuItem().name("Create")
				.generateErrorScreen(true)
				.defaultSuccessScreen("DepartmentDetail"));
		
		MainMenu.saveAll();
		vpManual("screen",true,WorkFlowEditor.hasScreen("ErrorList")).performTest();
		vpManual("screen",true,WorkFlowEditor.hasScreen("ErrorDetail")).performTest();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(Cfg.projectName+"->"+Cfg.wfName)
			.unwiredServer("My Unwired Server")
			.assignToUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, Cfg.wfName+".xbw", new Email()
			.subject("findall")
			.to(Cfg.deviceUser)
			.unwiredServer("My Unwired Server"));

		
////1.used to Android:passed 
//	TestResult resultAndroid = Robot.run("tplan.Workflow.Supplement.Android.S665758_Cancel_ErrorDetail_Android.Script");
//	vpManual("DeviceTest", true, resultAndroid.isPass()).performTest();
//
////2. BB6T :passed
//	TestResult resultBB = Robot.run("tplan.Workflow.Supplement.BB.S665758_Cancel_ErrorDetail_BB.Script");
//	vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
	}
}
//passed BB6T and Android 20120319
