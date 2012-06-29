package testscript.Workflow.CallActionOutSideMenu;
import resources.testscript.Workflow.CallActionOutSideMenu.CallAction_ActionsArea_DeleteHelper;
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
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CallAction_ActionsArea_Delete extends CallAction_ActionsArea_DeleteHelper
{
	/**
	 * Script Name   : <b>CallAction_ActionsArea_Delete</b>
	 * Generated     : <b>Mar 7, 2012 7:16:32 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/07
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addCustomAction("Start", new WFScreenMenuItem().name("item1")
				.type("Save"));
		vpManual("hasCustomaction", true, WorkFlowEditor.hasCustomActionInScreen("Start", "item1")).performTest();
		WorkFlowEditor.deleteCustomAction("Start", "item1");
		vpManual("hasCustomaction", false, WorkFlowEditor.hasCustomActionInScreen("Start", "item1")).performTest();
	}
}

