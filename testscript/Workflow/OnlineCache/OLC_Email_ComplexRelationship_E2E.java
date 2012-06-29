package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Email_ComplexRelationship_E2EHelper;
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
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.resource.RC;
import component.entity.resource.SAP;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Email_ComplexRelationship_E2E extends OLC_Email_ComplexRelationship_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Email_ComplexRelationship_E2E</b>
	 * Generated     : <b>Nov 24, 2011 11:01:26 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/24
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("SalesOrder")
				.connectionProfile("sap")
				.operation("Application Components->Sales and Distribution->Sales->SalesOrder,GetList")
				.parameter("CUSTOMER_NUMBER,in")
				.parameter("MATERIAL,in")
				.parameter("SALES_ORGANIZATION,in")
				.parameter("SALES_ORDERS,out")
				);
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "SalesOrder");
//		mbo.setLoadArgument(new LoadArgument().name("userID").defaultValue("102"));
//		mbo.setLoadArgument(new LoadArgument().name("userID").propagateTo("id"));
//		
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.serverConnectionMapping("ws,ws"));
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_SERVER_INIT)
//				.project(Cfg.projectName)
//				.mbo("User")
//				.objectQuery("findByParameter")
//				.to("DT")
//				.toMatchingRule("DT"));
//		
////		WorkFlowEditor.dragMbo(Cfg.projectName, "User");
////		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("id")
////				.newKey("key1,string"));
////		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
////				.name("find")
////				.project(Cfg.projectName)
////				.mbo("User")
////				.type("Online Request")
////				.objectQuery("findByParameter")
////				.defaultSuccessScreen("User")
////				.parametermapping("userID,key1"));
//		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
//				.deployToServer("true")
//				.assignToUser("DT")
//				.unwiredServer("My Unwired Server"), 
//				customJsTestScripts(), "", sendNotification());
//		WFCustomizer.verifyResult(new WFClientResult().data("value=ooo,id=User_name_attribKey"));
	
	}
}

