package testscript.Workflow.EmailTriggering;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_E2E_08Helper;
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
import com.sybase.automation.framework.widget.DOF;

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
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_E2E_08 extends EMailTriggering_E2E_08Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_E2E_08</b>
	 * Generated     : <b>Oct 19, 2011 10:25:09 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/19
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//restore the initial table record
		String sqldelete = "delete from AllDT where int1=4";
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), sqldelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WN.useProject(Cfg.projectName);
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE )
				.serverConnectionMapping("My Sample Database,sampledb"));

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.option(WorkFlow.SP_CLIENT_INIT)
				.mbo("AllDT")
				.objectQuery("findByPrimaryKey")
				.subject("dept_id=4")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("int1,Subject,dept_id=")
				);
		
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("Open AllDTcreate").type("Open").screen("AllDTcreate"));
		
		MainMenu.saveAll();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
		//Tooling is OK<<<<<<<<<<<<<<<<<<<
		//VP1:client to create record:
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript()
//			, 
//			testscript.Workflow.cfg.Cfg.tplanScript_client_1
			);
		
		//vp: Client=the new record is added in the datebase:
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("int1=4");
		clause.add("string1='4'");
		clause.add("string2='4'");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "wf", "AllDT", clause)).performTest();
		
		deleteGeneratedWorkFlowFolder();
		//send Notification
		//VP2:Server to show detail of new record:
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"),
			customTestScriptServer(), 
//			testscript.Workflow.cfg.Cfg.tplanScript_server_1,
			new CallBackMethod().receiver(WorkFlowEditor.class)
					.methodName("sendNotification")
					.parameter(new Email()
					.selectTo(Cfg.deviceUser)
					.subject("dept_id=4")));
		
		//VP3:update the record"
		WFCustomizer.verifyResult(new WFClientResult()
				.data("id=AllDT_int1_attribKey,value=4|" +
						"id=AllDT_string1_attribKey,value=4|" +
					  "id=AllDT_string2_attribKey,value=4"));
		
//vp:verify the updated record has updated in backend db of table Mbo1..
		java.util.List<String> clauseServer = new ArrayList<String>();
		clauseServer.add("int1=4");
		clauseServer.add("string1='42'");
		clauseServer.add("string2='42'");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("localhost", "wf", "AllDT", clauseServer)).performTest();
	}
	
	//vp1:client create...
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("AllDTcreate").throughMenuItem("Open AllDTcreate");
		s.screen("AllDTcreate").setData("AllDT_create_int1_paramKey", "4");
		s.screen("AllDTcreate").setData("AllDT_create_string1_paramKey", "4");
		s.screen("AllDTcreate").setData("AllDT_create_string2_paramKey", "4");
		s.screen("AllDTcreate").setData("AllDT_create_byte1_paramKey", "4");
		s.screen("AllDTcreate").menuItem("Create");
		return s;
	}
	
	//vp2:server detail and update...
	private CustomJsTestScript customTestScriptServer() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
		s.screen("AllDTDetail").getData("AllDT_string1_attribKey");
		s.screen("AllDTDetail").getData("AllDT_string2_attribKey");
		
		s.screen("AllDTDetail").moveTo("AllDTupdateinstance").throughMenuItem("Open AllDTupdateinstance");
		s.screen("AllDTupdateinstance").setData("AllDT_string1_attribKey","42");
		s.screen("AllDTupdateinstance").setData("AllDT_string2_attribKey","42");
		s.screen("AllDTupdateinstance").menuItem("Update");
		return s;
	}
	
	public static void deleteGeneratedWorkFlowFolder() {
		//delete the Generated Workflow->"+"myWF1
		DOF.getWNTree().click(RIGHT,atPath(WN.filePath(Cfg.projectName, "Generated Workflow->"+"myWF")));
		DOF.getContextMenu().click(atPath("Delete"));
		DOF.getButton(DOF.getDialog("Confirm Resource Delete"), "&Yes").click();
		sleep(3);
		}
}
//passed BB6T 20120217 :Note: need to switch from client WF to server WF,
