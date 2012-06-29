package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_ActionsHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flv
 */
public class ACT_Actions extends ACT_ActionsHelper
{
	/**
	 * Script Name   : <b>ACT_Open</b>
	 * Generated     : <b>Sep 26, 2011 05:53:02 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/26
	 * @author flv
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WFScreenMenuItem wfmenu;
		WorkFlowEditor.addMenuItem("Start Screen", wfmenu = new WFScreenMenuItem()
				.name("item1")
				.type("Open Screen"));
		
		WorkFlowEditor.addScreen("OpenScreen");
		
		vpManual("exist", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "item1")).performTest();
		
		// There should be an error item listed in the problem view saying the screen property is not set
		vpManual("error", 2, Problems.getErrors().size()).performTest();
		
		// Select a screen from the drop-down list
		vpManual("exist", true, WorkFlowEditor.hasScreen("OpenScreen")).performTest();
//		vpManual("exist", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "item1")).performTest();
		
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem().name("item1").type("Open Screen").screen("OpenScreen"));
		vpManual("exist", true, WorkFlowEditor.hasLinkBetween("Start Screen", "OpenScreen")).performTest();
		
//		Switch the action type to Submit
		WorkFlowEditor.setMenuItem("Start Screen", wfmenu.type("Submit Workflow"));
		vpManual("exist", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "item1")).performTest();
		
//		Switch the action type to Save
		WorkFlowEditor.setMenuItem("Start Screen", wfmenu.type("Save Screen"));
		vpManual("exist", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "item1")).performTest();
		
//		Switch the action type to Close
		WorkFlowEditor.setMenuItem("Start Screen", wfmenu.type("Close Workflow"));
		vpManual("exist", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "item1")).performTest();
		
//		Switch the action type to Cancel
		WorkFlowEditor.setMenuItem("Start Screen", wfmenu.type("Cancel Screen"));
		vpManual("exist", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "item1")).performTest();
		}
}


