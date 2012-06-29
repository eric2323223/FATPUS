package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.A683365_ACT_Menu_Items_Name_NegativeHelper;
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
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class A683365_ACT_Menu_Items_Name_Negative extends A683365_ACT_Menu_Items_Name_NegativeHelper
{
	/**
	 * Script Name   : <b>A683365_ACT_Menu_Items_Name_Negative</b>
	 * Generated     : <b>Oct 12, 2011 8:16:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		//WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
			.name("")
		);
		PropertiesView.clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Name:"), "qw!@#$%^");
		DOF.getTextField(DOF.getRoot(), "Key:").click();
		vpManual("validation", true, DOF.getCustomCLabel(
				DOF.getStatusLine(DOF.getRoot())).getProperty("text").toString().equals("Value not updated, qw!@#$%^ is not a valid menuitem name.")
				).performTest();
		//
		DOF.getTextField(DOF.getRoot(), "Key:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		DOF.getRoot().inputChars("qw!@#$%^");
		DOF.getTextField(DOF.getRoot(), "Name:").click();
		vpManual("validation", true, DOF.getCustomCLabel(
				DOF.getStatusLine(DOF.getRoot())).getProperty("text").toString().equals("Value not updated, qw!@#$%^ is not a valid menuitem name.")
				).performTest();
	}
}

