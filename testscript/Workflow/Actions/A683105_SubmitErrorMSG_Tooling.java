package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.A683105_SubmitErrorMSG_ToolingHelper;
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
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class A683105_SubmitErrorMSG_Tooling extends A683105_SubmitErrorMSG_ToolingHelper
{
	/**
	 * Script Name   : <b>A683105_SubmitErrorMSG_Tooling</b>
	 * Generated     : <b>Oct 12, 2011 7:17:58 AM</b>
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
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		//
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName).mbo("Department")
				.objectQuery("findAll")
				.submitErrMsg("submit error message")
				.defaultSuccessScreen("Department")
		);
		//&Generate Error Screen
		PropertiesView.clickTab("General");
		DOF.getButton(DOF.getRoot(), "&Generate Error Screen").click();
		DOF.getCCombo(DOF.getRoot(), "Error screen:").click();
		DOF.getPoppedUpList().click(atText("ErrorList"));
		MainMenu.saveAll();
		vpManual("editable", false, DOF.getTextField(DOF.getRoot(), "Submit error message:").isEnabled()).performTest();
		//
		PropertiesView.clickTab("General");
		DOF.getWFMenuItemFigure(DOF.getRoot(), "Cancel Screen").click();
		DOF.getWFMenuItemFigure(DOF.getRoot(), "findAll").click();
		//
		vpManual("editable", false, DOF.getTextField(DOF.getRoot(), "Submit error message:").isEnabled()).performTest();
		//
	}
}

