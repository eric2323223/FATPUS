package testscript.Workflow.TupleSupport;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.TupleSupport.Tuple_DynamicChoice_to_Editbox_3_E2EHelper;
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
public class Tuple_DynamicChoice_to_Editbox_3_E2E extends Tuple_DynamicChoice_to_Editbox_3_E2EHelper
{
	/**
	 * Script Name   : <b>Tuple_DynamicChoice_to_Editbox_3_1_E2E</b>
	 * Generated     : <b>Nov 5, 2011 3:42:16 AM</b>
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
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow()
				.mbo("Department")
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
				.mbo("Department")
				.defaultSuccessScreen("myscreen2"));
		 
		WorkFlowEditor.showPropertiesViewInSD("myscreen1");
		PropertiesView.setNewKeyBindMBOQueryRequest("key2,list,Department,dept_name,dept_head_id");
		
		WorkFlowEditor.addWidget("myscreen1", new WFEditBox().label("id1:")
				.newKey("id,int"));
		WorkFlowEditor.addWidget("myscreen1", new WFChoice().label("name1:")
				.newKey("nam,string")
				.option("Dynamic,key2.key1,key2.key1"));
		WorkFlowEditor.addWidget("myscreen1", new WFChoice().label("head_id1:")
				.newKey("head_id,int")
				.option("Dynamic,key2.key2,key2.key2"));
		
		//set myscreen2:
		WorkFlowEditor.addWidget("myscreen2", new WFEditBox().label("id2:")
				.key("id"));
		WorkFlowEditor.addWidget("myscreen2", new WFEditBox().label("name2:")
				.key("nam"));
		WorkFlowEditor.addWidget("myscreen2", new WFEditBox().label("head_id2:")
				.key("head_id"));
		WorkFlowEditor.addMenuItem("myscreen2", new WFScreenMenuItem().name("create")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("create")
				.defaultSuccessScreen("myscreen1")
				.parametermapping("dept_id,id")
				.parametermapping("dept_name,nam")
				.parametermapping("dept_head_id,head_id")
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
			"id=id,value=134|"+
			"id=nam,value=R & D|"+
			"id=head_id,value=902|"+
			"id=id,value=134"));
	
	//vp: the create record is added in the datebase:
	java.util.List<String> clause = new ArrayList<String>();
	clause.add("dept_id=134");
	clause.add("dept_name='R & D'");
	clause.add("dept_head_id=902");
	vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "wf", "Department", clause)).performTest();
	
	//restore the initial table record
	try {
		DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "delete from Department where dept_id=134");
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
		s.screen("myscreen1").setData("id","134");
		s.screen("myscreen1").setData("nam","R & D");
		s.screen("myscreen1").setData("head_id","902");
		s.screen("myscreen1").moveTo("myscreen2").throughMenuItem("ToScreen2");
		s.screen("myscreen2").getData("id");
		s.screen("myscreen2").getData("nam");
		s.screen("myscreen2").getData("head_id");
		s.screen("myscreen2").moveTo("myscreen1").throughMenuItem("create");
		s.screen("myscreen1").getData("id");
		return s;
	}
}
//passed BB6T 20120214