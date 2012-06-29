package testscript.Workflow.testscript;
import resources.testscript.Workflow.testscript.WF_Credential_AndroidHelper;
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

import component.entity.ATAPI;
import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.WFProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFAuthentication;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class WF_Credential_Android extends WF_Credential_AndroidHelper
{
	/**
	 * Script Name   : <b>WF_Credential_Android</b>
	 * Generated     : <b>Aug 15, 2011 1:04:01 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/15
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
////		ATAPI.registerApplicationConnection(GlobalConfig.HOST_NAME);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->bonus (dba)", 10 ,10);
//		WN.createWorkFlow(new WorkFlow()
//			.startParameter(Cfg.projectName)
//			.name("wf1")
//			.option(WorkFlow.SP_CLIENT_INIT));
//		WFProperties wf = new WFProperties(Cfg.projectName, "wf1.xbw");
//		wf.setAuthentication(new WFAuthentication().type(WFAuthentication.TYPE_STATIC)
//				.supCPAuthentication("true"));
//		WorkFlowEditor.dragMbo("wf", "Bonus");
//		WorkFlowEditor.link("Start Screen", "Bonus_create");
//		WorkFlowEditor.link("Start Screen", "Bonus_delete");
//		WorkFlowEditor.addMenuItemToScreen("Start SCreen", new WFScreenMenuItem()
//			.mbo("Bonus")
//			.name("getAll")
//			.type("Online Request")
//			.objectQuery("findAll"));
//		WN.createWorkFlowPackage(new WorkFlowPackage()
//			.startParameter("wf,wf1.xbw")
//			.unwiredServer("My Unwired Server")
//			.assignToUser("DeviceTest"));
		System.out.println(getScriptName());
		Robot.run(this);
	}
}

