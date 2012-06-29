package testscript.Workflow.SAP;

import resources.testscript.Workflow.SAP.SAP_TablePara_ops_2_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
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
import component.entity.model.MBOAttribute;
import component.entity.model.Modifications;
import component.entity.model.PK;
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
 * Description : Functional Test Script
 * 
 * @author eric
 */
public class SAP_TablePara_ops_2_E2E extends SAP_TablePara_ops_2_E2EHelper {
	/**
	 * Script Name : <b>SAP_TablePara_ops_1_E2E</b> Generated : <b>Oct 27, 2011
	 * 6:38:02 PM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 * 
	 * @since 2011/10/27
	 * @author eric
	 */
	public void testMain(Object[] args) {
		// create and link the server: sap-crm04.sybase.com
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap04"), true);
		WN.useProject(Cfg.projectName);
		//create two table parameters:
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("INVENTORY")
				.connectionProfile("sap04")
				.bapiOperation("BAPI_FIXEDASSET_GETLIST,GetList")
				.parameter("COMPANYCODE,in")
				.parameter("REQUESTEDTABLESX->INVENTORY[CHAR],in")	
				.parameter("INVENTORY->ASSET[CHAR],in")
				
				.parameter("INVENTORY->SUBNUMBER[CHAR],in")
				.parameter("INVENTORY,out")
				.parameter("INVENTORY->ASSET[CHAR],in")
				
				.parameterValue("COMPANYCODE,2000")
				.parameterValue("REQUESTEDTABLESX->INVENTORY,x")
				.parameterValue("INVENTORY->ASSET,000000001000")
				.parameterValue("INVENTORY->SUBNUMBER,0000")
				);
		
		//create two structure pks:
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("pk1")
				.type("string[20]")
				.storage("Transient"));
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "INVENTORY");
		mbo.setLoadArgument(new LoadArgument().name("REQUESTEDTABLESX->INVENTORY").pk("pk1"));
		mbo.setAttribute(new MBOAttribute().name("ASSET").primaryKey(true));
		// delete the attribute "date" type of Fixassert,as its value is null
		mbo.deleteAttribute("DATE");
		MainMenu.saveAll();
		
		vpManual("hasMBO",true,WN.hasMboInProject(Cfg.projectName,"INVENTORY")).performTest();
		
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "INVENTORY"))
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("sap04,sap")
				);
		
		//create client WorkFlow Project:
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "INVENTORY");
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("INVENTORY1:")
				.newKey("INVENTORY1,string"));
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("INVENTORY")
				.objectQuery("findAll")
				.defaultSuccessScreen("INVENTORY")
				.pkMapping("pk1,INVENTORY1")
				);
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser),
				customJsTestScript(), 
				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
		WFCustomizer.verifyResult(new WFClientResult().data(
					"id=INVENTORY_ASSET_attribKey,value=000000001000|"+					
					"id=INVENTORY_SUBNUMBER_attribKey,value=0000"
					));	
	}

	private CustomJsTestScript customJsTestScript() {
	// TODO Auto-generated method stub
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("INVENTORY1","x");
		s.screen("Start").moveTo("INVENTORY").throughMenuItem("findAll");
		s.screen("INVENTORY").moveTo("INVENTORYDetail").throughListItem("0");
		s.screen("INVENTORYDetail").getData("INVENTORY_ASSET_attribKey");
		s.screen("INVENTORYDetail").getData("INVENTORY_SUBNUMBER_attribKey");
		return s;
	}
}
//passed BB6T 20120227