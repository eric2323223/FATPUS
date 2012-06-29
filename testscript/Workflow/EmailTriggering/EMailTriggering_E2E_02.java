package testscript.Workflow.EmailTriggering;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_E2E_02Helper;
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
public class EMailTriggering_E2E_02 extends EMailTriggering_E2E_02Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_E2E_02</b>
	 * Generated     : <b>Oct 19, 2011 8:03:40 PM</b>
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
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
//		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Mbo1");
		WorkFlowEditor.removeScreen("Mbo1create");
		WorkFlowEditor.removeScreen("Mbo1updateinstance");
		WorkFlowEditor.removeScreen("Mbo1deleteinstance");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "Mbo1");
		
		//set the screen "MyUpdate"
		WorkFlowEditor.addScreen("MyUpdate");
		WorkFlowEditor.link("Mbo1Detail","MyUpdate");
		
		WorkFlowEditor.addMenuItem("MyUpdate", new WFScreenMenuItem().name("update")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Mbo1")
				.operation("update")
				.defaultSuccessScreen("Mbo1")
				.parametermapping("C1,Mbo1_C1_attribKey")
				.parametermapping("C2,Mbo1_C2_attribKey")
		);
		
		WorkFlowEditor.addWidget("MyUpdate", new WFEditBox().label("C1:")
				.key("Mbo1_C1_attribKey"));
		WorkFlowEditor.addWidget("MyUpdate", new WFEditBox().label("C2:")
				.key("Mbo1_C2_attribKey"));
		
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Mbo1")
				.objectQuery("findAll")
				.from("imo@localhost")
				.subject("<id>1</id>")
				.fromMatchingRule("imo")
				);
		
		MainMenu.saveAll();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();

		WFCustomizer.runTest(new WorkFlowPackage()
						.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
						.unwiredServer("My Unwired Server")
						.assignToUser(Cfg.deviceUser)
						.deployToServer("true"), 
						customTestScript(), 
//						"tplan.Workflow.iconcommon.BB.server_dt_icon.Script",
						new CallBackMethod().receiver(WorkFlowEditor.class)
							.methodName("sendNotification")
							.parameter(new Email()
									.from("imo@localhost")
									.to("DT")
									.subject("<id>1</id>")));
		
		
		//vp1:verify the number of record:
		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=3"));
		
		sleep(2);
		//vp2:verify the update record  has added into backend db in mbo1
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("C1=1");
		clause.add("C2='new'");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("Localhost", "wf", "Mbo1", clause)).performTest();
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Mbo1").getListItemsCount();
		s.screen("Mbo1").moveTo("Mbo1Detail").throughListItem("1->0");
		s.screen("Mbo1Detail").moveTo("MyUpdate").throughMenuItem("Open MyUpdate");
		s.screen("MyUpdate").setData("Mbo1_C2_attribKey", "new");
		s.screen("MyUpdate").moveTo("Mbo1").throughMenuItem("update");
		s.screen("Mbo1").menuItem("Submit");
		return s;
	}
		
}

//passed ok 2012.1.30
