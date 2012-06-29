package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.Ctrl_Link_10_211Helper;
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
import component.entity.model.WFLink;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Link_10_211 extends Ctrl_Link_10_211Helper
{
	/**
	 * Script Name   : <b>Ctrl_Link_10_211</b>
	 * Generated     : <b>Nov 7, 2011 2:57:59 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/07
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test Ctrl_Link_10
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wflink")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wflink.xbw", "Start Screen", new WFLink().label("linkctrl").newKey("linkkey,string"));
	
		//copy and paste control
	   TestObject[] boxes = DOF.getWFLinkFigures(DOF.getRoot());
		   TestObject box = boxes[0];
		((GefEditPartTestObject)box).click(RIGHT);
	   DOF.getContextMenu().click(atPath("Edit->Copy"));
	   DOF.getWFScreenDisplayFigure().click(RIGHT);
	   DOF.getContextMenu().click(atPath("Edit->Paste"));
	   TestObject[] boxes1 = DOF.getWFLinkFigures(DOF.getRoot());
	   TestObject box1 = boxes1[1];
	   ((GefEditPartTestObject)box1).click();
	   PropertiesView.clickTab("General");
	   vpManual("pastelabel","linkkey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
	}
}

