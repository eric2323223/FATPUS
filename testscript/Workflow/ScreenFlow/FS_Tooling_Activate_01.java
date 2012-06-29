package testscript.Workflow.ScreenFlow;
import resources.testscript.Workflow.ScreenFlow.FS_Tooling_Activate_01Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class FS_Tooling_Activate_01 extends FS_Tooling_Activate_01Helper
{
	/**
	 * Script Name   : <b>FS_Tooling_Activate_01</b>
	 * Generated     : <b>Sep 5, 2011 10:10:29 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/05
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_ACTIVATE));
		WorkFlowEditor.addScreen("screen1");
		WorkFlowEditor.link(WorkFlow.SP_ACTIVATE, "screen1");
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WorkFlowEditor.open(Cfg.projectName, "myWF.xbw");
		vpManual("link", true, WorkFlowEditor.hasLinkBetween(WorkFlow.SP_ACTIVATE, "screen1")).performTest();
		WorkFlowEditor.deleteLink(WorkFlow.SP_ACTIVATE, "screen1");
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WorkFlowEditor.open(Cfg.projectName, "myWF.xbw");
		vpManual("link", false, WorkFlowEditor.hasLinkBetween(WorkFlow.SP_ACTIVATE, "screen1")).performTest();
		
	}
}

