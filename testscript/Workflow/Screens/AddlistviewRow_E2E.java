package testscript.Workflow.Screens;
import java.util.ArrayList;
import java.util.List;

import resources.testscript.Workflow.Screens.AddlistviewRow_E2EHelper;
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
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class AddlistviewRow_E2E extends AddlistviewRow_E2EHelper
{
	/**
	 * Script Name   : <b>AddlistviewRow_E2E_Android</b>
	 * Generated     : <b>Sep 19, 2011 9:21:47 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/19
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/add_a_b.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_b (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Wf_ff_a"))
			.target("Wf_ff_b")
			.mapping("aid,aid")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Wf_ff_a")
				.objectQuery("findByPrimaryKey")
				.subject("dept_id=1")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("aid,Subject,dept_id="));
		
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffa")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("WfffaDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffacreate")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffadeleteinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffaupdateinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffb")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("WfffbDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffbupdateinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffbadd")).performTest();
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser),
			customTestScript(), 
//			"tplan.Workflow.iconcommon.BB.server_dt_icon.Script",
			new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email()
			.unwiredServer("My Unwired Server")
			.selectTo(Cfg.deviceUser)
			.subject("dept_id=1")));
		
		sleep(1);
		//need to check data the backend DB
		vpManual("db",1,getDB("select * from Wf_ff_b where bid = 4 and aid = 1 and bname ='Bfour' ")).performTest();
		
		
//		java.util.List<String> clause = new ArrayList<String>();
//		clause.add("bid=4");
//		clause.add("aid=1");
//		clause.add("bname='Bfour'");
//		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "wf1", "Wf_ff_b", clause)).performTest();
	}

	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("WfffaDetail").moveTo("Wfffb").throughMenuItem("Open Wfffb");
		s.screen("Wfffb").moveTo("Wfffbadd").throughMenuItem("Add");
		
		s.screen("Wfffbadd").setData("Wf_ff_b_bid_attribKey", "4");
		s.screen("Wfffbadd").setData("Wf_ff_b_aid_attribKey", "1");
		s.screen("Wfffbadd").setData("Wf_ff_b_bname_attribKey", "Bfour");
	
		s.screen("Wfffbadd").moveTo("Wfffb").throughMenuItem("Create");
		s.screen("Wfffb").moveTo("WfffaDetail").throughMenuItem("Back");
		s.screen("WfffaDetail").menuItem("Submit");
		return s;
	}
	public static int getDB(String sql){
		try {
			ASA db = new ASA();
			db.setProperty("host", "localhost");
			db.setProperty("login", "dba");
			db.setProperty("password", "sql");
			db.setProperty("port", "5500");
			db.setProperty("driverClass", "com.sybase.jdbc3.jdbc.SybDriver");
			List result = DBUtil.runSQLForResult((IDBResource) db, sql);
			System.out.println(result.size());
			return result.size();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get record data from DB");
			}
	}

	
	
}
//PASSED BB6 20120202