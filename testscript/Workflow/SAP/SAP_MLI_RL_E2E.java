package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_MLI_RL_E2EHelper;
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
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.MBOAttribute;
import component.entity.model.Relationship;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SAP_MLI_RL_E2E extends SAP_MLI_RL_E2EHelper
{
	/**
	 * Script Name   : <b>SAP_MLI_RL_E2E</b>
	 * Generated     : <b>Dec 2, 2011 11:25:47 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/02
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
//		// TODO Insert code here
//		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
//		WN.useProject(Cfg.projectName);
//		
//		//create three mbos to make relationships:
//		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
//				.dataSourceType("SAP")
//				.name("customer_list")
//				.connectionProfile("sap")
//				.bapiOperation("BAPI_CUSTMATINFO_GETLIST,GetList")
//				.parameter("CUSTOMERMATERIALINFO,out")
//				);
//		
//		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
//				.dataSourceType("SAP")
//				.name("salesorder_list")
//				.connectionProfile("sap")
//				.bapiOperation("BAPI_SALESORDER_GETLIST,GetList")
//				.parameter("CUSTOMER_NUMBER,in")
//				.parameter("MATERIAL,in")
//				.parameter("SALES_ORGANIZATION,in")
//				.parameter("SALES_ORDERS,out")
//				.parameterValue("CUSTOMER_NUMBER,0000002006")
//				.parameterValue("MATERIAL,M-07")
//				.parameterValue("SALES_ORGANIZATION,1000"));
//				
//		
//		//Create the first operation called "create" for Salesorder_list
//		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "salesorder_list"))
//				.type("CREATE")
//				.name("create")
//				.operation("Application Components->Sales and Distribution->Sales->SalesOrder,CreateFromData")
//				.parameter("ORDER_ITEMS_IN,in")
//				.parameter("SALESDOCUMENT,out")
//				
//				);
//		
//		//Create the second operation called "update" for GetDetail
//		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "salesorder_list"))
//				.type("UPDATE")
//				.name("update")
//				.operation("Application Components->Sales and Distribution->Sales->SalesOrder,ChangeFromData")
//				.parameter("SALESDOCUMENT,in")
//				.parameterValue("SALESDOCUMENT,0000121895")
//		);
//		
//		MBOProperties mbo = new MBOProperties(Cfg.projectName, "customer_list");
//		mbo.setAttribute(new MBOAttribute().name("SALESORG").primaryKey(true));
//		
//		//create O2O relationship between Customer_list with Salesorder_list...
//		WN.createRelationship(new Relationship()
//			.startParameter(WN.mboPath(Cfg.projectName, "customer_list"))
//			.target("salesorder_list")
//			.mapping("SALESORG,SALES_ORG")
//			.composite("true")
//			.type(Relationship.TYPE_OTO));
//		
//		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "customer_list"))
//				.server("My Unwired Server")
//				.mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("sap,sap")
//				);
//		
//		//create WF..
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_CLIENT_INIT)
//				);
//		
//		WorkFlowEditor.dragMbo(Cfg.projectName, "customer_list");
//		
//		WorkFlowEditor.addWidget("Start", new WFEditBox().label("org:")
//				.newKey("org,string"));
//		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findByORG")
//				.type("Online Request")
//				.project(Cfg.projectName)
//				.mbo("customer_list")
//				.objectQuery("findByPrimaryKey")
//				.defaultSuccessScreen("customerlistDetail")
//				.parametermapping("SALESORG,org"));
//		MainMenu.saveAll();
//		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
//	
		//deploy WF to device..
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName,Cfg.wfName ))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser), 
				customJsTestScript(),
				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=customer_list_SALESORG_attribKey,value=1000|"+
				"id=salesorder_list_SALES_ORG_attribKey,value=1000"));	
}

	private CustomJsTestScript customJsTestScript() {
		// TODO Auto-generated method stub
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("org", "1000");
		s.screen("Start").moveTo("customerlistDetail").throughMenuItem("findByORG");
		s.screen("customerlistDetail").getData("customer_list_SALESORG_attribKey");
		s.screen("customerlistDetail").moveTo("salesorder_list").throughMenuItem("Open salesorderlistDetail");
//		s.screen("salesorder_list").moveTo("salesorder_listDetail").throughListItem("0");
		s.screen("salesorderlistDetail").getData("salesorder_list_SALES_ORG_attribKey");
		return s;
	}
}

