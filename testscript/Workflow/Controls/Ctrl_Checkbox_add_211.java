package testscript.Workflow.Controls;

import resources.testscript.Workflow.Controls.Ctrl_Checkbox_add_211Helper;
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
import component.entity.model.DeployOption;
import component.entity.model.WFCheckbox;
import component.entity.model.WFEditBox;

/**
 * Description : Functional Test Script
 * 
 * @author xjf
 */
public class Ctrl_Checkbox_add_211 extends Ctrl_Checkbox_add_211Helper {
	/**
	 * Script Name : <b>Script1</b> Generated : <b>Oct 16, 2011 7:50:46 PM</b>
	 * Description : Functional Test Script Original Host : WinNT Version 5.1
	 * Build 2600 (S)
	 * 
	 * @since 2011/10/16
	 * @author xjf
	 */
	public void testMain(Object[] args) {
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"),50, 90);

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"wfmbocreate").option(WorkFlow.SP_CLIENT_INIT));

		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw",
				"Start", new WFCheckbox().label("checkbox:"));

		// >>>>>>>>>>whether control has added>>>>>>>>
		PropertiesView.clickTab("General");
		vpManual(
				"addcheckbox",
				"checkbox:",
				DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text")
						.toString()).performTest();
		// >>>>>>>>>>whether control has added end >>>>>>>>

		// *********reopen workflow ***************
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25, 10));
		sleep(1);
		WN.closeAll();

		WN.openWorkFlow("wf", "wfmbocreate.xbw");
		// *********reopen workflow end***************

		// ************************verify whether the new added control
		// exist**************
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25, 10));
		TestObject[] boxes = DOF.getWFCheckBoxFigures(DOF.getRoot());
		TestObject box = boxes[0];
		((GefEditPartTestObject) box).click();
		PropertiesView.clickTab("General");
		vpManual(
				"addcheckboxexist",
				"checkbox:",
				DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text")
						.toString()).performTest();
		// ************************verify whether the new added control exist
		// end**************

		// ***********delete from keyboard***************
		TestObject[] boxes1 = DOF.getWFCheckBoxFigures(DOF.getRoot());
		System.out.print(boxes1);
		TestObject box1 = boxes1[0];
		((GefEditPartTestObject) box1).click();
		DOF.getRoot().inputKeys("{ExtDelete}");
		MainMenu.saveAll();

		// verify whether the control be deleted when reopen
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25, 10));
		sleep(1);
		WN.closeAll();
		WN.openWorkFlow("wf", "wfmbocreate.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25, 10));
		TestObject[] boxes2 = DOF.getWFCheckBoxFigures(DOF.getRoot());
		vpManual("delsuccess", 0, boxes2.length).performTest();

	}

}
