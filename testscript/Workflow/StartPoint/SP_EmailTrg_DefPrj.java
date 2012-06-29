package testscript.Workflow.StartPoint;
import java.awt.Point;

import resources.testscript.Workflow.StartPoint.SP_EmailTrg_DefPrjHelper;
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

import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_EmailTrg_DefPrj extends SP_EmailTrg_DefPrjHelper
{
	/**
	 * Script Name   : <b>SP_EmailTrg_DefPrj</b>
	 * Generated     : <b>Sep 14, 2011 2:38:53 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/14
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		Point point = WFFlowDesigner.getValidPoint();
		DOF.getPaletteRoot().click(
				atPath("xbwflow->Starting Points->" +WorkFlow.SP_SERVER_INIT));
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x, point.y));
		TopLevelTestObject dialog = DOF.getDialog("Notification Processing Wizard");
		sleep(1);
		DOF.getButton(dialog, "&Search...").click();
		sleep(2);
		TopLevelTestObject dialog2 = DOF.getDialog("Search For Mobile Business Object");
		
		vpManual("notselectedAllProject",false,((ToggleTestObject)DOF.getButton(dialog2, "&All mobile application projects")).getState().equals("NOT_SELECTED")).performTest();
		vpManual("showdefaultProject",Cfg.projectName,DOF.getCombo(DOF.getDialog("Search For Mobile Business Object"), "Project:").getProperty("text")).performTest();
		
		DOF.getButton(dialog2, "Cancel").click();
		sleep(1);
		DOF.getButton(dialog, "Cancel").click();
	}
}
//passed
