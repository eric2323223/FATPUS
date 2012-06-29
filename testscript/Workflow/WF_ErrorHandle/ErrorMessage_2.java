package testscript.Workflow.WF_ErrorHandle;
import resources.testscript.Workflow.WF_ErrorHandle.ErrorMessage_2Helper;
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
import component.entity.model.WFEditBox;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class ErrorMessage_2 extends ErrorMessage_2Helper
{
	/**
	 * Script Name   : <b>ErrorMessage_2</b>
	 * Generated     : <b>Oct 14, 2011 2:30:14 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/14
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("ActScr");
		WorkFlowEditor.addScreen("ErrScr");
		WorkFlowEditor.addScreen("ErrDetailScr");
		
		WorkFlowEditor.addMenuItem("ActScr", new WFScreenMenuItem().name("submit")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.invokeParentUpdate(true)
				.errorScreen("ErrScr")
				.defaultSuccessScreen("ActScr"));
		
		System.out.print("keys="+PropertiesView.getKeyOfScreen("ErrScr").getKey());
		vpManual("key",true,PropertiesView.getKeyOfScreen("ErrScr").getKey().contains("ErrorLogs,list")).performTest();
		
		WorkFlowEditor.addWidget("ErrScr", new WFLview()
			.key("ErrorLogs")
			.lvDetailScreen("ErrDetailScr"));
		
		WorkFlowEditor.addWidget("ErrDetailScr", new WFEditBox()
			.label("myEB:")
			.key("ErrorLogMessage"));
		
		vpManual("noError",0,Problems.getErrors().size()).performTest();
	}
}
//passed
