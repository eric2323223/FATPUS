package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Op_Other_Sync_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.PropertiesView;
import component.entity.WorkFlowEditor;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.Modifications;
import component.entity.model.WFKey;
import component.entity.model.WFKeyParameterMapping;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_Other_Sync_E2E extends OLC_Op_Other_Sync_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_Other_Sync_E2E</b>
	 * Generated     : <b>Oct 25, 2011 2:28:49 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
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
//		mbo.addOperation(new Operation()
//				.startParameter(WN.mboPath(Cfg.projectName,"States"))
//				.name("list")
//				.type("OTHER")
//				.sqlQuery("select * from states"));
//		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName,"States"))
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
//		WorkFlowEditor.link("States", "States_list");
//		
//		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("box1")
//				.newKey("key1,string"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("find")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("States")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("States")
//				.parametermapping("name,"+new WFKey().type("string").name("key2").toString())
				);
		
//		PropertiesView.set(new WFScreenMenuItem().name("find"), new Modifications().mod("update", new WFKeyParameterMapping().parameter("name").key("key1")));
		
		
		
		
//		PropertiesView.set(new WFScreen().name("States_list"), new Modifications().mod("update", new WFScreenMenuItem().name("List").type("Online Request").defaultSuccessScreen("States")));
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
//		
//		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//				.deployToServer("true")
//				.assignToUser("DT")
//				.unwiredServer("My Unwired Server"), customJsTestScript(), "");
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").setData("key1", "Alaska");
		s.screen("Start_Screen").moveTo("States").throughMenuItem("find");
		s.screen("States").moveTo("StatesDetail").throughListItem("0");
		s.screen("StatesDetail").moveTo("States_delete_instance").throughMenuItem("Open States_update_instance");
		s.screen("States_delete_instance").moveTo("States").throughMenuItem("Delete");
		return s;
	}
}

