package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Start_DB_dblHelper;
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
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Start_DB_dbl extends SP_Start_DB_dblHelper
{
	/**
	 * Script Name   : <b>SP_Start_DB_dbl</b>
	 * Generated     : <b>Sep 4, 2011 7:51:25 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/04
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option((WorkFlow.SP_CLIENT_INIT)));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFClientInitiateFlowStartingPointFigure().click();
		PropertiesView.setNewKeyServer("key1,double");
		
		System.out.print("===="+PropertiesView.getStartPoint(WorkFlow.SP_CLIENT_INIT).getKey());
		vpManual("Properies", "key1,double", PropertiesView.getStartPoint(WorkFlow.SP_CLIENT_INIT).getKey()).performTest();
		vpManual("noerror",0, Problems.getErrors().size()).performTest();
		
		MainMenu.saveAll();
		WN.closeAll();
		
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WorkFlowEditor.showPropertiesViewInFD();
		vpManual("Properies", "key1,double", PropertiesView.getStartPoint(WorkFlow.SP_CLIENT_INIT).getKey()).performTest();
	}
}
//passed
