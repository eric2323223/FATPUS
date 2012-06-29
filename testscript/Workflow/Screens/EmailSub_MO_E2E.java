package testscript.Workflow.Screens;


import resources.testscript.Workflow.Screens.EmailSub_MO_E2EHelper;
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
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class EmailSub_MO_E2E extends EmailSub_MO_E2EHelper
{
	/**
	 * Script Name   : <b>EmailSub_MO_E2E</b>
	 * Generated     : <b>Sep 16, 2011 3:43:39 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/16
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
		WN.deployProject(new DeployOption()
 			.startParameter(Cfg.projectName)
 			.server("My Unwired Server")
 				.mode(DeployOption.MODE_REPLACE)
 			.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Wf_ff_a")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser(Cfg.deviceUser)
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScript(), 
//		"tplan.Workflow.iconcommon.BB.server_dt_icon.Script",
		new CallBackMethod().receiver(WorkFlowEditor.class)
		.methodName("sendNotification")
		.parameter(new Email()
		.unwiredServer("My Unwired Server")
		.to(Cfg.deviceUser)
		.subject("findall")));
	
		WFCustomizer.verifyResult(new WFClientResult().data(
			"list_items_count=2|"+
			"id=Wf_ff_a_aid_attribKey,value=2|" +
			"id=Wf_ff_a_aname_attribKey,value=Atwo" ));
	}

		private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Wfffa").getListItemsCount();
			s.screen("Wfffa").moveTo("WfffaDetail").throughListItem("2->0");
			s.screen("WfffaDetail").getData("Wf_ff_a_aid_attribKey");
			s.screen("WfffaDetail").getData("Wf_ff_a_aname_attribKey");
			return s;
		}
}
//passed BB6T 20120207
