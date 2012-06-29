package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Signature_RT_AndroidHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.WFNewscreen;
import component.entity.model.WFSignature;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Ctrl_Signature_RT_Android extends Ctrl_Signature_RT_AndroidHelper
{
	/**
	 * Script Name   : <b>Ctrl_Signature_RT_Android</b>
	 * Generated     : <b>Aug 22, 2011 10:18:51 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/22
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_department (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,simpledb"));
		vpManual("deploy", true, EE.ifMboDeployed(new DeployedMbo()
					.connectionProfile("My Unwired Server")
					.domain("default")
					.name("Ff_wf_department")
					.pkg(Cfg.projectName+":1.0"))).performTest();
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
			"wfsignature").option(WorkFlow.SP_SERVER_INIT));

		PropertiesView.jumpStart(new WorkFlow()
				.mbo("Ff_wf_department")
				.objectQuery("findByPrimaryKey")
				.subject("dept1")
				.subjectMatchingRule("dept")   
				.setParameterValue("dept_id,Subject,dept"));

		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
		WorkFlowEditor.addWidget(Cfg.projectName, "wfsignature.xbw", "linktoemail", new WFSignature().label("dept_sign:")
			.labelPosition("LEFT")
			.newKeyBindMbo("key1,string,Ff_wf_department,dept_sign"));

		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "wfsignature"))
			.unwiredServer("My Unwired Server")
			.assignToUser("DeviceTest")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "wfsignature.xbw", new Email()
				.bcc("DeviceTest")
			.cc("DeviceTest")
			.subject("sign3")
			.to("DeviceTest")
			.unwiredServer("My Unwired Server"));
	
//		TestResult result = Robot.run(this);
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		
	}
}

