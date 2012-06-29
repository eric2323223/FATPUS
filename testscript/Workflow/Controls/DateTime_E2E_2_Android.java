package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.DateTime_E2E_2_AndroidHelper;
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
import component.entity.model.DeployOption;
import component.entity.model.ScrapbookCP;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DateTime_E2E_2_Android extends DateTime_E2E_2_AndroidHelper
{
	/**
	 * Script Name   : <b>DateTime_E2E_2_Android</b>
	 * Generated     : <b>Sep 5, 2011 12:51:04 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/05
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"wf_dateTime_2").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "T_olc");
		WorkFlowEditor.link("Start Screen", "T_olcDetail");
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "wf_dateTime_2.xbw"))
				.unwiredServer("My Unwired Server")
				.assignToUser("DeviceTest")
				.verifyResult("Successfully deployed the workflow", true));
//ET: is ok, device: only need to click the datatime cell to open detail to verify the value is same as the value in listview datatime cell
		
		
		
	}
}

