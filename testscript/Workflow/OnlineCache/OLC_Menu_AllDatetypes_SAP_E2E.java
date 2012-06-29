package testscript.Workflow.OnlineCache;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;

import resources.testscript.Workflow.OnlineCache.OLC_Menu_AllDatetypes_SAP_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.properties.attributes.AttributeMappingTab;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Menu_AllDatetypes_SAP_E2E extends OLC_Menu_AllDatetypes_SAP_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Menu_AllDatetypes_SAP_E2E</b>
	 * Generated     : <b>Nov 24, 2011 9:59:26 PM</b>
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
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "salesorder_list");
		mbo.setLoadArgument(new LoadArgument().name("MATERIAL").propagateTo("MATERIAL"));
	
		
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
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("sap,sap"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "salesorder_list");
				
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("material")
				.newKey("MATERIAL,string"));
		
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("find")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("salesorder_list")
				.objectQuery("findByParameter")
				.parametermapping("MATERIAL,MATERIAL")
				.defaultSuccessScreen("salesorderlist"));
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("DT")
				.unwiredServer("My Unwired Server"), 
				customJsTestScript(),
				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
		WFCustomizer.verifyResult(new WFClientResult().data("value=M-07,id=salesorder_list_MATERIAL_attribKey|"+
				"value=1000,id=salesorder_list_SALES_ORG_attribKey|"+
				"value=0000002006,id=salesorder_list_SOLD_TO_attribKey"));
	
	}
	
	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("MATERIAL", "M-07");
		s.screen("Start").moveTo("salesorderlist").throughMenuItem("find");
		s.screen("salesorderlist").moveTo("salesorderlistDetail").throughListItem("0");
		s.screen("salesorderlistDetail").getData("salesorder_list_MATERIAL_attribKey");
		s.screen("salesorderlistDetail").getData("salesorder_list_SALES_ORG_attribKey");
		s.screen("salesorderlistDetail").getData("salesorder_list_SOLD_TO_attribKey");
		return s;
	}
}
//Passed BB6T 20120419
