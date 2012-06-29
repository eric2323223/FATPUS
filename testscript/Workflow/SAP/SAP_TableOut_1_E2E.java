package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_TableOut_1_E2EHelper;
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
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SAP_TableOut_1_E2E extends SAP_TableOut_1_E2EHelper
{
	/**
	 * Script Name   : <b>SAP_TableOut_1_E2E</b>
	 * Generated     : <b>Oct 27, 2011 7:20:52 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/27
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		WN.deleteAllProject();
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.useProject(Cfg.projectName);
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("ORG_ASSIGNMENT")
				.connectionProfile("sap")
				.bapiOperation("BAPI_EMPLOYEE_GETDATA,GetList")
				.parameter("EMPLOYEE_ID,in")
				.parameter("ORG_ASSIGNMENT,out")
				.parameter("COMMUNICATION,out")
				.parameterValue("EMPLOYEE_ID,0050995"));
		MainMenu.saveAll();
		
		vpManual("hasMBO",true,WN.hasMboInProject(Cfg.projectName, "ORG_ASSIGNMENT")).performTest();
		vpManual("hasMBO",true,WN.hasMboInProject(Cfg.projectName, "COMMUNICATION")).performTest();
		
		
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "COMMUNICATION"))
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("sap,sap")
				);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "ORG_ASSIGNMENT");
		WorkFlowEditor.dragMbo(Cfg.projectName, "COMMUNICATION");
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("ORG_ASSIGNMENT")
				.defaultSuccessScreen("ORGASSIGNMENT")
				.objectQuery("findAll"));
		
		WorkFlowEditor.addMenuItem("ORGASSIGNMENTDetail", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("COMMUNICATION")
				.defaultSuccessScreen("COMMUNICATION")
				.objectQuery("findAll"));
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		

		
	WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.deployToServer("true")
			.unwiredServer("My Unwired Server")
			.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser), 
			customJsTestScript());
		
	//vp:data in device
	WFCustomizer.verifyResult(new WFClientResult().data(
			"list_items_count=1|"+
			"id=ORG_ASSIGNMENT_PERNO_attribKey,value=0050995|"+
			"id=ORG_ASSIGNMENT_INFOTYPE_attribKey,value=0001|"+
			"list_items_count=1|"+
			"id=COMMUNICATION_PERNO_attribKey,value=0050995|"+
			"id=COMMUNICATION_INFOTYPE_attribKey,value=0105"));	
	
}

private CustomJsTestScript customJsTestScript() {
	// TODO Auto-generated method stub
	CustomJsTestScript s = new CustomJsTestScript();
	s.screen("Start").moveTo("ORG_ASSIGNMENT").throughMenuItem("findall");
	
	s.screen("ORG_ASSIGNMENT").getListItemsCount();
	s.screen("ORG_ASSIGNMENT").moveTo("ORG_ASSIGNMENTDetail").throughListItem("0");
	s.screen("ORG_ASSIGNMENTDetail").getData("ORG_ASSIGNMENT_PERNO_attribKey");
	s.screen("ORG_ASSIGNMENTDetail").getData("ORG_ASSIGNMENT_INFOTYPE_attribKey");
	
	s.screen("ORG_ASSIGNMENTDetail").moveTo("COMMUNICATION").throughMenuItem("findall");
	s.screen("COMMUNICATION").getListItemsCount();
	s.screen("COMMUNICATION").moveTo("COMMUNICATIONDetail").throughListItem("0");
	s.screen("COMMUNICATIONDetail").getData("COMMUNICATION_PERNO_attribKey");
	s.screen("COMMUNICATIONDetail").getData("COMMUNICATION_INFOTYPE_attribKey");
	
	return s;
	}
}
//PASSED BB6T 20120222
