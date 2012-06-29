package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.D_659790_AutoSave_Before_GenHelper;
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
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WorkFlowPackageCreationWizard;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class D_659790_AutoSave_Before_Gen extends D_659790_AutoSave_Before_GenHelper
{
	/**
	 * Script Name   : <b>D_659790_AutoSave_Before_Gen</b>
	 * Generated     : <b>Nov 24, 2011 9:46:01 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/24
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		MainMenu.saveAll();
		
		//modify the wf...
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		WFFlowDesigner.addScreen("test");
		
		//deploy...
		new WorkFlowPackageCreationWizard().start(Cfg.projectName+"->"+Cfg.wfName);
		sleep(1);
		DOF.getButton(DOF.getDialog("Warning"), "&Yes").click();
		sleep(1);
		DOF.getButton(DOF.getDialog("New Mobile Workflow Package Generation"), "&Finish").click();
		
	}
}
//passed
