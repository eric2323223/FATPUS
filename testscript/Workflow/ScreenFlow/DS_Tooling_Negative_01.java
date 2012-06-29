package testscript.Workflow.ScreenFlow;
import resources.testscript.Workflow.ScreenFlow.DS_Tooling_Negative_01Helper;
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
public class DS_Tooling_Negative_01 extends DS_Tooling_Negative_01Helper
{
	/**
	 * Script Name   : <b>DS_Tooling_Negative_01</b>
	 * Generated     : <b>Sep 5, 2011 10:02:24 PM</b>
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
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("screen1");
		WorkFlowEditor.link("Client-initiated", "screen1");
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Client-initiated", "screen1")).performTest();
		WorkFlowEditor.addScreen("screen2");
		WorkFlowEditor.link("Client-initiated", "screen2");
		vpManual("link", false, WorkFlowEditor.hasLinkBetween("Client-initiated", "screen2")).performTest();
	}
}

