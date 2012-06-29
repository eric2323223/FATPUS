package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.Ctrl_Label_3_211Helper;
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
import component.entity.model.WFLabel;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Label_3_211 extends Ctrl_Label_3_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Nov 3, 2011 11:56:18 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/03
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test Ctrl_Label_3
//		WN.useProject(Cfg.projectName);
//		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
////		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
////					.server("My Unwired Server")
////					.serverConnectionMapping("My Sample Database,sampledb"));
//	
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("wf")
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.addWidget(Cfg.projectName, "wf.xbw", "Start Screen", new WFLabel().defaultvalue("labelctrl"));
//		
//		
//		
//		
//		TestObject[] boxes = DOF.getWFLabelFigures(DOF.getRoot());
//	    TestObject box = boxes[0];
//		((GefEditPartTestObject)box).doubleClick();
//		TBD.........System.out.print(box.getProperty("text").toString());
		//how to get the label's name TBD....
//		PropertiesView.
		System.out.println(DOF.getTextField(DOF.getRoot(), "Default value:").getProperty("text"));
	   vpManual("labelname","labelctrl",DOF.getTextField(DOF.getRoot(), "Default value:").getProperty("text")).performTest();
	}
}

