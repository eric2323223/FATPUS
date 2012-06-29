package testscript.Workflow.SAP;
import resources.testscript.Workflow.SAP.SAP_RLTN_3_E2EHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SAP_RLTN_3_E2E extends SAP_RLTN_3_E2EHelper
{
	/**
	 * Script Name   : <b>SAP_RLTN_3_E2E</b>
	 * Generated     : <b>Dec 1, 2011 10:54:31 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/01
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.useProject(Cfg.projectName);
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("Existcheck")
				.connectionProfile("sap")
				.bapiOperation("BAPI_COMPANY_EXISTENCECHECK,ExistenceCheck")
				.parameter("COMPANYID,in")
				.parameter("RETURN,out")
				.parameterValue("COMPANYID,002000"));
		
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.name("GetDetail")
				.connectionProfile("sap")
				.bapiOperation("BAPI_COMPANY_GETDETAIL,GetDetail")
				.parameter("COMPANYID,in")
				.parameter("COMPANY_DETAIL,out")
				.parameterValue("COMPANYID,002000"));
		
		//create O2O relationship between ExistenceCheck with GetDetail...
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Existcheck"))
			.target("GetDetail")
			.mapping("Existcheck->Arguments->COMPANYID[STRING(6)],GetDetail->Arguments->COMPANYID[STRING(6)]")
			.composite("true")
			.type(Relationship.TYPE_OTO));
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Existcheck");
		mbo.renameAttribute("TYPE", "TYPE1");
		
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName, "Existcheck"))
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("sap,sap"));
		
		//create WF..
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Existcheck")
				.objectQuery("findByPrimaryKey")
				.subject("id=002000")
				.subjectMatchingRule("id=")
				.setParameterValue("COMPANYID,Subject,id=")
				);
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.deployToServer("true")
				.unwiredServer("My Unwired Server")
				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser), 
				customJsTestScript(),
				testscript.Workflow.cfg.Cfg.tplanScript_server_2, 
				new CallBackMethod().receiver(WorkFlowEditor.class)
					.methodName("sendNotification")
					.parameter(new Email()
					.unwiredServer("My Unwired Server")
					.selectTo(testscript.Workflow.cfg.Cfg.deviceUser)
					.subject("id=002000")));
		
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Existcheck_LOG_MSG_NO_attribKey,value=0000|"+
				"id=GetDetail_COMPANY_attribKey,value=002000|"+
				"id=GetDetail_NAME1_attribKey,value='IDES UK LTD'|"+					
				"id=GetDetail_COMPANYID_attribKey,value=002000"));	
}

	private CustomJsTestScript customJsTestScript() {
		// TODO Auto-generated method stub
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("ExistcheckDetail").getData("Existcheck_LOG_MSG_NO_attribKey");
		s.screen("ExistcheckDetail").moveTo("GetDetailDetail").throughMenuItem("Open GetDetailDetail");
		s.screen("GetDetailDetail").getData("GetDetail_COMPANY_attribKey");
		s.screen("GetDetailDetail").getData("GetDetail_NAME1_attribKey");
		s.screen("GetDetailDetail").getData("GetDetail_COMPANYID_attribKey");
		return s;
	}
}
//run passed
