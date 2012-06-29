package testscript.Workflow.Screens;
import java.util.ArrayList;

import resources.testscript.Workflow.Screens.DndOp_OP_update_E2EHelper;
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
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DndOp_OP_update_E2E extends DndOp_OP_update_E2EHelper
{
	/**
	 * Script Name   : <b>DndOp_OP_update_E2E</b>
	 * Generated     : <b>Sep 16, 2011 11:12:20 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/16
	 * @author FANFEI
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
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				);
		
		PropertiesView.jumpStart(new WorkFlow().mbo("AllDT")
				.objectQuery("findByPrimaryKey")
				.subject("dept_id=1")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("int1,Subject,dept_id=")
				);
		
		WorkFlowEditor.dragOperation(Cfg.projectName, "AllDT","update");
		WorkFlowEditor.renameScreenName("AllDTupdateinstance","AUI");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "AUI");
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser(Cfg.deviceUser)
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScript(), 
//		Cfg.tplanScript_server_1,
		new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to(Cfg.deviceUser)
					.subject("dept_id=4")));
		
//		//vp2:verify the new record  has added in  backend db 
//		java.util.List<String> clause = new ArrayList<String>();
//		clause.add("int1=4");
//		clause.add("string1='42'");
//		clause.add("string2='42'");
//		vpManual("dbresult",1,CDBUtil.getRecordCount("localhost", Cfg.projectName, "AllDT", clause)).performTest();
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("AUI").setData("AllDT_string1_attribKey", "42");
		s.screen("AUI").setData("AllDT_string2_attribKey", "42");
		s.screen("AUI").menuItem("Update");
		return s;
	}
}
//passed BB6T 20120308

