package testscript.Workflow.Screens;
import java.util.ArrayList;

import com.sybase.automation.framework.common.CDBUtil;

import resources.testscript.Workflow.Screens.DndMBO_OP_create_E2EHelper;
import testscript.Workflow.cfg.Cfg;

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
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DndMBO_OP_create_E2E extends DndMBO_OP_create_E2EHelper
{
	/**
	 * Script Name   : <b>DndMBO_OP_create_E2E</b>
	 * Generated     : <b>Sep 9, 2011 12:58:44 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/09
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow().mbo("AllDT")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall")
				);
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllDT");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "AllDT");
		WorkFlowEditor.addMenuItem("AllDTDetail", new WFScreenMenuItem().name("Open AllDTcreate").type("Open").screen("AllDTcreate"));
		WorkFlowEditor.setMenuItem("AllDTcreate", new WFScreenMenuItem().name("Create").defaultSuccessScreen("AllDTDetail"));
		
		MainMenu.saveAll();
		
		
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser(Cfg.deviceUser)
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScript(), 
//		"tplan.Workflow.iconcommon.BB.server_dt_icon.Script",
		new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to(Cfg.deviceUser)
					.subject("findall")));
		
		
		WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=2|"+
				"id=AllDT_int1_attribKey,value=1"));
		
		//need to check data the backend DB
		sleep(1);
		vpManual("db",0,AddlistviewRow_E2E.getDB("select * from AllDT where int1=3 and string1 = '3' and bname ='BNewthree'")).performTest();
		
		
		
		
//		//vp2:verify the new record  has added into backend db 
//		java.util.List<String> clause = new ArrayList<String>();
//		clause.add("int1=3");
//		clause.add("string1='3'");
//		vpManual("dbresult",1,CDBUtil.getRecordCount("Localhost",Cfg.projectName, "AllDT", clause)).performTest();
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("AllDT").getListItemsCount();
		s.screen("AllDT").moveTo("AllDTDetail").throughListItem("0");
		s.screen("AllDTDetail").moveTo("AllDTcreate").throughMenuItem("Open AllDTcreate");
		
		s.screen("AllDTcreate").setData("AllDT_create_int1_paramKey", "3");
		s.screen("AllDTcreate").setData("AllDT_create_string1_paramKey", "3");
		s.screen("AllDTcreate").setData("AllDT_create_string2_paramKey", "3");
		
		s.screen("AllDTcreate").setData("AllDT_create_long1_paramKey", "3");
		s.screen("AllDTcreate").setData("AllDT_create_date1_paramKey", "2012-02-02");
		
		s.screen("AllDTcreate").setData("AllDT_create_datetime1_paramKey", "2012-02-02T20:27:57");
		s.screen("AllDTcreate").setData("AllDT_create_time1_paramKey", "20:27:57");
		s.screen("AllDTcreate").setData("AllDT_create_decimal1_paramKey", "3");
		
		s.screen("AllDTcreate").setData("AllDT_create_float1_paramKey", "3");
		s.screen("AllDTcreate").setData("AllDT_create_double1_paramKey", "3");
		s.screen("AllDTcreate").setData("AllDT_create_bool1_paramKey", "false");
		
		s.screen("AllDTcreate").setData("AllDT_create_byte1_paramKey", "3");
		s.screen("AllDTcreate").setData("AllDT_create_short1_paramKey", "3");
		
		s.screen("AllDTcreate").moveTo("AllDTDetail").throughMenuItem("Create");
		s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
		return s;
	}
}
//passed BB6T 20120203
