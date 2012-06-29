package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CSFO_ReCreate_From_ParentChildHelper;
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
public class CSFO_ReCreate_From_ParentChild extends CSFO_ReCreate_From_ParentChildHelper
{
	/**
	 * Script Name   : <b>CSFO_ReCreate_From_ParentChild</b>
	 * Generated     : <b>Sep 8, 2011 3:04:51 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/08
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("Employee")
			.mapping("dept_id,dept_id")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		vpManual("screen", true, WorkFlowEditor.hasScreen("Department")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("DepartmentDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Departmentcreate")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Departmentdeleteinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Departmentupdateinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Employee")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("EmployeeDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Employeeadd")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Employeeupdateinstance")).performTest();
		WorkFlowEditor.dragMbo(Cfg.projectName, "Employee");
		vpManual("screen", true, WorkFlowEditor.hasScreen("Employee1")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("EmployeeDetail1")).performTest();
		vpManual("screen", false, WorkFlowEditor.hasScreen("Employeeadd1")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Employeeadd")).performTest();
	}
}
//passed17
