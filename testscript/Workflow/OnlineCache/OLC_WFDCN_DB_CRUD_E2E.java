package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_WFDCN_DB_CRUD_E2EHelper;
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
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author mzhao
 */
public class OLC_WFDCN_DB_CRUD_E2E extends OLC_WFDCN_DB_CRUD_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_WFDCN_DB_CRUD_E2E</b>
	 * Generated     : <b>Apr 24, 2012 8:58:48 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/24
	 * @author mzhao
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->Department (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		mbo.setMboDefinition("sql", "select * from sampledb.dba.Department where dept_name=:dept_name");
		mbo.setLoadArgument(new LoadArgument().name("dept_name").type("STRING").propagateTo("dept_name"));
		
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.DCN));
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
				.objectQuery("findByParameter")
				.subject("dept_id=700")
				.subjectMatchingRule("dept_id=")
				.to("dt")
				);
		WorkFlowEditor.link("Activate", "Credential R...");
		
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
		.deployToServer("true")
		.assignToUser("dt")
		.unwiredServer("My Unwired Server"), customJsTestScript(), "tplan.Workflow.iconcommon.BB.myWF_icon2.Script");	
	}
		
		private CustomJsTestScript customJsTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Activate").moveTo("Credential Request").throughMenuItem("Open Credential Request");
			s.screen("Credential Request").setData("cc_username","supAdmin");
			s.screen("Credential Request").setData("cc_password","s3pAdmin");
			s.screen("Credential Request").menuItem("Save");
			return s;
		}
}

