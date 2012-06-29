package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S670127_NoDatePicker_for_readonly_E2EHelper;
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
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S670127_NoDatePicker_for_readonly_E2E extends S670127_NoDatePicker_for_readonly_E2EHelper
{
	/**
	 * Script Name   : <b>S670127_NoDatePicker_for_readonly_BB_E2E</b>
	 * Generated     : <b>Mar 15, 2012 12:45:58 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/15
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)",10,10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		PropertiesView.jumpStart(new WorkFlow().mbo("AllDT")
				.objectQuery("findByPrimaryKey")
				.subject("int=1")
				.subjectMatchingRule("int=")
				.setParameterValue("int1,Subject,int="));
		WorkFlowEditor.dragOperation(Cfg.projectName, "AllDT", "update");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "AllDTupdateinstance");
		
		PropertiesView.resetWidget("AllDTupdateinstance", new WFEditBox().label("Date1:").ifReadonly(true));
		PropertiesView.resetWidget("AllDTupdateinstance", new WFEditBox().label("Datetime1:").ifReadonly(true));
		PropertiesView.resetWidget("AllDTupdateinstance", new WFEditBox().label("Time1:").ifReadonly(true));
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(Cfg.projectName+"->"+Cfg.wfName)
			.unwiredServer("My Unwired Server")
			.assignToUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, Cfg.wfName+".xbw", new Email()
			.subject("int=1")
			.to(Cfg.deviceUser)
			.unwiredServer("My Unwired Server"));
	
//    //1.used to Android:passed 
//		TestResult resultAndroid = Robot.run("tplan.Workflow.Supplement.Android.S670127_NoDatePicker_for_readonly_Android.Script");
//		vpManual("DeviceTest", true, resultAndroid.isPass()).performTest();
	
	//2. BB6T :passed
	TestResult result = Robot.run("tplan.Workflow.Supplement.BB.S670127_NoDatePicker_for_readonly_BB.Script");
	vpManual("DeviceTest", true, result.isPass()).performTest();
	
}
}
//passed BB6T and Android 20120315
