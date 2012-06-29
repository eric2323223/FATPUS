package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_E2E_5_AndroidHelper;
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
import component.entity.MBOProperties;
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
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class NQ_E2E_5_Android extends NQ_E2E_5_AndroidHelper
{
	/**
	 * Script Name   : <b>NQ_E2E_5_Android</b>
	 * Generated     : <b>Oct 24, 2011 12:46:55 AM</b>
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
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		WN.createObjectQuery(new ObjectQuery()
			.name("ObjQuery")
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.parameter("name,string,true,dept_name")
			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_name = :name")
			.returnType(ObjectQueryWizard.RT_MULTIPLE)
		);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("dept_Name")
							   .logicalType("TEXT")
							   .newKey("nameVal,string")
		);
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("ObjectQuery")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("ObjQuery")
				.parametermapping("name,nameVal")
				.generateErrorScreen(true)
				.defaultSuccessScreen("Department")
		);
		// reConfig MBO.
		mbo.deleteObjectQuery("ObjQuery");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
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
//		WFCustomizer.verifyResult(new WFClientResult().data("id=name,value=100"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").setData("nameVal", "deptName");
		s.screen("Start_Screen").moveTo("ErrorList").throughMenuItem("ObjectQuery");
		s.screen("ErrorList").moveTo("ErrorDetail").throughListItem("0");
		s.screen("ErrorDetail").checkListItem("code", "0");
		s.screen("ErrorDetail").checkListItem("500", "1");
//		s.screen("DepartmentDetail").moveTo("Department_delete_instance").throughMenuItem("Open_Department_delete_instance");
//		s.screen("Department_delete_instance").moveTo("Department").throughMenuItem("Delete");
//		s.screen("Department").checkListItem("Department_dept_name_attribKey","Sales");
//		s.screen("Department_update_instance").setData("Department_dept_name_attribKey","Sale");
//		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
//		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
//		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		return s;
	}
}

