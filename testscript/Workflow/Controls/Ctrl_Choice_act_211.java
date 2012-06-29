package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Choice_act_211Helper;
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
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Choice_act_211 extends Ctrl_Choice_act_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 13, 2011 3:14:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/13
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wf2")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "Start", new WFChoice().label("choice:"));	
		PropertiesView.clickTab("General");
	    vpManual("choice","choice:",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
	 //*************reopen   
	    DOF.getWFScreenDesignCanvas().click();
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wf2.xbw");
	    
	    
	  //************************verify whether the new added control exist**************
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
TestObject[] boxes = DOF.getWFChoiceFigures(DOF.getRoot());
TestObject box = boxes[0];
((GefEditPartTestObject)box).click();
PropertiesView.clickTab("General");
vpManual("addedchoice","choice:",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
	//************************verify whether the new added control exist end**************


	
	}
}

