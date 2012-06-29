package testscript.Workflow.CacheUpdatePolicy;
import java.util.ArrayList;

import resources.testscript.Workflow.CacheUpdatePolicy.OPtion_Replace_E2EHelper;
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

import component.dialog.ConfirmResourceDeleteDialog;
import component.entity.EE;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author mzhao
 */
public class OPtion_Replace_E2E extends OPtion_Replace_E2EHelper
{
	/**
	 * Script Name   : <b>OPtion_Replace</b>
	 * Generated     : <b>Mar 26, 2012 2:46:16 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/26
	 * @author mzhao
	 */
	public void testMain(Object[] args) 
	{
		// TODO ensure the MBO package in the server package list
		//delete original WF
//		WN.hasWorkFlowInProject(Cfg.projectName,"myWF");
//		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(Cfg.projectName)+"->"+"myWF.xbw"));
//	//	DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(Cfg.projectName)+"->Generated Workflow->"+"myWF"));
//		DOF.getContextMenu().click(atPath("Delete"));
//		ConfirmResourceDeleteDialog.delete();
//		
//		//delete original MBO
//		WN.deleteMbo(Cfg.projectName,"Department");
//		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(Cfg.projectName)));
//		DOF.getContextMenu().click(atPath("Open in Diagram Editor"));
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->bonus (dba)");
//		
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "Bonus","create");
		
//		WorkFlowEditor.link("Start","DepartmentCD..");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Open Bonuscreate")
				.type("Open")
				.screen("Bonuscreate"));
		
		MainMenu.saveAll();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
		//deploy wf:
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(testscript.Workflow.cfg.Cfg.deviceUser),
			customTestScript(), 
			testscript.Workflow.cfg.Cfg.tplanScript_client_4);
		
//		vp2:verify the new create record  has added into backend db in department
		java.util.List<String> clause = new ArrayList<String>();
			clause.add("emp_id=1");
			clause.add("bonus_date='2009-09-09'");
			clause.add("bonus_amount=1.234");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("localhost", "wf", "Bonus", clause)).performTest();
	}

	private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Start").moveTo("Bonuscreate").throughMenuItem("Open Bonuscreate");
			s.screen("Bonuscreate").setData("Bonus_create_emp_id_paramKey", "1");
			s.screen("Bonuscreate").setData("Bonus_create_bonus_date_paramKey", "2009-09-09");
			s.screen("Bonuscreate").setData("Bonus_create_bonus_amount_paramKey", "1.234");
			s.screen("Bonuscreate").menuItem("Create");
			return s;
		
		
	}
}

