package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_NoScreenFlowHelper;
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
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_NoScreenFlow extends SP_Email_NoScreenFlowHelper
{
	/**
	 * Script Name   : <b>SP_Email_NoScreenFlow</b>
	 * Generated     : <b>Sep 13, 2011 3:52:32 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/13
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		vpManual("haserror",1,Problems.getErrors().size()).performTest();
		
		WorkFlowEditor.addScreen("screen1");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "screen1");
		vpManual("haserror",0,Problems.getErrors().size()).performTest();
		
		WorkFlowEditor.removeScreen("screen1");
		vpManual("haserror",1,Problems.getErrors().size()).performTest();
		
		WorkFlowEditor.addScreen("screen1");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "screen1");
		vpManual("haserror",0,Problems.getErrors().size()).performTest();
	}
}
//passed
