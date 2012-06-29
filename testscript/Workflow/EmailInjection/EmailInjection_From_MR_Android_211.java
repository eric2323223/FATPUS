package testscript.Workflow.EmailInjection;
import resources.testscript.Workflow.EmailInjection.EmailInjection_From_MR_Android_211Helper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class EmailInjection_From_MR_Android_211 extends EmailInjection_From_MR_Android_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 31, 2011 1:49:12 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/31
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test EmailInjection_From_MR_Android
//		WN.useProject(Cfg.projectName);
//		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->mydepartment (dba)"), 10, 10);
//		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//					.server("My Unwired Server")
//					.serverConnectionMapping("My Sample Database,sampledb"));
		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("emailinjectionfrom")
//				.option(WorkFlow.SP_SERVER_INIT));


//		PropertiesView.jumpStart(new WorkFlow()
//		        .mbo("Mydepartment")
//				.objectQuery("findAll")
//				.from("frommr")
//				.fromMatchingRule("frommr")
//				);
//		
//		WorkFlowEditor.dragMbo(Cfg.projectName, "Mydepartment");
//		
//		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"Mydepartment");
//		
//		WFCustomizer.runTest(new WorkFlowPackage()
//		.startParameter(WN.filePath(Cfg.projectName, "emailinjectionfrom"))
//		.unwiredServer("My Unwired Server")
//		.deployToServer("true")
//		.assignToSelectedUser("AN"),
//		customTestScript(),
//		"", new CallBackMethod().receiver(WorkFlowEditor.class)
//		.methodName("sendNotification")
//		.parameter(new Email()
//		.unwiredServer("My Unwired Server")
//		.to("AN")
//		.from("frommr")
//		.subject("testfrommr")));
//		//Verify: Android has tip:Unsupported action - that action is not currently supported.
//		WFCustomizer.verifyResult(new WFClientResult().data("found=true"));
//		}
//
//		private CustomJsTestScript customTestScript() {
//		CustomJsTestScript s = new CustomJsTestScript();
//		s.screen("Mydepartment").checkListItem("100", "0");
//		
//		
//		return s;
	}
}

