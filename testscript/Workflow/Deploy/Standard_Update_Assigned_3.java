package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.Standard_Update_Assigned_3Helper;
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
import component.entity.model.DeployedWorkFlow;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Standard_Update_Assigned_3 extends Standard_Update_Assigned_3Helper
{
	/**
	 * Script Name   : <b>Standard_Update_Assigned_3</b>
	 * Generated     : <b>Oct 10, 2011 7:46:33 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/10
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
				.unwiredServer("My Unwired Server")
				.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.setWorkFlow(new WorkFlow().name(Cfg.wfName).version("2"));
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->"+Cfg.wfName)
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToSelectedUser("DT")
				.verifyResult("Assigning workflow myWF to DT", true));
		vpManual("deploy", true, EE.ifWorkFlowDeployed(new DeployedWorkFlow().unwiredServer("My Unwired Server")
				.version("1")
				.name(Cfg.wfName)));
		vpManual("deploy", true, EE.ifWorkFlowDeployed(new DeployedWorkFlow().unwiredServer("My Unwired Server")
				.version("2")
				.name(Cfg.wfName)));
	}
}

