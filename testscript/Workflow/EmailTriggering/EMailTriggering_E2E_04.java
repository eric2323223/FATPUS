package testscript.Workflow.EmailTriggering;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.EmailTriggering.EMailTriggering_E2E_04Helper;
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
import component.entity.PropertiesView;
import component.entity.WFScreenDesigner;
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
import component.entity.model.WFLview;
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
public class EMailTriggering_E2E_04 extends EMailTriggering_E2E_04Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_E2E_04</b>
	 * Generated     : <b>Oct 19, 2011 8:25:49 PM</b>
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
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Mbo1")
				.objectQuery("findByPrimaryKey")
				.from("imo@localhost")
				.subject("<id>1</id>")
				.fromMatchingRule("imo")
				.setParameterValue("C1,Subject,<id>,</id>")
		);
//		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Mbo1");
		WorkFlowEditor.removeScreen("Mbo1");
		WorkFlowEditor.removeScreen("Mbo1Detail");
		WorkFlowEditor.removeScreen("Mbo1create");
		WorkFlowEditor.removeScreen("Mbo1deleteinstance");
		WorkFlowEditor.addScreen("MyDetail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "MyDetail");
		
		WorkFlowEditor.addMenuItem("MyDetail", new WFScreenMenuItem().name("Open Mbo1updateinstance").type("Open").screen("Mbo1updateinstance"));
		
		WorkFlowEditor.addWidget("MyDetail", new WFEditBox().label("C1:")
				.key("Mbo1_C1_attribKey"));
		WorkFlowEditor.addWidget("MyDetail", new WFEditBox().label("C2:")
				.key("Mbo1_C2_attribKey"));
		
		WorkFlowEditor.setMenuItem("Mbo1updateinstance",new WFScreenMenuItem().name("Update").defaultSuccessScreen("MyDetail"));
		MainMenu.saveAll();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
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
			      "id=Mbo1_C2_attribKey,value=one|"+
			      "id=Mbo1_C1_attribKey,value=1"));
		
		//vp: the new record is added in the datebase:
		sleep(1);
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("C1=1");
		clause.add("C2='newone'");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "wf", "Mbo1", clause)).performTest();
		
	}
 
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("MyDetail").getData("Mbo1_C1_attribKey");
		s.screen("MyDetail").getData("Mbo1_C2_attribKey");
		s.screen("MyDetail").moveTo("Mbo1updateinstance").throughMenuItem("Open Mbo1updateinstance");
		s.screen("Mbo1updateinstance").setData("Mbo1_C2_attribKey","newone");
		s.screen("Mbo1updateinstance").moveTo("MyDetail").throughMenuItem("Update");
		s.screen("MyDetail").getData("Mbo1_C1_attribKey");
		return s;
	}
}
//passed BB6T 20120216 