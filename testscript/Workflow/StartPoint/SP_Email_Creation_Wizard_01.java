package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_Creation_Wizard_01Helper;
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
import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_Creation_Wizard_01 extends SP_Email_Creation_Wizard_01Helper
{
	/**
	 * Script Name   : <b>SP_Email_Creation_Wizard_01</b>
	 * Generated     : <b>Sep 14, 2011 2:22:31 PM</b>
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
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->customer (dba)"), 10, 10);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.project(Cfg.projectName)
				.type(WorkFlow.SP_SERVER_INIT)
				.mbo("Customer")
				.objectQuery("findAll")
				);
		vpManual("Properies", false, WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_SERVER_INIT).mbo("Customer"))).performTest();
		
		WorkFlowEditor.removeStartPoint(WorkFlow.SP_SERVER_INIT);
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.project(Cfg.projectName)
			.type(WorkFlow.SP_SERVER_INIT)
			.mbo("Customer"));
	}
}
//passed
