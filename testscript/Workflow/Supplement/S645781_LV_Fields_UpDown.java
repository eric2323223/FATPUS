package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S645781_LV_Fields_UpDownHelper;
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
public class S645781_LV_Fields_UpDown extends S645781_LV_Fields_UpDownHelper
{
	/**
	 * Script Name   : <b>S645781_LV_Fields_UpDown</b>
	 * Generated     : <b>Mar 13, 2012 2:26:37 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/13
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
		sleep(1);
		PropertiesView.maximize();
		PropertiesView.clickTab("Cell");
		
		//add the second fields in a cell line 0:
		TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),0 , 0);
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0"), "&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Listview Field");

		DOF.getCCombo(dialog, "Key:").click();
		DOF.getCCombo(dialog, "Key:").setProperty("text", "Department_dept_name_attribKey");

		DOF.getButton(dialog,"OK").click();
			sleep(1);
		TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),1 , 0);
			sleep(1);
		System.out.println(PropertiesView.getCellOfListView("cell line 0").getKey().toString());
		vpManual("orderBefore","Department_dept_id_attribKey,:Department_dept_name_attribKey,",PropertiesView.getCellOfListView("cell line 0").getKey().toString()).performTest();
//		
		//up:
		TableHelper.clickAtCell(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")),1 , 0);
		sleep(2);
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0"), "&Up").click();
		MainMenu.saveAll();
		sleep(2);
		System.out.println(PropertiesView.getCellOfListView("cell line 0").getKey().toString());
		vpManual("orderup","Department_dept_name_attribKey,:Department_dept_id_attribKey,",PropertiesView.getCellOfListView("cell line 0").getKey().toString()).performTest();
		
		//down:
		TableHelper.clickAtCell(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")),0 , 0);
		sleep(2);
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0"), "&Down").click();
				MainMenu.saveAll();
		sleep(2);
		
		System.out.println(PropertiesView.getCellOfListView("cell line 0").getKey().toString());
		vpManual("orderdown","Department_dept_id_attribKey,:Department_dept_name_attribKey,",PropertiesView.getCellOfListView("cell line 0").getKey().toString()).performTest();
		PropertiesView.restore();
		MainMenu.saveAll();
		
		vpManual("noerror",0,Problems.getErrors().size()).performTest();
	}
}
//passed
