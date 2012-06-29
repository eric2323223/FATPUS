package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.WF_Update_Uncheck_Client_E2EHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedWorkFlow;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class WF_Update_Uncheck_Client_E2E extends WF_Update_Uncheck_Client_E2EHelper
{
	/**
	 * Script Name   : <b>WF_Update_Uncheck_Client_E2E</b>
	 * Generated     : <b>Nov 2, 2011 10:44:11 PM</b>
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
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser("dt")
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScript(), 
		"tplan.Workflow.iconcommon.BB.myWF_icon.Script");
		
		vpManual("deploy", true, EE.ifWorkFlowDeployed(new DeployedWorkFlow().unwiredServer("My Unwired Server")
				.version("1")
				.name("myWF")));

		WFCustomizer.verifyResult(new WFClientResult().data(
			"id=Department_dept_id_attribKey,value=100|" +
			"id=Department_dept_name_attribKey,value=R & D|" +
			"id=Department_dept_head_id_attribKey,value=501" ));
		
		//modify the WF:
		WorkFlowEditor.removeScreen("Department_create");
		WorkFlowEditor.setWorkFlow(new WorkFlow().name(Cfg.wfName).version("2"));
		
		//!!!Note: need to change the WF icon to AirPlane Icon,TBD=Waiting for solving..
		DOF.getClientIcon(DOF.getRoot(),"Client icon:").click();
		sleep(5);
		
		//not assign WF:
		WN.createWorkFlowPackage(new WorkFlowPackage()
		.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
		.assignToUser("null")
		.unwiredServer("My Unwired Server")
		.verifyResult("Successfully deployed the workflow", true));
		
		vpManual("deploy", true, EE.ifWorkFlowDeployed(new DeployedWorkFlow().unwiredServer("My Unwired Server")
				.version("2")
				.name("myWF")));
		
//		////1.used to Android:
////	TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_680437_DynaChoice_Date.Script");
////	vpManual("DeviceTest", true, result.isPass()).performTest();

//		2.used to BB6T: passed
	TestResult resultBB = Robot.run("tplan.Workflow.iconcommon.BB.myWF_icon.Script");
	vpManual("DeviceTest", true, resultBB.isPass()).performTest();
	}
	
	private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Start_Screen").moveTo("Department").throughMenuItem("findall");
			s.screen("Department").moveTo("DepartmentDetail").throughListItem("100->0");
			s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
			s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
			s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
			return s;
	}
}
//passed BB6T 20120215 :need to change WF icon to AirPlane in line 91
