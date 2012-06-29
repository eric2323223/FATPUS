package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.AutoAssign_7Helper;
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
import component.entity.model.UnwiredServerCP;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class AutoAssign_7 extends AutoAssign_7Helper
{
	/**
	 * Script Name   : <b>AutoAssign_7</b>
	 * Generated     : <b>Sep 29, 2011 11:20:14 PM</b>
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
				.unwiredServer("CleanServer")
				.verifyResult("Unable to connect to the Unwired Server InvalidServer", false));
		//TBD
	}
}

