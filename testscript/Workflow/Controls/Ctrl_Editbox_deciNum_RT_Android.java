package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Editbox_deciNum_RT_AndroidHelper;
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
import component.entity.model.DeployedMbo;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Ctrl_Editbox_deciNum_RT_Android extends Ctrl_Editbox_deciNum_RT_AndroidHelper
{
	/**
	 * Script Name   : <b>Ctrl_Editbox_deciNum_RT_Android</b>
	 * Generated     : <b>Sep 2, 2011 4:05:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/02
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->T_ALLdatatypesa (dbo)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,simpledb"));
		vpManual("deploy", true, EE.ifMboDeployed(new DeployedMbo()
					.connectionProfile("My Unwired Server")
					.domain("default")
					.name("T_ALLdatatypesa")
					.pkg(Cfg.projectName+":1.0"))).performTest();
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
					"wf_deciNum").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "T_ALLdatatypesa");
		WorkFlowEditor.link("Start Screen", "T_ALLdatatypesa_create");
		

		WN.createWorkFlowPackage(new WorkFlowPackage()
		.startParameter(WN.wfPath(Cfg.projectName, "wf_deciNum.xbw"))
		.unwiredServer("My Unwired Server")
		.assignToUser("DeviceTest")
		.verifyResult("Successfully deployed the workflow", true));
		
	// only need to input data in C7 (data type is decimal)to verify... in device.
		
//		vpManual("DeviceTest", true, Robot.run(this).isPass()).performTest();
	}
}

