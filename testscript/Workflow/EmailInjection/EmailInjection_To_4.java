package testscript.Workflow.EmailInjection;
import resources.testscript.Workflow.EmailInjection.EmailInjection_To_4Helper;
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
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class EmailInjection_To_4 extends EmailInjection_To_4Helper
{
	/**
	 * Script Name   : <b>EmailInjection_To_4</b>
	 * Generated     : <b>Sep 16, 2011 2:38:01 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/16
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll"));
		
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->"+Cfg.wfName)
				.unwiredServer("My Unwired Server")
				.assignToUser(Cfg.deviceUser)
				.deployToServer("true"));
		
		WorkFlowEditor.sendNotification(new Email()
			.unwiredServer("My Unwired Server")
			.to("DT,DT2")
			.verifyMessage("The Cc and Bcc fields are not sent in the notification.  These fields are used for matching rules only. ", false));

	}
}
//passed
