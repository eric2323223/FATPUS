package testscript.Workflow.ScreenFlow;
import resources.testscript.Workflow.ScreenFlow.FS_Tooling_Email_01Helper;
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
public class FS_Tooling_Email_01 extends FS_Tooling_Email_01Helper
{
	/**
	 * Script Name   : <b>FS_Tooling_Email_01</b>
	 * Generated     : <b>Sep 6, 2011 12:01:10 AM</b>
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
		WorkFlowEditor.addScreen("screen1");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "screen1");
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WorkFlowEditor.open(Cfg.projectName, "myWF.xbw");
		vpManual("link1", true, WorkFlowEditor.hasLinkBetween("Server-initiated (States)", "screen1")).performTest();
		WorkFlowEditor.deleteLink("Server-initiated (States)", "screen1");
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WorkFlowEditor.open(Cfg.projectName, "myWF.xbw");
		vpManual("link2", false, WorkFlowEditor.hasLinkBetween("Server-initiated (States)", "screen1")).performTest();
		
	}
}

