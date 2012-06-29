package testscript.Workflow.OnlineCache;
import java.util.List;

import resources.testscript.Workflow.OnlineCache.OLC_Op_Mixed_DB_E2EHelper;
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
import com.sybase.automation.framework.common.DBUtil;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
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
 * @author ffan
 */
public class OLC_Op_Mixed_DB_E2E extends OLC_Op_Mixed_DB_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_Mixed_DB_E2E</b>
	 * Generated     : <b>Apr 24, 2012 8:37:08 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/24
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createCacheGroup(new CacheGroup().startParameter(Cfg.projectName).name("online").type(CachePolicy.ONLINE));
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "States");
		mbo.setMboDefinition("sql", "select * from states where state_name=:name");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("state_name"));
		
		WN.changeCacheGroup(Cfg.projectName, "States", "Default (Default)", "online");
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				 .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("name")
				.newKey("statesname,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findStatesByname")
				.project(Cfg.projectName)
				.mbo("States")
				.type("Online Request")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("States")
				.parametermapping("name,statesname"));
		
		WorkFlowEditor.setMenuItem("Statesupdateinstance",new WFScreenMenuItem().name("Update")
		           .defaultSuccessScreen("Start"));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("dept_id")
				.newKey("dept_id,int"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findDeptByID")
				.project(Cfg.projectName)
				.mbo("Department")
				.type("Online Request")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("DepartmentDetail")
				.parametermapping("dept_id,dept_id"));
		
		WorkFlowEditor.setMenuItem("Departmentupdateinstance",new WFScreenMenuItem().name("Update")
				           .defaultSuccessScreen("Start"));
		MainMenu.saveAll();		
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("DT")
				.unwiredServer("My Unwired Server"), 
				customJsTestScripts(), 
				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
	
		//verify the DB data>>>>>>>>>>>>
		//need to check states the backend DB
		vpManual("db",1,getDB("select * from Department where dept_id=100 and dept_name='newname'")).performTest();
		
		//need to check states the backend DB
		vpManual("db",1,getDB("select * from States where state_name = 'Alaskanew'")).performTest();
		
	}

	private CustomJsTestScript customJsTestScripts() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("dept_id", "100");
		s.screen("Start").moveTo("DepartmentDetail").throughMenuItem("findDeptByID");
		s.screen("DepartmentDetail").moveTo("Departmentupdateinstance").throughMenuItem("Open Departmentupdateinstance");
		s.screen("Departmentupdateinstance").setData("Department_dept_name_attribKey","newname");
		s.screen("Departmentupdateinstance").moveTo("Start").throughMenuItem("Update");
		
		s.screen("Start").setData("statesname", "Alaska");
		s.screen("Start").moveTo("States").throughMenuItem("findStatesByname");
		s.screen("States").moveTo("StatesDetail").throughListItem("0");
		s.screen("StatesDetail").getData("States_state_id_attribKey");
		s.screen("StatesDetail").moveTo("Statesupdateinstance").throughMenuItem("Open Statesupdateinstance");
		s.screen("Statesupdateinstance").setData("States_state_name_attribKey","Alaskanew");
		s.screen("Statesupdateinstance").moveTo("Start").throughMenuItem("Update");
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
//passed BB6T 20120424
