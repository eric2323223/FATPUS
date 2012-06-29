package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_StructPara_ops_E2EHelper;
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
import component.entity.model.MBOAttribute;
import component.entity.model.Modifications;
import component.entity.model.PK;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.Criteria;
import component.entity.resource.RC;
import component.entity.resource.SAP;

import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SAP_StructPara_ops_E2E extends SAP_StructPara_ops_E2EHelper
{
	/**
	 * Script Name   : <b>SAP_StructPara_ops_E2E</b>
	 * Generated     : <b>Nov 14, 2011 3:12:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/14
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// create and link the server: sap-crm04.sybase.com
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class,
		new Criteria().equal("name", "sap-crm04"))).name("sap04"), true);
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
				.parameter("COMPANYCODE,in")
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
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("GENERALDATA")
				.newKey("GENERALDATA,string"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("INVENTORY")
				.newKey("INVENTORY,string"));
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Fixassert")
				.objectQuery("findAll")
				.defaultSuccessScreen("Fixassert")
				.pkMapping("pk1,GENERALDATA")
				.pkMapping("pk2,INVENTORY")
				);
	//	WorkFlowEditor.link("FixassertDetail", "Fixassertcreate");
		WorkFlowEditor.addMenuItem("FixassertDetail", new WFScreenMenuItem().name("Open Fixassertcreate")
				.type("Open")
				.screen("Fixassertcreate"));
		MainMenu.saveAll();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser),
				customJsTestScript());
		
		WFCustomizer.verifyResult(new WFClientResult().data(
					"id=Fixassert_ASSET_attribKey,value=000000001000|"+					
					"id=Fixassert_SUBNUMBER_attribKey,value=0000"
					));	
	}

	private CustomJsTestScript customJsTestScript() {
	// TODO Auto-generated method stub
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("GENERALDATA","x");
		s.screen("Start").setData("INVENTORY","x");
		s.screen("Start").moveTo("Fixassert").throughMenuItem("findAll");
		s.screen("Fixassert").moveTo("FixassertDetail").throughListItem("0");
		s.screen("FixassertDetail").getData("Fixassert_ASSET_attribKey");
		s.screen("FixassertDetail").getData("Fixassert_SUBNUMBER_attribKey");
		
		//execute create operation:
		s.screen("FixassertDetail").moveTo("Fixassertcreate").throughMenuItem("Open Fixassertcreate");
		s.screen("Fixassertcreate").setData("Fixassert_create_ASSETCLASS_paramKey","00003200");
		s.screen("Fixassertcreate").setData("Fixassert_create_DESCRIPT_paramKey","0000");
		s.screen("Fixassertcreate").setData("Fixassert_create_MAIN_DESCRIPT_paramKey","MyCreate");
		s.screen("Fixassertcreate").setData("Fixassert_create_COMPANYCODE_paramKey","2000");
		s.screen("Fixassertcreate").menuItem("Create");
		
		return s;
	}
		
}
//passed BB6T 20120224