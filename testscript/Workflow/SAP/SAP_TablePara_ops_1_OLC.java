package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_TablePara_ops_1_OLCHelper;
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
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.MBOAttribute;
import component.entity.model.Modifications;
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
 * Description   : Functional Test Script
 * @author eric
 */
public class SAP_TablePara_ops_1_OLC extends SAP_TablePara_ops_1_OLCHelper
{
	/**
	 * Script Name   : <b>SAP_TablePara_ops_1_OLC</b>
	 * Generated     : <b>Nov 22, 2011 6:11:31 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/22
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
				.parameter("BANK_LIST,in")
				.parameterValue("BANK_CTRY,AM"));
		
		//Create operation "update"...
		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Bank"))
				.type("UPDATE")
				.name("update")
				.operation("Application Components->Cross-Application Components->Bank->Bank,Change")
				.parameter("BANK_ADDRESS->CITY[CHAR],in")
				.parameter("BANKCOUNTRY,in")
				.parameter("BANKKEY,in")
				.parameter("RETURN,out")
				);
		
		//Create operation "create"...
		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Bank"))
				.type("CREATE")
				.name("create")
				.operation("Application Components->Cross-Application Components->Bank->Bank,Create")
				.parameter("BANK_ADDRESS->CITY[CHAR],in")
				.parameter("BANK_ADDRESS->BANK_NAME[CHAR],in")
				.parameter("BANK_CTRY,in")
				.parameter("BANK_KEY,in")
			);
		
		WN.changeCacheGroup(Cfg.projectName, "Bank", "Default (Default)", "online");
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Bank");
		mbo.setLoadArgument(new LoadArgument().name("BANK_CTRY")
				.propagateTo("BANK_CTRY"));
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
		
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("BANK_CTRY")
				.newKey("BANK_CTRY,string"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Bank")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("Bank")
				.parametermapping("BANK_CTRY,BANK_CTRY")
				);
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
	}
}

