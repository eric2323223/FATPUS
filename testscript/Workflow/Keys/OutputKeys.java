package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.OutputKeysHelper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class OutputKeys extends OutputKeysHelper
{
	/**
	 * Script Name   : <b>OutputKeys</b>
	 * Generated     : <b>Sep 30, 2011 8:29:59 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/30
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("submit workflow")
				.type("Submit Workflow"));
		vpManual("Properies", "false", PropertiesView.getButtonState("Output Keys","Add")).performTest();
		vpManual("Properies", "false", PropertiesView.getButtonState("Output Keys","Remove")).performTest();
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("online request")
				.type("Online Request"));
		vpManual("Properies", "true", PropertiesView.getButtonState("Output Keys","Add")).performTest();
		
		System.out.print("add="+DOF.getButton(DOF.getRoot(), "&Add...").invoke("getEnabled"));
		vpManual("Properies", "false", PropertiesView.getButtonState("Output Keys","Remove")).performTest();
		
	}
}
//passed
