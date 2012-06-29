package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Email_Complex_E2EHelper;
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
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.LoadArgument;
import component.entity.model.PK;
import component.entity.model.WFEditBox;
import component.entity.model.WFParameterMatchingRule;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author mzhao
 */
public class OLC_Email_Complex_E2E extends OLC_Email_Complex_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Email_Complex_E2E</b>
	 * Generated     : <b>Apr 19, 2012 4:08:26 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/19
	 * @author mzhao
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
//		WN.createPK(new PK().startParameter(Cfg.projectName)
//				.name("pk1")
//				.type("INT")
//				.nullable("false")
//				.storage("Transient"));
//		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
//				.dataSourceType("JDBC")
//				.name("Product")
//				.connectionProfile("My Sample Database")
//				.sqlQuery("select * from product where name=:name and quantity=:quantity"));
//		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Product");
//
//		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("name"));
//		mbo.setLoadArgument(new LoadArgument().name("quantity").type("INT").pk("pk1").defaultValue("0"));
//		
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				 .mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("Product")
				.objectQuery("findByParameter")
				.to(Cfg.deviceUser)
				.from("name=Sweatshirt")
				.subject("pk=39")
				.toMatchingRule("dt")
				
		.parameterMatchingRule(new WFParameterMatchingRule()
		.name("name")
		.startTag("name=")
		.field("From").toString())	
		
		.parameterMatchingRule(new WFParameterMatchingRule()
		.name("pk1")
		.startTag("pk=")
		.field("Subject").toString())
		
);
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("dt")
				.unwiredServer("My Unwired Server"), 
				customJsTestScripts(), "tplan.Workflow.iconcommon.BB.server_dt_icon2.Script", sendNotification() );
		
		WFCustomizer.verifyResult(new WFClientResult()
		.data("list_items_count=1|"+
				"id=Product_name_attribKey,value=Sweatshirt"));
	}
		
		private CallBackMethod sendNotification() {
			return new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email().to("dt").cc("name=Sweatshirt").subject("pk=39"));
		}
	
	

	private CustomJsTestScript customJsTestScripts() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Product").getListItemsCount();
		s.screen("Product").moveTo("ProductDetail").throughListItem("0");
		s.screen("ProductDetail").getData("Product_name_attribKey");
		return s;
	}
}

