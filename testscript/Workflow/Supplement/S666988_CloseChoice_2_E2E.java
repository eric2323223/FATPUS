package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S666988_CloseChoice_2_E2EHelper;
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
import component.entity.GlobalConfig;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFChoice;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S666988_CloseChoice_2_E2E extends S666988_CloseChoice_2_E2EHelper
{
	/**
	 * Script Name   : <b>S666988_CloseChoice_BB6_touch_2_E2E</b>
	 * Generated     : <b>Mar 14, 2012 2:51:29 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/14
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
		
		WorkFlowEditor.addWidget("myscreen1", new WFChoice().label("id1:")
				.newKey("id1,int")
				.option("Static,A1,1:,B1,2:,C1,5")
				);
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(Cfg.projectName+"->"+Cfg.wfName)
			.unwiredServer("My Unwired Server")
			.assignToUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, Cfg.wfName+".xbw", new Email()
			.subject("findall")
			.to(Cfg.deviceUser)
			.unwiredServer("My Unwired Server"));
		
		//BB6T :passed
		TestResult result = Robot.run("tplan.Workflow.Supplement.BB.S666988_CloseChoice_BB6_touch_2.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
		
//		//Android :passed
//		TestResult resultAndroid = Robot.run("tplan.Workflow.Supplement.Android.S666988_CloseChoice_Android_2.Script");
//		vpManual("DeviceTest", true, resultAndroid.isPass()).performTest();	
	}
}
//passed BB6T and Android 20120319