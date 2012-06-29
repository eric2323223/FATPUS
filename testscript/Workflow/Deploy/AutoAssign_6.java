package testscript.Workflow.Deploy;
import com.sybase.automation.framework.widget.DOF;

import resources.testscript.Workflow.Deploy.AutoAssign_6Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.UnwiredServer;
import component.entity.model.UnwiredServerCP;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class AutoAssign_6 extends AutoAssign_6Helper
{
	/**
	 * Script Name   : <b>AutoAssign_6</b>
	 * Generated     : <b>Sep 29, 2011 11:11:19 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/29
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.createUnwiredServerCP(new UnwiredServerCP()
				.name("InvalidServer")
				.host("unknown"), true);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->"+Cfg.wfName)
				.unwiredServer("InvalidServer")
				.verifyResult("Unable to connect to the Unwired Server InvalidServer", false));
		
		DOF.getButton(DOF.getDialog("New Mobile Workflow Package Generation"), "Cancel").click();
	}
}

