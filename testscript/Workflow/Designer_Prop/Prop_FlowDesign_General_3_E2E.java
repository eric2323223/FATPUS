package testscript.Workflow.Designer_Prop;
import resources.testscript.Workflow.Designer_Prop.Prop_FlowDesign_General_3_E2EHelper;
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
import component.entity.WFFlowDesigner;
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
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Prop_FlowDesign_General_3_E2E extends Prop_FlowDesign_General_3_E2EHelper
{
	/**
	 * Script Name   : <b>Prop_FlowDesign_General_3_E2E</b>
	 * Generated     : <b>Oct 25, 2011 10:36:10 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//					.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF")
//				.option(WorkFlow.SP_SERVER_INIT)
//			);
//
//		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
//				.objectQuery("findAll")
//				.subject("findall")
//				.subjectMatchingRule("findall"));
//
//		WorkFlowEditor.dragOperation(Cfg.projectName, "Department","create");
//		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"Departmentcr...");
//		WFFlowDesigner.setMarkMessageAsProcessedAfterProcessing("true");
//
//		MainMenu.saveAll();
//		vpManual("hasError",0,Problems.getErrors().size()).performTest();
//		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser("dt")
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript(), 
			"tplan.Workflow.iconcommon.BB.server_dt_icon.Script", new CallBackMethod().receiver(WorkFlowEditor.class)
									.methodName("sendNotification")
									.parameter(new Email().selectTo("dt")
											.subject("findall")
						));
		
		//2.used to BB6: vp= the Email ico is marked...
		TestResult resultBB = Robot.run("tplan.Workflow.iconcommon.BB.EmailIconMarked.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Departmentcreate").setData("Department_create_dept_id_paramKey", "24");
		s.screen("Departmentcreate").setData("Department_create_dept_name_paramKey", "mark");
		s.screen("Departmentcreate").setData("Department_create_dept_head_id_paramKey", "20");
		s.screen("Departmentcreate").menuItem("Create");
		return s;
	}
}
//need to run in 1.30: menuItem() cannot end custmeTestScript,waiting for solving