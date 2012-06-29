package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CS_EmailSub_One2Many_Cascade_Parent_MOHelper;
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
public class CS_EmailSub_One2Many_Cascade_Parent_MO extends CS_EmailSub_One2Many_Cascade_Parent_MOHelper
{
	/**
	 * Script Name   : <b>CS_EmailSub_One2Many_Cascade_Parent_MO</b>
	 * Generated     : <b>Sep 8, 2011 11:03:18 PM</b>
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
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		vpManual("screen", true, WorkFlowEditor.hasScreen("Department")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("DepartmentDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Departmentcreate")).performTest();
		vpManual("screen", false, WorkFlowEditor.hasScreen("Departmentdelete")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Departmentdeleteinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Departmentupdateinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Employee")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("EmployeeDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Employeeadd")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Employeeupdateinstance")).performTest();
		
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Server-initiated (Department)","Department")).performTest();
//		vpManual("link", false, WorkFlowEditor.hasLinkBetween("Server-initiated (Department)","DepartmentDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Department","DepartmentDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("DepartmentDetail","Employee")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Employee","EmployeeDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("EmployeeDetail","Employeeupdateinstance")).performTest();
	}
}
//passed3
