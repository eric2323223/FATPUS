package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.DPLY_PKG_CreateZip_3Helper;
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
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DPLY_PKG_CreateZip_3 extends DPLY_PKG_CreateZip_3Helper
{
	/**
	 * Script Name   : <b>DPLY_PKG_CreateZip_3</b>
	 * Generated     : <b>Sep 28, 2011 11:42:38 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/28
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
				.unwiredServer("My Unwired Server"));
		vpManual("noError", 0, Problems.getErrors().size()).performTest();

	}
}

