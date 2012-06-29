package testscript.Workflow.CallActionOutSideMenu;
import resources.testscript.Workflow.CallActionOutSideMenu.CallAction_ActionsArea_UpdateHelper;
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
public class CallAction_ActionsArea_Update extends CallAction_ActionsArea_UpdateHelper
{
	/**
	 * Script Name   : <b>CallAction_ActionsArea_Update</b>
	 * Generated     : <b>Mar 7, 2012 6:15:02 PM</b>
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
		WorkFlowEditor.addScreen("newScreen");
		WorkFlowEditor.addCustomAction("Start", new WFScreenMenuItem().name("item1")
				.type("Open")
				.screen("Start"));
		WorkFlowEditor.copyAndPasteCustomAction("Start", "item1", "newScreen");
		vpManual("hasCustomaction", true, WorkFlowEditor.hasCustomActionInScreen("Start", "item1")).performTest();
		vpManual("customAction", true, WorkFlowEditor.hasLinkBetween("Start", "Start")).performTest();
	}
}

