package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.workflow_Wizard_ServerProfileHelper;
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
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.model.DeployOption;
import component.entity.model.DeployedWorkFlow;
import component.entity.model.Relationship;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class workflow_Wizard_ServerProfile extends workflow_Wizard_ServerProfileHelper
{
	/**
	 * Script Name   : <b>workflow_Wizard_ServerProfile</b>
	 * Generated     : <b>Sep 26, 2011 11:00:12 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/26
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->"+Cfg.wfName)
				.deployToServer("true")
				.unwiredServer("My Unwired Server"));
		vpManual("deploy", true, EE.ifWorkFlowDeployed(new DeployedWorkFlow()
				.name(Cfg.wfName)
				.unwiredServer("My Unwired Server")
				.version("1"))).performTest();
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

