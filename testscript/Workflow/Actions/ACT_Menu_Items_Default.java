package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Menu_Items_DefaultHelper;
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
public class ACT_Menu_Items_Default extends ACT_Menu_Items_DefaultHelper
{
	/**
	 * Script Name   : <b>ACT_Menu_Items_Default</b>
	 * Generated     : <b>Sep 19, 2011 11:34:10 PM</b>
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
				.defaultItem("true")
				.type("Custom"));
		vpManual("default", "true", PropertiesView.getMenuItem("item1").getDefaultItem()).performTest();
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem()
				.name("item1")
				.defaultItem("false"));
		vpManual("default", "false", PropertiesView.getMenuItem("item1").getDefaultItem()).performTest();
	}
}

