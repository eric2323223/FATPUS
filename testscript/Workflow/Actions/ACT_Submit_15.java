package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Submit_15Helper;
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
import com.sybase.automation.framework.tool.NativeInvoker;
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
 * @author flv
 */
public class ACT_Submit_15 extends ACT_Submit_15Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_15</b>
	 * Generated     : <b>Sep 29, 2011 4:27:22 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/29
	 * @author flv
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
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("create")
		);
		WorkFlowEditor.setMenuItem("Screen1", new WFScreenMenuItem()
				.name("submit")
				.type("Submit Workflow")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Operation:").getProperty("text").equals("create")).performTest();
		//
		WorkFlowEditor.setMenuItem("Screen1", new WFScreenMenuItem()
				.name("submit")
				.operation("update")
		);
		//
		WorkFlowEditor.setMenuItem("Screen1", new WFScreenMenuItem()
				.name("submit")
				.type("Online Request")
		);
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Operation:").getProperty("text").equals("update")).performTest();
		//
		WorkFlowEditor.setMenuItem("Screen1", new WFScreenMenuItem()
				.name("submit")
				.type("Submit Workflow")
		);
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Operation:").getProperty("text").equals("update")).performTest();

	}
}

