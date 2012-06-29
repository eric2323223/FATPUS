package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Menu_ReturnMulti_E2EHelper;
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
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.ObjectQuery;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Menu_ReturnMulti_E2E extends OLC_Menu_ReturnMulti_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Menu_ReturnMulti_Android</b>
	 * Generated     : <b>Oct 21, 2011 2:42:24 AM</b>
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
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "States"))
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT)
				);
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("item1")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("States")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("States"));
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("strEdit").newKey("nameKey,string"));
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem()
				.name("item1")
				.parametermapping("name,nameKey"));
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.wfPath(Cfg.projectName, Cfg.wfName))
				.deployToServer("false")
				.unwiredServer("My Unwired Server")
				.assignToUser("DT")
				, customJsTestScript(), "");
		
		WFCustomizer.verifyResult(new WFClientResult().data("id=States_country_attribKey,value=USA"));
		
	}

	private CustomJsTestScript customJsTestScript() {
		return null;
	}
}

