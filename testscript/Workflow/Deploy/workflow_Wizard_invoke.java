package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.workflow_Wizard_invokeHelper;
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

import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class workflow_Wizard_invoke extends workflow_Wizard_invokeHelper
{
	/**
	 * Script Name   : <b>workflow_Wizard_invoke</b>
	 * Generated     : <b>Sep 26, 2011 7:38:47 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/26
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		DOF.getMenu().click(atPath("File->New->Mobile Workflow Forms Editor"));
		sleep(1);
		vpManual("exist", true, DOF.getDialog("New")!=null).performTest();
		DOF.getButton(DOF.getDialog("New"), "Cancel").click();
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(Cfg.projectName)));
		DOF.getContextMenu().click(atPath("New->Mobile Workflow Forms Editor"));
		sleep(1);
		vpManual("exist", true, DOF.getDialog("New")!=null).performTest();
		DOF.getButton(DOF.getDialog("New"), "Cancel").click();
		
	}
}

