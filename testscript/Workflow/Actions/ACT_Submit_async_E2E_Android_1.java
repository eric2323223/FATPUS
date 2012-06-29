package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.ACT_Submit_async_E2E_Android_1Helper;
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
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class ACT_Submit_async_E2E_Android_1 extends ACT_Submit_async_E2E_Android_1Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_async_E2E_Android_1</b>
	 * Generated     : <b>Oct 25, 2011 7:11:10 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
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
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "Department", "create");
		WorkFlowEditor.link("Client-initiated", "Department_create");
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
		clause.add("dept_id=12");
		clause.add("dept_name='12'");
		clause.add("dept_head_id=12");
		vpManual("error", 1, CDBUtil.getRecordCount("flv-vmpc", "wf", "Department", clause)).performTest();
//		WFCustomizer.verifyResult(new WFClientResult().data());
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department_create").setData("Department_create_dept_id_paramKey", "12");
		s.screen("Department_create").setData("Department_create_dept_name_paramKey", "12");
		s.screen("Department_create").setData("Department_create_dept_head_id_paramKey", "12");
		//
		s.screen("Department_create").moveTo("Department_create").throughMenuItem("Create");
		return s;
	}
}
