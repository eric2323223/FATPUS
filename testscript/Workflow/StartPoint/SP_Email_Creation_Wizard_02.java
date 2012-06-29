package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_Creation_Wizard_02Helper;
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
public class SP_Email_Creation_Wizard_02 extends SP_Email_Creation_Wizard_02Helper
{
	/**
	 * Script Name   : <b>SP_Email_Creation_Wizard_02</b>
	 * Generated     : <b>Sep 14, 2011 11:23:58 AM</b>
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
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName));
		
		Boolean onepage = WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findByPrimaryKey"));
		
		vpManual("Properies",true, onepage).performTest();
		
		WorkFlowEditor.removeStartPoint(WorkFlow.SP_SERVER_INIT);
		
		Boolean twopage = WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT)
			.mbo("Department")
			.objectQuery("findByPrimaryKey")
			.subject("dept_id=1"));
		
		vpManual("Properies",true, twopage).performTest();
		
		WorkFlowEditor.removeStartPoint(WorkFlow.SP_SERVER_INIT);
		
		Boolean threepage = WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT)
			.mbo("Department")
			.objectQuery("findByPrimaryKey")
			.subject("dept_id=1")
			.subjectMatchingRule("dept_id="));
		
		vpManual("Properies",true, threepage).performTest();
		
		WorkFlowEditor.removeStartPoint(WorkFlow.SP_SERVER_INIT);
		
		Boolean fourpage = WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT)
			.mbo("Department")
			.objectQuery("findByPrimaryKey")
			.subject("dept_id=1")
			.subjectMatchingRule("dept_id=")
			.parameterValue("dept_id3,Subject,dept_id"));
		
		vpManual("Properies",true, fourpage).performTest();
	}
}
//passed
