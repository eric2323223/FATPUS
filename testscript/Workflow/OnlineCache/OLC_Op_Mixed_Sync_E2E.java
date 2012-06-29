package testscript.Workflow.OnlineCache;
import com.sybase.automation.framework.widget.DOF;

import resources.testscript.Workflow.OnlineCache.OLC_Op_Mixed_Sync_E2EHelper;
import testscript.Workflow.cfg.Cfg;

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
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_Mixed_Sync_E2E extends OLC_Op_Mixed_Sync_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_Mixed_Sync_E2E</b>
	 * Generated     : <b>Oct 25, 2011 7:03:33 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createCacheGroup(new CacheGroup().startParameter(Cfg.projectName).name("online").type(CachePolicy.ONLINE));
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.changeCacheGroup(Cfg.projectName, "States", "Default (Default)", "online");	
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "States");
		mbo.setMboDefinition("sql", "select * from states where state_name=:name");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("state_name"));
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName,"States"))
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("box1")
				.newKey("key1,string"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findState")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("States")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("States")
				.parametermapping("name,key1")
				);
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAllDepartment")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department")
				);
		
		PropertiesView.set(new WFScreen().name("States_update_instance"), new Modifications().mod("update", new WFScreenMenuItem().name("Update").type("Online Request").defaultSuccessScreen("StatesDetail")));
		PropertiesView.set(new WFScreen().name("Department_update_instance"), new Modifications().mod("update", new WFScreenMenuItem().name("Update").type("Online Request").defaultSuccessScreen("DepartmentDetail")));
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.assignToUser("DT")
				.unwiredServer("My Unwired Server"), customJsTestScript(), "");
		WFCustomizer.verifyResult(new WFClientResult().data("key=States_state_name_attribKey,value=Also|" +
				""));
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").setData("key1", "Alaska");
		s.screen("Start_Screen").moveTo("States").throughMenuItem("findState");
		s.screen("States").moveTo("StatesDetail").throughListItem("0");
		s.screen("StatesDetail").moveTo("States_update_instance").throughMenuItem("Open States_update_instance");
		s.screen("States_update_instance").setData("States_state_name_attribKey", "Also");
		s.screen("States_update_instance").moveTo("StateDetail").throughMenuItem("Update");
		s.screen("StatesDetail").getData("States_state_name_attribKey");
		
		return s;
	}
}

