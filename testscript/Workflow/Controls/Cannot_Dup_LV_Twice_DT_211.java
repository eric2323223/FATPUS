package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Cannot_Dup_LV_Twice_DT_211Helper;
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
import com.sybase.automation.framework.widget.helper.MenuHelper;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFHtmlView;
import component.entity.model.WFLview;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Cannot_Dup_LV_Twice_DT_211 extends Cannot_Dup_LV_Twice_DT_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 25, 2011 7:23:32 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test 628826_Cannot_Dup_HV_and_LV_Twice_DT

		logInfo("feature has changed,this case need not execute");
		
//		WN.useProject(Cfg.projectName);
//		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//					.server("My Unwired Server")
//					.serverConnectionMapping("My Sample Database,sampledb"));
//	
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("wf2")
//				.option(WorkFlow.SP_CLIENT_INIT));
//		
//		WorkFlowEditor.addScreen("linktoemail");
//		WorkFlowEditor.link("Start Screen", "linktoemail");
//		
//		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "linktoemail", new WFLview());
//		TestObject[] boxes2 = DOF.getWFListViewFigures(DOF.getRoot());
//	    TestObject box2 = boxes2[0];
//	   ((GefEditPartTestObject)box2).click(RIGHT);
//	   DOF.getContextMenu().click(atPath("Edit->Cut"));
//	   
//	   DOF.getWFScreenDisplayFigure().click();
//	   vpManual("pastedisable","false",MenuHelper.isItemEnabled(DOF.getMenu(), "Edit->Delete")).performTest();
//
//		
////		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "linktoemail", new WFHtmlView());
//		
////		***********copy/cut/paste control**************
////		 TestObject[] boxes = DOF.getWFHtmlViewFigures(DOF.getRoot());
////	    TestObject box = boxes[0];
////	   ((GefEditPartTestObject)box).click(RIGHT);
////	   DOF.getContextMenu().click(atPath("Edit->Cut"));
////	   DOF.getWFScreenDisplayFigure().click(RIGHT);
////	   DOF.getContextMenu().click(atPath("Edit->Paste"));
////		
////	   TestObject[] boxes1 = DOF.getWFHtmlViewFigures(DOF.getRoot());
////		vpManual("ifpastesucc","1",boxes1.length).performTest();
////		((GefEditPartTestObject)DOF.getWFHtmlViewFigures(DOF.getRoot())[0]).click(RIGHT);
////		DOF.getContextMenu().click(atPath("Delete"));
////		
////		MainMenu.saveAll();
////		DOF.getWFScreenDesignCanvas().click();
////		
////		DOF.getRoot().click();
////	    WN.closeAll();
////	    WN.openWorkFlow("wf", "wf2.xbw");
////
////		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "linktoemail", new WFLview());
////		MainMenu.saveAll();
//		DOF.getWFScreenDisplayFigure().click();
//		TestObject[] boxes4 = DOF.getWFListViewFigures(DOF.getRoot());
//	    TestObject box4 = boxes4[0];
//	   ((GefEditPartTestObject)box4).click(RIGHT);
//	   DOF.getContextMenu().click(atPath("Edit->Cut"));
////	   
////	  
////	   
//	   DOF.getWFScreenDisplayFigure().click();
		
		
	}
}

