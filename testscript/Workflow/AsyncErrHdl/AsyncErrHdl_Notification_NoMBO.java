package testscript.Workflow.AsyncErrHdl;
import resources.testscript.Workflow.AsyncErrHdl.AsyncErrHdl_Notification_NoMBOHelper;
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
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class AsyncErrHdl_Notification_NoMBO extends AsyncErrHdl_Notification_NoMBOHelper
{
	/**
	 * Script Name   : <b>AsyncErrHdl_Notification_NoMBO</b>
	 * Generated     : <b>Mar 16, 2012 3:01:42 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/16
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_SERVER_INIT));
		PropertiesView.set(new StartPoint()
				.from("test")
				.subject("subject")
				.generateErrorScreen(true)
				.type(WorkFlow.SP_SERVER_INIT));
		
//		vpManual("noError", 0, Problems.getErrors().size()).performTest();
		vpManual("from", "test", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getFrom()).performTest();
		vpManual("subject", "subject", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getSubject()).performTest();
		vpManual("errorScreen", "ErrorList", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getErrorScreen()).performTest();
	}
}

