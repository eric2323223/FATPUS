package testscript.Workflow.CallActionOutSideMenu;
import resources.testscript.Workflow.CallActionOutSideMenu.CallAction_Online6_E2EHelper;
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
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Operation;
import component.entity.model.OperationParameter;
import component.entity.model.PK;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class CallAction_Online6_E2E extends CallAction_Online6_E2EHelper
{
	/**
	 * Script Name   : <b>CallAction_Online6_E2E</b>
	 * Generated     : <b>Mar 30, 2012 12:10:05 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/30
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb").type(
								"Sybase_ASA_12.x").name("My Sample Database"),
						GlobalConfig.getRFTProjectRoot()
								+ "/testscript/Workflow/CallActionOutSideMenu/cfg/ASA_ddl.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb").type(
								"Sybase_ASA_12.x").name("My Sample Database"),
						GlobalConfig.getRFTProjectRoot()
								+ "/testscript/Workflow/CallActionOutSideMenu/cfg/ASA_ddl.sql");
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ASA (dba)"),10, 10);
		WN.createPK(new PK().startParameter(Cfg.projectName).name("pk1").type(
				"DOUBLE").nullable("true").storage("Transient").defaultValue(
				"111"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "ASA");
		mbo.setOperation(new Operation().startParameter(
				WN.mboPath(Cfg.projectName, "ASA")).name("create").parameter(""));
		/
		WN.deployProject(new DeployOption().mode(DeployOption.MODE_REPLACE)
				.startParameter(Cfg.projectName).server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName).startParameter(
				Cfg.projectName).option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "ASA");
		WorkFlowEditor.link("Start", "ASAcreate");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("ASA")
				.objectQuery("findAll")
				.defaultSuccessScreen("ASA")
		);
		WorkFlowEditor.addCustomAction("ASAcreate", new WFScreenMenuItem()
				.name("custCreate")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("ASA")
				.operation("create")
				.defaultSuccessScreen("Start")
				.parametermapping("a,ASA_create_a_paramKey")
				.parametermapping("b,ASA_create_b_paramKey")
				.parametermapping("c,ASA_create_c_paramKey")
				);
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(Cfg.projectName)
				.unwiredServer("My Unwired Server")
				.assignToUser(Cfg.deviceUser), customJsTestScript(), "tplan.Workflow.common.StartWF_BB");
		WFCustomizer.verifyResult(new WFClientResult().data("found=true"));
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("ASAcreate").throughMenuItem("Open ASAcreate");
		s.screen("ASAcreate").setData("ASA_create_a_paramKey", "121");
		s.screen("ASAcreate").moveTo("Start").throughCustomAction("custCreate");
		s.screen("Start").moveTo("ASA").throughMenuItem("findAll");
		s.screen("ASA").checkListItem("121", "0");
		return s;
	}
}
