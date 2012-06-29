package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Submit_13Helper;
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
import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class ACT_Submit_13 extends ACT_Submit_13Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_13</b>
	 * Generated     : <b>Sep 29, 2011 1:32:00 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/29
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
		);
		//Add 4 screens, named "Screen1", "Screen2", "Success", "Error". 
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("Screen1");
		WorkFlowEditor.link("Client-initiated", "Screen1");
		WorkFlowEditor.addMenuItem("Screen1", new WFScreenMenuItem()
				.name("submit")
				.type("Submit Workflow")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("create")
				.parametermapping("dept_id,key1")
				.parametermapping("dept_name,key2")
				.parametermapping("dept_head_id,key3")
		);
		
		vpManual("properties", "dept_id,key1", PropertiesView.getMenuItem("submit").getParameterMapping()[0]).performTest();
		vpManual("properties", "dept_name,key2", PropertiesView.getMenuItem("submit").getParameterMapping()[1]).performTest();
		vpManual("properties", "dept_head_id,key3", PropertiesView.getMenuItem("submit").getParameterMapping()[2]).performTest();
	}
}

