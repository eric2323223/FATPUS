package testscript.Workflow.ConditionalScreen;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.common.DBUtil;

import resources.testscript.Workflow.ConditionalScreen.CdtnScr_Request_CRUD_01_Update_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.Modification;
import component.entity.model.Modifications;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CdtnScr_Request_CRUD_01_Update_E2E extends CdtnScr_Request_CRUD_01_Update_E2EHelper
{
	/**
	 * Script Name   : <b>CdtnScr_Request_CRUD_01_E2E</b>
	 * Generated     : <b>Nov 11, 2011 1:59:42 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/11
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		//update:
		
		//delete the date which will be created in the following code
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "delete from States where state_id='CH'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//create the record to update>>>>>>>
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "insert into States values('CH','China','BJ','SX','South')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		MainMenu.saveAll();
		sleep(1);
		WorkFlowEditor.setMenuItem("Statesupdateinstance", 	new WFScreenMenuItem().name("Update")
				.type("Online Request")
				.defaultSuccessScreen("StatesDetail"));
		
		PropertiesView.addConditionTableOfMenuItem("c1", "Start");
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser)
			.deployToServer("true")
			.unwiredServer("My Unwired Server"), script()
//			, 
//			testscript.Workflow.cfg.Cfg.tplanScript_client_1
			);
		
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=States_state_id_attribKey,value=AB|"+
				"id=id,value=AB"));	
		
		//vp2:verify the update record  has added into backend db in state
		vpManual("db",1,DBUtil.getDB("select * from states where state_id='AB' and state_name='Sin City'")).performTest();
		
	}
	
	private CustomJsTestScript script() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setConditionScreenRule(Cfg.CdtnScr_Request_CRUD_01_E2E);
		s.screen("Start").setData("id","AB");
		s.screen("Start").moveTo("StatesDetail").throughMenuItem("findByPK");
		s.screen("StatesDetail").getData("States_state_id_attribKey");
		//update:
		s.screen("StatesDetail").moveTo("Statesupdateinstance").throughMenuItem("Open Statesupdateinstance");
		s.screen("Statesupdateinstance").setData("States_state_name_attribKey", "Sin City");
		s.screen("Statesupdateinstance").moveTo("Start").throughMenuItem("Update");
		s.screen("Start").getData("id");
		return s;
	}
	
}
//passed 20120307
