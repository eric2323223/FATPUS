package testscript.Workflow.EmailInjection;
import resources.testscript.Workflow.EmailInjection.EmailInjection_Profile_1_211Helper;
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
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;
import component.entity.wizard.SendNotificationWizard;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class EmailInjection_Profile_1_211 extends EmailInjection_Profile_1_211Helper
{
	/**
	 * Script Name   : <b>EmailInjection_Profile_211</b>
	 * Generated     : <b>Oct 9, 2011 11:13:08 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/09
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF")
//				.option(WorkFlow.SP_SERVER_INIT)
//			.mbo("Department")
//				.subject("test")
//				.objectQuery("findAll")
//				.subjectMatchingRule("test"));
		
//		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->myWF")
//				.unwiredServer("My Unwired Server")
////				.assignToUser("DT")
//				.assignToUser("ios")
//				.deployToServer("true"));
		
		
		
//		WorkFlowEditor.sendNotification(new Email()
////		.selectTo("ios")
//		.unwiredServer("My Unwired Server")
//		.verifyUnwiredServer("UnwiredServer1,My Unwired Server",false)
//		);
		
//  
//		DOF.getWFFlowDesignCanvas().click(RIGHT);
//		DOF.getContextMenu().click(atPath("Send a notification..."));
//		WO.setCombo(DOF.getCombo(DOF.getDialog("Send Notification To A Device User"), "Unwired Server profile:"), "UnwiredServer1");
//		vpManual("selectsucc","UnwiredServer1",DOF.getCombo(DOF.getDialog("Send Notification To A Device User"), "Unwired Server profile:").getProperty("text").toString());
	}
}

