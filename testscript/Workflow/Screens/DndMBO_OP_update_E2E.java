package testscript.Workflow.Screens;
import java.util.ArrayList;

import resources.testscript.Workflow.Screens.DndMBO_OP_update_E2EHelper;
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
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DndMBO_OP_update_E2E extends DndMBO_OP_update_E2EHelper
{
	/**
	 * Script Name   : <b>DndMBO_OP_update_E2E_Android</b>
	 * Generated     : <b>Sep 13, 2011 8:14:41 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/13
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//					.mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow().mbo("AllDT")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall")
				);
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllDT");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "AllDT");
		WorkFlowEditor.setMenuItem("AllDTupdateinstance", new WFScreenMenuItem().name("Update").defaultSuccessScreen("AllDTDetail"));
		MainMenu.saveAll();
		
		
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser(Cfg.deviceUser)
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScript(), 
//		"tplan.Workflow.iconcommon.BB.server_dt_icon.Script", 
		new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to(Cfg.deviceUser)
					.subject("findall")));
		
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=AllDT_int1_attribKey,value=3|"+
				"id=AllDT_string1_attribKey,value=3|"+
				"id=AllDT_int1_attribKey,value=3"));
		
//		//vp2:verify the update record  has changed in  backend db 
//		java.util.List<String> clause = new ArrayList<String>();
//		clause.add("int1=3");
//		clause.add("string1='32'");
//		clause.add("string2='332'");
//		vpManual("dbresult",1,CDBUtil.getRecordCount("Localhost", "wf", "AllDT", clause)).performTest();
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("AllDT").moveTo("AllDTDetail").throughListItem("3->0");
		s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
		s.screen("AllDTDetail").getData("AllDT_string1_attribKey");
		s.screen("AllDTDetail").moveTo("AllDTupdateinstance").throughMenuItem("Open AllDTupdateinstance");
		
		s.screen("AllDTupdateinstance").setData("AllDT_string1_attribKey", "32");
		s.screen("AllDTupdateinstance").setData("AllDT_string2_attribKey", "332");
		
		s.screen("AllDTupdateinstance").moveTo("AllDTDetail").throughMenuItem("Update");
		s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
		return s;
	}
}
//passed BB6T 20120203
