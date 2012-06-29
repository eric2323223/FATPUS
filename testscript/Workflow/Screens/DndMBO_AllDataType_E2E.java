package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.DndMBO_AllDataType_E2EHelper;
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
import component.entity.GlobalConfig;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DndMBO_AllDataType_E2E extends DndMBO_AllDataType_E2EHelper
{
	/**
	 * Script Name   : <b>DndMBO_AllDataType_E2E</b>
	 * Generated     : <b>Sep 16, 2011 11:14:30 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/16
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
		//create Alldatetype table:
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/create_ALLDT.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		WN.deployProject(new DeployOption()
	         	.startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllDT");
		vpManual("hasscreen",true,WorkFlowEditor.hasScreen("AllDT")).performTest();
		vpManual("hasscreen",true,WorkFlowEditor.hasScreen("AllDTDetail")).performTest();
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
			.name("findAll")
			.type("Online Request")
			.project(Cfg.projectName)
			.mbo("AllDT")
			.objectQuery("findAll")
			.defaultSuccessScreen("AllDT"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript()
//			, 
//			"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
			);
		
		WFCustomizer.verifyResult(new WFClientResult().data(
			"list_items_count=2|" +
			"id=AllDT_int1_attribKey,value=1|" +
			"id=AllDT_string1_attribKey,value=1"));
		}

		private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Start").moveTo("AllDT").throughMenuItem("findAll");
			s.screen("AllDT").getListItemsCount();
			s.screen("AllDT").moveTo("AllDTDetail").throughListItem("0");
			s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
			s.screen("AllDTDetail").getData("AllDT_string1_attribKey");
			return s;
		}
}
//passed BB6T 20120203
