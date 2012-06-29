package testscript.Workflow.StartPoint;
import com.sybase.automation.framework.widget.DOF;

import resources.testscript.Workflow.StartPoint.SP_Activate_DB_RemovedHelper;
import testscript.Workflow.cfg.Cfg;

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
public class SP_Activate_DB_Removed extends SP_Activate_DB_RemovedHelper
{
	/**
	 * Script Name   : <b>SP_Activate_DB_Removed</b>
	 * Generated     : <b>Sep 1, 2011 7:13:29 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/01
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFActivateFlowStartingPointFigure().click();
		PropertiesView.setNewKeyServer("key1,string");
		vpManual("Properies", "key1,string", PropertiesView.getStartPoint(WorkFlow.SP_ACTIVATE).getKey()).performTest();
		MainMenu.saveAll();
		
		PropertiesView.removeKey("key1");
		MainMenu.saveAll();
		System.out.println(PropertiesView.getStartPoint(WorkFlow.SP_ACTIVATE).getKey());
		vpManual("Properies", null, PropertiesView.getStartPoint(WorkFlow.SP_ACTIVATE).getKey()).performTest();
		
		WN.closeAll();
		
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFActivateFlowStartingPointFigure().click();
		vpManual("Properies", null, PropertiesView.getStartPoint(WorkFlow.SP_ACTIVATE).getKey()).performTest();
	}
}
//passed
