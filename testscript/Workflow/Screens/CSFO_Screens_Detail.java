package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CSFO_Screens_DetailHelper;
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
public class CSFO_Screens_Detail extends CSFO_Screens_DetailHelper
{
	/**
	 * Script Name   : <b>CSFO_Screens_Detail</b>
	 * Generated     : <b>Sep 8, 2011 1:53:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/08
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF"));
		WorkFlowEditor.dragMbo(Cfg.projectName, "States");
		vpManual("screen", true , WorkFlowEditor.hasScreen("StatesDetail") ).performTest();
	}
}
//passed18
