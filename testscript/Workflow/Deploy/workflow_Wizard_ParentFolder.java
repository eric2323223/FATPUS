package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.workflow_Wizard_ParentFolderHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class workflow_Wizard_ParentFolder extends workflow_Wizard_ParentFolderHelper
{
	/**
	 * Script Name   : <b>workflow_Wizard_ParentFolder</b>
	 * Generated     : <b>Sep 26, 2011 7:50:57 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/26
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createProject("xf");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.parentFolder("xf")
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		vpManual("exist", false, WN.hasWorkFlowInProject(Cfg.projectName, "myWF")).performTest();
		vpManual("exist", true, WN.hasWorkFlowInProject("xf", "myWF")).performTest();
		WN.deleteProject("xf");
	}
}

