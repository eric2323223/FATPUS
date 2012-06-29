package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Editbox_prop_01_211Helper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Editbox_prop_01_211 extends Ctrl_Editbox_prop_01_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 11, 2011 11:29:55 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/11
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start", new WFEditBox().label("editbox:"));
		
		PropertiesView.set(new WFEditBox()
		.label("changeeditbox")
		.labelPosition("RIGHT")
		.ifReadonly(true)
		.ifRequired(false));
		
		DOF.getRoot().click();
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wfmbocreate.xbw");
	    
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
	    TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
	    TestObject box = boxes[0];
		((GefEditPartTestObject)box).click();
		PropertiesView.clickTab("General");
	   vpManual("changedlable","changeeditbox",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
	   vpManual("changedposition","RIGHT",DOF.getCCombo(DOF.getRoot(), "Label position:").getProperty("text").toString()).performTest();
	   vpManual("whethercheck","true",DOF.getButton(DOF.getRoot(),"Read &only").invoke("getSelection")).performTest();

	}
}

