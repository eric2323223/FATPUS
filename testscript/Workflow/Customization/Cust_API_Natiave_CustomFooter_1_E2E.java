package testscript.Workflow.Customization;
import java.io.File;

import resources.testscript.Workflow.Customization.Cust_API_Natiave_CustomFooter_1_E2EHelper;
import testscript.Workflow.Customization.cfg.Config;
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
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Cust_API_Natiave_CustomFooter_1_E2E extends Cust_API_Natiave_CustomFooter_1_E2EHelper
{
	/**
	 * Script Name   : <b>Cust_API_Natiave_CustomFooter_1_E2E</b>
	 * Generated     : <b>Mar 27, 2012 3:53:14 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/27
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.assignToUser(Cfg.deviceUser)
				.unwiredServer("My Unwired Server")
				.deployToServer("true"), 
			customJsTestScript(), 
			Cfg.tplanScript_client_1);
		
//		1.used to BB6: passed
		WFCustomizer.verifyResult(new WFClientResult().data("PassGetCurrentScreen=Start"));
		
	}
	
	private CustomJsTestScript customJsTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setMethod("customAfterShowScreen", new File(Config.Cust_API_Natiave_CustomFooter_1));
		return s;
	}
}	
//passed BB6T 20120331

