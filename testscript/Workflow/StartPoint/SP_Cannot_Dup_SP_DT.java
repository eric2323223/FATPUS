package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Cannot_Dup_SP_DTHelper;
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
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Cannot_Dup_SP_DT extends SP_Cannot_Dup_SP_DTHelper
{
	/**
	 * Script Name   : <b>SP_Cannot_Dup_SP_DT</b>
	 * Generated     : <b>Sep 14, 2011 9:30:26 AM</b>
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
//		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->employee (dba)"), 10, 10);
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF"));
//		WorkFlowEditor.addStartingPoint(new StartPoint()
//				.type(WorkFlow.SP_CLIENT_INIT));
		DOF.getWFClientInitiateFlowStartingPointFigure().click(RIGHT);
		System.out.println(MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Copy"));
//		DOF.getWFCredentialRequestFlowStartingPointFigure().click(RIGHT);
		vpManual("cutAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Cut")).performTest();
		vpManual("copyAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Copy")).performTest();
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_ACTIVATE));
		vpManual("cutAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Cut")).performTest();
		vpManual("copyAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Copy")).performTest();
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_CREDENTIAL_REQUEST));
		vpManual("cutAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Cut")).performTest();
		vpManual("copyAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Copy")).performTest();
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT)
				.mbo("Employee"));
		vpManual("cutAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Cut")).performTest();
		vpManual("copyAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Copy")).performTest();
		WorkFlowEditor.addScreen("Myscr");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"Myscr");
		
		WorkFlowEditor.multiselect("Myscr",WorkFlow.SP_CLIENT_INIT);
		vpManual("cutAndPaste", true, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Cut")).performTest();
		vpManual("copyAndPaste", true, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Copy")).performTest();
		
	}
}

