package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Time_Consistent_AndroidHelper;
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
import component.entity.model.DeployOption;
import component.entity.model.WFCheckbox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Time_Consistent_Android extends Time_Consistent_AndroidHelper
{
	/**
	 * Script Name   : <b>Time_Consistent_Android</b>
	 * Generated     : <b>Aug 23, 2011 10:05:21 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/23
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.type("TYPE_MBS")
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"wfTime_Consistent").option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "T_olc");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
			.name("findall")
			.type("Online Request")
			.mbo("wf/T_olc")
			.objectQuery("findAll")
			.defaultSuccessScreen("T_olc"));
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "wfTime_Consistent.xbw"))
				.unwiredServer("My Unwired Server")
				.assignToUser("DeviceTest")
				.verifyResult("Successfully deployed the workflow", true));
		
//		vpManual("DeviceTest", true, Robot.run(this).isPass()).performTest();
	}
}

