package testscript.Workflow.Customization;
import java.io.File;

import resources.testscript.Workflow.Customization.Cust_API_GenUtil_getAttribute_1Helper;
import testscript.Workflow.Customization.cfg.Config;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.NanoHTTPD;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WFEditBox;
import component.entity.model.WFLabel;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class Cust_API_GenUtil_getAttribute_1 extends Cust_API_GenUtil_getAttribute_1Helper
{
	/**
	 * Script Name   : <b>Cust_API_GenUtil_getAttribute_1</b>
	 * Generated     : <b>Mar 22, 2012 2:42:29 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/22
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addWidget("Start", new WFEditBox()
				.label("label1")
				.defaultValue("test")
				.newKey("key1,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findAll")
				.project(Cfg.projectName)
				.mbo("Department")
				.type("Online Request")
				.defaultSuccessScreen("Department")
				.objectQuery("findAll"));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.unwiredServer("My Unwired Server")
				.assignToSelectedUser("DT"), customJsTestScript(), "tplan.Workflow.common.StartWF_android");
	
		WFCustomizer.verifyResult(new WFClientResult().data("value=text"));
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setMethod("customBeforeShowScreen", new File(Config.Cust_API_GenUtil_getAttribute_1));
//		s.screen("Start").moveTo("Department").throughMenuItem("findAll");
//		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
		return s;
	}
}
