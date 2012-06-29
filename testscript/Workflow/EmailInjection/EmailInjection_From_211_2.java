package testscript.Workflow.EmailInjection;
import resources.testscript.Workflow.EmailInjection.EmailInjection_From_211_2Helper;
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
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class EmailInjection_From_211_2 extends EmailInjection_From_211_2Helper
{
	/**
	 * Script Name   : <b>EmailInjection_From_211_2</b>
	 * Generated     : <b>Sep 29, 2011 3:30:14 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/29
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
//				.objectQuery("findAll"));
//		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->myWF")
//				.unwiredServer("My Unwired Server")
////				.assignToUser("DT")
//				.assignToUser("DeviceTest")
//				.deployToServer("true"));
		
////test from field manually	
//		WorkFlowEditor.sendNotification(new Email()
//		.unwiredServer("My Unwired Server")
//		.to("ios")
//		.from("ios")
//		);


	}

}


