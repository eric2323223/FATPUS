package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_New_Key_in_ScreenHelper;
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
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WFSlider;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_New_Key_in_Screen extends SP_New_Key_in_ScreenHelper
{
	/**
	 * Script Name   : <b>SP_New_Key_in_Screen</b>
	 * Generated     : <b>Sep 13, 2011 5:57:18 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/13
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("newscreen");
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("dept_id:")
				.newKey("id,int"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findByPK")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("newscreen")
				.parametermapping("dept_id,id"));
		
		WorkFlowEditor.addWidget("newscreen", new WFEditBox().label("dept_name:")
				.newKey("nam,string"));
		
		MainMenu.saveAll();
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFClientInitiateFlowStartingPointFigure().click();
		
		System.out.print("keys="+PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey());
		vpManual("key", true, PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey().contains("nam,string")).performTest();
		vpManual("noerror", 0, Problems.getErrors().size()).performTest();
		
	}
}
//passed
