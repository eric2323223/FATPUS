package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CS_EmailSub_One2Many_Cascade_Parent_SOHelper;
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
public class CS_EmailSub_One2Many_Cascade_Parent_SO extends CS_EmailSub_One2Many_Cascade_Parent_SOHelper
{
	/**
	 * Script Name   : <b>CS_EmailSub_One2Many_Cascade_Parent_SO</b>
	 * Generated     : <b>Sep 8, 2011 10:49:40 PM</b>
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
				.objectQuery("findByPrimaryKey")
				.subject("dept_id")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("dept_id,Subject,dept_id="));
		
		vpManual("creen", true, WorkFlowEditor.hasScreen("Department")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("DepartmentDetail")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Departmentcreate")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Departmentdeleteinstance")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Departmentupdateinstance")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Employee")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("EmployeeDetail")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Employeeadd")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Employeeupdateinstance")).performTest();
//		vpManual("link", false, WorkFlowEditor.hasLinkBetween("Server-initiated (Department)","Department")).performTest();
//		vpManual("link", false, WorkFlowEditor.hasLinkBetween("Department","DepartmentDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("DepartmentDetail","Employee")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Employee","EmployeeDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("EmployeeDetail","Employeeupdateinstance")).performTest();
	}
}
//passed3
