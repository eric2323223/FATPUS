package testscript.Workflow.DCN;
import resources.testscript.Workflow.DCN.WFDCN_ActiveSP_DCNCache_E2EHelper;
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
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class WFDCN_ActiveSP_DCNCache_E2E extends WFDCN_ActiveSP_DCNCache_E2EHelper
{
	/**
	 * Script Name   : <b>WFDCN_ActiveSP_DCNCache_E2E</b>
	 * Generated     : <b>Nov 18, 2011 5:44:38 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/18
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
				.name("Department")
				.dataSourceType("JDBC")
				.connectionProfile("My Sample Database")
				.sqlQuery("select * from department"));
		WN.createCacheGroup(new CacheGroup()
				.startParameter(Cfg.projectName)
				.name("dcn")
				.type(CachePolicy.DCN));
		WN.changeCacheGroup(Cfg.projectName, "Department", "Default (Default)", "dcn");	
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.option(WorkFlow.SP_ACTIVATE)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST)
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.from("ff")
				.subject("updated")
				.subjectMatchingRule("updated")
				.to("DT")
				.toMatchingRule("DT")
				);
		
	WorkFlowEditor.dragOperation(Cfg.projectName, "Department", "create");
	WorkFlowEditor.link(WorkFlow.SP_ACTIVATE,"Department_create");
	
//	WFCustomizer.runTest(new WorkFlowPackage()
//		.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//		.assignToUser("DT")
//		.deployToServer("true")
//		.unwiredServer("My Unwired Server"), activate(), "");
//		
	}
	
	//Verify active screen appeared propertly:
	private CustomJsTestScript activate() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Activate_Screen").moveTo("Credential_Request_Screen").throughMenuItem("Open Credential Request Screen");
		s.screen("Credential_Request_Screen").setData("cc_username","supAdmin");
		s.screen("Credential_Request_Screen").setData("cc_password","s3pAdmin");
		s.screen("Credential_Request_Screen").menuItem("Save_Screen");
		return s;
	}
}

