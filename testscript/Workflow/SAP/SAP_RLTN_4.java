package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_RLTN_4Helper;
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
public class SAP_RLTN_4 extends SAP_RLTN_4Helper
{
	/**
	 * Script Name   : <b>SAP_RLTN_4</b>
	 * Generated     : <b>Dec 1, 2011 11:32:23 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/01
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.useProject(Cfg.projectName);
		
		//create three mbos to make relationships:
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("customer_list")
				.connectionProfile("sap")
				.bapiOperation("BAPI_CUSTMATINFO_GETLIST,GetList")
				.parameter("CUSTOMERMATERIALINFO,out")
				);
		
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("salesorder_list")
				.connectionProfile("sap")
				.bapiOperation("BAPI_SALESORDER_GETLIST,GetList")
				.parameter("CUSTOMER_NUMBER,in")
				.parameter("MATERIAL,in")
				.parameter("SALES_ORGANIZATION,in")
				.parameter("SALES_ORDERS,out")
				.parameterValue("CUSTOMER_NUMBER,0000002006")
				.parameterValue("MATERIAL,M-07")
				.parameterValue("SALES_ORGANIZATION,1000"));
				
		//create O2O relationship between Customer_list with Salesorder_list...
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "customer_list"))
			.target("salesorder_list")
			.mapping("SALESORG,SALES_ORG")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "customer_list"))
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("sap,sap")
				);
		
		//create WF..
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT)
				);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "customer_list");
		
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("org:")
				.newKey("org,string"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findByORG")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("customer_list")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("customer_listDetail")
				.parametermapping("SALESORG,org"));
		MainMenu.saveAll();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
	}
}
//passed
