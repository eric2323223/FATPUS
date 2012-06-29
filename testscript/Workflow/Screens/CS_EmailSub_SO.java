package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CS_EmailSub_SOHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ObjectQuery;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CS_EmailSub_SO extends CS_EmailSub_SOHelper
{
	/**
	 * Script Name   : <b>CS_EmailSub_SO</b>
	 * Generated     : <b>Sep 8, 2011 3:16:34 AM</b>
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
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("dept_id=1")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("dept_id,Subject,dept_id="));
		
		vpManual("screen", true, WorkFlowEditor.hasScreen("Department")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("DepartmentDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Departmentcreate")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Departmentdeleteinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Departmentupdateinstance")).performTest();
//		vpManual("link", false, WorkFlowEditor.hasLinkBetween("Server-initiated (Department)", "Department")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Server-initiated (Department)", "DepartmentDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("DepartmentDetail", "Departmentupdateinstance")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("DepartmentDetail", "Departmentdeleteinstance")).performTest();
	}
}

