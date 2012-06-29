package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.One2One_E2E_Act_OneCUD_AndroidHelper;
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
import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class One2One_E2E_Act_OneCUD_Android extends One2One_E2E_Act_OneCUD_AndroidHelper
{
	/**
	 * Script Name   : <b>One2One_E2E_Act_OneCUD_Android</b>
	 * Generated     : <b>Oct 26, 2011 1:44:24 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/26
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
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "P");
		WorkFlowEditor.link("Start Screen", "P_create");
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("pid")
							   .newKey("pid,int")
							   .setDefaultValue("1"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findByPk")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("P")
				.objectQuery("findByPrimaryKey")
				.parametermapping("pid,pid")
				.defaultSuccessScreen("PDetail"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("P")
				.objectQuery("findAll")
				.defaultSuccessScreen("P"));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android");
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=P_pid_attribKey,value=1|"+
				"id=P_pname_attribKey,value=1|"+ 
				"id=P_pid_attribKey,value=1|"+
				"id=P_pname_attribKey,value=flv|"+ 
				"found=false"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("P_create").throughMenuItem("Open P_create");
		s.screen("P_create").setData("P_create_pid_paramKey", "9");
		s.screen("P_create").setData("P_create_pname_paramKey", "9");
		s.screen("P_create").menuItem("Create");
		s.screen("P_create").moveTo("Start_Screen").throughMenuItem("Cancel");
		//
		s.screen("Start_Screen").moveTo("PDetail").throughMenuItem("findByPk");
		s.screen("PDetail").getData("P_pid_attribKey");
		s.screen("PDetail").getData("P_pname_attribKey");
		s.screen("PDetail").moveTo("P_update_instance").throughMenuItem("Open P_update_instance");
		s.screen("P_update_instance").setData("P_pname_attribKey", "flv");
		s.screen("P_update_instance").menuItem("Update");
		s.screen("P_update_instance").moveTo("PDetail").throughMenuItem("Cancel");
		s.screen("PDetail").moveTo("Start_Screen").throughMenuItem("Back");
		//
		s.screen("Start_Screen").moveTo("PDetail").throughMenuItem("findByPk");
		s.screen("PDetail").getData("P_pid_attribKey");
		s.screen("PDetail").getData("P_pname_attribKey");
		s.screen("PDetail").moveTo("P_delete_instance").throughMenuItem("Open P_delete_instance");
		s.screen("P_delete_instance").menuItem("Delete");
		s.screen("P_delete_instance").moveTo("PDetail").throughMenuItem("Cancel");
		s.screen("PDetail").moveTo("Start_Screen").throughMenuItem("Back");
		// Verify data
		s.screen("Start_Screen").moveTo("P").throughMenuItem("findAll");
		s.screen("P").checkListItem("1", "0");
		return s;
	}
}
