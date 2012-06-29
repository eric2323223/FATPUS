package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_Simple_AllDT_E2EHelper;
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
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.Modifications;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SAP_Simple_AllDT_E2E extends SAP_Simple_AllDT_E2EHelper
{
	/**
	 * Script Name   : <b>SAP_Simple_AllDT_E2E</b>
	 * Generated     : <b>Oct 27, 2011 7:13:27 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/27
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.useProject(Cfg.projectName);
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("Bank")
				.connectionProfile("sap")
				.operation("Application Components->Cross-Application Components->Bank->Bank,GetList")
				.parameter("BANK_CTRY,in")
				.parameter("BANK_LIST,out")
				.parameter("BANK_LIST,in")
				.parameterValue("BANK_CTRY,AM"));
		
		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Bank"))
				.type("UPDATE")
				.name("update")
				.operation("Application Components->Cross-Application Components->Bank->Bank,Change")
				.parameter("BANK_ADDRESS->CITY[CHAR],in")
				.parameter("BANKCOUNTRY,in")
				.parameter("BANKKEY,in")
				.parameter("RETURN,out"));
		
		MainMenu.saveAll();
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "Bank"))
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("sap,sap")
				);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Bank");
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Bank")
				.objectQuery("findAll")
				.defaultSuccessScreen("Bank")
				);
		WorkFlowEditor.setMenuItem("Bankupdate1", new WFScreenMenuItem().name("Update1")
				.defaultSuccessScreen("Start"));
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser), 
				customJsTestScript());
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Bank_BANK_CTRY_attribKey,value=AM|"+
				"id=Bank_BANK_KEY_attribKey,value=900002|"+
				"id=Bank_BANK_NAME_attribKey,value=AM|"+
				"id=Bank_CITY_attribKey,value=Xian"));	
		
}
	private CustomJsTestScript customJsTestScript() {
		// TODO Auto-generated method stub
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("Bank").throughMenuItem("findAll");
		s.screen("Bank").moveTo("BankDetail").throughListItem("900002->1");
		
		s.screen("BankDetail").getData("Bank_BANK_CTRY_attribKey");
		s.screen("BankDetail").getData("Bank_BANK_KEY_attribKey");
		s.screen("BankDetail").getData("Bank_BANK_NAME_attribKey");
		s.screen("BankDetail").getData("Bank_CITY_attribKey");
		
		s.screen("BankDetail").moveTo("Bankupdate1").throughMenuItem("Open Bankupdate1");
		
		s.screen("Bankupdate1").setData("Bank_update1_CITY_paramKey", "AM");
		s.screen("Bankupdate1").setData("Bank_update1_BANKCOUNTRY_paramKey", "AM");
	
		s.screen("Bankupdate1").moveTo("Start").throughMenuItem("Update1");
		
		return s;
	}	
}
//passed BB6T 20120228
