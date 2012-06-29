package testscript.Workflow.EmailTriggering;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_E2E_03Helper;
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
public class EMailTriggering_E2E_03 extends EMailTriggering_E2E_03Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_E2E_03</b>
	 * Generated     : <b>Oct 19, 2011 8:13:11 PM</b>
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
		String sqldelete = "delete from Mbo1 where C1=2";
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), sqldelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sqlcreate = "insert into Mbo1 values (2,'two')";
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

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Mbo1");
		WorkFlowEditor.removeScreen("Mbo1create");
		WorkFlowEditor.removeScreen("Mbo1updateinstance");
		WorkFlowEditor.removeScreen("Mbo1deleteinstance");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "Mbo1");
		
		//set the screen "Mydpdate"
		WorkFlowEditor.addScreen("Mydelete");
		WorkFlowEditor.link("Mbo1Detail","Mydelete");
		
		WorkFlowEditor.addWidget("Mydelete", new WFEditBox().label("C1")
				.key("Mbo1_C1_attribKey"));
		WorkFlowEditor.addWidget("Mydelete", new WFEditBox().label("C2")
				.key("Mbo1_C2_attribKey"));
		
		WorkFlowEditor.addMenuItem("Mydelete", new WFScreenMenuItem().name("delete")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Mbo1")
				.operation("delete")
				.defaultSuccessScreen("Mbo1")
				.parametermapping("C1,Mbo1_C1_attribKey")
				.parametermapping("C2,Mbo1_C2_attribKey")
				);
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Mbo1")
				.objectQuery("findByPrimaryKey")
				.from("imo@localhost")
				.subject("<id>1</id>")
				.fromMatchingRule("imo")
				.setParameterValue("C1,Subject,<id>,</id>,"));
		
		MainMenu.saveAll();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();

		WFCustomizer.runTest(new WorkFlowPackage()
						.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
						.assignToUser(Cfg.deviceUser)
						.unwiredServer("My Unwired Server")
						.deployToServer("true"), 
						customTestScript(), 
//						"tplan.Workflow.iconcommon.BB.server_dt_icon.Script", 
						new CallBackMethod().receiver(WorkFlowEditor.class)
							.methodName("sendNotification")
							.parameter(new Email()
									.from("imo@localhost")
									.subject("<id>2</id>")));
		
		
		WFCustomizer.verifyResult(new WFClientResult()
			.data("id=Mbo1_C1_attribKey,value=2|" +
				"id=Mbo1_C2_attribKey,value=two"));
		
		//vp:verify the record  has deleted in backend db of department
		
		vpManual("dbresult", true, 0 == DBUtil.getDB("select * from Mbo1 where C1=2 and C2='two'")).performTest();
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Mbo1").moveTo("Mbo1Detail").throughListItem("2->0");
		s.screen("Mbo1Detail").moveTo("Mydelete").throughMenuItem("Open Mydelete");
		s.screen("Mydelete").getData("Mbo1_C1_attribKey");
		s.screen("Mydelete").getData("Mbo1_C2_attribKey");
		sleep(1);
		s.screen("Mydelete").moveTo("Mbo1").throughMenuItem("delete");
		s.screen("Mbo1").menuItem("Submit");
		return s;
	}
}
//passed 20120131