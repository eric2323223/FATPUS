package testscript.Workflow.Credential;
import resources.testscript.Workflow.Credential.Security_ShowScreen_E2E_Android_1Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.properties.workflow.WFControlObject;

/**
 * Description   : Functional Test Script
 * @author flv
 */
public class Security_ShowScreen_E2E_Android_1 extends Security_ShowScreen_E2E_Android_1Helper
{
	/**
	 * Script Name   : <b>Security_ShowScreen_E2E_Android_1</b>
	 * Generated     : <b>Oct 22, 2011 1:38:35 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/22
	 * @author flv
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
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		//drag MBO
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		//
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.setShowCredScreen(false)
				.defaultSuccessScreen("Department")
		        .generateErrorScreen(true)
		);
		
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		ToggleGUITestObject obj = WFControlObject.getCheckBoxOnRoot("Authentication", "Use static credentials");
		obj.clickToState(NOT_SELECTED);
		MainMenu.saveAll();
		//
		Object o = obj.invoke("getSelection");
		vpManual("selected", true, o.equals(false)).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
		.assignToUser("supAdmin")
		.unwiredServer("My Unwired Server")
		.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
		customTestScript());
		WFCustomizer.verifyResult(new WFClientResult().data(
				"list_items_count=1"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Credential_Request").setData("cc_username", "supAdmin");
		s.screen("Credential_Request").setData("cc_password", "supAdmin");
		s.screen("Credential_Request").moveTo("Start").throughMenuItem("Save");
		s.screen("Start").moveTo("ErrorList").throughMenuItem("findAll");
		s.screen("ErrorList").getListItemsCount();
		return s;
	}
}
