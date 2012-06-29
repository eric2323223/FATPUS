package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S666988_CloseChoice_1_E2EHelper;
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
public class S666988_CloseChoice_1_E2E extends S666988_CloseChoice_1_E2EHelper
{
	/**
	 * Script Name   : <b>S666988_CloseChoice_BB6_touch_1</b>
	 * Generated     : <b>Mar 13, 2012 8:06:56 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/13
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow()
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WorkFlowEditor.addScreen("myscreen1");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "myscreen1");
		
		WorkFlowEditor.showPropertiesViewInSD("myscreen1");
		PropertiesView.setNewKeyBindMBOQueryRequest("key2,list,Department,dept_name");
		WorkFlowEditor.addWidget("myscreen1", new WFChoice().label("id:")
				.newKey("id,int")
				.option("Dynamic,key2.key1,key2.key1")
				.ifRequired(true));
		
		WorkFlowEditor.addWidget("myscreen1", new WFEditBox().label("name:")
				.newKey("n,string"));
		
		WorkFlowEditor.addWidget("myscreen1", new WFEditBox().label("head_id:")
				.newKey("head_id,int"));
		
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(Cfg.projectName+"->"+Cfg.wfName)
			.unwiredServer("My Unwired Server")
			.assignToUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true)
			);
		WorkFlowEditor.sendNotification(Cfg.projectName, Cfg.wfName+".xbw", new Email()
			.subject("findall")
			.to(Cfg.deviceUser)
			.unwiredServer("My Unwired Server"));
		
		//BB6T :passed
	TestResult resultBB = Robot.run("tplan.Workflow.Supplement.BB.S666988_CloseChoice_BB6_touch_1.Script");
	vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
//		//Android :passed
//	TestResult resultAndroid = Robot.run("tplan.Workflow.Supplement.Android.S666988_CloseChoice_Android_1.Script");
//	vpManual("DeviceTest", true, resultAndroid.isPass()).performTest();		
	}
}
//passed BB6T and Android 20120315