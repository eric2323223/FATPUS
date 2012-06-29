package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_DB_mbo_relationshipHelper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Relationship;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_DB_mbo_relationship extends SP_Email_DB_mbo_relationshipHelper
{
	/**
	 * Script Name   : <b>SP_Email_DB_mbo_relationship</b>
	 * Generated     : <b>Sep 13, 2011 3:20:22 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/13
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->employee (dba)"), 100, 10);
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("employees")
				.mapping("dept_id,dept_id")
				.composite("true")
				.target("Employee"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT).mbo("Department"));
		PropertiesView.setEmailMbo("Department");
		PropertiesView.clickTab("Keys");
		PropertiesView.setNewKeyBindMBORelationship("WorkFlow.SP_SERVER_INIT,email_key_mbo_relationship,list,Department,employees,emp_id");
		vpManual("Properies", "email_key_mbo_relationship,list,Department/employees", PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey()).performTest();
		
		PropertiesView.setKeyUserDefined("email_key_mbo_relationship");
		vpManual("Properies", "email_key_mbo_relationship,list,", PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey()).performTest();
	}
}
//passed
