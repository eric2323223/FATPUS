package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.WF_Update_uncheck_subscription_E2EHelper;
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
public class WF_Update_uncheck_subscription_E2E extends WF_Update_uncheck_subscription_E2EHelper
{
	/**
	 * Script Name   : <b>WF_Update_uncheck_subscription_E2E</b>
	 * Generated     : <b>Nov 3, 2011 6:10:30 AM</b>
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
				.option(WorkFlow.SP_ACTIVATE)
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		//deploy the first wf to device:
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser("dt"),
			customTestScript(), 
			"tplan.Workflow.iconcommon.BB.server_dt_icon.Script", new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
				.unwiredServer("My Unwired Server")
				.to("dt")
				.subject("findall")));
		
		vpManual("deploy", true, EE.ifWorkFlowDeployed(new DeployedWorkFlow().unwiredServer("My Unwired Server")
				.version("1")
				.name("myWF")));
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Department_dept_id_attribKey,value=100|" +
				"id=Department_dept_name_attribKey,value=R & D|" +
				"id=Department_dept_head_id_attribKey,value=501" ));
		
		//modify wf:
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		PropertiesView.jumpStart(new WorkFlow().subject("two")
						  .subjectMatchingRule("two"));
		WorkFlowEditor.setWorkFlow(new WorkFlow().name("myWF").version("2"));
		
		//!!!Note: need to change the WF icon to AirPlane Icon,TBD=Waiting for solving..
		DOF.getClientIcon(DOF.getRoot(),"Client icon:").click();
		sleep(5);
		
		MainMenu.saveAll();
		
		//unassign to user:
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->"+"myWF")
				.deployToServer("true")
				.assignToUser("null")
				.unwiredServer("My Unwired Server"));
		
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
				.unwiredServer("My Unwired Server")
				.to("dt")
				.subject("two"));
		
		vpManual("deploy", true, EE.ifWorkFlowDeployed(new DeployedWorkFlow().unwiredServer("My Unwired Server")
				.version("2")
				.name("myWF")));

		//vp:used to vp the icon could coexist in the same device 
//		2.used to BB6T: 
		TestResult resultBB = Robot.run("tplan.Workflow.iconcommon.BB.server_dt_icon_deploy.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
}
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Activate_Screen").moveTo("Department").throughMenuItem("Submit Workflow");
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("100->0");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		return s;
	}
}
