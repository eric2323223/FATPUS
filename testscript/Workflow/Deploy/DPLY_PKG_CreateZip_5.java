package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.DPLY_PKG_CreateZip_5Helper;
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
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.DeployedWorkFlow;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DPLY_PKG_CreateZip_5 extends DPLY_PKG_CreateZip_5Helper
{
	/**
	 * Script Name   : <b>DPLY_PKG_CreateZip_5</b>
	 * Generated     : <b>Sep 29, 2011 2:05:36 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/29
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->"+Cfg.wfName)
				.externalFolder(MainMenu.getCurrentWorkspace()+"\\"+Cfg.projectName+"\\GW")
				.deployToServer("true")
				.assignToUser("DT")
				.validateControls("true")
				.saveConfiguration("config1")
				.unwiredServer("My Unwired Server"));
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
		EE.deleteWorkFlow(new DeployedWorkFlow().unwiredServer("My Unwired Server").version("1").name(Cfg.wfName));
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(Cfg.projectName+"->"+Cfg.wfName)
//				.externalFolder(MainMenu.getCurrentWorkspace()+"\\"+Cfg.projectName+"\\GW")
				.selectConfiguration("config1"));
//		TBD

	}
}

