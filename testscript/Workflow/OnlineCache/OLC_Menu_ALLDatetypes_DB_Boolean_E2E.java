package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Menu_ALLDatetypes_DB_Boolean_E2EHelper;
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
import component.entity.model.WFCheckbox;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author min zhao
 */
public class OLC_Menu_ALLDatetypes_DB_Boolean_E2E extends OLC_Menu_ALLDatetypes_DB_Boolean_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Menu_ALLDatetypes_DB_Boolean_E2E</b>
	 * Generated     : <b>Apr 11, 2012 1:18:06 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/11
	 * @author min zhao
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/Alldate.sql");
		
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllData (dba)");
//		MBOProperties mbo = new MBOProperties(Cfg.projectName, "AllData");
//		mbo.setMboDefinition("sql", "SELECT * FROM sampledb.dba.AllData where type_BOOLEAN=:type_BOOLEAN");
//		mbo.setLoadArgument(new LoadArgument().name("type_BOOLEAN").type("BOOLEAN").propagateTo("type_BOOLEAN"));
//		
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				 .mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
//		
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllData");
		WorkFlowEditor.addWidget("Start", new WFCheckbox().label("type_BOOLEAN")
				.newKey("key1,bool"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("find")
				.project(Cfg.projectName)
				.mbo("AllData")
				.type("Online Request")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("AllData")
				.parametermapping("type_BOOLEAN,key1"));
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("dt")
				.unwiredServer("My Unwired Server"), 
				customJsTestScripts(), "tplan.Workflow.iconcommon.BB.myWF_icon2.Script" );
		
		WFCustomizer.verifyResult(new WFClientResult()
		.data("list_items_count=1|"+
				"id=AllData_type_BOOLEAN_attribKey,value=True"));
	
	}

	private CustomJsTestScript customJsTestScripts() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("key1", "True");
		s.screen("Start").moveTo("AllData").throughMenuItem("find");
		s.screen("AllData").getListItemsCount();
		s.screen("AllData").moveTo("AllDataDetail").throughListItem("0");
		s.screen("AllDataDetail").getData("AllData_type_BOOLEAN_attribKey");
		return s;
	}
}

