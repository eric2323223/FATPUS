package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.Initial_New_NoAssignHelper;
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
import component.entity.model.DeployedWorkFlow;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Initial_New_NoAssign extends Initial_New_NoAssignHelper
{
	/**
	 * Script Name   : <b>Initial_New_NoAssign</b>
	 * Generated     : <b>Oct 9, 2011 11:35:30 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/09
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.deleteAllWorkFlowsFromUnwiredServer("My Unwired Server");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->"+Cfg.wfName)
				.deployToServer("true")
				.assignToSelectedUser("DT")
				.unwiredServer("My Unwired Server")
				.verifyResult("Assigning workflow myWF to DT", true));

		vpManual("deploy", true, EE.ifWorkFlowDeployed(new DeployedWorkFlow().unwiredServer("My Unwired Server")
				.version("1")
				.name(Cfg.wfName)));
	
	}
}

