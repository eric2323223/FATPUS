package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_641211_Choice_Complex_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.ObjectQuery;
import component.entity.model.WFChoice;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_641211_Choice_Complex_E2E extends Ctrl_641211_Choice_Complex_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_641211_Choice_Complex_E2E</b>
	 * Generated     : <b>Dec 16, 2011 1:31:36 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/16
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 100, 10);
		
		ObjectQuery oq = new ObjectQuery().name("findByheadid")
			.parameter("dept_head_id,INT,true,dept_head_id")
			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_head_id = :dept_head_id")
			.returnType(ObjectQueryWizard.RT_MULTIPLE)
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Department");
		new ObjectQueryWizard().create(oq, new WizardRunner()); 
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findAll")
				.subject("dept=1")
				.subjectMatchingRule("dept=")
				);
		
		WorkFlowEditor.addScreen("A");
		WorkFlowEditor.addScreen("B");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "A");
	
		WorkFlowEditor.showPropertiesViewInSD("A");
		PropertiesView.setNewKeyBindMBOQueryRequest("key3,list,Department,dept_head_id,dept_name");
		
		WorkFlowEditor.addWidget("A",new WFChoice().label("dept_head_id:")
				.newKey("headid,int")
				.option("Dynamic,key3.key1,key3.key2"));
		
		WorkFlowEditor.addMenuItem("A",new WFScreenMenuItem().name("findByheadid")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByheadid")
				.defaultSuccessScreen("B")
				.parametermapping("dept_head_id,headid"));
		
		WorkFlowEditor.addWidget("B",new WFChoice().label("dept_id:")
				.newKey("deptid,int")
				.option("Dynamic,Department.Department_dept_id_attribKey,Department.Department_dept_id_attribKey"));
		
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("dept=1")
			.to("dt")
			.unwiredServer("My Unwired Server"));
		
		////1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_641211_Choice_Complex.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		////2.used to BB6T:passed
		TestResult resultBB= Robot.run("tplan.Workflow.Controls.BB.Ctrl_641211_Choice_Complex.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
	}
}

