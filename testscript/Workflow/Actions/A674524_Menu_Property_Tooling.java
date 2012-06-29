package testscript.Workflow.Actions;
import com.sybase.automation.framework.widget.DOF;

import resources.testscript.Workflow.Actions.A674524_Menu_Property_ToolingHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class A674524_Menu_Property_Tooling extends A674524_Menu_Property_ToolingHelper
{
	/**
	 * Script Name   : <b>A674524_Menu_Property_Tooling</b>
	 * Generated     : <b>Oct 12, 2011 5:39:32 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		//
		WorkFlowEditor.link("Start Screen", "Department_create");
		//
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department")
		);
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.assignToUser("supAdmin")
				.unwiredServer("My Unwired Server")
				.deployToServer("true")
				.verifyResult("Assigning workflow myWF to supAdmin", true)
		);
		//
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), "Department_create").doubleClick();
		sleep(0.5);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		DOF.getWFMenuItemFigure(DOF.getRoot(), "Create").click();
		PropertiesView.clickTab("General");
		//
		vpManual("results", true, null == DOF.getTextField(DOF.getRoot(), "Submit confirmation message:")).performTest();
	}
}

