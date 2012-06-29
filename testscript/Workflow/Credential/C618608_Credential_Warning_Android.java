package testscript.Workflow.Credential;
import resources.testscript.Workflow.Credential.C618608_Credential_Warning_AndroidHelper;
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
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;
import component.view.properties.workflow.WFControlObject;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class C618608_Credential_Warning_Android extends C618608_Credential_Warning_AndroidHelper
{
	/**
	 * Script Name   : <b>C618608_Credential_Warning_Android</b>
	 * Generated     : <b>Oct 22, 2011 12:12:42 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/22
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST)
				.mbo("Department")
				.objectQuery("findAll")
				.from("supAdmin")
				.to("supAdmin")
				.cc("supAdmin")
				.subject("all")
				.subjectMatchingRule("all")
				.received("2011-10-21")
				.body("<name>[name]</name><value>{value}</value>")
				.verifySubject("Subject,all", true));
		sleep(5);
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		ToggleGUITestObject obj = WFControlObject.getCheckBoxOnRoot("Authentication", "Use static credentials");
		obj.clickToState(NOT_SELECTED);
		MainMenu.saveAll();
		//
		Object o = obj.invoke("getSelection");
		vpManual("selected", true, o.equals(false)).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(),  
			new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
					.unwiredServer("My Unwired Server")
					.to("supAdmin")
					.from("RFT")
					.subject("all")
					.body("bodybody"))
		);
		//
		logInfo("Need manual test");
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Credential_Request").setData("cc_username", "dba");
		s.screen("Credential_Request").setData("cc_password", "sql");
		s.screen("Credential_Request").menuItem("Save");
//		s.screen("scr1").moveTo("scr2").throughMenuItem("Save Screen");
//		s.screen("scr2").setData("key1", "11");
//		s.screen("scr2").setData("key2", "AA");
//		s.screen("scr2").setData("key3", "11");
//		s.screen("scr2").moveTo("scr2").throughMenuItem("Create");
//		s.screen("scr2").moveTo("Department").throughMenuItem("findAll");
//		s.screen("Department").moveTo("DepartmentDetail").throughListItem("AA->1");
//		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
//		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
//		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		return s;
	}
		
}
