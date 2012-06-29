package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_TablePara_ops_2Helper;
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
import component.entity.resource.SAPFour;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SAP_TablePara_ops_2 extends SAP_TablePara_ops_2Helper
{
	/**
	 * Script Name   : <b>SAP_TablePara_ops_1</b>
	 * Generated     : <b>Nov 29, 2011 5:41:51 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/29
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		// create and link the server: sap-crm04.sybase.com
		EE.createSAPCP(new SAPCP(RC.getResource(SAPFour.class)).name("sap04"), true);
		WN.useProject(Cfg.projectName);
//		//create two table parameters:
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("INVENTORY")
				.connectionProfile("sap04")
				.bapiOperation("BAPI_FIXEDASSET_GETLIST,GetList")
				.parameter("COMPANYCODE,in")
				.parameter("REQUESTEDTABLESX->INVENTORY[CHAR],in")
				.parameter("INVENTORY->ASSET[CHAR],in")
				.parameter("INVENTORY,out")
				.parameter("INVENTORY->SUBNUMBER[CHAR],in")
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
		
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("INVENTORY1:")
				.newKey("INVENTORY1,string"));
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("INVENTORY")
				.objectQuery("findAll")
				.defaultSuccessScreen("INVENTORY")
				.pkMapping("pk1,INVENTORY1")
				);
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
	}
}
//passed