package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_CloseHelper;
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

import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class ACT_Close extends ACT_CloseHelper
{
	/**
	 * Script Name   : <b>ACT_Close</b>
	 * Generated     : <b>Sep 29, 2011 5:32:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/29
	 * @author flvVm
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Type")
				.type("Close Workflow")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Close Workflow")).performTest();
		//
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Type")
				.type("Open Screen")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Open Screen")).performTest();
		//
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Type")
				.type("Submit Workflow")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Submit Workflow")).performTest();
		//
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Type")
				.type("Cancel Screen")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Cancel Screen")).performTest();
		//
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Type")
				.type("Save Screen")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Save Screen")).performTest();
		//
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Type")
				.type("Close Workflow")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Close Workflow")).performTest();
		//
	}
}

