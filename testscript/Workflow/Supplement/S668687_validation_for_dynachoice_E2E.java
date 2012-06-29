package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S668687_validation_for_dynachoice_E2EHelper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S668687_validation_for_dynachoice_E2E extends S668687_validation_for_dynachoice_E2EHelper
{
	/**
	 * Script Name   : <b>S668687_validation_for_dynachoice_E2E</b>
	 * Generated     : <b>Mar 13, 2012 3:48:23 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/13
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)",10,10);
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.mode(DeployOption.MODE_REPLACE)
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.option(WorkFlow.SP_SERVER_INIT)
//				.name("myWF")
//				.mbo("Department")
//				.objectQuery("findAll")
//				.subject("findall")
//				.subjectMatchingRule("findall"));
//		
//		WorkFlowEditor.addScreen("myscreen1");
//		WorkFlowEditor.link("Department", "myscreen1");
//		WorkFlowEditor.showPropertiesViewInSD("myscreen1");
//		PropertiesView.setNewKeyBindMBOQueryRequest("key2,list,Department,dept_head_id");
//		
//		WorkFlowEditor.addWidget("myscreen1", new WFEditBox().label("id:")
//				.newKey("id,int"));
		
//		WorkFlowEditor.addWidget("myscreen1", new WFEditBox().label("name:")
//				.newKey("namekey,string"));
//		
//		WorkFlowEditor.addWidget("myscreen1", new WFChoice().label("head_id:")
//				.newKey("head_id,int")
//				.ifRequired(true)
//				.validationMessage("this is my validation Message")
//				.option("Dynamic,key2.key1,key2.key1"));
//		
//		//set myscreen1:
//		WorkFlowEditor.addMenuItem("myscreen1", new WFScreenMenuItem().name("create")
//				.type("Submit Workflow")
//				.project(Cfg.projectName)
//				.mbo("Department")
//				.operation("create")
//				.parametermapping("dept_id,id")
//				.parametermapping("dept_name,namekey")
//				.parametermapping("dept_head_id,head_id"));
//		
//		MainMenu.saveAll();
//		
//		WN.createWorkFlowPackage(new WorkFlowPackage()
//			.startParameter(Cfg.projectName+"->"+Cfg.wfName)
//			.unwiredServer("My Unwired Server")
//			.assignToUser(Cfg.deviceUser)
//			.verifyResult("Successfully deployed the workflow", true));
//		WorkFlowEditor.sendNotification(Cfg.projectName, Cfg.wfName+".xbw", new Email()
//			.subject("findall")
//			.to(Cfg.deviceUser)
//			.unwiredServer("My Unwired Server"));
//		
//////1.used to Android:passed
////	TestResult resultAndroid = Robot.run("tplan.Workflow.Supplement.Android.S668687_validation_for_dynachoice_Android.Script");
////	vpManual("DeviceTest", true, resultAndroid.isPass()).performTest();
//
//		
////2. BB6T:
	TestResult resultBB = Robot.run("tplan.Workflow.Supplement.BB.S668687_validation_for_dynachoice_BB.Script");
	vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
	}
}

