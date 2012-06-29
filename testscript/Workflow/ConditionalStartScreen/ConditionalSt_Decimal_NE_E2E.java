package testscript.Workflow.ConditionalStartScreen;
import resources.testscript.Workflow.ConditionalStartScreen.ConditionalSt_Decimal_NE_E2EHelper;
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
import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author mzhao
 */
public class ConditionalSt_Decimal_NE_E2E extends ConditionalSt_Decimal_NE_E2EHelper
{
	/**
	 * Script Name   : <b>ConditionalSt_Decimal_NE_E2E</b>
	 * Generated     : <b>Mar 23, 2012 1:04:28 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/23
	 * @author mzhao
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/Alldatetype_setup.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllMBODataType (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
		.name("myWF")
		.option(WorkFlow.SP_SERVER_INIT)
		.project(Cfg.projectName)
		.mbo("AllMBODataType")
		.objectQuery("findByPrimaryKey")
        .subject("id=1")
		.subjectMatchingRule("id=")
		.setParameterValue("id,Subject,id=,,"));
		
		MainMenu.saveAll();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
		
		// create new string type conditional screen 
		WorkFlowEditor.addScreen("Success");
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		PropertiesView.addConditionTableOfSP("decimal", "Success");
	    MainMenu.saveAll();
		
	    
		// Generate WF to server
//		
//		WFCustomizer.runTest(new WorkFlowPackage()
//		.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//		.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser)
//		.deployToServer("true")
//		.unwiredServer("My Unwired Server"), script(), 
////		testscript.Workflow.cfg.Cfg.tplanScript_server_3,
//		
//		new CallBackMethod().receiver(WorkFlowEditor.class)
//				.methodName("sendNotification")
//				.parameter(new Email()
//				.unwiredServer("My Unwired Server")
//				.selectTo(testscript.Workflow.cfg.Cfg.deviceUser)
//				.subject("id=1")));	
		
		
//		WFCustomizer.verifyResult(new WFClientResult().data(
//				"list_items_count=0"));		
//		
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
		.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser)
		.deployToServer("true")
		.unwiredServer("My Unwired Server"), script(), 
//		testscript.Workflow.cfg.Cfg.tplanScript_server_3,
		
		new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
				.unwiredServer("My Unwired Server")
				.selectTo(testscript.Workflow.cfg.Cfg.deviceUser)
				.subject("id=3")));	
		
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"list_items_count=0"));
		}
	
	
      //T-plan
	     private CustomJsTestScript script() {
       CustomJsTestScript s = new CustomJsTestScript();
       s.setConditionScreenRule(Cfg.Conditional_Decimal_LE_E2E);
       s.screen("Success").getListItemsCount();

      return s;
	}
}
