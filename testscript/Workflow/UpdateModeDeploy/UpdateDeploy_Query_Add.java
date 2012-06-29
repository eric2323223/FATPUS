package testscript.Workflow.UpdateModeDeploy;
import resources.testscript.Workflow.UpdateModeDeploy.UpdateDeploy_Query_AddHelper;
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
import component.entity.MainMenu;
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
 * @author whan
 */
public class UpdateDeploy_Query_Add extends UpdateDeploy_Query_AddHelper
{
	/**
	 * Script Name   : <b>UpdateDeploy_Query_Add</b>
	 * Generated     : <b>Mar 18, 2012 11:45:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/19
	 * @author whan
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
	
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_UPDATE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
	
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start", 
				new WFEditBox().label("dept_id")
							   .logicalType("TEXT")
							   .newKey("dept_id,int"));
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
		.name("findByPk")
		.type("Online Request")
		.project(Cfg.projectName)
		.mbo("Department")
		.objectQuery("findByPrimaryKey")
		.parametermapping("dept_id,dept_id")
		.defaultSuccessScreen("DepartmentDetail"));
	
		
	
		WN.createObjectQuery(new ObjectQuery()
		.name("ObjQuery")
		.startParameter(WN.mboPath(Cfg.projectName, "Department"))
		.parameter("name,string,true,dept_name")
		.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_name = :name")
		.returnType(ObjectQueryWizard.RT_RESULTSET));
	
		MainMenu.saveAll();
		
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_UPDATE)
				.serverConnectionMapping("My Sample Database,sampledb"));

			vpManual("error", 0, Problems.getErrors().size()).performTest();
		
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
           Cfg.tplanScript_client_1);
			
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Department_dept_id_attribKey,value=100|"+
				"id=Department_dept_name_attribKey,value=R & D|"+ 
				"id=Department_dept_head_id_attribKey,value=501|" 
				));
	

//	WN.createObjectQuery(new ObjectQuery()
//	.name("ObjQuery")
//	.startParameter(WN.mboPath(Cfg.projectName, "Department"))
//	.parameter("name,string,true,dept_name")
//	.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_name = :name")
//	.returnType(ObjectQueryWizard.RT_RESULTSET));
//	
//	WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//			.server("My Unwired Server")
//			.mode(DeployOption.MODE_UPDATE)
//			.serverConnectionMapping("My Sample Database,sampledb"));
//	
//	
//	
//	vpManual("error", 0, Problems.getErrors().size()).performTest();
//	
//	
//	WFCustomizer.runTest(new WorkFlowPackage()
//		.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//		.assignToUser("supAdmin")
//		.unwiredServer("My Unwired Server")
//		.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
//		customTestScript(), 
//       Cfg.tplanScript_client_1);
//		
//	WFCustomizer.verifyResult(new WFClientResult().data(
//			"id=Department_dept_id_attribKey,value=100|"+
//			"id=Department_dept_name_attribKey,value=R & D|"+ 
//			"id=Department_dept_head_id_attribKey,value=501|" 
//			));
}


	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("dept_id", "100");
		
		s.screen("Start").moveTo("DepartmentDetail").throughMenuItem("findByPk");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		
		
		return s;
	}

	
}
