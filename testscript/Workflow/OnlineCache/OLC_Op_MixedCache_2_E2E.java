package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Op_MixedCache_2_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.common.CDBUtil;
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

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_MixedCache_2_E2E extends OLC_Op_MixedCache_2_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_MixedCache_2_E2E</b>
	 * Generated     : <b>Oct 27, 2011 5:21:16 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/27
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
				.mode(DeployOption.MODE_UPDATE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT)
				.name(Cfg.wfName));
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("state").newKey("key,string"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findState")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("States")
				.objectQuery("findByParameter")
				.parametermapping("name,key")
				.defaultSuccessScreen("States"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findDepartments")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		
		vpManual("cdb", 0 , CDBUtil.getRecordCount(Cfg.projectName, "States")).performTest();
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser("DT"), customJsTestScript(), "");
		
		vpManual("cdb", 0 , CDBUtil.getRecordCount(Cfg.projectName, "States")).performTest();
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").setData("box", "Alberta");
		s.screen("Start_Screen").moveTo("States").throughMenuItem("findState");
		return s;
	}
}

