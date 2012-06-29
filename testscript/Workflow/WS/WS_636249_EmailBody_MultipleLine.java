package testscript.Workflow.WS;
import resources.testscript.Workflow.WS.WS_636249_EmailBody_MultipleLineHelper;
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
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.SAPCP;
import component.entity.model.WSCP;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class WS_636249_EmailBody_MultipleLine extends WS_636249_EmailBody_MultipleLineHelper
{
	/**
	 * Script Name   : <b>WS_636249_EmailBody_MultipleLine</b>
	 * Generated     : <b>Nov 29, 2011 3:48:52 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/29
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
	//vp2:create wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF2")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("d1=1")
				.body("ss\'ss")
				.subjectMatchingRule("d1=")
				);
		
	//vp2:jumpStart
		PropertiesView.jumpStart(new WorkFlow()
				.mbo("Department")
				.objectQuery("findAll")
				.subject("d2=1")
				.body("ss\'ss")
				.subjectMatchingRule("D1="));
	
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF2"))
			.unwiredServer("My Unwired Server")
			.assignToUser("DT")
			.verifyResult("Successfully deployed the workflow", true));

		
	//vp3:send notification
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF2.xbw", new Email()
				.cc("supAdmin")
				.subject("d2=")
				.to("dt")
				.body("ss\'ss")
				.unwiredServer("My Unwired Server")
				.verifyMessage("", true));
	}
}
		

//passed
