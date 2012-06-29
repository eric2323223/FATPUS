package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.SF_OpError_01_E2EHelper;
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

import component.entity.DeployWizard;
import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Role;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SF_OpError_01_E2E extends SF_OpError_01_E2EHelper
{
	/**
	 * Script Name   : <b>SF_OpError_01_E2E_Android</b>
	 * Generated     : <b>Sep 19, 2011 1:35:28 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/19
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption()
 		.startParameter(Cfg.projectName)
 		.server("My Unwired Server")
 		.mode(DeployOption.MODE_REPLACE)
 		.serverConnectionMapping("My Sample Database,sampledb"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("scr1");
		WorkFlowEditor.addScreen("scr2");
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("Open scr1").type("Open").screen("scr1"));
		
		WorkFlowEditor.addWidget("scr1", new WFEditBox().label("dept_id:")
				.newKey("dept_id,int"));
		WorkFlowEditor.addWidget("scr1", new WFEditBox().label("dept_name:")
				.newKey("dept_name,string"));
		WorkFlowEditor.addWidget("scr1", new WFEditBox().label("dept_head_id:")
				.newKey("dept_head_id,int"));
		
		WorkFlowEditor.addMenuItem("scr1", new WFScreenMenuItem()
				.name("create")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("create")
				.setErrorScreen("scr2")
				.parametermapping("dept_id,dept_id")
				.parametermapping("dept_name,dept_name")
				.parametermapping("dept_head_id,dept_head_id")
				);
		
		WorkFlowEditor.addWidget("scr2", new WFLview()
				.key("ErrorLogs"));
		MainMenu.saveAll();
		
		PropertiesView.addCell("0","cell line 0","ErrorLogMessage","100");
		MainMenu.saveAll();
		
		vpManual("error",0,Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser),
			customTestScript()
//			, 
//			"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
			);
		
		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=1"));
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("scr1").throughMenuItem("Open scr1");
		s.screen("scr1").setData("dept_id","100");
		s.screen("scr1").setData("dept_name","new");
		s.screen("scr1").setData("dept_head_id","123");
		s.screen("scr1").moveTo("scr2").throughMenuItem("create");
		s.screen("scr2").getListItemsCount();
		return s;
	}
}
//passed BB6T 20120208
