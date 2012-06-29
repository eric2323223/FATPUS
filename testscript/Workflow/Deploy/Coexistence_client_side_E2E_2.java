package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.Coexistence_client_side_E2E_2Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedWorkFlow;
import component.entity.model.Email;
import component.entity.model.Module;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Coexistence_client_side_E2E_2 extends Coexistence_client_side_E2E_2Helper
{
	/**
	 * Script Name   : <b>Coexistence_client_side_E2E_2_1</b>
	 * Generated     : <b>Nov 3, 2011 2:28:36 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/03
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
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		MainMenu.saveAll();
		
		//TPlan: Deploy the first wf to device:
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser("dt")
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScript(), 
		"tplan.Workflow.iconcommon.BB.server_dt_icon.Script", new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to("dt")
					.subject("findall")));
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Department_dept_id_attribKey,value=100|" +
				"id=Department_dept_name_attribKey,value=R & D|" +
				"id=Department_dept_head_id_attribKey,value=501"));
		

		//modify wf:
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		PropertiesView.jumpStart(new WorkFlow().subject("two")
						  .subjectMatchingRule("two"));
		WorkFlowEditor.setWorkFlow(new WorkFlow().name("myWF").version("2"));
		
		//!!!!!!!!!!!!!!ffan Note:need to change WF icon to Airplane icon,TBD......
		DOF.getClientIcon(DOF.getRoot(),"Client icon:").click();
		//need to click airplane icon
		sleep(5);
		
		MainMenu.saveAll();
		
		//TPlan: Deploy the second wf to device:
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser("dt")
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScriptTwo(), 
		"tplan.Workflow.iconcommon.BB.server_dt_icon2.Script", new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to("dt")
					.subject("two")));
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Department_dept_id_attribKey,value=200|" +
				"id=Department_dept_name_attribKey,value=Sales|" +
				"id=Department_dept_head_id_attribKey,value=902"));
		
		//vp: the two WF icon are coexist on device: 
		//2.used to BB: passed
		TestResult resultBB = Robot.run("tplan.Workflow.iconcommon.BB.server_dt_icon_deploy.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("100->0");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		return s;
	}
	private CustomJsTestScript customTestScriptTwo() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("200->0");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		return s;
	}
}
//passed BB6T 20120210 need to change WF icon to AirPlane in line 94