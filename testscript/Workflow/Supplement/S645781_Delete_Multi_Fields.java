package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S645781_Delete_Multi_FieldsHelper;
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
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S645781_Delete_Multi_Fields extends S645781_Delete_Multi_FieldsHelper
{
	/**
	 * Script Name   : <b>S645781_Delete_Multi_Fields</b>
	 * Generated     : <b>Mar 12, 2012 10:27:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/12
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)",10,10);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findall")
				.project(Cfg.projectName)
				.type("Online Request")
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		
		//delete the celllines of listview :
		vpManual("haslv",true,WorkFlowEditor.hasWidgetInScreen("Department", new WFLview().key("Department"))).performTest();
		PropertiesView.maximize();
		PropertiesView.clickTab("Cell");
		
		//add the second fields in a cell line 0:
		TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),0 , 0);
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0"), "&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Listview Field");

		DOF.getCCombo(dialog, "Key:").click();
		DOF.getCCombo(dialog, "Key:").setProperty("text", "Department_dept_name_attribKey");

		WO.setTextField(dialog, DOF.getTextField(dialog, "Field width:"), "100");
		DOF.getButton(dialog,"OK").click();
		
		TableHelper.clickAtCell(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")),0 , 0);
		
		TableHelper.clickAtCell(SHIFT_LEFT,DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")),1 , 0);
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0"), "&Delete").click();
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		PropertiesView.restore();
		MainMenu.saveAll();
		
		String[] cellname = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")), 0);
		System.out.println(cellname.length);
		
		vpManual("cell",0,cellname.length).performTest();
		vpManual("noerror",0,Problems.getErrors().size()).performTest();
	}
}
//passed


