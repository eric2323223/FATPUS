package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Choice_RT_AndroidHelper;
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
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Ctrl_Choice_RT_Android extends Ctrl_Choice_RT_AndroidHelper
{
	/**
	 * Script Name   : <b>Ctrl_Choice_RT_Android</b>
	 * Generated     : <b>Aug 17, 2011 11:36:19 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/17
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"wf2").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "Start Screen", new WFChoice()
				.label("choice1")
				.labelPosition("RIGHT")
				.newKey("key1,decimal")
				.ifReadonly(false)
				.ifRequired(false)
				.logicalType("NUMERIC")
				.item("item1,1")
				.item("item2,2"));
		WorkFlowEditor.setWorkFlow(new WorkFlow().version("2"));
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "wf2.xbw"))
				.unwiredServer("My Unwired Server")
				.assignToUser("DeviceTest")
				.verifyResult("Successfully deployed the workflow", true));
	
//		TestResult result = Robot.run(this);
//		vpManual("DeviceTest", true, result.isPass()).performTest();
	}
}

