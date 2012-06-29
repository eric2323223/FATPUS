package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CS_EmailSub_MOHelper;
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
import component.entity.model.ObjectQuery;
import component.entity.model.WizardRunner;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CS_EmailSub_MO extends CS_EmailSub_MOHelper
{
	/**
	 * Script Name   : <b>CS_EmailSub_MO</b>
	 * Generated     : <b>Sep 8, 2011 10:22:01 PM</b>
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
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll"));
		
		vpManual("creen", true, WorkFlowEditor.hasScreen("Department")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("DepartmentDetail")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Departmentcreate")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Departmentdeleteinstance")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Departmentupdateinstance")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Server-initiated (Department)", "Department")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Department", "DepartmentDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("DepartmentDetail", "Departmentupdateinstance")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("DepartmentDetail", "Departmentdeleteinstance")).performTest();
	}
}
//passed3 