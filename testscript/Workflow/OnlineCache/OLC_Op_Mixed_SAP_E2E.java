package testscript.Workflow.OnlineCache;
import com.sybase.automation.framework.widget.DOF;

import resources.testscript.Workflow.OnlineCache.OLC_Op_Mixed_SAP_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WFScreen;
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
import component.entity.model.Modifications;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFKey;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_Mixed_SAP_E2E extends OLC_Op_Mixed_SAP_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_Mixed_SAP_E2E</b>
	 * Generated     : <b>Oct 25, 2011 9:59:27 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.useProject(Cfg.projectName);
		WN.createCacheGroup(new CacheGroup().startParameter(Cfg.projectName).name("online").type(CachePolicy.ONLINE));
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("Bank")
				.connectionProfile("sap")
				.operation("Application Components->Cross-Application Components->Bank->Bank,GetList")
				.parameter("BANK_CTRY,in")
				.parameter("BANK_LIST,out")
				.parameterValue("BANK_CTRY,AM"));
		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Bank"))
				.type("UPDATE")
				.name("update")
				.operation("Application Components->Cross-Application Components->Bank->Bank,Change")
				.parameter("BANK_ADDRESS->CITY[CHAR],in")
				.parameter("BANKCOUNTRY,in")
				.parameter("BANKKEY,in")
				.parameter("RETURN,out")
				);
		
		WN.copyMbo(Cfg.projectName, "Bank", "BankOnline");
		WN.changeCacheGroup(Cfg.projectName, "BankOnline", "Default (Default)", "online");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "BankOnline");
		mbo.setLoadArgument(new LoadArgument().name("BANK_CTRY").propagateTo("BANK_CTRY"));
   
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("sap,sap"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Bank");
		WorkFlowEditor.dragMbo(Cfg.projectName, "BankOnline");
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("country").newKey("key,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findAllBank")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Bank")
				.defaultSuccessScreen("Bank")
				.objectQuery("findAll"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findAllBankOnline")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("BankOnline")
				.parametermapping("BANK_CTRY,key")
				.defaultSuccessScreen("BankOnline")
				.objectQuery("findByParameter"));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigureToClick("Bankupdate1").doubleClick();
		PropertiesView.getMenuItem("Update1");
		PropertiesView.set(new WFScreenMenuItem()
				.defaultSuccessScreen("Start"));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigureToClick("BankOnlineupdate1").doubleClick();
		PropertiesView.getMenuItem("Update1");
		PropertiesView.set(new WFScreenMenuItem()
				.parametermapping("BANK_ADDRESS.CITY,BankOnline_update1_CITY_paramKey:BANKCOUNTRY,BankOnline_update1_BANKCOUNTRY_paramKey")
				.defaultSuccessScreen("BankOnlineDetail"));
		
	//not used>>>>>>>>	
////		PropertiesView.set(new WFScreen().name("Bank_update"), new Modifications()
////				.mod("update", new WFScreenMenuItem().name("Update")
////						.defaultSuccessScreen("BankDetail")));
////		PropertiesView.set(new WFScreen().name("BankOnline_update"), new Modifications()
////				.mod("update", new WFScreenMenuItem().name("Update")
////						.parametermapping("BANK_ADDRESS.CITY,BankOnline_update_CITY_paramKey:BANKCOUNTRY,BankOnline_update_BANKCOUNTRY_paramKey")
////						.defaultSuccessScreen("BankOnlineDetail")));
		//not used<<<<<<<<<<<<<<<	
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser("DT"), customJsTestScript(),
				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Bank_BANK_CTRY_attribKey,value=AM|"+
				"id=BankOnline_BANK_CTRY_attribKey,value=AM"));	
		
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("key","AM");
		s.screen("Start").moveTo("Bank").throughMenuItem("findAllBank");
		s.screen("Bank").moveTo("BankDetail").throughListItem("0");
		s.screen("BankDetail").getData("Bank_BANK_CTRY_attribKey");
		
		s.screen("BankDetail").moveTo("Bankupdate1").throughMenuItem("Open Bankupdate1");
		s.screen("Bankupdate1").setData("Bank_update1_BANKCOUNTRY_paramKey", "AM");
		s.screen("Bankupdate1").setData("Bank_update1_CITY_paramKey", "AN");
		s.screen("Bankupdate1").moveTo("Start").throughMenuItem("Update1");
		
		s.screen("Start").setData("key","AM");
		s.screen("Start").moveTo("BankOnline").throughMenuItem("findAllBankOnline");
		s.screen("BankOnline").moveTo("BankOnlineDetail").throughListItem("0");
		s.screen("BankOnlineDetail").getData("BankOnline_BANK_CTRY_attribKey");
		
		s.screen("BankOnlineDetail").moveTo("BankOnlineupdate1").throughMenuItem("Open BankOnlineupdate1");
		s.screen("BankOnlineupdate1").setData("BankOnline_update1_BANKCOUNTRY_paramKey", "AM");
		s.screen("BankOnlineupdate1").setData("BankOnline_update1_CITY_paramKey", "AN");
		s.screen("BankOnlineupdate1").moveTo("BankOnlineDetail").throughMenuItem("Update1");
		return s;
	}
}
//Passed BB6T 20120419
