package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Text_ActHelper;
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

import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFEditBox;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class ACT_Text_Act extends ACT_Text_ActHelper
{
	/**
	 * Script Name   : <b>ACT_Text_Act</b>
	 * Generated     : <b>Sep 29, 2011 5:56:26 PM</b>
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
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("test")
							   .logicalType("NUMERIC")
							   .newKey("key1,int")
		);
		PropertiesView.clickTab("General");
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Default value:"), "123");
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		MainMenu.saveAll();
		controlEdit("test");
		// add another text
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("another")
							   .logicalType("NUMERIC")
							   .newKey("key2,int")
		);
		//
		PropertiesView.clickTab("General");
		MainMenu.saveAll();
		controlEdit("another");
		MainMenu.saveAll();
		vpManual("exist", false, WorkFlowEditor.hasWidgetInScreen("Start Screen", new WFEditBox().label("another"))).performTest();

	}
	
	private void controlEdit(String string) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), "Start Screen").doubleClick();
		TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
		for(TestObject box:boxes){
			((GefEditPartTestObject)box).click();
			sleep(0.5);
			String label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
			if(label.equals(string) && string.equals("test")){
				((GefEditPartTestObject)box).click(RIGHT);
				DOF.getContextMenu().click(atPath("Delete"));
			} else {
				DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
			}
		}
	}
}

