package testscript.Workflow.CallActionOutSideMenu;
import resources.testscript.Workflow.CallActionOutSideMenu.CallAction_Online3_E2EHelper;
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
import component.entity.model.ScrapbookCP;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class CallAction_Online3_E2E extends CallAction_Online3_E2EHelper
{
	/**
	 * Script Name   : <b>CallAction_Online3_E2E</b>
	 * Generated     : <b>Mar 29, 2012 1:14:37 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/29
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb").type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/CallActionOutSideMenu/cfg/ASA_ddl.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb").type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/CallActionOutSideMenu/cfg/ASA_ddl.sql");
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ASA (dba)"), 10, 10);
		WN.deployProject(new DeployOption()
				.mode(DeployOption.MODE_REPLACE)
				.startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "ASA");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start", 
				new WFEditBox().label("a")
							   .logicalType("TEXT")
							   .newKey("a,int"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findByPk")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("ASA")
				.objectQuery("findByPrimaryKey")
				.parametermapping("a,a")
				.defaultSuccessScreen("ASAupdateinstance"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("ASA")
				.objectQuery("findAll")
				.defaultSuccessScreen("ASA")
		);
		WorkFlowEditor.addCustomAction("ASAupdateinstance", new WFScreenMenuItem()
				.name("custUpdate")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("ASA")
				.operation("update")
				.defaultSuccessScreen("Start")
				.parametermapping("b,ASA_b_attribKey")
				.parametermapping("c,ASA_c_attribKey")
				.parametermapping("a,ASA_a_attribKey")
				);
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser), 
				customJsTestScript());
		WFCustomizer.verifyResult(new WFClientResult().data("found=true"));
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("a", "1");
		s.screen("Start").moveTo("ASAupdateinstance").throughMenuItem("findByPk");
		s.screen("ASAupdateinstance").setData("ASA_b_attribKey", "121");
		s.screen("ASAupdateinstance").moveTo("Start").throughCustomAction("custUpdate");
		s.screen("Start").moveTo("ASA").throughMenuItem("findAll");
		s.screen("ASA").checkListItem("121", "1");
		return s;
	}
}

