package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Submit_8Helper;
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
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Operation;
import component.entity.model.OperationParameter;
import component.entity.model.PK;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class ACT_Submit_8 extends ACT_Submit_8Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_8</b>
	 * Generated     : <b>Sep 28, 2011 2:26:27 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/28
	 * @author flvVm
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));

		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Create")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.timeOut("90")
		);
		vpManual("exist", false, DOF.getCustomCLabel(
				DOF.getStatusLine(DOF.getRoot())).getProperty("text").toString().equals("Value not updated, the valid value should be integer.")
				).performTest();
		//
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem().name("Create")
				.timeOut("9abc")
		);
		vpManual("exist", true, DOF.getCustomCLabel(
				DOF.getStatusLine(DOF.getRoot())).getProperty("text").toString().equals("Value not updated, the valid value should be integer.")
				).performTest();
		//
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem().name("Create")
				.timeOut("9999999999")
		);
		vpManual("exist", true, DOF.getCustomCLabel(
				DOF.getStatusLine(DOF.getRoot())).getProperty("text").toString().equals("Value not updated, the valid value should be integer.")
				).performTest();
		vpManual("exist", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "Create")).performTest();
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		
	}
}

