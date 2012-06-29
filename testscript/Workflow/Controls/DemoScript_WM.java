package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.DemoScript_WMHelper;
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

import component.entity.WN;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.model.DeployOption;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DemoScript_WM extends DemoScript_WMHelper
{
	/**
	 * Script Name   : <b>DemoScript_WM</b>
	 * Generated     : <b>Aug 26, 2011 2:24:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/26
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.demo_project);
		WN.deployProject(new DeployOption().startParameter("WorkflowAutomation")
				.server("My Unwired Server")
				.serverConnectionMapping("sampledb,sampledb"));
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter("WorkflowAutomation->CRUD.xbw")
				.unwiredServer("My Unwired Server")
				.assignToUser("DT2"), this);
		
		WFCustomizer.verifyResult(new WFClientResult().data("SyncCreate=Pass|AsyncDelete=Pass"));
	}
}

