package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.EmailSub_AllDataTYpe_MO_E2EHelper;
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
import component.entity.GlobalConfig;
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
import component.entity.model.ObjectQuery;
import component.entity.model.ScrapbookCP;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class EmailSub_AllDataTYpe_MO_E2E extends EmailSub_AllDataTYpe_MO_E2EHelper
{
	/**
	 * Script Name   : <b>EmailSub_AllDataTYpe_MO_E2E</b>
	 * Generated     : <b>Sep 16, 2011 4:16:11 PM</b>
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
		
//	EE.runSQL(new ScrapbookCP().database("sampledb")
//	.type("Sybase_ASA_12.x").name("My Sample Database"), 
//	GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/create_AllDT.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		WN.deployProject(new DeployOption()
	         	.startParameter(Cfg.projectName)
				.server("My Unwired Server")
					.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("AllDT")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser(Cfg.projectName)
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
			"id=AllDT_int1_attribKey,value=1|" +
			"id=AllDT_string1_attribKey,value=1|" +
			"id=AllDT_date1_attribKey,value=2012-01-03T12:00:00|" +
			"id=AllDT_datetime1_attribKey,value=2012-01-03T00:00:00|" +
			"id=AllDT_time1_attribKey,value=12:25:13.000|" +
			"id=AllDT_bool1_attribKey,value=False|" +
			"id=AllDT_short1_attribKey,value=1"	));
		}

		private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("AllDT").getListItemsCount();
			s.screen("AllDT").moveTo("AllDTDetail").throughListItem("0");
			s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
			s.screen("AllDTDetail").getData("AllDT_string1_attribKey");
			s.screen("AllDTDetail").getData("AllDT_date1_attribKey");
			s.screen("AllDTDetail").getData("AllDT_datetime1_attribKey");
			s.screen("AllDTDetail").getData("AllDT_time1_attribKey");
			s.screen("AllDTDetail").getData("AllDT_bool1_attribKey");
			s.screen("AllDTDetail").getData("AllDT_short1_attribKey");
			return s;
		}
}
//PASSED BB6T 20120209
