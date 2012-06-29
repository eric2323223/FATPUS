package testscript.Workflow.EmailTriggering;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_E2E_05Helper;
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
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_E2E_05 extends EMailTriggering_E2E_05Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_E2E_05</b>
	 * Generated     : <b>Oct 19, 2011 9:03:19 PM</b>
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
		String sqldelete = "delete from Mbo1 where C1=1";
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), sqldelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sqlcreate = "insert into Mbo1 values (1,'one')";
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), sqlcreate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->mbo1 (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE )
				.serverConnectionMapping("My Sample Database,sampledb"));

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Mbo1")
				.objectQuery("findByPrimaryKey")
				.from("imo@localhost")
				.subject("<id>2</id>")
				.fromMatchingRule("imo")
				.setParameterValue("C1,Subject,<id>,</id>")
		);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Mbo1");
		WorkFlowEditor.removeScreen("Mbo1");
		WorkFlowEditor.removeScreen("Mbo1updateinstance");
		WorkFlowEditor.removeScreen("Mbo1create");
		WorkFlowEditor.removeScreen("Mbo1deleteinstance");
		sleep(1);
		MainMenu.saveAll();
		WorkFlowEditor.addScreen("MyUpdate");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "Mbo1Detail");
		WorkFlowEditor.addMenuItem("Mbo1Detail", new WFScreenMenuItem().name("Open MyUpdate")
				.type("Open")
				.screen("MyUpdate"));
		sleep(1);
		MainMenu.saveAll();
		
		WorkFlowEditor.removeMenuItem("Mbo1Detail", "Back");
		WorkFlowEditor.addMenuItem("Mbo1Detail", new WFScreenMenuItem().name("submit")
				.type("Submit Workflow")
				.project(Cfg.projectName)
				.mbo("Mbo1"));
		sleep(1);
		MainMenu.saveAll();
		
		WorkFlowEditor.addWidget("MyUpdate", new WFEditBox().label("C1:")
				.key("Mbo1_C1_attribKey")
				.ifReadonly(true));
		sleep(1);
		MainMenu.saveAll();
		WorkFlowEditor.addWidget("MyUpdate", new WFEditBox().label("C2:")
				.key("Mbo1_C2_attribKey"));
		sleep(1);
		MainMenu.saveAll();
		WorkFlowEditor.addMenuItem("MyUpdate", new WFScreenMenuItem().name("update")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Mbo1")
				.operation("update")
				.submitErrMsg("this is my submit error message")
				.defaultSuccessScreen("Mbo1Detail")
				.parametermapping("C1,Mbo1_C1_attribKey")
				.parametermapping("C2,Mbo1_C2_attribKey")
				);
		
		MainMenu.saveAll();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript(), 
//			"tplan.Workflow.iconcommon.BB.server_dt_icon.Script",
			new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
					.from("imo@localhost")
					.subject("<id>1</id>")));
		
		WFCustomizer.verifyResult(new WFClientResult()
			.data("id=Mbo1_C1_attribKey,value=1|" +
			      "id=Mbo1_C2_attribKey,value=one"));
		
		//vp: the new record is added in the datebase:
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("C1=1");
		clause.add("C2='newone'");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "wf", "Mbo1", clause)).performTest();
		
	}
 
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Mbo1Detail").getData("Mbo1_C1_attribKey");
		s.screen("Mbo1Detail").getData("Mbo1_C2_attribKey");
		s.screen("Mbo1Detail").moveTo("MyUpdate").throughMenuItem("Open MyUpdate");
		s.screen("MyUpdate").setData("Mbo1_C2_attribKey","newone");
		s.screen("MyUpdate").moveTo("Mbo1Detail").throughMenuItem("update");
		s.screen("Mbo1Detail").menuItem("submit");
		return s;
	}
}
