package testscript.Workflow.Controls;

import resources.testscript.Workflow.Controls.Ctrl_Editbox_RT_AndroidHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFEditBox;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description : Functional Test Script
 * 
 * @author eric
 */
public class Ctrl_Editbox_RT_Android extends Ctrl_Editbox_RT_AndroidHelper {
	/**
	 * Script Name : <b>Ctrl_Editbox_RT_Android</b> Generated : <b>Aug 16, 2011
	 * 1:28:00 AM</b> Description : Functional Test Script Original Host : WinNT
	 * Version 5.1 Build 2600 (S)
	 * 
	 * @since 2011/08/16
	 * @author eric
	 */
	public void testMain(Object[] args) {
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"wf").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addEditBox(Cfg.projectName, "wf.xbw", "Start Screen", new WFEditBox().label("ckbox1")
				.labelPosition("TOP")
				.logicalType("TEXT")
				.newKey("key1,string")
				.ifReadonly(false)
				.password(true)
				.maxLength("20")
				.ifRequired(true)
				.lines("2")
				.validationExpression("expres{2}ion\\d+")
				.validationMessage("Please input a validate expression(expression1)")
				);
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "wf"))
				.unwiredServer("My Unwired Server")
				.assignToUser("DT")
				.verifyResult("Successfully deployed the workflow", true));
		
		TestResult result = Robot.run(this);
		vpManual("DeviceTest", true, result.isPass()).performTest();
	}
}
