package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.D_689898_Screen_DeleteHelper;
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
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import component.entity.EE;
import component.entity.MainMenu;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFButton;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class D_689898_Screen_Delete extends D_689898_Screen_DeleteHelper
{
	/**
	 * Script Name   : <b>D689898_Screen_Delete</b>
	 * Generated     : <b>Nov 24, 2011 10:41:23 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/24
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("s1");
		WorkFlowEditor.addScreen("s2");
		WorkFlowEditor.link("Start Screen", "s1");
		
		//add button
		WorkFlowEditor.addWidget("s1", new WFButton()
				.key("tos2")
				.type("Open Screen")
				.screenn("s2")
				);
		vpManual("haslink",true,WorkFlowEditor.hasLinkBetween("s1", "s2")).performTest();
		
		//delete s2:s
		WorkFlowEditor.removeScreen("s2");
		vpManual("haslink",false,WorkFlowEditor.hasLinkBetween("s1", "s2")).performTest();
		MainMenu.saveAll();
		
		//deploy:
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, Cfg.wfName))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		
	}
}
//passed
