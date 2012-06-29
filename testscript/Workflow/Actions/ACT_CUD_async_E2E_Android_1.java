package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.ACT_CUD_async_E2E_Android_1Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class ACT_CUD_async_E2E_Android_1 extends ACT_CUD_async_E2E_Android_1Helper
{
	/**
	 * Script Name   : <b>ACT_CUD_async_E2E_Android_1</b>
	 * Generated     : <b>Oct 26, 2011 12:12:07 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/26
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "Department", "delete");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("dept_id")
							   .setDefaultValue("500")
							   .newKey("dept_id,int"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findByPk")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.parametermapping("dept_id,dept_id")
				.defaultSuccessScreen("Department_delete_instance"));
		//
		WorkFlowEditor.setMenuItem("Department_delete_instance", new WFScreenMenuItem()
				.name("Delete")
				.submitConfirmMsg("Async delete opetation."));
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
		//
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("dept_id=500");
		clause.add("LOGICAL_DEL=1");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("flv-vmpc", "wf", "Department", clause)).performTest();
//		WFCustomizer.verifyResult(new WFClientResult().data());
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("Department_delete_instance").throughMenuItem("findByPk");
		//
		s.screen("Department_delete_instance").moveTo("").throughMenuItem("Delete");
		return s;
	}
}