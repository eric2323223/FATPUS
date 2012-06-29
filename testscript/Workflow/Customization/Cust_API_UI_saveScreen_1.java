package testscript.Workflow.Customization;
import java.io.File;

import resources.testscript.Workflow.Customization.Cust_API_UI_saveScreen_1Helper;
import testscript.Workflow.Customization.cfg.Config;
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
import component.entity.WN;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class Cust_API_UI_saveScreen_1 extends Cust_API_UI_saveScreen_1Helper
{
	/**
	 * Script Name   : <b>Cust_API_UI_saveScreen_1</b>
	 * Generated     : <b>Mar 30, 2012 10:04:36 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/30
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
//		WorkFlowEditor.addWidget("Start", new WFEditBox()
//				.label("label1")
//				.defaultValue("test")
//				.newKey("key1,string"));
//		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
//				.name("findAll")
//				.project(Cfg.projectName)
//				.mbo("Department")
//				.type("Online Request")
//				.defaultSuccessScreen("Department")
//				.objectQuery("findAll"));
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.unwiredServer("My Unwired Server")
				.assignToSelectedUser("DT"), customJsTestScript(), "tplan.Workflow.common.StartWF_android");
	
		WFCustomizer.verifyResult(new WFClientResult().data("valueCount=0|valueCount=1|value=TEST"));
	}

	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setMethod("customAfterShowScreen", new File(Config.Cust_API_UI_saveScreen_1));
		return s;
	}
}

