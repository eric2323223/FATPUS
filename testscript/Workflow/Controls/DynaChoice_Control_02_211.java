package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.DynaChoice_Control_02_211Helper;
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
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFChoice;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class DynaChoice_Control_02_211 extends DynaChoice_Control_02_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 25, 2011 2:54:18 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test DynaChoice_Control_02
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wf2")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		
		WorkFlowEditor.addScreen("linktostartscr");
		WorkFlowEditor.link("Start Screen", "linktostartscr");
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
		.name("findAll")
		.type("Online Request")
		.project(Cfg.projectName)
		.mbo("Department")
		.objectQuery("findAll")
		.defaultSuccessScreen("linktostartscr"));
	
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "linktostartscr", new WFChoice().label("choice:"));	
		
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		 TestObject[] boxes = DOF.getWFChoiceFigures(DOF.getRoot());
		 TestObject box = boxes[0];
		 ((GefEditPartTestObject)box).click();
		 PropertiesView.clickTab("General");
		 
		 DOF.getButton(DOF.getRoot(), "&Dynamic").click();
		 DOF.getCCombo(DOF.getRoot(), "Value key:").click();
		 WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Value key:"),PropertiesView.verifykeylistcom().split(",")[3]);
		 vpManual("valuekeybind","Department.Department_dept_id_attribKey",DOF.getCCombo(DOF.getRoot(), "Value key:").getProperty("text").toString()).performTest();
		 
		 
		 DOF.getCCombo(DOF.getRoot(), "Display name key:").click();
		 WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Display name key:"),PropertiesView.verifykeylistcom().split(",")[4]);
		 vpManual("namekeybind","Department.Department_dept_name_attribKey",DOF.getCCombo(DOF.getRoot(), "Display name key:").getProperty("text").toString()).performTest();
	}
}

