package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Email_ReturnSingleHelper;
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
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
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
import component.view.properties.attributes.LoadArgumentTab;

/**
 * Description   : Functional Test Script
 * @author mzhao
 */
public class OLC_Email_ReturnSingle extends OLC_Email_ReturnSingleHelper
{
	/**
	 * Script Name   : <b>OLC_Email_ReturnSingle</b>
	 * Generated     : <b>Apr 18, 2010 7:32:32 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/04/18
	 * @author mzhao
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/Alldate.sql");
//		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllData (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "AllData");
		mbo.setMboDefinition("sql", "SELECT * FROM sampledb.dba.AllData where type_INT=:type_INT and type_STRING=:type_STRING"+
                                     " and type_DATETIME=:type_DATETIME and type_DECIMAL=:type_DECIMAL");
		mbo.setLoadArgument(new LoadArgument().name("type_INT").type("INT").propagateTo("type_INT"));
		mbo.setLoadArgument(new LoadArgument().name("type_STRING").type("STRING").propagateTo("type_STRING"));
		mbo.setLoadArgument(new LoadArgument().name("type_DATETIME").type("DATETIME").propagateTo("type_DATETIME"));
		mbo.setLoadArgument(new LoadArgument().name("type_DECIMAL").type("DECIMAL").propagateTo("type_DECIMAL"));
	
		// modify the object queries' default value
		PropertiesView.maximize();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
		
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(atIndex(0)), atColumn("Return type"))); 
		WO.setCCombo(DOF.getCCombo(DOF.getTable(DOF.getRoot())),"Single object");
		PropertiesView.restore();
		MainMenu.saveAll();
//		
//		
//		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				 .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		
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
//		       
//		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("dt")
				.unwiredServer("My Unwired Server"), 
				customJsTestScripts(), "tplan.Workflow.iconcommon.BB.server_dt_icon2.Script", sendNotification() );
		
		WFCustomizer.verifyResult(new WFClientResult()
		.data("id=AllData_id_attribKey,value=4"));
	
	}
	
	private CallBackMethod sendNotification() {
		return new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to("dt").from("string=Q/A").cc("decimal=2").subject("datetime=2010-08-08T20:00:00").body("int=1"));
	}

	private CustomJsTestScript customJsTestScripts() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("AllDataDetail").getData("AllData_id_attribKey");
		return s;
	}
}

