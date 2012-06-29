package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CS_AllDataTypeHelper;
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
import component.entity.GlobalConfig;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFCheckbox;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CS_AllDataType extends CS_AllDataTypeHelper
{
	/**
	 * Script Name   : <b>CS_AllDataType</b>
	 * Generated     : <b>Sep 8, 2011 1:52:01 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/08
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/create_AllDT.sql");
					
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllDT");
		vpManual("hasscreen", true, WorkFlowEditor.hasScreen("AllDTcreate")).performTest();
		vpManual("hasmenu", true, WorkFlowEditor.hasMenuItemInScreen("AllDTcreate", "Create")).performTest();
		vpManual("haswidget",true,WorkFlowEditor.hasWidgetInScreen("AllDTcreate",new WFEditBox().label("Int1:"))).performTest();
	
		PropertiesView.clickTab("General");
		System.out.print(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text"));
		vpManual("dataBinding","AllDT_create_int1_paramKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
	}
}
//passed