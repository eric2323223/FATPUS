package testscript.Workflow.OnlineCache;
import java.util.List;

import com.sybase.automation.framework.common.DBUtil;

import resources.testscript.Workflow.OnlineCache.OLC_Op_Create_ASync_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
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
public class OLC_Op_Create_ASync_E2E extends OLC_Op_Create_ASync_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_Create_Sync_E2E</b>
	 * Generated     : <b>Oct 23, 2011 6:55:23 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/23
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
//		MBOProperties mbo = new MBOProperties(Cfg.projectName, "States");
//		mbo.setMboDefinition("sql", "select * from states where state_name=:name");
//		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("state_name"));
//		
//		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName,"States"))
//				.server("My Unwired Server")
//				.mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_CLIENT_INIT));
//		
//		WorkFlowEditor.dragOperation(Cfg.projectName, "States","create");
//		WorkFlowEditor.link("Start", "Statescreate");
//		MainMenu.saveAll();
//		
////		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("find")
////				.type("Online Request")
////				.project(Cfg.projectName)
////				.mbo("States")
////				.objectQuery("findByParameter")
////				.defaultSuccessScreen("States"));
////		WorkFlowEditor.addWidget("Start", new WFEditBox().label("box1")
////				.newKey("key1,string"));
////		
////		vpManual("noError", 0, Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.assignToUser("dt")
				.unwiredServer("My Unwired Server"), customJsTestScript(), "tplan.Workflow.iconcommon.BB.myWF_icon2.Script");
		
		vpManual("db",1,getDB("select * from States where state_id='AC' and country='mm'")).performTest();
		
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("Statescreate").throughMenuItem("Open Statescreate");
		s.screen("Statescreate").setData("States_create_state_id_paramKey", "MM");
		s.screen("Statescreate").setData("States_create_state_name_paramKey", "ff");
		s.screen("Statescreate").setData("States_create_state_capital_paramKey", "ss");
		s.screen("Statescreate").setData("States_create_country_paramKey", "mm");
		s.screen("Statescreate").setData("States_create_region_paramKey", "South");
		s.screen("Statescreate").menuItem("Create");
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

