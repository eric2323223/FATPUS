package testscript.Workflow.AsyncErrHdl;
import resources.testscript.Workflow.AsyncErrHdl.AsyncErrHdl_Notification_ErrorScreen2Helper;
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
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class AsyncErrHdl_Notification_ErrorScreen2 extends AsyncErrHdl_Notification_ErrorScreen2Helper
{
	/**
	 * Script Name   : <b>AsyncErrHdl_Notification_ErrorScreen2</b>
	 * Generated     : <b>Mar 15, 2012 11:47:04 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/15
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll"));
		PropertiesView.set(new StartPoint()
				.generateErrorScreen(true)
				.type(WorkFlow.SP_SERVER_INIT));
		WorkFlowEditor.close(Cfg.projectName, Cfg.wfName);
		WorkFlowEditor.open(Cfg.projectName, Cfg.wfName);
		vpManual("name", "/AsyncRequestError", WorkFlowEditor.getLinkTypeBetween(WorkFlow.SP_SERVER_INIT, "ErrorList")).performTest();
	}
}

