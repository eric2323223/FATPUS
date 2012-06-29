package testscript.Workflow.EmailInjection;
import resources.testscript.Workflow.EmailInjection.EmailInjection_GetDeviceUsers_211Helper;
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
import com.sybase.automation.framework.widget.helper.ListHelper;

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
public class EmailInjection_GetDeviceUsers_211 extends EmailInjection_GetDeviceUsers_211Helper
{
	/**
	 * Script Name   : <b>test</b>
	 * Generated     : <b>Sep 29, 2011 7:23:23 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/29
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		DOF.getDialog("Send Notification To A Device User");
		
//		System.out.print("ffff="+ListHelper.hasItem(DOF.getCombo(DOF.getDialog("Send Notification To A Device User"),"To:"),"ios"));
//		DOF.getButton(DOF.getDialog("Send Notification To A Device User"), "&Get Device Users").click();
//		DOF.getCombo(DOF.getDialog("Send Notification To A Device User"),"To:").click();
//		String[] items = ListHelper.getItems(DOF.getCombo(DOF.getDialog("Send Notification To A Device User"),"To:"));
//		System.out.println(items.length);
//		for(String item:items){
//			
//			System.out.print(item+" ");
//		}
		
//		String[] rs=WorkFlowEditor.sendNotificationgetitems();
//		
//		for(String r:rs){
//			
//		System.out.print(r+" ");
//		}
		
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF")
//				.option(WorkFlow.SP_SERVER_INIT)
//			    .mbo("Department")
//				.subject("test")
//				.objectQuery("findAll"));
//		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->myWF")
//				.unwiredServer("My Unwired Server")
////				.assignToUser("DT")
//				.assignToUser("DeviceTest")
//				.deployToServer("true"));
	
	
//		WorkFlowEditor.sendNotification(new Email()
//				.selectFrom("DeviceTest")
//				.verifySelectFrom("DeviceTest,ios", true)
//				.selectTo("ios")
//				.verifySelectTo("DeviceTest,ios", true)
//				.selectBcc("DeviceTest")
//				.verifySelectBcc("DeviceTest,ios", true)
//				.selectCc("DeviceTest")
//				.verifySelectCc("DeviceTest,ios", false)
//				);
	//vpManual("getTolist",new String[]{"DeviceTest","ios"},WorkFlowEditor.sendNotificationgetitems()).performTest();
//	vpManual("getTolist","true",WorkFlowEditor.sendNotification(new Email().selectFrom("DeviceTest")
//			.verifySelectFrom("DeviceTest,ios", false))).performTest();
//	WorkFlowEditor.sendNotification(new Email().to("")
		
//		WorkFlowEditor.sendNotification(new Email()
//		.selectBcc("AN")
//		.verifySelectBcc("AN,ios,supAdmin", false));
		
//		WorkFlowEditor.sendNotification(new Email()
//		.selectTo("ios")
//		.verifySelectTo("AN,ios,supAdmin", false)
//		);
		
//		WorkFlowEditor.sendNotification(new Email()
//		.selectCc("AN")
//		.verifySelectCc("AN,ios,supAdmin", false)
//		);
		
		
//			.verifyMessage(" Specify a value for the To field", false));
//	WorkFlowEditor.sendNotification(new Email().to("")
////			
//			
//			.verifyMessage("The Cc and Bcc fields are not sent in the notification.  These fields are used for matching rules only.  ", true));

	
}
	}

