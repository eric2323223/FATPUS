package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CSFO_CreateScreenHelper;
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
import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CSFO_CreateScreen extends CSFO_CreateScreenHelper
{
	/**
	 * Script Name   : <b>CSFO_CreateScreen</b>
	 * Generated     : <b>Sep 7, 2011 8:46:36 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/07
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF"));
		WorkFlowEditor.dragOperation(Cfg.projectName, "States", "create");
		
		vpManual("screen", true , WorkFlowEditor.hasScreen("Statescreate") ).performTest();
		vpManual("menuItem", true, WorkFlowEditor.hasMenuItemInScreen("Statescreate", "Create")).performTest();
		vpManual("editBox", true, WorkFlowEditor.hasWidgetInScreen("Statescreate", new WFEditBox().label("State id:"))).performTest();
		vpManual("editBox", true, WorkFlowEditor.hasWidgetInScreen("Statescreate", new WFEditBox().label("State name:"))).performTest();
	}
}
//passed7
