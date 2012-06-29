package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Op_CRUD_Sync_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.LoadArgument;
import component.entity.model.Modifications;
import component.entity.model.Screen;
import component.entity.model.WFKey;
import component.entity.model.WFKeyParameterMapping;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_CRUD_Sync_E2E extends OLC_Op_CRUD_Sync_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_CRUD_Sync_E2E</b>
	 * Generated     : <b>Oct 20, 2011 4:56:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/20
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
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "States","update");
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		WorkFlowEditor.removeScreen("States_create");
		WorkFlowEditor.addMenuItem("StatesDetail", new WFScreenMenuItem()
				.name("update")
				.type("Open Screen")
				.screen("States_update_instance"));
		WorkFlowEditor.setMenuItem("States_update_instance", new WFScreenMenuItem().name("Update")
				.type("Online Request")
				.defaultSuccessScreen("StatesDetail"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("States")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("States"));
		PropertiesView.set(new WFScreenMenuItem().name("findAll"), new Modifications().mod("update", new WFKeyParameterMapping()
				.parameter("name")
				.key(new WFKey().name("key1").type("string").sentByServer("false").toString())));
		PropertiesView.set(new WFScreen().name("States"), new Modifications().mod("update", new WFKey().name("States")
				.sentByServer("false")));
		
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
	
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("DT")
				.unwiredServer("My Unwired Server"), 
				customJsTestScript(), "" );
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("States").throughMenuItem("findAll");
		s.screen("States").moveTo("StatesDetail").throughListItem("0");
		
		return s;
	}
}

