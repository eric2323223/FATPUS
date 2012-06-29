package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.DemoScript_AndroidHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.WN;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.model.DeployOption;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DemoScript_Android extends DemoScript_AndroidHelper
{
	/**
	 * Script Name   : <b>DemoScript</b>
	 * Generated     : <b>Aug 25, 2011 1:14:42 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/25
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.demo_project);
		WN.deployProject(new DeployOption().startParameter("WorkflowAutomation")
				.server("My Unwired Server")
				.serverConnectionMapping("sampledb,sampledb"));
//		WFCustomizer.runTest(new WorkFlowPackage()
//				.startParameter("WorkflowAutomation->CRUD")
//				.unwiredServer("My Unwired Server")
//				.deployToServer("true")
//				.assignToUser("DT"), this);
//		
//		WFCustomizer.verifyResult(new WFClientResult().data("SyncCreate=Pass|AsyncDelete=Pass"));
	}
}

