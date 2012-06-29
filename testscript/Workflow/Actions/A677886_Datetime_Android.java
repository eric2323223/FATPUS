package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.A677886_Datetime_AndroidHelper;
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
import com.sybase.automation.framework.common.CDBUtil;

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
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class A677886_Datetime_Android extends A677886_Datetime_AndroidHelper
{
	/**
	 * Script Name   : <b>A677886_Datetime_Android</b>
	 * Generated     : <b>Oct 31, 2011 3:34:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/31
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/DiffTZ.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/DiffTZ_data.sql");
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->DiffTZ (dba)");
		//
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "DiffTZ");
		//
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("DiffTZ")
				.objectQuery("findAll")
				.defaultSuccessScreen("DiffTZ"));
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
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=DiffTZ_d_attribKey,value=2011-10-27T08:00:00|"+
				"id=DiffTZ_e_attribKey,value=2011-10-27T08:00:00|" +
				"id=DiffTZ_d_attribKey,value=2011-10-27T08:00:00|"+
				"id=DiffTZ_e_attribKey,value=2011-10-27T08:00:00" ));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("DiffTZ").throughMenuItem("findAll");
		s.screen("DiffTZ").moveTo("DiffTZDetail").throughListItem("2->0");
		s.screen("DiffTZDetail").getData("DiffTZ_d_attribKey");
		s.screen("DiffTZDetail").getData("DiffTZ_e_attribKey");
		//
		s.screen("DiffTZDetail").moveTo("DiffTZ_update_instance").throughMenuItem("Open DiffTZ_update_instance");
//		s.screen("DiffTZ_update_instance").setData("DiffTZ_c_attribKey", "2011-01-27");
		s.screen("DiffTZ_update_instance").moveTo("DiffTZ_update_instance").throughMenuItem("Update");
		s.screen("DiffTZDetail").getData("DiffTZ_d_attribKey");
		s.screen("DiffTZDetail").getData("DiffTZ_e_attribKey");
		
		return s;
	}
}
