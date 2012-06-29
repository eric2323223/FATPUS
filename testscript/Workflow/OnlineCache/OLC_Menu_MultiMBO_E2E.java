package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Menu_MultiMBO_E2EHelper;
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
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.properties.attributes.AttributeMappingTab;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class OLC_Menu_MultiMBO_E2E extends OLC_Menu_MultiMBO_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Menu_MultiMBO_E2E</b>
	 * Generated     : <b>Apr 20, 2012 11:18:35 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/20
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.createCacheGroup(new CacheGroup().startParameter(Cfg.projectName).name("online").type(CachePolicy.ONLINE));
	
		//the first online MBO
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
		
		WN.changeCacheGroup(Cfg.projectName, "Salesorder_list", "Default (Default)", "online");
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
		
		//another online MBO 
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("Bank")
				.connectionProfile("sap")
				.operation("Application Components->Cross-Application Components->Bank->Bank,GetList")
				.parameter("BANK_CTRY,in")
				.parameter("BANK_LIST,out")
				.parameterValue("BANK_CTRY,AM"));
		
		WN.changeCacheGroup(Cfg.projectName, "Bank", "Default (Default)", "online");
		MBOProperties onlinembo = new MBOProperties(Cfg.projectName, "Bank");
		onlinembo.setLoadArgument(new LoadArgument().name("BANK_CTRY").propagateTo("BANK_CTRY"));
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("sap,sap"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Bank");
				
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("BANK_CTRY")
				.newKey("BANK_CTRY,string"));
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findBank")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Bank")
				.objectQuery("findByParameter")
				.parametermapping("BANK_CTRY,BANK_CTRY")
				.defaultSuccessScreen("Bank"));
		MainMenu.saveAll();

		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser("DT"), 
				customJsTestScript(), 
				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
		WFCustomizer.verifyResult(new WFClientResult().data("list_item_count=81"));
	}
	
		private CustomJsTestScript customJsTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Start").setData("BANK_CTRY", "AM");
			s.screen("Start").moveTo("Bank").throughMenuItem("findBank");
			s.screen("Bank").getListItemsCount();
			return s;
	}
}
//passed BB6T 20120420
