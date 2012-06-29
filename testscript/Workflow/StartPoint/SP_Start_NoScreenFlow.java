package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Start_NoScreenFlowHelper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Start_NoScreenFlow extends SP_Start_NoScreenFlowHelper
{
	/**
	 * Script Name   : <b>SP_Start_NoScreenFlow</b>
	 * Generated     : <b>Sep 4, 2011 7:58:58 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/04
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_CLIENT_INIT));
		vpManual("error", 1, Problems.getErrors().size()).performTest();
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT)
				.mbo("States"));
		vpManual("error", 1, Problems.getErrors().size()).performTest();
		WorkFlowEditor.addScreen("screen1");
		WorkFlowEditor.link("Client-initiated", "screen1");
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
		WorkFlowEditor.removeScreen("screen1");
		vpManual("error", 1, Problems.getErrors().size()).performTest();
		WorkFlowEditor.addScreen("screen1");
		WorkFlowEditor.link("Server-initiated (States)", "screen1");
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
	}
}
//passed
