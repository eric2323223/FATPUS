package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.DPLY_PKG_CreateZip_2Helper;
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

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DPLY_PKG_CreateZip_2 extends DPLY_PKG_CreateZip_2Helper
{
	/**
	 * Script Name   : <b>DPLY_PKG_CreateZip_2</b>
	 * Generated     : <b>Sep 27, 2011 1:39:57 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/27
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
				.externalFolder(MainMenu.getCurrentWorkspace()+"\\"+Cfg.projectName+"\\GW")
				.deployToServer("false")
				.unwiredServer("My Unwired Server"));

		vpManual("zipFile", false, WN.hasGeneratedWorkFlowFile(Cfg.projectName, Cfg.wfName, Cfg.wfName+".zip")).performTest();
		vpManual("manifest", false, WN.hasGeneratedWorkFlowFile(Cfg.projectName, Cfg.wfName, "manifest.xml")).performTest();
		vpManual("workflowclient", false, WN.hasGeneratedWorkFlowFile(Cfg.projectName, Cfg.wfName, "WorkflowClient.xml")).performTest();
		vpManual("zipFile", true, WN.hasFile(Cfg.projectName, "GW->myWF->myWF.zip")).performTest();
		vpManual("manifest", true, WN.hasFile(Cfg.projectName, "GW->myWF->manifest.xml")).performTest();
		vpManual("workflowclient", true, WN.hasFile(Cfg.projectName, "GW->myWF->WorkflowClient.xml")).performTest();
	}
}

