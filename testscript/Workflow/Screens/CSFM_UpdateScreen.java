package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CSFM_UpdateScreenHelper;
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

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CSFM_UpdateScreen extends CSFM_UpdateScreenHelper
{
	/**
	 * Script Name   : <b>CSFM_UpdateScreen</b>
	 * Generated     : <b>Sep 7, 2011 8:21:22 PM</b>
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
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		vpManual("line", true , WorkFlowEditor.hasLinkBetween("States", "StatesDetail") ).performTest();
		vpManual("menuItem1", true , WorkFlowEditor.hasMenuItemInScreen("States", "Submit") ).performTest();
		vpManual("menuItem2", true , WorkFlowEditor.hasMenuItemInScreen("StatesDetail", "Open Statesupdateinstance") ).performTest();
		vpManual("menuItem3", true , WorkFlowEditor.hasMenuItemInScreen("StatesDetail", "Open Statesdeleteinstance") ).performTest();
		
	}
}
//passed3
