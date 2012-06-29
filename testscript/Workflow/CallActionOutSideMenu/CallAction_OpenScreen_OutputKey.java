package testscript.Workflow.CallActionOutSideMenu;
import resources.testscript.Workflow.CallActionOutSideMenu.CallAction_OpenScreen_OutputKeyHelper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CallAction_OpenScreen_OutputKey extends CallAction_OpenScreen_OutputKeyHelper
{
	/**
	 * Script Name   : <b>CallAction_OpenScreen_OutputKey</b>
	 * Generated     : <b>Mar 8, 2012 11:12:08 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/08
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addCustomAction("Start Screen", new WFScreenMenuItem().name("item1")
				.type("Open Screen")
				.screen("Start Screen"));
		vpManual("paraMapping", 0, PropertiesView.getCustomAction("Start Screen","item1").getOutputKey().length).performTest();
	
	}
}

