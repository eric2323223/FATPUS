package testscript.Workflow.Credential;
import resources.testscript.Workflow.Credential.Security_Negative_E2E_Android_01Helper;
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
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;
import component.view.properties.workflow.WFControlObject;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Security_Negative_E2E_Android_01 extends Security_Negative_E2E_Android_01Helper
{
	/**
	 * Script Name   : <b>Security_Negative_E2E_Android_01</b>
	 * Generated     : <b>Oct 21, 2011 11:18:57 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/21
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CREDENTIAL_REQUEST));
		MainMenu.saveAll();
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		ToggleGUITestObject obj = WFControlObject.getCheckBoxOnRoot("Authentication", "Use static credentials");
		obj.clickToState(NOT_SELECTED);
		MainMenu.saveAll();
		//
		WorkFlowEditor.addScreen("scr1");
		WorkFlowEditor.link("Credential Request", "scr1");
		//
		WorkFlowEditor.addMenuItem("scr1", new WFScreenMenuItem()
				.name("Save")
				.type("Save")
		);
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "scr1", 
				new WFEditBox().label("username")
							   .logicalType("NUMERIC")
							   .newKey("username,decimal")
							   .setCredentialUserName(true)
							   .setDefaultValue("1")
		);
		//
		GefEditPartTestObject usr = WFControlObject.getEditBoxOnScreen("scr1", "username");
		usr.click();
		PropertiesView.maximize();
		ToggleGUITestObject usrCheckBox = WFControlObject.getCheckBoxOnRoot("Advanced", "Credential cache &username");
		vpManual("enable", false, usrCheckBox.isEnabled()).performTest();
		ToggleGUITestObject pwdCheckBox = WFControlObject.getCheckBoxOnRoot("Advanced", "Credential cache pass&word");
		vpManual("enable", false, pwdCheckBox.isEnabled()).performTest();
		PropertiesView.restore();
		//
		WorkFlowEditor.addScreen("scr2");
		WorkFlowEditor.link("Client-initiated", "scr2");
		WorkFlowEditor.addMenuItem("scr2", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
		);
		//
		MainMenu.saveAll();
		vpManual("error", true, Problems.getErrors().get(0).getDescription().contains("Please specify which editboxes are the credential cache username and password")).performTest();
		//
		vpManual("error", 1, Problems.getErrors().size()).performTest();
		//
//		WFCustomizer.runTest(new WorkFlowPackage()
//		.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//		.continueWithError("true")
//		.assignToUser("supAdmin")
//		.unwiredServer("My Unwired Server")
//		.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
//		customTestScript());
//		
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("scr2").moveTo("scr1").throughMenuItem("findAll");
		s.screen("scr1").moveTo("scr2").throughMenuItem("Save");
		return s;
	}
	
}

