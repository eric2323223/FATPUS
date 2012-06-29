package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Email_ReturnMulti_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.Modifications;
import component.entity.model.ObjectQuery;
import component.entity.model.WFEditBox;
import component.entity.model.WFKey;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Email_ReturnMulti_E2E extends OLC_Email_ReturnMulti_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Email_ReturnMulti_E2E</b>
	 * Generated     : <b>Oct 21, 2011 3:14:32 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/21
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
				.dataSourceType("JDBC")
				.name("States")
				.connectionProfile("My Sample Database")
				.sqlQuery("select * from states where country=:name"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "States");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("country"));
		mbo.setObjectQuery(new ObjectQuery().name("findByParameter")
				.returnType("Multiple objects"));
//		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "States"))
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("States")
				.objectQuery("findByParameter"));
		WorkFlowEditor.addScreen("Screen1");
		WorkFlowEditor.addWidget("Screen1", new WFEditBox().label("box1").newKey("key1,string"));
		WorkFlowEditor.addMenuItem("Screen1", new WFScreenMenuItem().name("item1")
				.parametermapping("name,key1"));
	}
}

