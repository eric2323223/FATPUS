package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Slider_prop_211Helper;
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
import component.entity.model.WFSlider;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Slider_prop_211 extends Ctrl_Slider_prop_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 17, 2011 7:57:49 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/17
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wf2")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "Start Screen", new WFSlider().label("slider:"));
		
		PropertiesView.set(new WFSlider()
		.label("slider_prop_modify")
		.labelPosition("TOP")
		.newKey("sliderkey,decimal")
		.ifReadonly(false)
		.maxValue("100")
		.minValue("1")
		.validationMessage("slider validation"));
		
		DOF.getWFScreenDesignCanvas().click();
		sleep(1);
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wf2.xbw");
		
		//************************verify whether the new added control exist**************
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
        TestObject[] boxes = DOF.getWFSliderFigures(DOF.getRoot());
        TestObject box = boxes[0];
        ((GefEditPartTestObject)box).click();
        PropertiesView.clickTab("General");
        
        vpManual("addsliderexist","slider_prop_modify",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
        vpManual("changedposition","TOP",DOF.getCCombo(DOF.getRoot(), "Label position:").getProperty("text").toString()).performTest();
        vpManual("ifonlycheck","false",DOF.getButton(DOF.getRoot(),"Read &only").invoke("getSelection")).performTest();
        vpManual("verifynewkey","sliderkey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text").toString()).performTest();
        vpManual("vefifymax","100",DOF.getTextField(DOF.getRoot(), "Maximum value:").getProperty("text").toString()).performTest();
        vpManual("vefifymin","1",DOF.getTextField(DOF.getRoot(), "Minimum value:").getProperty("text").toString()).performTest();
        PropertiesView.clickTab("Advanced");
        vpManual("validationmessage","slider validation",DOF.getTextField(DOF.getRoot(), "Validation message:").getProperty("text").toString()).performTest();
        
	//************************ end**************
	}
}

