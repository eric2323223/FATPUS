package testscript.Workflow.AsyncErrHdl;
import resources.testscript.Workflow.AsyncErrHdl.AsyncErrHdl_SubmitWF_NoActionHelper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class AsyncErrHdl_SubmitWF_NoAction extends AsyncErrHdl_SubmitWF_NoActionHelper
{
	/**
	 * Script Name   : <b>AsyncErrHdl_SubmitWF_NoAction</b>
	 * Generated     : <b>Mar 14, 2012 10:56:43 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/14
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("item1")
				.type("Submit Workflow")
				.project(Cfg.projectName)
				.mbo("Department")
				.errorScreen("Start")
				.subject("subject")
				.from("unkown"));
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
		WFScreenMenuItem menuItem = PropertiesView.getMenuItem("Start","item1");
		vpManual("errorScreen", "Start", menuItem.getErrorScreen()).performTest();
		vpManual("subject", "subject", menuItem.getSubject()).performTest();
		vpManual("from", "unkown", menuItem.getFrom()).performTest();
	}
}

