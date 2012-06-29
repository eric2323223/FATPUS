package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.Android3_0_Functions1_E2EHelper;
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
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
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
public class Android3_0_Functions1_E2E extends Android3_0_Functions1_E2EHelper
{
	/**
	 * Script Name   : <b>Android3_0_Functions1</b>
	 * Generated     : <b>Mar 19, 2012 2:33:07 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/19
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
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		
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
		
		PropertiesView.setAuthentication("supAdmin","s3pAdmin");
		MainMenu.saveAll();
		
		//used to Android3:
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"),
			 customTestScript()
//			 , 
//			"tplan.Workflow.iconcommon.Android.myWF_Client_android3.Script"
			 );

		WFCustomizer.verifyResult(new WFClientResult().data(
			"id=Department_dept_id_attribKey,value=100")); 
		
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Credential_Request").setData("cc_username","supAdmin");
		s.screen("Credential_Request").setData("cc_password","s3pAdmin");
		s.screen("Credential_Request").moveTo("Start").throughMenuItem("Save");
		s.screen("Start").setData("dept_id", "100");
		s.screen("Start").moveTo("DepartmentDetail").throughMenuItem("findByID");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		return s;
	}
}
//passed Android3 20120320

