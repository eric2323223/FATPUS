package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Misc_01_211Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.DeployOption;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Misc_01_211 extends Ctrl_Misc_01_211Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Oct 24, 2011 11:21:30 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/24
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test Ctrl_Misc_01
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), "Start").doubleClick();
		
		DOF.getWFScreenDisplayFigure().click(RIGHT);
		DOF.getContextMenu().click(atPath("Add Note"));
		DOF.getRoot().inputKeys("NOTE ADDED" + "{Enter}");
		
		DOF.getWFScreenDesignCanvas().click(atPoint(33, 17));
		System.out.print(MenuHelper.isItemEnabled(DOF.getMenu(), "Edit->Delete"));
		vpManual("deletedisable","false",MenuHelper.isItemEnabled(DOF.getMenu(), "Edit->Delete")).performTest();
		DOF.getWFScreenDesignCanvas().click(atPoint(33, 17));
		
		DOF.getRoot().inputKeys("{ExtDelete}");
		
		
		
	}
}

