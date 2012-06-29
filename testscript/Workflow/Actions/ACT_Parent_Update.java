package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Parent_UpdateHelper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flv
 */
public class ACT_Parent_Update extends ACT_Parent_UpdateHelper
{
	/**
	 * Script Name   : <b>ACT_Parent_Update</b>
	 * Generated     : <b>Sep 29, 2011 5:00:46 PM</b>
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
		WorkFlowEditor.addScreen("ErrorScreen");
		WorkFlowEditor.addScreen("SuccessScreen");
		WorkFlowEditor.link("Client-initiated", "Screen1");
		WorkFlowEditor.addMenuItem("Screen1", new WFScreenMenuItem()
				.name("submit")
				.type("Submit Workflow")
				.project(Cfg.projectName)
				.mbo("Department")
		);
		PropertiesView.clickTab("General");
		DOF.getButton(DOF.getRoot(), "Invoke &parent update").click();
		MainMenu.saveAll();
		WorkFlowEditor.setMenuItem("Screen1", new WFScreenMenuItem()
				.name("submit")
				.type("Online Request")
				.errorScreen("ErrorScreen")
				.defaultSuccessScreen("SuccessScreen")
		);
		
		//the goto connections to "Success" and "Error" 
		vpManual("exist1", true, WorkFlowEditor.hasLinkBetween("Screen1", "SuccessScreen")).performTest();
		vpManual("exist2", true, WorkFlowEditor.hasLinkBetween("Screen1", "ErrorScreen")).performTest();
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

