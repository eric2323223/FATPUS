package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Dynachoice_E2E_01Helper;
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
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.WFChoice;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Dynachoice_E2E_01 extends Dynachoice_E2E_01Helper
{
	/**
	 * Script Name   : <b>Dynachoice_E2E_01</b>
	 * Generated     : <b>Nov 1, 2011 9:42:21 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/01
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("Employee")
			.mapping("dept_id,dept_id")
			.composite("true")
			.bidirectional("true")
			.type(Relationship.TYPE_OTM));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow()
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("dept=1")
				.subjectMatchingRule("dept=")
				.setParameterValue("dept_id,Subject,dept="));
		
		WorkFlowEditor.addScreen("myscreen");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"myscreen");
		
		PropertiesView.setNewKeyBindMBORelationship("Screen Design,key2,list,Department,employees,emp_id");
		
		WorkFlowEditor.addWidget("myscreen", new WFChoice().label("emp_id:")
				.newKey("emp_id,int")
				.option("Dynamic,key2.key1,key2.key1")
			);
		
		MainMenu.saveAll();
		
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
//			.assignToUser("DT")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("dept=100")
			.to("DT")
			.unwiredServer("My Unwired Server"));
		
//	//1.used to Android:passed
//	TestResult result = Robot.run("tplan.Workflow.Controls.android.Dynachoice_E2E_01.Script");
//	vpManual("DeviceTest", true, result.isPass()).performTest();
	
	//2.used to BB:passed
	TestResult result = Robot.run("tplan.Workflow.Controls.BB.Dynachoice_E2E_01.Script");
	vpManual("DeviceTest", true, result.isPass()).performTest();
		
	}
}
//Passed with TPlan
