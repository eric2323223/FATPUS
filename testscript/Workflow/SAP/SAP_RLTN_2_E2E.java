package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_RLTN_2_E2EHelper;
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
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.MBOAttribute;
import component.entity.model.Relationship;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;
import component.view.properties.attributes.AttributeMappingTab;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SAP_RLTN_2_E2E extends SAP_RLTN_2_E2EHelper
{
	/**
	 * Script Name   : <b>SAP_RLTN_2_E2E</b>
	 * Generated     : <b>Dec 2, 2011 3:58:47 AM</b>
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
		mbot.setAttribute(new MBOAttribute().name("MATERIAL").primaryKey(true));
		
		//need to delete the attributes which value is null in table of salesorder_list and GetStatus
		mbo.selectMbo();
		PropertiesView.maximize();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes Mapping"));
		AttributeMappingTab.deleteAttribute("DOC_DATE");
		AttributeMappingTab.deleteAttribute("REQ_DATE");
		AttributeMappingTab.deleteAttribute("VALID_FROM");
		AttributeMappingTab.deleteAttribute("VALID_TO");
		AttributeMappingTab.deleteAttribute("CREATION_DATE");
		AttributeMappingTab.deleteAttribute("CREATION_TIME");
		MainMenu.saveAll();
		PropertiesView.restore();
		
		//need to delete the attributes which value is null in table of GetStatus
		MBOProperties mbo1 = new MBOProperties(Cfg.projectName, "GetStatus");
		mbo1.selectMbo();
		PropertiesView.maximize();
		PropertiesTabHelper.clickTabName("Attributes");
		DOF.getCTabFolder(DOF.getRoot(), "Attributes").click(atText("Attributes Mapping"));
		AttributeMappingTab.deleteAttribute("DOC_DATE");
		AttributeMappingTab.deleteAttribute("REQ_DATE_H");
		AttributeMappingTab.deleteAttribute("REQ_DATE");
		AttributeMappingTab.deleteAttribute("DELIV_DATE");
		AttributeMappingTab.deleteAttribute("CREATION_DATE");
		AttributeMappingTab.deleteAttribute("CREATION_TIME");
		MainMenu.saveAll();
		PropertiesView.restore();
		
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
			.mapping("+ MATERIAL,MATERIAL")
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
	
		//deploy WF to device..
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "mailo2o"))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser), 
				customJsTestScript(),
				testscript.Workflow.cfg.Cfg.tplanScript_server_2,
				new CallBackMethod().receiver(WorkFlowEditor.class)
					.methodName("sendNotification")
					.parameter(new Email()
					.unwiredServer("My Unwired Server")
					.selectTo(testscript.Workflow.cfg.Cfg.deviceUser)
					.subject("org=1000")));
		
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=customer_list_SALESORG_attribKey,value=1000|"+
				"id=salesorder_list_SALES_ORG_attribKey,value=1000|"+					
				"id=salesorder_list_MATERIAL_attribKey,value=M-07|"+					
				"id=GetStatus_MATERIAL_attribKey,value=M-07"));	
}

	private CustomJsTestScript customJsTestScript() {
		// TODO Auto-generated method stub
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("customer_listDetail").getData("customer_list_SALESORG_attribKey");
		
		s.screen("customer_listDetail").moveTo("salesorder_listDetail").throughMenuItem("Open salesorder_listDetail");
		
		s.screen("salesorder_listDetail").getData("salesorder_list_SALES_ORG_attribKey");
		s.screen("salesorder_listDetail").getData("salesorder_list_MATERIAL_attribKey");
		
		s.screen("salesorder_listDetail").moveTo("GetStatusDetail").throughMenuItem("Open GetStatusDetail");
		s.screen("GetStatusDetail").getData("GetStatus_MATERIAL_attribKey");
		return s;
	}
}
//passed BB6T 20120224
