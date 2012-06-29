package testscript.Workflow.EmailInjection;
import resources.testscript.Workflow.EmailInjection.EmailInjection_Profile_4Helper;
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

import component.entity.ASACPCreationWizard;
import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ASACP;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.UnwiredServerCP;
import component.entity.model.VerificationCriteria;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class EmailInjection_Profile_4 extends EmailInjection_Profile_4Helper
{
	/**
	 * Script Name   : <b>EmailInjection_Profile_4</b>
	 * Generated     : <b>Sep 20, 2011 10:53:14 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/20
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.subject("test")
				.objectQuery("findAll"));
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->"+Cfg.wfName)
				.unwiredServer("My Unwired Server")
				.assignToUser(Cfg.deviceUser)
				.deployToServer("true"));
		EE.deleteCP("My Unwired Server");
		
		 WorkFlowEditor.sendNotification(new Email()
				.cc(Cfg.deviceUser)
				.verifyMessage(" Specify a value for the Unwired Server profile",false));
		 
		//create My unwired server CP:
			EE.createUnwiredServerCP(new UnwiredServerCP()
					.name("My Unwired Server")
					.host(Cfg.hostName)
					.password("s3pAdmin")
					,false);
	}
}
//passed
