package testscript.Workflow.cfg;
import resources.testscript.Workflow.cfg.SimpleE2EHelper;

import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class SimpleE2E extends SimpleE2EHelper
{
	/**
	 * Script Name   : <b>SimpleE2E</b>
	 * Generated     : <b>Apr 26, 2012 11:53:18 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/26
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
				.name("Department")
				.dataSourceType("JDBC")
				.connectionProfile("My Sample Database")
				.sqlQuery("select * from department"));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findAll")
				.project(Cfg.projectName)
				.mbo("Department")
				.type("Online Request")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.unwiredServer("My Unwired Server")
				.deployToServer("true")
				.assignToUser("supAdmin"), script());
//				.assignToUser("hwc"), script());
		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=7"));
	}

	private CustomJsTestScript script() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("Department").throughMenuItem("findAll");
		s.screen("Department").getListItemsCount();
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
		return s;
	}
}

