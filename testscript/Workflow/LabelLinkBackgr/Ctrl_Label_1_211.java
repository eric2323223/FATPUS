package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.Ctrl_Label_1_211Helper;
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
import component.entity.model.WFEditBox;
import component.entity.model.WFLabel;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Label_1_211 extends Ctrl_Label_1_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Nov 3, 2011 11:01:47 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/03
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test Ctrl_Label_1 and Ctrl_Label_2
			
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start Screen", new WFLabel().name("labelctrl"));
		
		TestObject[] boxes = DOF.getWFLabelFigures(DOF.getRoot());
	    TestObject box = boxes[0];
		((GefEditPartTestObject)box).click();
		PropertiesView.clickTab("General");
	   vpManual("addedlabelexist","labelctrl",DOF.getTextField(DOF.getRoot(), "Default value:").getProperty("text").toString()).performTest();
	}
}

