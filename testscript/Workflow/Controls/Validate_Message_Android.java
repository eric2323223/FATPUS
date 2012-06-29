package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Validate_Message_AndroidHelper;
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
import component.entity.model.Relationship;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WFSlider;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Validate_Message_Android extends Validate_Message_AndroidHelper
{
	/**
	 * Script Name   : <b>Validate_Message_Android</b>
	 * Generated     : <b>Aug 31, 2011 2:10:59 PM</b>
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
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start Screen", new WFSlider().label("id:")
				.labelPosition("LEFT")
				.newKey("key1,int")
				.maxValue("20")
				.minValue("0"));
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start Screen", new WFSlider().label("head:")
				.labelPosition("LEFT")
				.newKey("key2,int")
				.maxValue("30")
				.minValue("0"));
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start Screen", new WFEditBox().label("name:")
				.labelPosition("LEFT")
				.newKey("key3,string"));
		String[] parameter={"dept_id,key1","dept_head_id,key2","dept_name,key3"};
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("create")
				.type("Online Request")
				.mbo("wf/Department")
				.operation("create")
				.parametermapping(parameter));
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "wfmbocreate.xbw"))
			.unwiredServer("My Unwired Server")
			.assignToUser("DeviceTest")
			.verifyResult("Successfully deployed the workflow", true));
		
		// only need to input invalide value in android
//		TestResult result = Robot.run(this);
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
	}
}

