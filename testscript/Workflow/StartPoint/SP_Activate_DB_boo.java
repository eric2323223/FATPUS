package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Activate_DB_booHelper;
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

import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Activate_DB_boo extends SP_Activate_DB_booHelper
{
	/**
	 * Script Name   : <b>SP_Activate_DB_boo</b>
	 * Generated     : <b>Sep 8, 2011 5:42:19 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/08
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFActivateFlowStartingPointFigure().click();
		PropertiesView.setNewKeyServer("key1,bool");
		vpManual("Properies", "key1,bool", PropertiesView.getStartPoint(WorkFlow.SP_ACTIVATE).getKey()).performTest();
		
		MainMenu.saveAll();
		WN.closeAll();
		
		WN.openWorkFlow(Cfg.projectName, Cfg.wfName+".xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFActivateFlowStartingPointFigure().click();
		vpManual("Properies", "key1,bool", PropertiesView.getStartPoint(WorkFlow.SP_ACTIVATE).getKey()).performTest();
	}
}
//passed
