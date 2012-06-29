package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Relationship_Keylist_Tooling_211Helper;
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
import component.entity.GlobalConfig;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Relationship_Keylist_Tooling_211 extends Relationship_Keylist_Tooling_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 25, 2011 11:21:29 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test 678528_Relationship_Keylist_Tooling
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/create_table_myemployee.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->myemployee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("Myemployee")
			.mapping("dept_id,dept_id")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		DOF.getWFScreenFigure(DOF.getRoot(), "Myemployee_update_instance").doubleClick();
		
		
		DOF.getWFMenuItemFigure(DOF.getRoot(), "Update").click(atPoint(5,5));
		sleep(1);
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click();
		PropertiesView.clickTab("Parameter Mappings");
		TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()), 0, 0);
		DOF.getButton(DOF.getRoot(), "&Edit...").click();
		
		TopLevelTestObject dialog = DOF.getDialog("Parameter Mapping");
		DOF.getButton(dialog, "OK").click();
		
		//Verify the keys display well on Parameter Mapping table
		String cellvalue=TableHelper.getCellValue(DOF.getTable(DOF.getRoot()),0,0);
		vpManual("mappingkeyexist","true",TableHelper.hasDataInColumn(DOF.getTable(DOF.getRoot()),"Key Name",cellvalue));
	}
}

