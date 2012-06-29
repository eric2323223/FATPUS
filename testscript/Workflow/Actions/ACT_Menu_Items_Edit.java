package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Menu_Items_EditHelper;
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
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class ACT_Menu_Items_Edit extends ACT_Menu_Items_EditHelper
{
	/**
	 * Script Name   : <b>ACT_Menu_Items_Edit</b>
	 * Generated     : <b>Sep 19, 2011 6:59:28 PM</b>
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
				.type("Custom"));
		vpManual("itemExist", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "item1")).performTest();
		WorkFlowEditor.copyAndPasteMenuItem("Start Screen", "item1");
		vpManual("itemExist", false, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "Copy_1_item1")).performTest();
		WorkFlowEditor.cutAndPasteMenuItem("Start Screen", "item1");
		vpManual("itemExist", false, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "item1")).performTest();
	}
}

