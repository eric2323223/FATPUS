package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Slider_act_211Helper;
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
import component.entity.model.WFChoice;
import component.entity.model.WFSlider;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Slider_act_211 extends Ctrl_Slider_act_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 17, 2011 7:40:12 PM</b>
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
		PropertiesView.clickTab("General");
	    vpManual("slider","slider:",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
	 //*************reopen   
	    DOF.getRoot().click();
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wf2.xbw");
	    
	  //************************verify whether the new added control exist**************
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
TestObject[] boxes = DOF.getWFSliderFigures(DOF.getRoot());
TestObject box = boxes[0];
((GefEditPartTestObject)box).click();
PropertiesView.clickTab("General");
vpManual("addedslider","slider:",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
	//************************verify whether the new added control exist end**************

//****************delete from keyboard
TestObject[] boxes1 = DOF.getWFSliderFigures(DOF.getRoot());
System.out.print(boxes1);
TestObject box1 = boxes1[0];
((GefEditPartTestObject)box1).click();	
DOF.getRoot().inputKeys("{ExtDelete}");
    MainMenu.saveAll(); 
//************end


	
	}
}

