package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CSFO_Many2One_ParentHelper;
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
import component.entity.model.Relationship;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CSFO_Many2One_Parent extends CSFO_Many2One_ParentHelper
{
	/**
	 * Script Name   : <b>CSFO_Many2One_Parent</b>
	 * Generated     : <b>Sep 15, 2011 3:51:40 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/15
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Employee"))
			.target("Department")
			.mapping("dept_id,dept_id")
			.type(Relationship.TYPE_MTO));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Employee");
		
		vpManual("creen", false, WorkFlowEditor.hasScreen("Department")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Employeeupdateinstance")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Employeedeleteinstance")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("EmployeeDetail")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Employee")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Employeecreate")).performTest();
	}
}
//passed
