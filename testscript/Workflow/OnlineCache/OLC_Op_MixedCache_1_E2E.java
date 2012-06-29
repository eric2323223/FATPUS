package testscript.Workflow.OnlineCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import resources.testscript.Workflow.OnlineCache.OLC_Op_MixedCache_1_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.common.DBUtil;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_MixedCache_1_E2E extends OLC_Op_MixedCache_1_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_MixedCache_1_E2E</b>
	 * Generated     : <b>Oct 26, 2011 7:27:52 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/26
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createCacheGroup(new CacheGroup().startParameter(Cfg.projectName)
				.name("online")
				.type(CachePolicy.ONLINE));
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		WN.changeCacheGroup(Cfg.projectName, "States", "Default (Default)", "online");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "States");
		mbo.setMboDefinition("sql", "select * from states where state_name=:name");
		mbo.setLoadArgument(new LoadArgument().name("name").propagateTo("state_name"));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT)
				.name(Cfg.wfName));
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("state").newKey("key,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findState")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("States")
				.objectQuery("findByParameter")
				.parametermapping("name,key")
				.defaultSuccessScreen("States"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findDepartments")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser("DT"), 
				customJsTestScript(), 
				testscript.Workflow.cfg.Cfg.tplanScript_client_1) ;
		
		
		//need to check states the backend DB
		vpManual("db",5,getDB("select * from Department")).performTest();
		
		//need to check states the backend DB
		vpManual("db",1,getDB("select * from States where state_name = 'Alaskanew'")).performTest();
		
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("key", "Alaska");
		s.screen("Start").moveTo("States").throughMenuItem("findState");
		s.screen("States").moveTo("StatesDetail").throughListItem("0");
		s.screen("StatesDetail").moveTo("Statesupdateinstance").throughMenuItem("Open Statesupdateinstance");
		s.screen("Statesupdateinstance").setData("States_state_name_attribKey","Alaskanew");
		s.screen("Statesupdateinstance").menuItem("Update");
		return s;
}
	
	public static int getDB(String sql){
		try {
			ASA db = new ASA();
			db.setProperty("host", "localhost");
			db.setProperty("login", "dba");
			db.setProperty("password", "sql");
			db.setProperty("port", "5500");
			db.setProperty("driverClass", "com.sybase.jdbc3.jdbc.SybDriver");
			List result = DBUtil.runSQLForResult((IDBResource) db, sql);
			System.out.println(result.size());
			return result.size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get record data from DB");
			}
	}
}
//passed BB6T 20120420