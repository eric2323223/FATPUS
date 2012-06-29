package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_CUD_sync_E2E_2_AndroidHelper;
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
import component.entity.model.ObjectQuery;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class ACT_CUD_sync_E2E_2_Android extends ACT_CUD_sync_E2E_2_AndroidHelper
{
	/**
	 * Script Name   : <b>ACT_CUD_sync_E2E_2_Android</b>
	 * Generated     : <b>Oct 24, 2011 11:47:34 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/24
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
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.link("Start Screen", "Department_create");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("dept_id")
							   .logicalType("TEXT")
							   .newKey("dept_id,int"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findByPk")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.parametermapping("dept_id,dept_id")
				.defaultSuccessScreen("DepartmentDetail"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
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
				"id=Department_dept_id_attribKey,value=100|"+
				"id=Department_dept_name_attribKey,value=R & D|"+ 
				"id=Department_dept_head_id_attribKey,value=501|" + 
				"id=Department_dept_id_attribKey,value=200|" +
				"id=Department_dept_name_attribKey,value=Sales|"+ 
				"id=Department_dept_head_id_attribKey,value=902|" + 
				"found=true|" + 
				"found=true|" + 
				"found=false"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").setData("dept_id", "100");
		s.screen("Start_Screen").moveTo("Department_create").throughMenuItem("Open Department_create");
		s.screen("Department_create").setData("Department_create_dept_id_paramKey", "55");
		s.screen("Department_create").setData("Department_create_dept_name_paramKey", "66");
		s.screen("Department_create").setData("Department_create_dept_head_id_paramKey", "77");
		s.screen("Department_create").moveTo("Department_create").throughMenuItem("Create");
		s.screen("Department_create").moveTo("Start_Screen").throughMenuItem("Cancel");
		//
		s.screen("Start_Screen").moveTo("DepartmentDetail").throughMenuItem("findByPk");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		s.screen("DepartmentDetail").moveTo("Department_update_instance").throughMenuItem("Open Department_update_instance");
		s.screen("Department_update_instance").setData("Department_dept_name_attribKey", "update");
		s.screen("Department_update_instance").moveTo("Department_update_instance").throughMenuItem("Update");
		s.screen("Department_update_instance").moveTo("DepartmentDetail").throughMenuItem("Cancel");
		s.screen("DepartmentDetail").moveTo("Start_Screen").throughMenuItem("Back");
		//
		s.screen("Start_Screen").setData("dept_id", "200");
		s.screen("Start_Screen").moveTo("DepartmentDetail").throughMenuItem("findByPk");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		s.screen("DepartmentDetail").moveTo("Department_delete_instance").throughMenuItem("Open Department_delete_instance");
		s.screen("Department_delete_instance").moveTo("Department_delete_instance").throughMenuItem("Delete");
		s.screen("Department_delete_instance").moveTo("DepartmentDetail").throughMenuItem("Cancel");
		s.screen("DepartmentDetail").moveTo("Start_Screen").throughMenuItem("Back");
		// Verify data
		s.screen("Start_Screen").moveTo("Department").throughMenuItem("findAll");
		s.screen("Department").checkListItem("55", "0");
		s.screen("Department").checkListItem("update", "1");
		s.screen("Department").checkListItem("200", "0");
		return s;
	}
}
