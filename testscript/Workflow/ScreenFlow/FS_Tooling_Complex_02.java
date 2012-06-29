package testscript.Workflow.ScreenFlow;
import resources.testscript.Workflow.ScreenFlow.FS_Tooling_Complex_02Helper;
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
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class FS_Tooling_Complex_02 extends FS_Tooling_Complex_02Helper
{
	/**
	 * Script Name   : <b>FS_Tooling_Complex_02</b>
	 * Generated     : <b>Sep 6, 2011 12:57:40 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/06
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_SERVER_INIT)
				.mbo("States"));
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_ACTIVATE));
		WorkFlowEditor.addScreen("screen1");
		WorkFlowEditor.link(WorkFlow.SP_ACTIVATE, "screen1");
		vpManual("link11", true, WorkFlowEditor.hasLinkBetween(WorkFlow.SP_ACTIVATE, "screen1")).performTest();
		WorkFlowEditor.deleteLink(WorkFlow.SP_ACTIVATE, "screen1");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "screen1");
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WorkFlowEditor.open(Cfg.projectName, "myWF.xbw");
		vpManual("link12", true, WorkFlowEditor.hasLinkBetween("Server-initiated (States)", "screen1")).performTest();
		
	}
}

