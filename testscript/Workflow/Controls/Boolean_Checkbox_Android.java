package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Boolean_Checkbox_AndroidHelper;
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
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFCheckbox;
import component.entity.model.WFNewscreen;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Boolean_Checkbox_Android extends Boolean_Checkbox_AndroidHelper
{
	/**
	 * Script Name   : <b>Boolean_Checkbox_Android</b>
	 * Generated     : <b>Sep 5, 2011 1:44:00 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/05
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
////		EE.runSQL(new ScrapbookCP().type("Sybase_ASA_12.x")
////				.name("My Sample Database")
////				.database("sampledb"), Cfg.create_table_ff);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ff_wf_employee (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfboolcheckbox")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Ff_wf_employee")
				.objectQuery("findByPrimaryKey")
				.subjectMatchingRule("emp")
				.subject("emp0")
				.setParameterValue("emp_id,Subject,emp"));
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "wfboolcheckbox"))
			.unwiredServer("My Unwired Server")
			.assignToUser("DeviceTest")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "wfboolcheckbox.xbw", new Email()
				.unwiredServer("My Unwired Server")
		        .bcc("DeviceTest")
				.cc("DeviceTest")
				.subject("emp2")
				.to("DeviceTest")
				);
		//ok
	}
}

