package testscript.Workflow.ConditionalScreen;
import java.util.ArrayList;

import resources.testscript.Workflow.ConditionalScreen.CdtnScr_Request_CRUD_01_Delete_E2EHelper;
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
import com.sybase.automation.framework.common.DBUtil;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Modifications;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class CdtnScr_Request_CRUD_01_Delete_E2E extends CdtnScr_Request_CRUD_01_Delete_E2EHelper
{
	/**
	 * Script Name   : <b>CdtnScr_Request_CRUD_01_Delete_E2E</b>
	 * Generated     : <b>Feb 29, 2012 9:11:24 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/02/29
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//delete:
		 WN.useProject(Cfg.projectName);
		 EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
			 .server("My Unwired Server")
			 .mode(DeployOption.MODE_REPLACE)
			 .serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("id:").newKey("id,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findByPK")
				.type("Online Request")
				.mbo("States")
				.objectQuery("findByPrimaryKey")
				.project(Cfg.projectName)
				.defaultSuccessScreen("StatesDetail")
				.parametermapping("state_id,id")
				);
	
		WorkFlowEditor.setMenuItem("Statesdeleteinstance", 	new WFScreenMenuItem().name("Delete")
				.type("Online Request")
				.defaultSuccessScreen("StatesDetail"));
		
		PropertiesView.addConditionTableOfMenuItem("c1", "Start");
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser)
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), script()
//				, 
//				testscript.Workflow.cfg.Cfg.tplanScript_client_1
				);
		
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=States_state_id_attribKey,value=CH|"+
				"id=id,value=CH"));	
		
		//vp2:verify the delete record  has deleted into backend db in state
		vpManual("db",0,DBUtil.getDB("select * from states where state_id='CH' and state_name='China'")).performTest();
		
	}
	
	private CustomJsTestScript script() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setConditionScreenRule(Cfg.CdtnScr_Request_CRUD_01_E2E);
		s.screen("Start").setData("id","CH");
		s.screen("Start").moveTo("StatesDetail").throughMenuItem("findByPK");
		s.screen("StatesDetail").getData("States_state_id_attribKey");
		s.screen("StatesDetail").moveTo("Statesdeleteinstance").throughMenuItem("Open Statesdeleteinstance");
		//delete:>>>>>>>>>>>>>>
		s.screen("Statesdeleteinstance").moveTo("Start").throughMenuItem("Delete");
		s.screen("Start").getData("id");
		return s;
	}
	
	
}
//passed BB6T 20120302 