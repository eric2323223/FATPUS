package testscript.Workflow.ConditionalScreen;
import java.util.ArrayList;

import resources.testscript.Workflow.ConditionalScreen.CdtnScr_Request_Alltypes_1_E2EHelper;
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
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CdtnScr_Request_Alltypes_1_E2E extends CdtnScr_Request_Alltypes_1_E2EHelper
{
	/**
	 * Script Name   : <b>CdtnScr_Request_Alltypes_1_E2E</b>
	 * Generated     : <b>Nov 10, 2011 9:52:53 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/10
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF")
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
//		WorkFlowEditor.addWidget("Start", new WFEditBox().label("id")
//				.newKey("id,string"));
//		
//		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
//				.name("item1")
//				.type("Online Request")
//				.mbo("States")
//				.objectQuery("findByPrimaryKey")
//				.project(Cfg.projectName)
//				.defaultSuccessScreen("StatesDetail")
//				.parametermapping("state_id,id")
//				);
//		
//		WorkFlowEditor.setMenuItem("Statesupdateinstance", 	new WFScreenMenuItem().name("Update")
//				.type("Online Request")
//				.defaultSuccessScreen("StatesDetail"));
//		
//		PropertiesView.addConditionTableOfMenuItem("c1", "Start");
//		
//		WFCustomizer.runTest(new WorkFlowPackage()
//				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser)
//				.deployToServer("true")
//				.unwiredServer("My Unwired Server"), script()
////				, 
////				testscript.Workflow.cfg.Cfg.tplanScript_client_1
//				);
//		
//		//vp:data in device
//		WFCustomizer.verifyResult(new WFClientResult().data(
//				"id=id,value=AB"));	
//		
//		//vp2:verify the update record  has added into backend db in state
//		java.util.List<String> clause = new ArrayList<String>();
//		clause.add("state_id='AB'");
//		clause.add("state_name='Sin City'");
//		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("localhost", "wf", "States", clause)).performTest();
//	}
//	
//	private CustomJsTestScript script() {
//		CustomJsTestScript s = new CustomJsTestScript();
//		s.setConditionScreenRule(Cfg.CdtnScr_Request_Alltypes_1_E2E);
//		s.screen("Start").setData("id","AB");
//		s.screen("Start").moveTo("StatesDetail").throughMenuItem("item1");
//		s.screen("StatesDetail").moveTo("Statesupdateinstance").throughMenuItem("Open Statesupdateinstance");
//		s.screen("Statesupdateinstance").setData("States_state_name_attribKey", "Sin City");
//		s.screen("Statesupdateinstance").moveTo("Start").throughMenuItem("Update");
//		s.screen("Start").getData("id");
//		return s;
	}
}
//passed BB6T 20120229

