package testscript.Workflow.CallActionOutSideMenu;
import resources.testscript.Workflow.CallActionOutSideMenu.CallAction_Open_E2EHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WFLabel;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CallAction_Open_E2E extends CallAction_Open_E2EHelper
{
	/**
	 * Script Name   : <b>CallAction_Open_E2E</b>
	 * Generated     : <b>Mar 7, 2012 10:52:07 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/07
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addCustomAction("Start Screen", new WFScreenMenuItem().name("item1")
				.type("Open Screen")
				.screen("Department"));
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(Cfg.projectName)
				.unwiredServer("My Unwired Server")
				.assignToUser(Cfg.deviceUser), customJsTestScript(), "tplan.Workflow.common.StartWF_BB");
		WFCustomizer.verifyResult(new WFClientResult().data("id=Department_dept_id_attribKey,value=XXX"));
	
	}
	
	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").customAction("item1");
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("0");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		return s;
	}
}

