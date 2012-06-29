package testscript.Workflow.AsyncErrHdl;
import resources.testscript.Workflow.AsyncErrHdl.AsyncErrHdl_SubmitWF_NoMBOHelper;
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
public class AsyncErrHdl_SubmitWF_NoMBO extends AsyncErrHdl_SubmitWF_NoMBOHelper
{
	/**
	 * Script Name   : <b>AsyncErrHdl_SubmitWF_NoMBO</b>
	 * Generated     : <b>Mar 14, 2012 10:50:09 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/14
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("item1")
				.type("Submit Workflow")
				.errorScreen("Start")
				.subject("subject")
				.from("unknown"));
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
		WFScreenMenuItem menuItem = PropertiesView.getMenuItem("Start", "item1");
		vpManual("subject", "subject", menuItem.getSubject()).performTest();
		vpManual("subject", "unknown", menuItem.getFrom()).performTest();

	}
}

