package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_TableOut_1Helper;
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
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.LoadArgument;
import component.entity.model.MBOAttribute;
import component.entity.model.PK;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.WFScreenMenuItem;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SAP_TableOut_1 extends SAP_TableOut_1Helper
{
	/**
	 * Script Name   : <b>SAP_TableOut_1</b>
	 * Generated     : <b>Nov 14, 2011 10:21:25 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/14
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		WN.deleteAllProject();
//		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
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
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "ORG_ASSIGNMENT");
		WorkFlowEditor.dragMbo(Cfg.projectName, "COMMUNICATION");
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("ORG_ASSIGNMENT")
				.defaultSuccessScreen("ORG_ASSIGNMENT")
				.objectQuery("findAll"));
		
		WorkFlowEditor.addMenuItem("ORG_ASSIGNMENTDetail", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("COMMUNICATION")
				.defaultSuccessScreen("COMMUNICATION")
				.objectQuery("findAll"));
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
	}
}
//PASSED
