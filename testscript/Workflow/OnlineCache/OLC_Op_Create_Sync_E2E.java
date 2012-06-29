package testscript.Workflow.OnlineCache;
import java.util.ArrayList;
import java.util.List;

import resources.testscript.Workflow.OnlineCache.OLC_Op_Create_Sync_E2EHelper;
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
import component.entity.MBOProperties;
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
import component.entity.model.Modifications;
import component.entity.model.WFEditBox;
import component.entity.model.WFKeyParameterMapping;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_Create_Sync_E2E extends OLC_Op_Create_Sync_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_Create_ASync_E2E</b>
	 * Generated     : <b>Oct 24, 2011 1:11:13 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/24
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "States");
		mbo.setMboDefinition("sql", "select * from states where state_name=:name");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("state_name"));
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName,"States"))
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("find")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("States")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("States"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("box1").newKey("key1,string"));
		PropertiesView.set(new WFScreenMenuItem().name("find").defaultSuccessScreen("States"), new Modifications().mod("Online Request", new WFKeyParameterMapping().parameter("name").key("key1")));
		
//		WorkFlowEditor.link("States", "Statescreate");
		
//	  //	PropertiesView.set(new WFScreen().name("Statescreate"), new Modifications().mod("update", new WFScreenMenuItem().name("Create").defaultSuccessScreen("States")));
//	//	PropertiesView.set(new WFScreen().name("States"), new Modifications().mod("add", new WFScreenMenuItem().name("create").type("Open Screen").screen("Statescreate")));
//		//vpManual("noError", 0, Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.assignToUser("dt")
				.unwiredServer("My Unwired Server"), customJsTestScript(), "tplan.Workflow.iconcommon.BB.myWF_icon2.Script");
		
		vpManual("db",1,getDB("select * from States where state_id='AA' and country='cc'")).performTest();
		
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("key1", "Alaska");
		s.screen("Start").moveTo("States").throughMenuItem("find");
		s.screen("States").moveTo("Statescreate").throughMenuItem("Open Statescreate");
		s.screen("Statescreate").setData("States_create_state_id_paramKey", "AA");
		s.screen("Statescreate").setData("States_create_state_name_paramKey", "ff");
		s.screen("Statescreate").setData("States_create_state_capital_paramKey", "ss");
		s.screen("Statescreate").setData("States_create_country_paramKey", "cc");
		s.screen("Statescreate").setData("States_create_region_paramKey", "South");
		s.screen("Statescreate").moveTo("States").throughMenuItem("Create");
//		s.screen("States").moveTo("StatesDetail").throughListItem("0");
//		s.screen("StatesDetail").getData("States_state_id_attribKey");
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

