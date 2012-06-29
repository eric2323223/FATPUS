package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Email_ReturnMulti_part1_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.LoadArgument;
import component.entity.model.Modifications;
import component.entity.model.ObjectQuery;
import component.entity.model.WFEditBox;
import component.entity.model.WFKey;
import component.entity.model.WFParameterMatchingRule;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Email_ReturnMulti_part1_E2E extends OLC_Email_ReturnMulti_part1_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Email_ReturnMulti_E2E</b>
	 * Generated     : <b>Oct 21, 2011 3:14:32 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/21
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/Alldate.sql");
		
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllData (dba)");
//		MBOProperties mbo = new MBOProperties(Cfg.projectName, "AllData");
//		mbo.setMboDefinition("sql", "SELECT * FROM sampledb.dba.AllData where type_INT=:type_INT and type_STRING=:type_STRING"+
//                                     " and type_DATETIME=:type_DATETIME and type_DECIMAL=:type_DECIMAL");
//		mbo.setLoadArgument(new LoadArgument().name("type_INT").type("INT").propagateTo("type_INT"));
//		mbo.setLoadArgument(new LoadArgument().name("type_STRING").type("STRING").propagateTo("type_STRING"));
//		mbo.setLoadArgument(new LoadArgument().name("type_DATETIME").type("DATETIME").propagateTo("type_DATETIME"));
//		mbo.setLoadArgument(new LoadArgument().name("type_DECIMAL").type("DECIMAL").propagateTo("type_DECIMAL"));
//		
//		
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				 .mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
		
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("AllData")
				.objectQuery("findByParameter")
				.to(Cfg.deviceUser)
				.from("string=Q/A")
				.cc("decimal=1")
				.subject("datetime=2009-09-09T20:00:00")
				.body("int=1")
				.toMatchingRule("dt")
				.fromMatchingRule("string=")
				.ccMatchingRule("decimal=")
				.bodyMatchingRule("int=")
				.subjectMatchingRule("datetime=")
				.parameterMatchingRule(new WFParameterMatchingRule()
						.name("type_INT")
						.startTag("int=")
						.field("Body").toString())
		.parameterMatchingRule(new WFParameterMatchingRule()
		.name("type_STRING")
		.startTag("string=")
		.field("From").toString())
		
		.parameterMatchingRule(new WFParameterMatchingRule()
		.name("type_DATETIME")
		.startTag("datetime=")
		.field("Subject").toString())
		
		.parameterMatchingRule(new WFParameterMatchingRule()
		.name("type_DECIMAL")
		.startTag("decimal=")
		.field("CC").toString()));
		       
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("dt")
				.unwiredServer("My Unwired Server"), 
				customJsTestScripts(), "tplan.Workflow.iconcommon.BB.server_dt_icon2.Script", sendNotification() );
		
		WFCustomizer.verifyResult(new WFClientResult()
		.data("list_items_count=2|"+
				"id=AllData_type_STRING_attribKey,value=Q/A"));
	
	}
	
	private CallBackMethod sendNotification() {
		return new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to("dt").from("string=Q/A").cc("decimal=2").subject("datetime=2010-08-08T20:00:00").body("int=1"));
	}

	private CustomJsTestScript customJsTestScripts() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("AllData").getListItemsCount();
		s.screen("AllData").moveTo("AllDataDetail").throughListItem("0");
		s.screen("AllDataDetail").getData("AllData_type_STRING_attribKey");
		return s;
	}
}

