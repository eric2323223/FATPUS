package testscript.Workflow.AsyncErrHdl;
import resources.testscript.Workflow.AsyncErrHdl.AsyncErrHdl_SubmitWF_ErrorScreen1Helper;
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

/**
 * Description   : Functional Test Script
 * @author test
 */
public class AsyncErrHdl_SubmitWF_ErrorScreen1 extends AsyncErrHdl_SubmitWF_ErrorScreen1Helper
{
	/**
	 * Script Name   : <b>AsyncErrHdl_SubmitWF_ErrorScreen1</b>
	 * Generated     : <b>Mar 13, 2012 6:44:05 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/13
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("item1")
				.type("Submit Workflow"));
		vpManual("name", null, PropertiesView.getMenuItem("Start","item1").getErrorScreen()).performTest();
		PropertiesView.set(new WFScreenMenuItem().name("item1").type("Submit Workflow")
				.subject("subject")
				.errorScreen("Start"));
		vpManual("subject", "subject", PropertiesView.getMenuItem("Start","item1").getSubject()).performTest();
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		vpManual("subject", "subject", PropertiesView.getMenuItem("Start","item1").getSubject()).performTest();
	}
}

