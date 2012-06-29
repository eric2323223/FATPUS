package testscript.Workflow.I18N;
import resources.testscript.Workflow.I18N.I18N_CreateHelper;
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
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class I18N_Create extends I18N_CreateHelper
{
	/**
	 * Script Name   : <b>I18N_Create</b>
	 * Generated     : <b>Nov 28, 2011 7:05:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/28
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/I18N/conf/I18N_ddl.sql");
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->I18N (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "I18N");
		WorkFlowEditor.link("Start Screen", "I18N_create");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("I18N")
				.objectQuery("findAll")
				.defaultSuccessScreen("I18N"));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android");
		WFCustomizer.verifyResult(new WFClientResult().data("found=true"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("I18N_create").throughMenuItem("Open I18N_create");
		s.screen("I18N_create").setData("I18N_create_name_paramKey", "创建");
		s.screen("I18N_create").setData("I18N_create_info_paramKey", "创建inf");
		s.screen("I18N_create").menuItem("Create");
		s.screen("I18N_create").moveTo("Start_Screen").throughMenuItem("Cancel");
		s.screen("Start_Screen").moveTo("I18N").throughMenuItem("findAll");
		s.screen("I18N").checkListItem("创建", "0");
		//
		return s;
	}
}

