package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_StructPara_opsHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.MBOAttribute;
import component.entity.model.PK;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.entity.resource.SAPFour;
import component.view.Problems;
import component.entity.customJsGenerator.CustomJsTestScript;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SAP_StructPara_ops extends SAP_StructPara_opsHelper
{
	/**
	 * Script Name   : <b>SAP_StructPara_ops</b>
	 * Generated     : <b>Nov 14, 2011 7:23:12 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/14
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class,
//		new Criteria().equal("name", "sap-crm04"))).name("sap04"), true);
		WN.useProject(Cfg.projectName);
		
		//create two structure load parameters:
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("Fixassert")
				.connectionProfile("sap04")
				.bapiOperation("BAPI_FIXEDASSET_GETLIST,GetList")
				.parameter("COMPANYCODE,in")
				.parameter("INVENTORY,out")
				.parameter("REQUESTEDTABLESX->GENERALDATA[CHAR],in")
				.parameter("REQUESTEDTABLESX->INVENTORY[CHAR],in")
				.parameter("GENERALDATA,out")
				.parameterValue("COMPANYCODE,2000")
				.parameterValue("REQUESTEDTABLESX->GENERALDATA,x")
				.parameterValue("REQUESTEDTABLESX->INVENTORY,x")
				);
		//create two structure pks:
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("pk1")
				.type("string[20]")
				.storage("Transient"));
		
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("pk2")
				.type("string[20]")
				.storage("Transient"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Fixassert");
		mbo.setLoadArgument(new LoadArgument().name("REQUESTEDTABLESX->GENERALDATA").pk("pk1"));
		mbo.setLoadArgument(new LoadArgument().name("REQUESTEDTABLESX->INVENTORY").pk("pk2"));
		mbo.setAttribute(new MBOAttribute().name("ASSET").primaryKey(true));
		
		// delete the attribute "date" type of Fixassert,as this value is null
		mbo.deleteAttribute("DATE");
		MainMenu.saveAll();
		
		vpManual("hasMBO",true,WN.hasMboInProject(Cfg.projectName,"Fixassert")).performTest();
		vpManual("hasMBO",true,WN.hasMboInProject(Cfg.projectName,"GENERALDATA")).performTest();
		vpManual("hasMBO",true,WN.hasFile(Cfg.projectName, "Structures->BAPI_FIXEDASSET_GETLIST_REQUESTEDTABLESX")).performTest();
		
		//new create operation:>>>>>>>>>>>>>>>>
		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Fixassert"))
				.type("CREATE")
				.name("create")
				.operation("Application Components->Financial Accounting->Asset Accounting->Basic Functions->FixedAsset,CreateFromData")
				.parameter("GENERALDATA,in")
				.parameter("RETURN,out"));
		
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "Fixassert"))
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("sap04,sap")
				);
		
		//create client WorkFlow Project:
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Fixassert");
		
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("GENERALDATA")
				.newKey("GENERALDATA,string"));
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("INVENTORY")
				.newKey("INVENTORY,string"));
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Fixassert")
				.objectQuery("findAll")
				.defaultSuccessScreen("Fixassert")
				.pkMapping("pk1,GENERALDATA")
				.pkMapping("pk2,INVENTORY")
				);
		WorkFlowEditor.link("FixassertDetail", "Fixassert_create");
		MainMenu.saveAll();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
	}
}
//passed
