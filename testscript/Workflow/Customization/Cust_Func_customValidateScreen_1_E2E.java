package testscript.Workflow.Customization;
import java.io.File;

import resources.testscript.Workflow.Customization.Cust_Func_customValidateScreen_1_E2EHelper;
import testscript.Workflow.Customization.cfg.Config;
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

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Cust_Func_customValidateScreen_1_E2E extends Cust_Func_customValidateScreen_1_E2EHelper
{
	/**
	 * Script Name   : <b>Cust_Func_customValidateScreen_1_E2E</b>
	 * Generated     : <b>Mar 22, 2012 7:44:21 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/22
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("AllDT")
				.objectQuery("findByPrimaryKey")
				.subject("int1=1")
				.subjectMatchingRule("int1=")
				.setParameterValue("int1,Subject,int1="));
		WorkFlowEditor.link("AllDTDetail", "AllDTcreate");
		WorkFlowEditor.addMenuItem("AllDTcreate", new WFScreenMenuItem().name("save"));
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.assignToUser(Cfg.deviceUser)
				.unwiredServer("My Unwired Server")
				.deployToServer("true"), 
				customJsTestScript(), 
				"tplan.Workflow.Customization.click_server_icon.Script", 
			new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email().selectTo(Cfg.deviceUser)
						.subject("int1=1")));
		
//used to BB6T :passed 
		WFCustomizer.verifyResult(new WFClientResult().data("message=all true"));

	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setMethod("customValidateScreen", new File(Config.Cust_Func_customValidateScreen_1));
	
		s.screen("AllDTDetail").moveTo("AllDTcreate").throughMenuItem("Open AllDTcreate");
		s.screen("AllDTcreate").setData("AllDT_create_int1_paramKey", "3");
		s.screen("AllDTcreate").setData("AllDT_create_string2_paramKey", "sybase@sina.com");
		s.screen("AllDTcreate").menuItem("save");

	return s;
	}
}
//passed BB6T 20120405
