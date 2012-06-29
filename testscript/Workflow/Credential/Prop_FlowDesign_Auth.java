package testscript.Workflow.Credential;
import resources.testscript.Workflow.Credential.Prop_FlowDesign_AuthHelper;
import testscript.Workflow.cfg.Cfg;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.view.properties.workflow.WFControlObject;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Prop_FlowDesign_Auth extends Prop_FlowDesign_AuthHelper
{
	/**
	 * Script Name   : <b>Prop_FlowDesign_Auth</b>
	 * Generated     : <b>Oct 13, 2011 2:17:30 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/13
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		PropertiesView.clickTab("General");
		//DOF.getCCombo(DOF.getRoot(), "Client icon:").click();
		WFControlObject.getImageCombo().click();
		vpManual("icon", true, WFControlObject.getImageCombo().getProperty("Text").equals("New Workflow")).performTest();
		//
		ToggleGUITestObject obj = WFControlObject.getCheckBoxOnRoot("Authentication", "Use static credentials");
		obj.clickToState(NOT_SELECTED);
		MainMenu.saveAll();
		Object o = obj.invoke("getSelection");
		vpManual("selected", true, o.equals(false)).performTest();
		//
		obj.clickToState(SELECTED);
		o = obj.invoke("getSelection");
		MainMenu.saveAll();
		vpManual("selected", true, o.equals(true)).performTest();
		GuiTestObject radiobtn = WFControlObject.getRadioButtonOnRoot("Authentication", "Use SUP Server connection profile authentication");
		radiobtn.click();
		o = radiobtn.invoke("getSelection");
		MainMenu.saveAll();
		vpManual("selected", true, o.equals(true)).performTest();
		//
		GuiTestObject radiobtn1 = WFControlObject.getRadioButtonOnRoot("Authentication", "Use hard-coded credentials");
		radiobtn1.click();
		o = radiobtn1.invoke("getSelection");
		MainMenu.saveAll();
		vpManual("selected", true, o.equals(true)).performTest();
		//
		GuiTestObject usern = WFControlObject.getTextFieldOnRoot("Authentication", "Username:");
		GuiTestObject passw = WFControlObject.getTextFieldOnRoot("Authentication", "Password:");
		usern.click();
		DOF.getRoot().inputKeys("supAdmin");
		passw.click();
		DOF.getRoot().inputKeys("s3pAdmin");
		MainMenu.saveAll();
		//
		vpManual("selected", true, usern.getProperty("text").toString().equals("supAdmin")).performTest();
		vpManual("selected", true, passw.getProperty("text").toString().equals("s3pAdmin")).performTest();
		//
		GefEditPartTestObject eb1 = WFControlObject.getEditBoxOnScreen("Credential Request", "Username:");
		GefEditPartTestObject eb2 = WFControlObject.getEditBoxOnScreen("Credential Request", "Password:");
		eb1.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Edit->Cut"));
		eb1 = WFControlObject.getEditBoxOnScreen("Credential Request", "Username:");
		vpManual("contextmenu", true, null == eb1).performTest();
		DOF.getWFScreenDisplayFigure().click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Edit->Paste"));
		eb1 = WFControlObject.getEditBoxOnScreen("Credential Request", "Username:");
		vpManual("contextmenu", true, null != eb1).performTest();
		eb1.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Edit->Copy"));
		eb1 = WFControlObject.getEditBoxOnScreen("Credential Request", "Username:");
		vpManual("contextmenu", true, null != eb1).performTest();
		DOF.getWFScreenDisplayFigure().click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Edit->Paste"));
		//eb1 = ComponetUtils.getEditBoxOnScreen("Credential Request Screen", "Username:");
		vpManual("contextmenu", true, WFControlObject.getEditBoxOnScreen1("Credential Request", "Username:").size() == 2).performTest();
		//
		eb1.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Delete"));
		vpManual("contextmenu", true, WFControlObject.getEditBoxOnScreen1("Credential Request", "Username:").size() == 1).performTest();
		//
		eb1 = WFControlObject.getEditBoxOnScreen("Credential Request", "Username:");
		eb1.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Show Properties View"));
		eb1.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Add Note"));
		DOF.getRoot().inputKeys("Note Test Username." + "{ENTER}");
		vpManual("exist", true, DOF.getWFNoteFigure(DOF.getRoot(), "Note Test Username.").exists()).performTest();
		
		//>>>>>>>>>>>>>>>>>>>>>>>
		eb2.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Edit->Cut"));
		eb2 = WFControlObject.getEditBoxOnScreen("Credential Request", "Password:");
		vpManual("contextmenu", true, null == eb2).performTest();
		DOF.getWFScreenDisplayFigure().click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Edit->Paste"));
		eb2 = WFControlObject.getEditBoxOnScreen("Credential Request", "Password:");
		vpManual("contextmenu", true, null != eb2).performTest();
		eb2.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Edit->Copy"));
		eb2 = WFControlObject.getEditBoxOnScreen("Credential Request", "Password:");
		vpManual("contextmenu", true, null != eb2).performTest();
		DOF.getWFScreenDisplayFigure().click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Edit->Paste"));
		//eb1 = ComponetUtils.getEditBoxOnScreen("Credential Request Screen", "Password:");
		vpManual("contextmenu", true, WFControlObject.getEditBoxOnScreen1("Credential Request", "Password:").size() == 2).performTest();
		//
		eb2.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Delete"));
		vpManual("contextmenu", true, WFControlObject.getEditBoxOnScreen1("Credential Request", "Password:").size() == 1).performTest();
		//
		eb2 = WFControlObject.getEditBoxOnScreen("Credential Request", "Password:");
		eb2.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Show Properties View"));
		eb2.click(RIGHT);
		sleep(0.5);
		DOF.getContextMenu().click(atPath("Add Note"));
		DOF.getRoot().inputKeys("Note Test Password." + "{ENTER}");
		vpManual("exist", true, DOF.getWFNoteFigure(DOF.getRoot(), "Note Test Password.").exists()).performTest();
		
	}
}

