package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Activate_DB_DTHelper;
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
public class SP_Activate_DB_DT extends SP_Activate_DB_DTHelper
{
	/**
	 * Script Name   : <b>SP_Activate_DB_DT</b>
	 * Generated     : <b>Sep 8, 2011 5:43:42 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/08
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFActivateFlowStartingPointFigure().click();
		PropertiesView.setNewKeyServer("key1,DateTime");
		vpManual("Properies", "key1,DateTime", PropertiesView.getStartPoint(WorkFlow.SP_ACTIVATE).getKey()).performTest();
		
		MainMenu.saveAll();
		WN.closeAll();
		
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFActivateFlowStartingPointFigure().click();
		vpManual("Properies", "key1,DateTime", PropertiesView.getStartPoint(WorkFlow.SP_ACTIVATE).getKey()).performTest();
	}
}

