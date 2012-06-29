package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Email_AllDatetypes_Date_E2EHelper;
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
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.LoadArgument;
import component.entity.model.WFParameterMatchingRule;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author min zhao
 */
public class OLC_Email_AllDatetypes_Date_E2E extends OLC_Email_AllDatetypes_Date_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Email_AllDatetypes_Date_E2E</b>
	 * Generated     : <b>Apr 9, 2012 4:56:43 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/09
	 * @author min zhao
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/Alldate.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllData (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "AllData");
		mbo.setMboDefinition("sql", "SELECT * FROM sampledb.dba.AllData where type_DATE=:type_DATE");
		mbo.setLoadArgument(new LoadArgument().name("type_DATE").defaultValue("1"));
		mbo.setLoadArgument(new LoadArgument().name("type_DATE").type("DATE").propagateTo("type_DATE"));
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				 .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_SERVER_INIT)
//				.project(Cfg.projectName)
//				.mbo("AllData")
//				.objectQuery("findByParameter")
//				.to(Cfg.deviceUser)
//				.body("<typeInt>2009-09-09</typeInt>")
//				.toMatchingRule("dt")
//				.parameterMatchingRule(new WFParameterMatchingRule()
//						.name("type_DATE")
//						.startTag("<typeInt>")
//						.endTag("</typeInt>")
//						.field("Body").toString()));
//		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("dt")
				.unwiredServer("My Unwired Server"), 
				customJsTestScripts(), "tplan.Workflow.iconcommon.BB.server_dt_icon2.Script", sendNotification() );
		
		WFCustomizer.verifyResult(new WFClientResult()
		.data("list_items_count=2|"+
				"id=AllData_type_DATE_attribKey,value=2009-09-09T12:00:00"));
	
	}
	
	private CallBackMethod sendNotification() {
		return new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to("dt").body("<typeInt>2009-09-09</typeInt>"));
	}

	private CustomJsTestScript customJsTestScripts() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("AllData").getListItemsCount();
		s.screen("AllData").moveTo("AllDataDetail").throughListItem("0");
		s.screen("AllDataDetail").getData("AllData_type_DATE_attribKey");
		return s;
	}
}

