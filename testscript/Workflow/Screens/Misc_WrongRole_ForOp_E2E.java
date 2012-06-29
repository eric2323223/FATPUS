package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.Misc_WrongRole_ForOp_E2EHelper;
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
import component.entity.model.WFScreenMenuItem;
import component.entity.model.Widget;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Misc_WrongRole_ForOp_E2E extends Misc_WrongRole_ForOp_E2EHelper
{
	/**
	 * Script Name   : <b>Misc_WrongRole_ForOp_E2E_Android2</b>
	 * Generated     : <b>Sep 19, 2011 11:03:57 AM</b>
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
		WN.createRole(new Role().name("myrole1")
				.startParameter(Cfg.projectName));
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		MBOProperties mbo = new MBOProperties(WN.projectNameWithVersion(Cfg.projectName),"Department");
		mbo.addOperationRole("create", "myrole1");
		new DeployWizard().deploy(new DeployOption()
			.startParameter(WN.projectNameWithVersion(Cfg.projectName))
			.server("My Unwired Server")
			.mode(DeployOption.MODE_REPLACE)
			.serverConnectionMapping(new String[]{"My Sample Database", "sampledb"})
			.mapRole(new String[]{"myrole1", "Auto"})
			, new WizardRunner());
		vpManual("hasdeploy",true,EE.ifMboDeployed(new DeployedMbo()
			.connectionProfile("My Unwired Server")
			.domain("default")
			.name("Department")
			.pkg(Cfg.projectName+":1.0"))).performTest();
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("dept_id:")
				.newKey("id,int"));
		MainMenu.saveAll();
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("dept_name:")
				.newKey("n,string"));
		MainMenu.saveAll();
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("head_id:")
				.newKey("head_id,int"));
		MainMenu.saveAll();
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
			.name("mycreate")
			.type("Online Request")
			.project(Cfg.projectName)
			.mbo("Department")
			.operation("create")
			.setErrorScreen("General")
			.parametermapping("dept_id,id")
			.parametermapping("dept_head_id,head_id")
			.parametermapping("dept_name,n")
			);
		
		vpManual("error",0,Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser),
			customTestScript()
//			, 
//			""
			);
		
		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=1"));
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("id","100");
		s.screen("Start").setData("n","new");
		s.screen("Start").setData("head_id","1293");
		s.screen("Start").moveTo("ErrorList").throughMenuItem("mycreate");
		s.screen("ErrorList").getListItemsCount();
		return s;
}//ok
}
//Note:BB6T don't contain this case  
