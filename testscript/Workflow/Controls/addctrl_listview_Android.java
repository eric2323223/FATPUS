package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.addctrl_listview_AndroidHelper;
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
import component.entity.model.DeployedMbo;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class addctrl_listview_Android extends addctrl_listview_AndroidHelper
{
	/**
	 * Script Name   : <b>addctrl_listview_Android</b>
	 * Generated     : <b>Aug 31, 2011 2:38:30 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/31
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,simpledb"));
		vpManual("deploy", true, EE.ifMboDeployed(new DeployedMbo()
				.connectionProfile("My Unwired Server")
				.domain("default")
				.name("Department")
				.pkg(Cfg.projectName+":1.0"))).performTest();
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfaddctrlist")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addWidget(Cfg.projectName, "wfaddctrlist.xbw", "Department", new WFEditBox().label("dept_id2")
				.newKey("key1,string"));
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.mbo("wf/Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "wfaddctrlist.xbw"))
			.unwiredServer("My Unwired Server")
			.assignToUser("DeviceTest")
			.verifyResult("Successfully deployed the workflow", true));
		
        // only need to see menu->findall->end of record have Editbox "dept_id2"
		
//		TestResult result = Robot.run(this);
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		
	}
}

