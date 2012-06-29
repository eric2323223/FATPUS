package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_RLTN_2Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.DeployOption;
import component.entity.model.MBOAttribute;
import component.entity.model.Relationship;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SAP_RLTN_2 extends SAP_RLTN_2Helper
{
	/**
	 * Script Name   : <b>SAP_RLTN_2</b>
	 * Generated     : <b>Dec 2, 2011 3:58:27 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/02
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
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
				
		
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("GetStatus")
				.connectionProfile("sap")
				.bapiOperation("BAPI_SALESORDER_GETSTATUS,GetStatus")
				.parameter("SALESDOCUMENT,in")
				.parameter("STATUSINFO,out")
				.parameterValue("SALESDOCUMENT,0000005120"));
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "customer_list");
		mbo.setAttribute(new MBOAttribute().name("SALESORG").primaryKey(true));
		
		MBOProperties mbot = new MBOProperties(Cfg.projectName, "salesorder_list");
		mbot.setAttribute(new MBOAttribute().name("METERIAL").primaryKey(true));
		
		
		//create O2O relationship between Customer_list with Salesorder_list...
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "customer_list"))
			.target("salesorder_list")
			.mapping("SALESORG,SALES_ORG")
			.composite("true")
			.type(Relationship.TYPE_OTO));
		
		//create O2O relationship between  Salesorder_list with GetDetail...
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "salesorder_list"))
			.target("GetStatus")
			.mapping("METERIAL,METERIAL")
			.composite("true")
			.type(Relationship.TYPE_OTO));
		
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "customer_list"))
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("sap,sap")
				);
		
		//create WF..
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("customer_list")
				.objectQuery("findByPrimaryKey")
				.subject("org=1000")
				.subjectMatchingRule("org=")
				.setParameterValue("SALESORG,Subject,org=")
				);
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
	}
}
//passed
