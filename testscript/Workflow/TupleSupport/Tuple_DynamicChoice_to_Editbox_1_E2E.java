package testscript.Workflow.TupleSupport;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.TupleSupport.Tuple_DynamicChoice_to_Editbox_1_E2EHelper;
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
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Tuple_DynamicChoice_to_Editbox_1_E2E extends Tuple_DynamicChoice_to_Editbox_1_E2EHelper
{
	/**
	 * Script Name   : <b>Tuple_DynamicChoice_to_Editbox_1_1_E2E</b>
	 * Generated     : <b>Nov 5, 2011 3:37:41 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/05
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->mbo1 (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow()
				.mbo("Mbo1")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WorkFlowEditor.addScreen("myscreen1");
		WorkFlowEditor.addScreen("myscreen2");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "myscreen1");
		
		//set myscreen1:
		WorkFlowEditor.addMenuItem("myscreen1", new WFScreenMenuItem().name("ToScreen2")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Mbo1")
				.defaultSuccessScreen("myscreen2"));
		
		WorkFlowEditor.showPropertiesViewInSD("myscreen1");
		PropertiesView.setNewKeyBindMBOQueryRequest("key2,list,Mbo1,C2");
		
		WorkFlowEditor.addWidget("myscreen1", new WFEditBox().label("C1:")
				.newKey("c1,int"));
		
		WorkFlowEditor.addWidget("myscreen1", new WFChoice().label("C2:")
				.newKey("c2,string")
				.option("Dynamic,key2.key1,key2.key1"));
		
		//set myscreen2:
		WorkFlowEditor.addWidget("myscreen2", new WFEditBox().label("C1:")
				.key("c1"));
		WorkFlowEditor.addWidget("myscreen2", new WFEditBox().label("C2:")
				.key("c2"));
		
		WorkFlowEditor.addMenuItem("myscreen2", new WFScreenMenuItem().name("create")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Mbo1")
				.operation("create")
				.defaultSuccessScreen("myscreen1")
				.parametermapping("C1,c1")
				.parametermapping("C2,c2")
				);
		MainMenu.saveAll();
		
		//deploy wf:
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(testscript.Workflow.cfg.Cfg.deviceUser),
			customTestScript(), 
//			testscript.Workflow.cfg.Cfg.tplanScript_server_2, 
			new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
				.unwiredServer("My Unwired Server")
				.to(testscript.Workflow.cfg.Cfg.deviceUser)
				.subject("findall")));
		
		WFCustomizer.verifyResult(new WFClientResult().data(
			"id=c1,value=9|"+
			"id=c2,value=one|"+
			"id=c1,value=9"));
		
		//vp: the new record is added in the datebase:
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("C1=9");
		clause.add("C2='one'");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "wf", "Mbo1", clause)).performTest();
		
		//restore the initial table record
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "delete from Mbo1 where C1=9");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("myscreen1").setData("c1","9");
		s.screen("myscreen1").setData("c2","one");
		s.screen("myscreen1").moveTo("myscreen2").throughMenuItem("ToScreen2");
		s.screen("myscreen2").getData("c1");
		s.screen("myscreen2").getData("c2");
		s.screen("myscreen2").moveTo("myscreen1").throughMenuItem("create");
		s.screen("myscreen1").getData("c1");
		return s;
	}
}
//passed BB6T 20120214

