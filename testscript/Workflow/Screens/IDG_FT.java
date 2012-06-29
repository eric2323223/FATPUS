package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.IDG_FTHelper;
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
public class IDG_FT extends IDG_FTHelper
{
	/**
	 * Script Name   : <b>IDG_FT</b>
	 * Generated     : <b>Sep 8, 2011 11:32:40 PM</b>
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
				.option(WorkFlow.SP_CLIENT_INIT)
				.name("myWF"));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		vpManual("hasmenu",true,WorkFlowEditor.hasMenuItemInScreen("EmployeeDetail","Employeedeleteinstance"));
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("EmployeeDetail","Employeeupdateinstance")).performTest();
	}
}
//passed20
