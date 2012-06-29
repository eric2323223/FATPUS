package testscript.Workflow.EmailTriggering;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_E2E_01Helper;
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
import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.common.DBUtil;

import component.entity.EE;
import component.entity.GlobalConfig;
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
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_E2E_01 extends EMailTriggering_E2E_01Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_E2E_01</b>
	 * Generated     : <b>Oct 19, 2011 7:47:38 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/19
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/EmailTriggering/setup/mbo1.sql");
	
		//restore the initial table record
		String sqldelete = "delete from Mbo1 where C1=3";
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), sqldelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->mbo1 (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				);
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Mbo1")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WorkFlowEditor.dragOperation(Cfg.projectName, "Mbo1","create");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"Mbo1create");
		
		MainMenu.saveAll();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
		//deploy wf:
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser),
			customTestScript(), 
//			testscript.Workflow.cfg.Cfg.tplanScript_server_1, 
			
			new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
				.unwiredServer("My Unwired Server")
				.selectTo(Cfg.deviceUser)
				.subject("findall")));
		
//		vp2:verify the new create record  has added into backend db in department
		sleep(2);
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("C1=3");
		clause.add("C2='test'");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("localhost", "wf", "Mbo1", clause)).performTest();
	}

	private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Mbo1create").setData("Mbo1_create_C1_paramKey", "3");
			s.screen("Mbo1create").setData("Mbo1_create_C2_paramKey", "test");
			s.screen("Mbo1create").menuItem("Create");
			return s;
	}
}
//passed 20120523
