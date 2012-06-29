package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_MLI_RLHelper;
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
import component.entity.model.DeployOption;
import component.entity.model.MBOAttribute;
import component.entity.model.Relationship;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SAP_MLI_RL extends SAP_MLI_RLHelper
{
	/**
	 * Script Name   : <b>SAP_MLI_RL</b>
	 * Generated     : <b>Dec 2, 2011 11:25:19 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/02
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
				.parameter("SALES_ORGANIZATION,in")
				.parameter("SALES_ORDERS,out")
				.parameterValue("CUSTOMER_NUMBER,0000002006")
				.parameterValue("SALES_ORGANIZATION,1000"));
		
		//Create the first operation called "create" for Salesorder_list
		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Salesorder_list"))
				.type("CREATE")
				.name("create")
				.operation("Application Components->Sales and Distribution->Sales->SalesOrder,CreateFromData")
				.parameter("ORDER_ITEMS_IN,in")
				.parameter("SALESDOCUMENT,out")
				
				);
		
		//Create the second operation called "update" for GetDetail
		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Salesorder_list"))
				.type("UPDATE")
				.name("update")
				.operation("Application Components->Sales and Distribution->Sales->SalesOrder,ChangeFromData")
				.parameter("SALESDOCUMENT,in")
				.parameterValue("SALESDOCUMENT,0000121895")
		);
		
		//create O2O relationship between Customer_list with Salesorder_list...
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Customer_list"))
			.target("Salesorder_list")
			.mapping("SalesORG,Sales_ORG")
			.composite("true")
			.type(Relationship.TYPE_OTO));
		
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "Customer_list"))
				.server("My Unwired Server")
				.serverConnectionMapping("sap,sap")
				);
		
		//create WF..
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Customer_list")
				.objectQuery("findByPrimaryKey")
				.subject("org=1000")
				.subjectMatchingRule("org=")
				.setParameterValue("SalesORG,Subject,org=")
				);
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
	}
}
//PASSED
