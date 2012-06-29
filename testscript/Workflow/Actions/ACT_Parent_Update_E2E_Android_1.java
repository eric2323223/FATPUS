package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.ACT_Parent_Update_E2E_Android_1Helper;
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

import component.entity.EE;
import component.entity.GlobalConfig;
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
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;
import component.view.properties.workflow.WFControlObject;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class ACT_Parent_Update_E2E_Android_1 extends ACT_Parent_Update_E2E_Android_1Helper
{
	/**
	 * Script Name   : <b>ACT_Parent_Update_E2E_Android_1</b>
	 * Generated     : <b>Oct 25, 2011 2:32:01 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/relationship.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/P_data.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/C_data.sql");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->P (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->C (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "P"))
			.target("C")
			.mapping("pid,pid")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
		);
		WorkFlowEditor.dragMbo(Cfg.projectName, "P");
		//Jump Start
		PropertiesView.jumpStart(new WorkFlow()
				.from("supAdmin")
				.to("supAdmin")
				.cc("supAdmin")
				.subject("all")
				.subjectMatchingRule("all")
				.mbo("P")
				.objectQuery("findAll")
				.received("2011-10-21")
				.body("<name>[name]</name><value>{value}</value>")
				.verifySubject("Subject,all", true));
		WorkFlowEditor.link("Server-initiated", "P");
		Object obj = WFControlObject.getRadioButton("P", "Submit", "Invoke &parent update").invoke("getEnabled");
		vpManual("parentupdate", true, obj.equals(true)).performTest();
		WorkFlowEditor.setMenuItem("P", new WFScreenMenuItem()
			.name("Submit")
			.type("Online Request"));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android", 
			new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
					.unwiredServer("My Unwired Server")
					.to("supAdmin")
					.from("RFT")
					.subject("all")
					.body("bodybody")));
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=P_pid_attribKey,value=1|"+
				"id=P_pname_attribKey,value=1|"));
		
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("cid=4");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("flv-vmpc", "wf", "C", clause)).performTest();
		clause.clear();
		//
		clause.add("cid=2");
		clause.add("LOGICAL_DEL=1");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("flv-vmpc", "wf", "C", clause)).performTest();
		clause.clear();
		//
		clause.add("cid=1");
		clause.add("cname='flv'");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("flv-vmpc", "wf", "C", clause)).performTest();
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("P").moveTo("PDetail").throughListItem("1->0");
		s.screen("PDetail").getData("P_pid_attribKey");
		s.screen("PDetail").getData("P_pname_attribKey");
		//Open C
		s.screen("PDetail").moveTo("C").throughMenuItem("Open C");
		//Add
		s.screen("C").moveTo("C_add").throughMenuItem("Add");
		s.screen("C_add").setData("C_create_cid_paramKey", "4");
		s.screen("C_add").setData("C_create_pid_paramKey", "1");
		s.screen("C_add").setData("C_create_cname_paramKey", "4");
		s.screen("C_add").moveTo("C").throughMenuItem("Create");
		//--------
		s.screen("C").moveTo("CDetail").throughListItem("2->0");
		//C_delete_instance
		s.screen("CDetail").moveTo("C").throughMenuItem("C_delete_instance");
		s.screen("C").moveTo("CDetail").throughListItem("1->0");
		//Open Screen C_update_instance
		s.screen("CDetail").moveTo("C_update_instance").throughMenuItem("Open Screen C_update_instance");
		s.screen("C_update_instance").setData("C_cname_attribKey", "flv2");
		s.screen("C_update_instance").moveTo("CDetail").throughMenuItem("Update");
		//Back
		s.screen("CDetail").moveTo("C").throughMenuItem("Back");
		s.screen("C").moveTo("PDetail").throughMenuItem("Back");
		s.screen("PDetail").moveTo("P").throughMenuItem("Back");
		s.screen("P").moveTo("P").throughMenuItem("Submit");

		return s;
	}
}
