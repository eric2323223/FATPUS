package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_LView_OnEmptyList_2_AndroidHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFLview;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Ctrl_LView_OnEmptyList_2_Android extends Ctrl_LView_OnEmptyList_2_AndroidHelper
{
	/**
	 * Script Name   : <b>Ctrl_LView_OnEmptyList_2_Android</b>
	 * Generated     : <b>Aug 23, 2011 9:39:00 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/23
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"wflviewempty2").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addWidget(Cfg.projectName, "wflviewempty2.xbw", "Start Screen", new WFLview()
				.newKey("key1,list")
				.emptyMessage("~!@#$%^&*()+<>?/"));
		WorkFlowEditor.setWorkFlow(new WorkFlow().version("2"));
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "wflviewempty2.xbw"))
				.unwiredServer("My Unwired Server")
				.assignToUser("DeviceTest")
				.verifyResult("Successfully deployed the workflow", true));
		
//		TestResult result = Robot.run(this);
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		
		
		
		
	}
}

