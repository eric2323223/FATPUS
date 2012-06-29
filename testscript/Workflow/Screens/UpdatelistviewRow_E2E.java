package testscript.Workflow.Screens;
import java.util.ArrayList;

import resources.testscript.Workflow.Screens.UpdatelistviewRow_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.GlobalConfig;
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
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class UpdatelistviewRow_E2E extends UpdatelistviewRow_E2EHelper
{
	/**
	 * Script Name   : <b>UpdatelistviewRow_E2E_Android</b>
	 * Generated     : <b>Sep 19, 2011 10:01:23 AM</b>
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
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/add_a_b.sql");
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/add_record.sql");
		
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
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffa")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("WfffaDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffacreate")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffadeleteinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffaupdateinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffb")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("WfffbDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffbupdateinstance")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Wfffbadd")).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser),
			customTestScript(), 
//			Cfg.tplanScript_server_1,
			new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email()
			.unwiredServer("My Unwired Server")
			.selectTo(Cfg.deviceUser)
			.subject("dept_id=1")));
		
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Wf_ff_a_aid_attribKey,value=1|" +
				"id=Wf_ff_a_aname_attribKey,value=Aone|" +
				"id=Wf_ff_b_bid_attribKey,value=4|"+
				"id=Wf_ff_b_aid_attribKey,value=1|"+
				"id=Wf_ff_b_bname_attribKey,value=Bfour"));
		
		//need to check data the backend DB
		vpManual("db",0,AddlistviewRow_E2E.getDB("select * from Wf_ff_b where bid = 4 and aid = 1 and bname ='BNewthree' ")).performTest();
		
		
		
		
//		java.util.List<String> clause = new ArrayList<String>();
//		clause.add("bid=4");
//		clause.add("aid=1");
//		clause.add("bname='BNewthree'");
//		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", Cfg.projectName, "Wf_ff_b", clause)).performTest();
	}

	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("WfffaDetail").getData("Wf_ff_a_aid_attribKey");
		s.screen("WfffaDetail").getData("Wf_ff_a_aname_attribKey");
		s.screen("WfffaDetail").moveTo("Wfffb").throughMenuItem("Open Wfffb");
		
		s.screen("Wfffb").moveTo("WfffbDetail").throughListItem("4->0");
		
		s.screen("WfffbDetail").getData("Wf_ff_b_bid_attribKey");
		s.screen("WfffbDetail").getData("Wf_ff_b_aid_attribKey");
		s.screen("WfffbDetail").getData("Wf_ff_b_bname_attribKey");
		
		s.screen("WfffbDetail").moveTo("Wfffbupdateinstance").throughMenuItem("Open Wfffbupdateinstance");
		s.screen("Wfffbupdateinstance").setData("Wf_ff_b_bname_attribKey", "BNewthree");
		s.screen("Wfffbupdateinstance").moveTo("WfffbDetail").throughMenuItem("Update");
		s.screen("WfffbDetail").moveTo("Wfffb").throughMenuItem("Back");
		s.screen("Wfffb").moveTo("WfffaDetail").throughMenuItem("Back");
		s.screen("WfffaDetail").menuItem("Submit");
		return s;
	}
}
//CR...
