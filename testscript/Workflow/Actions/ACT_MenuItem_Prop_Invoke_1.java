package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_MenuItem_Prop_Invoke_1Helper;
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
import com.sybase.automation.framework.widget.DOF;

import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class ACT_MenuItem_Prop_Invoke_1 extends ACT_MenuItem_Prop_Invoke_1Helper
{
	/**
	 * Script Name   : <b>ACT_MenuItem_Prop_Invoke_1</b>
	 * Generated     : <b>Sep 19, 2011 7:26:31 PM</b>
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
				.name("submit")
				.type("Custom"));
		DOF.getWFMenuItemFigure(DOF.getRoot(), "submit").click(RIGHT);
		DOF.getContextMenu().click(atPath("Show Properties View"));
		vpManual("properties", true, DOF.getCTabItem(DOF.getRoot(), "Properties")!=null).performTest();
	}
}

