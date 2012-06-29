package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Choice_prop_01_211Helper;
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
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.EE;
import component.entity.MainMenu;
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
public class Ctrl_Choice_prop_01_211 extends Ctrl_Choice_prop_01_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 13, 2011 7:39:47 PM</b>
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
		((GefEditPartTestObject) box).click();
		PropertiesView.clickTab("General");
		vpManual("addedchoice","choice:",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
	//************************verify whether the new added control exist end**************

//***************change the choice info
		PropertiesView.set(new WFChoice().
				label("choicechanged")
				.labelPosition("RIGHT")
				.logicalType("NUMERIC")
				.ifReadonly(true)
				.ifRequired(true)
				.validationMessage("choice must be selected"));
		PropertiesView.clickTab("General");
		
vpManual("changedlable","choicechanged",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
vpManual("changedposition","RIGHT",DOF.getCCombo(DOF.getRoot(), "Label position:").getProperty("text").toString()).performTest();
vpManual("ifonlycheck","true",DOF.getButton(DOF.getRoot(),"Read &only").invoke("getSelection")).performTest();
vpManual("logicaltype","NUMERIC",DOF.getCCombo(DOF.getRoot(), "Logical type:").getProperty("text").toString()).performTest();
PropertiesView.clickTab("Advanced");
vpManual("requiredcheck","true",DOF.getButton(DOF.getRoot(),"&Required").invoke("getSelection")).performTest();
vpManual("validationmessage","choice must be selected",DOF.getTextField(DOF.getRoot(), "Validation message:").getProperty("text").toString()).performTest();
	
	//***************set items for static choice
		
PropertiesView.set(new WFChoice()
.logicalType("TEXT")
.option("Static,item1,1")
.option("Static,item2,2")
.option("Static,item3,3")
.option("Static,item4,4"));
	
//********************delete some item in static choice
PropertiesView.maximize();
		PropertiesView.clickTab("General");
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getRoot()), "Display name", "item4");
		DOF.getTable(DOF.getRoot()).click(atCell(atRow(row), atColumn("Display name")));
		DOF.getButton(DOF.getRoot(), "&Delete").click();
		vpManual("itemdelsuccess",false,TableHelper.hasDataInColumn(DOF.getTable(DOF.getRoot()),"Display name", "item4"));
		PropertiesView.restore();
		MainMenu.saveAll();
		sleep(0.5);
	
	//**********end

	//******get some column value from table
	PropertiesView.set(new WFChoice()
	.logicalType("NUMERIC")
	.option("Static,n1,5"));
	vpManual("ifitemadded",true,TableHelper.hasDataInColumn(DOF.getTable(DOF.getRoot()),"Display name", "n1")).performTest();
		
	//***************end
	
	
	//*****************reopen and verify the new added info
	
		DOF.getWFScreenDesignCanvas().click();
		WN.closeAll();
		WN.openWorkFlow("wf", "wf2.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25, 10));
		TestObject[] boxes1 = DOF.getWFChoiceFigures(DOF.getRoot());
		TestObject box1 = boxes1[0];
		((GefEditPartTestObject) box1).click();
		PropertiesView.clickTab("General");
 
 vpManual("logicaltype","NUMERIC",DOF.getCCombo(DOF.getRoot(), "Logical type:").getProperty("text").toString()).performTest();
 vpManual("ifchoicelistadded","item1",TableHelper.getItemTextAt(DOF.getTable(DOF.getRoot()),0)).performTest();
 vpManual("ifchoicelistadded","item2",TableHelper.getItemTextAt(DOF.getTable(DOF.getRoot()),1)).performTest();
 vpManual("ifchoicelistadded","item3",TableHelper.getItemTextAt(DOF.getTable(DOF.getRoot()),2)).performTest();
 
   
	}

	
}


