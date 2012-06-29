package testscript.Workflow.Customization;
import java.io.File;

import resources.testscript.Workflow.Customization.Cust_Func_customBeforeWorkflowLoad_1Helper;
import testscript.Workflow.Customization.cfg.Config;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class Cust_Func_customBeforeWorkflowLoad_1 extends Cust_Func_customBeforeWorkflowLoad_1Helper
{
	/**
	 * Script Name   : <b>Cust_Func_customBeforeWorkflowLoad_1</b>
	 * Generated     : <b>Mar 20, 2012 5:22:05 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/20
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("Department"));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.unwiredServer("My Unwired Server")
				.assignToSelectedUser("DT"), customJsTestScript(), "tplan.Workflow.common.StartEmail_android");
	
	//used to BB6T :passed
		WFCustomizer.verifyResult(new WFClientResult().data("uploadData=true"));
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setMethod("customBeforeWorkflowLoad", new File(Config.Cust_Func_customBeforeWorkflowLoad_1));
		return s;
	}
}
//used to BB6T :passed
