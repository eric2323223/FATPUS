   package testscript.Workflow.ConditionalScreen;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.ConditionalScreen.CdtnScr_Result_Alltypes_1_1_E2EHelper;
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
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Modifications;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;

/**
* Description : Functional Test Script
* @author ffan
*/
public class CdtnScr_Result_Alltypes_1_1_E2E extends CdtnScr_Result_Alltypes_1_1_E2EHelper
{
	/**
	 * Script Name   : <b>CdtnScr_Result_Alltypes_1_1_E2E</b>
	 * Generated     : <b>Mar 1, 2012 1:29:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/01
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{		
		// TODO Insert code here
		
		//restore the initial table record
		String sqldelete = "delete from AllDT where int1=1";
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), sqldelete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//restore the initial table record
		String sqlinsert = 
			"insert into dba.AllDT values(1,'1','1',1,'2012-01-03','2012-01-03 00:00:00.0','12:25:13',1.000,1.0,1.0,0,'1',1);";
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), sqlinsert);
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
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllDT");
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("int1:")
				.newKey("int1,int"));
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findByID")
				.type("Online Request")
				.mbo("AllDT")
				.objectQuery("findByPrimaryKey")
				.project(Cfg.projectName)
				.defaultSuccessScreen("AllDTDetail")
				.parametermapping("int1,int1")
				);
		
		WorkFlowEditor.setMenuItem("AllDTdeleteinstance", 	new WFScreenMenuItem().name("Delete")
				.type("Online Request")
				.defaultSuccessScreen("AllDTDetail"));
		
		PropertiesView.addConditionTableOfMenuItem("c1", "Start");
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser)
				.deployToServer("true")
				.unwiredServer("My Unwired Server"), script()
//				, 
//				testscript.Workflow.cfg.Cfg.tplanScript_client_1
				);
		
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=AllDT_int1_attribKey,value=1|"+
				"id=int1,value=1"));	
		
		//vp2:verify the update record  has added into backend db in state
		vpManual("db",0,DBUtil.getDB("select * from AllDT where int1=1 and string1='1'")).performTest();
	
		//restore the initial table record
		String sql = 
			"insert into dba.AllDT values(1,'1','1',1,'2012-01-03','2012-01-03 00:00:00.0','12:25:13',1.000,1.0,1.0,0,'1',1);";
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private CustomJsTestScript script() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setConditionScreenRule(Cfg.CdtnScr_Result_Alltypes_1_1_E2E);
		s.screen("Start").setData("int1","1");
		s.screen("Start").moveTo("AllDTDetail").throughMenuItem("findByID");
		s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
		s.screen("AllDTDetail").moveTo("AllDTdeleteinstance").throughMenuItem("Open AllDTdeleteinstance");
		s.screen("AllDTdeleteinstance").moveTo("Start").throughMenuItem("Delete");
		s.screen("Start").getData("int1");
		return s;
	}
}
//passed BB6T 20120301