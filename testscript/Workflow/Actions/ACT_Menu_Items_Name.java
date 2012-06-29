package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Menu_Items_NameHelper;
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
public class ACT_Menu_Items_Name extends ACT_Menu_Items_NameHelper
{
	/**
	 * Script Name   : <b>ACT_Menu_Items_Name</b>
	 * Generated     : <b>Sep 19, 2011 10:52:46 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/19
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("item1")
				.key("itme1Key")
				.type("Custom"));
		WorkFlowEditor.renameMenuItem("Start Screen", "item1","renamed");
		vpManual("menuItem", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "renamed")).performTest();
		vpManual("menuItem", false, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "item1")).performTest();
	}
}

