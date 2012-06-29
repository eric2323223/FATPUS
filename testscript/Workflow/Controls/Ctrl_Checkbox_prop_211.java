package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Checkbox_prop_211Helper;
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
import component.entity.model.WFCheckbox;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Checkbox_prop_211 extends Ctrl_Checkbox_prop_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 16, 2011 10:28:37 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/16
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
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start", new WFCheckbox().label("checkbox:"));
		
		PropertiesView.set(new WFCheckbox()
		.label("changecheckbox")
		.labelPosition("RIGHT")
		.ifReadonly(false)
		.ifRequired(true)
		.defaultValue("true")
		.validationMessage("validation failed"));
		
		DOF.getWFScreenDesignCanvas().click();
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wfmbocreate.xbw");
	    
	  //************************verify whether the new added control exist**************
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
        TestObject[] boxes = DOF.getWFCheckBoxFigures(DOF.getRoot());
        TestObject box = boxes[0];
        ((GefEditPartTestObject)box).click();
        PropertiesView.clickTab("General");
        
        vpManual("addcheckboxexist","changecheckbox",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
        vpManual("changedposition","RIGHT",DOF.getCCombo(DOF.getRoot(), "Label position:").getProperty("text").toString()).performTest();
        vpManual("ifonlycheck","false",DOF.getButton(DOF.getRoot(),"Read &only").invoke("getSelection")).performTest();
        vpManual("defaultvalue","true",DOF.getCCombo(DOF.getRoot(), "Default value:").getProperty("text").toString()).performTest();
        PropertiesView.clickTab("Advanced");
        vpManual("requiredcheck","true",DOF.getButton(DOF.getRoot(),"&Required").invoke("getSelection")).performTest();
        vpManual("validationmessage","validation failed",DOF.getTextField(DOF.getRoot(), "Validation message:").getProperty("text").toString()).performTest();
	//************************ end**************
	}
}

