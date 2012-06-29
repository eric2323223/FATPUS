package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Editbox_edit_211Helper;
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
import component.entity.model.DeployedMbo;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Editbox_edit_211 extends Ctrl_Editbox_edit_211Helper
{
	/**
	 * Script Name   : <b>Ctrl_Editbox_edit_211</b>
	 * Generated     : <b>Oct 11, 2011 1:16:05 AM</b>
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
			
		 //>>>>>>>>>>whether control has added>>>>>>>>
		PropertiesView.clickTab("General");
        vpManual("addeditbox","editbox:",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
		//>>>>>>>>>>whether control has added end >>>>>>>>
		
		
		//*********reopen workflow ***************
    DOF.getRoot().click();
    WN.closeAll();
    WN.openWorkFlow("wf", "wfmbocreate.xbw");
		//*********reopen workflow end***************
		
//************************verify whether the new added control exist**************
		    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
    TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
    TestObject box = boxes[0];
	((GefEditPartTestObject)box).click();
	PropertiesView.clickTab("General");
   vpManual("addeditboxexist","editbox:",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
		//************************verify whether the new added control exist end**************
		
		//***********copy/cut/paste control**************
   ((GefEditPartTestObject)box).click(RIGHT);
   DOF.getContextMenu().click(atPath("Edit->Copy"));
   DOF.getWFScreenDisplayFigure().click(RIGHT);
   DOF.getContextMenu().click(atPath("Edit->Paste"));
   TestObject[] boxes1 = DOF.getWFEditBoxFigures(DOF.getRoot());
   TestObject box1 = boxes1[1];
   ((GefEditPartTestObject)box1).click();
   PropertiesView.clickTab("General");
   vpManual("pasteeditbox","editbox:",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
   ((GefEditPartTestObject)box1).click(RIGHT);
   DOF.getContextMenu().click(atPath("Edit->Cut"));
   sleep(1);
   DOF.getWFScreenDisplayFigure().click(RIGHT);
   DOF.getContextMenu().click(atPath("Edit->Paste"));
   TestObject[] boxes2 = DOF.getWFEditBoxFigures(DOF.getRoot());
   TestObject box2 = boxes2[1];
   ((GefEditPartTestObject)box2).click();
   PropertiesView.clickTab("General");
   vpManual("pasteeditbox","editbox:",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest(); 
		//***********copy/cut/paste control end**************
		
		 //***********delete from keyboard***************
   TestObject[] boxes3 = DOF.getWFEditBoxFigures(DOF.getRoot());
   TestObject box3 = boxes3[1];
   ((GefEditPartTestObject)box3).click();	
   DOF.getRoot().inputKeys("{ExtDelete}");
       MainMenu.saveAll(); 
       
}
	}
