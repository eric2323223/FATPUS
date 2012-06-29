package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S670609_DatePicker_hide_E2EHelper;
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
import component.entity.LongOperationMonitor;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFLabel;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S670609_DatePicker_hide_E2E extends S670609_DatePicker_hide_E2EHelper
{
	/**
	 * Script Name   : <b>S670609_DatePicker_hide_BB_E2E</b>
	 * Generated     : <b>Mar 14, 2012 2:15:57 AM</b>
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
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragOperation(Cfg.projectName, "AllDT", "create");
		WorkFlowEditor.link("Start", "AllDTcreate");
		WorkFlowEditor.link("AllDTcreate","Start");
		WorkFlowEditor.addWidget("Start", new WFLabel().name("label"));
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(Cfg.projectName+"->"+Cfg.wfName)
			.unwiredServer("My Unwired Server")
			.assignToUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true));
		
	//1.used to Android:passed 
//		TestResult resultAndroid = Robot.run("tplan.Workflow.Supplement.Android.S670609_DatePicker_hide_Android.Script");
//		vpManual("DeviceTest", true, resultAndroid.isPass()).performTest();
		
	//BB6T :passed
		TestResult result = Robot.run("tplan.Workflow.Supplement.BB.S670609_DatePicker_hide_BB.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
	}
}
//passed BB6T and Android 20120316