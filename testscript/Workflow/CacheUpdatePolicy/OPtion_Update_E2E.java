package testscript.Workflow.CacheUpdatePolicy;
import java.util.ArrayList;

import resources.testscript.Workflow.CacheUpdatePolicy.OPtion_Update_E2EHelper;
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
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.Operation;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author mzhao
 */
public class OPtion_Update_E2E extends OPtion_Update_E2EHelper
{
	/**
	 * Script Name   : <b>OPtion_Update_1to3_E2E</b>
	 * Generated     : <b>Mar 25, 2012 11:21:55 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/25
	 * @author mzhao
	 */
	public void testMain(Object[] args) 
	{
		// TODO Need delete WF package in server package
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.mode(DeployOption.MODE_UPDATE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		// create a new create operation "CData"
//		MBOProperties mbo = new MBOProperties(Cfg.projectName,"Department");
//		mbo.addOperation(new Operation().name("CData").sqlQuery("INSERT INTO sampledb.dba.department"
//					+"(dept_id,dept_name,dept_head_id)VALUES(:dept_id,:dept_name,:dept_head_id)").parameterDefaultValue("dept_id:0"));
//		
//		// update deploy 
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.mode(DeployOption.MODE_UPDATE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF")
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.dragOperation(Cfg.projectName, "Department","CData");
////		
//////		//WorkFlowEditor.link("Start","DepartmentCD..");
//		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Open DepartmentCData")
//				.type("Open")
//				.screen("DepartmentCData"));
//		
//		MainMenu.saveAll();
//		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
		//deploy wf:
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(testscript.Workflow.cfg.Cfg.deviceUser),
			customTestScript(), 
			testscript.Workflow.cfg.Cfg.tplanScript_client_4);//BB:tplanScript_client_1,WM:tplanScript_client_3
		
//		vp2:verify the new create record  has added into backend db in department
		java.util.List<String> clause = new ArrayList<String>();
			clause.add("dept_id=25");
			clause.add("dept_name='mzhao'");
			clause.add("dept_head_id=20");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("localhost", "wf", "Department", clause)).performTest();
	}

	private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Start").moveTo("DepartmentCData").throughMenuItem("Open DepartmentCData");
			s.screen("DepartmentCData").setData("Department_CData_dept_id_paramKey", "25");
			s.screen("DepartmentCData").setData("Department_CData_dept_name_paramKey", "mzhao");
			s.screen("DepartmentCData").setData("Department_CData_dept_head_id_paramKey", "20");
			s.screen("DepartmentCData").menuItem("CData");
			return s;
		
		
		

	}
}

