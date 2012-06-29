package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_DB_strHelper;
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
public class SP_Email_DB_str extends SP_Email_DB_strHelper
{
	/**
	 * Script Name   : <b>SP_Email_DB_str</b>
	 * Generated     : <b>Sep 9, 2011 3:57:15 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/09
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall")
			);
		WorkFlowEditor.addScreen("myscreen");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "myscreen");
		
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		PropertiesView.setNewKeyServer("key1,string");
		
		vpManual("Properies", "key1,string,", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getKey()).performTest();
		vpManual("noerror",0, Problems.getErrors().size()).performTest();
		
		MainMenu.saveAll();
		WN.closeAll();
		
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WorkFlowEditor.showPropertiesViewInFD();
		vpManual("Properies", "key1,string,", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getKey()).performTest();
		
	}
}
//passed

