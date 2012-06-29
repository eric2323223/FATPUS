package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.WF_Replace_Check_Client_E2EHelper;
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
public class WF_Replace_Check_Client_E2E extends WF_Replace_Check_Client_E2EHelper
{
	/**
	 * Script Name   : <b>WF_Replace_check_client_E2E_1</b>
	 * Generated     : <b>Nov 3, 2011 2:54:19 AM</b>
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
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser("dt")
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript(), 
			"tplan.Workflow.iconcommon.BB.myWF_icon.Script");
		
		WFCustomizer.verifyResult(new WFClientResult().data(
			"id=Department_dept_id_attribKey,value=100|" +
			"id=Department_dept_name_attribKey,value=R & D|" +
			"id=Department_dept_head_id_attribKey,value=501" ));
		
		//Modify  wf:
		WorkFlowEditor.removeScreen("Department_create");
		
		//!!!Note: need to change the WF icon to AirPlane Icon,TBD=Waiting for solving..
		DOF.getClientIcon(DOF.getRoot(),"Client icon:").click();
		sleep(5);
		
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser("dt")
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript(), 
			"tplan.Workflow.iconcommon.BB.myWF_icon2.Script");
		
		WFCustomizer.verifyResult(new WFClientResult().data(
			"id=Department_dept_id_attribKey,value=100|" +
			"id=Department_dept_name_attribKey,value=R & D|" +
			"id=Department_dept_head_id_attribKey,value=501" ));
		
//		2.used to BB6T: 
		TestResult resultBB = Robot.run("tplan.Workflow.iconcommon.BB.server_dt_icon_deploy.Script");
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
//Passed BB6T 20120215 :Only need to change the WF icon to AirPlane Icon in line 85
