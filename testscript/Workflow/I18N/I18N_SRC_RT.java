package testscript.Workflow.I18N;
import java.io.UnsupportedEncodingException;

import resources.testscript.Workflow.I18N.I18N_SRC_RTHelper;
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
import com.sybase.automation.framework.widget.DOF;

import component.entity.MainMenu;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class I18N_SRC_RT extends I18N_SRC_RTHelper
{
	/**
	 * Script Name   : <b>I18N_SRC_RT</b>
	 * Generated     : <b>Nov 28, 2011 3:10:47 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/28
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("MenuTest");
		sleep(0.5);
		DOF.getWFScreenFigure(DOF.getRoot(), "MenuTest").click();
		sleep(1);
		DOF.getTextField(DOF.getRoot(), "Name:").click();
		DOF.getRoot().inputChars("\u83DC\u5355\u6D4B\u8BD5");
		WFFlowDesigner.arrangeAll();
		MainMenu.saveAll();
		WorkFlowEditor.link("Start Screen", "菜单测试");
		MainMenu.saveAll();
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("自定义")
				.type("Open Screen")
				.screen("菜单测试"));
		MainMenu.saveAll();
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
		WFCustomizer.verifyResult(new WFClientResult().data("value=菜单测试"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("____14499").throughMenuItem("自定义");
		s.screen("____14499").getScreenDisplayName("____14499");
		return s;
	}
}