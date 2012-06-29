package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.One2One_E2E_Act_TwoUpdate_AndroidHelper;
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
public class One2One_E2E_Act_TwoUpdate_Android extends One2One_E2E_Act_TwoUpdate_AndroidHelper
{
	/**
	 * Script Name   : <b>One2One_E2E_Act_TwoUpdate_Android</b>
	 * Generated     : <b>Oct 26, 2011 2:58:12 AM</b>
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
		WorkFlowEditor.dragOperation(Cfg.projectName, "C", "update");
		
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("cid")
							   .newKey("cid,int")
							   .setDefaultValue("1"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findByPk")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("C")
				.objectQuery("findByPrimaryKey")
				.parametermapping("cid,cid")
				.defaultSuccessScreen("C_update_instance"));
		WorkFlowEditor.setMenuItem("C_update_instance", new WFScreenMenuItem()
			.name("Update")
			.type("Online Request"));
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
				"id=C_pid_attribKey,value=1|"+
				"id=C_cname_attribKey,value=11|"+ 
				"id=C_cid_attribKey,value=1|" + 
				"id=C_name_attribKey,value=flv"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("C_update_instance").throughMenuItem("findByPk");
		s.screen("C_update_instance").getData("C_pid_attribKey");
		s.screen("C_update_instance").getData("C_cname_attribKey");
		s.screen("C_update_instance").getData("C_cid_attribKey");
		//
		s.screen("C_update_instance").setData("C_cname_attribKey", "flv");
		s.screen("C_update_instance").menuItem("Update");
		//
		s.screen("C_update_instance").moveTo("Start_Screen").throughMenuItem("Cancel");
		s.screen("Start_Screen").moveTo("C_update_instance").throughMenuItem("findByPk");
		s.screen("C_update_instance").getData("C_cname_attribKey");
		return s;
	}
}


