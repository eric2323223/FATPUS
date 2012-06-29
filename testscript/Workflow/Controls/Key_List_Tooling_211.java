package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Key_List_Tooling_211Helper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Key_List_Tooling_211 extends Key_List_Tooling_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 25, 2011 10:54:21 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test 673597_Key_List_Tooling
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start Screen", new WFEditBox().label("editbox:")
				.newKey("a,int")
				.logicalType("DATETIME"));
		
		TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
		TestObject box = boxes[0];
		((GefEditPartTestObject)box).click();
		PropertiesView.clickTab("General");
		String s = PropertiesView.verifykeylist();
		System.out.print(s);
		vpManual("verifykeylist","",s).performTest();
		
		PropertiesView.set(new WFEditBox().
				logicalType("TEXT"));
		TestObject[] boxes1 = DOF.getWFEditBoxFigures(DOF.getRoot());
		TestObject box1 = boxes1[0];
		((GefEditPartTestObject)box1).click();
		PropertiesView.clickTab("General");
		vpManual("verifykeylist","a,",PropertiesView.verifykeylist()).performTest();
	}
}

