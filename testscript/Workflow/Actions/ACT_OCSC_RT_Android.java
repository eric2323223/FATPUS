package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_OCSC_RT_AndroidHelper;
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
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class ACT_OCSC_RT_Android extends ACT_OCSC_RT_AndroidHelper
{
	/**
	 * Script Name   : <b>ACT_OCSC_RT_Android</b>
	 * Generated     : <b>Oct 24, 2011 4:15:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/24
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		//
		WorkFlowEditor.addScreen("OpenCancel");
		WorkFlowEditor.addScreen("SaveScreen");
		WorkFlowEditor.link("Start Screen", "OpenCancel");
		WorkFlowEditor.link("Start Screen", "SaveScreen");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "SaveScreen", 
				new WFEditBox().label("dept_id")
							   .newKey("dept_id,int"));
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "OpenCancel", 
				new WFEditBox().label("dept_id")
							   .key("dept_id"));
		//
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
			.name("Close")
			.type("Close Workflow"));
		WorkFlowEditor.addMenuItem("OpenCancel", new WFScreenMenuItem()
			.name("Cancel")
			.type("Cancel Screen"));
		WorkFlowEditor.addMenuItem("SaveScreen", new WFScreenMenuItem()
			.name("Cancel")
			.type("Cancel Screen"));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android");
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=deptid,value=12"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("SaveScreen").throughMenuItem("Open Screen SaveScreen");
		s.screen("SaveScreen").setData("dept_id", "12");
		s.screen("SaveScreen").moveTo("Start_Screen").throughMenuItem("Cancel");
		s.screen("Start_Screen").moveTo("OpenCancel").throughMenuItem("Open Screen OpenCancel");
		s.screen("OpenCancel").getData("dept_id");
		s.screen("OpenCancel").moveTo("Start_Screen").throughMenuItem("Cancel");
		s.screen("Start_Screen").moveTo("SaveScreen").throughMenuItem("Close");
		return s;
	}
}
