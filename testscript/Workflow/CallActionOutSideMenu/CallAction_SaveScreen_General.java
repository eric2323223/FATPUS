package testscript.Workflow.CallActionOutSideMenu;
import resources.testscript.Workflow.CallActionOutSideMenu.CallAction_SaveScreen_GeneralHelper;
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
public class CallAction_SaveScreen_General extends CallAction_SaveScreen_GeneralHelper
{
	/**
	 * Script Name   : <b>CallAction_SaveScreen_General</b>
	 * Generated     : <b>Mar 7, 2012 7:28:09 PM</b>
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
		WorkFlowEditor.addCustomAction("Start Screen", new WFScreenMenuItem().name("item1")
				.type("Save Screen"));
		vpManual("name", "item1", PropertiesView.getCustomAction("Start Screen","item1").getName()).performTest();
		vpManual("type", "Save Screen", PropertiesView.getCustomAction("Start Screen","item1").getType()).performTest();
	}
}

