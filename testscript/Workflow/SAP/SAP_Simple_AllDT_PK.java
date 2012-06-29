package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_Simple_AllDT_PKHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.LoadArgument;
import component.entity.model.MBOAttribute;
import component.entity.model.Modifications;
import component.entity.model.PK;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SAP_Simple_AllDT_PK extends SAP_Simple_AllDT_PKHelper
{
	/**
	 * Script Name   : <b>SAP_Simple_AllDT_PK</b>
	 * Generated     : <b>Nov 14, 2011 7:14:00 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/14
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
				.parameterValue("BANK_CTRY,CN"));
		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Bank"))
				.type("UPDATE")
				.name("update")
				.operation("Application Components->Cross-Application Components->Bank->Bank,Change")
				.parameter("BANKCOUNTRY,in")
				.parameter("BANKKEY,in")
				.parameter("RETURN,out")
				);
		
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("pkString")
				.type("STRING(20)")
				.storage("Transient"));
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Bank");
		mbo.setLoadArgument(new LoadArgument().name("BANK_CTRY").pk("pkString"));
		MainMenu.saveAll();
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Bank");
		
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("CTRY").newKey("key1,string"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAllByCTRY")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Bank")
				.defaultSuccessScreen("Bank")
				.objectQuery("findAll")
				.pkMapping("pkString,key1"));
		MainMenu.saveAll();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
	}
}
//passed
