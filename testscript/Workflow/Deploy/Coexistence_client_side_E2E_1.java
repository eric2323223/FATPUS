package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.Coexistence_client_side_E2E_1Helper;
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
import com.sybase.automation.framework.widget.DOF;
import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedWorkFlow;
import component.entity.model.Email;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Coexistence_client_side_E2E_1 extends Coexistence_client_side_E2E_1Helper
{
	/**
	 * Script Name   : <b>Coexistence_client_side_E2E_1</b>
	 * Generated     : <b>Nov 3, 2011 5:45:40 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/03
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		
		WorkFlowEditor.link("DepartmentDetail", "Department_create");
		MainMenu.saveAll();
		
		//deploy the first WF to the first Device(BB6T :dt):
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser("dt"),
			customTestScript(), 
			"tplan.Workflow.iconcommon.BB.myWF_icon.Script");
		
		WFCustomizer.verifyResult(new WFClientResult()
			.data("id=Department_dept_id_attribKey,value=100|" +
				"id=Department_dept_name_attribKey,value=R & D|" +
				"id=Department_dept_head_id_attribKey,value=501"));
		
	//************TBD:Note:need to put the snapshort of WF icon in Android into the folder of "BB.myWF_icon"
		//Modify WF :
		WorkFlowEditor.removeScreen("Department_update_instance");
		WorkFlowEditor.setWorkFlow(new WorkFlow().name(Cfg.wfName).version("2"));
		MainMenu.saveAll();
		
		//deploy the second WF to the second device (Android:DT):
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser("DT"),
			customTestScript(), 
			"tplan.Workflow.iconcommon.BB.myWF_icon.Script");
		
		WFCustomizer.verifyResult(new WFClientResult()
			.data("id=Department_dept_id_attribKey,value=100|" +
				"id=Department_dept_name_attribKey,value=R & D|" +
				"id=Department_dept_head_id_attribKey,value=501"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("Department").throughMenuItem("findall");
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("100->0");
		
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		return s;
	}
}
//passed without NOTE 20120210
