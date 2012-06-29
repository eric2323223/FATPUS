package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Relation_E2E_AndroidHelper;
import testscript.Workflow.Deploy.workflow_Wizard_invoke;
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
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;
import component.view.properties.workflow.WFControlObject;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class ACT_Relation_E2E_Android extends ACT_Relation_E2E_AndroidHelper
{
	/**
	 * Script Name   : <b>ACT_Relation_E2E_Android</b>
	 * Generated     : <b>Oct 25, 2011 2:34:17 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
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
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("P")
				.objectQuery("findAll")
				.defaultSuccessScreen("P"));
		//
		Object obj = WFControlObject.getRadioButton("P", "Submit", "Invoke &parent update").invoke("getEnabled");
		vpManual("parentupdate", true, obj.equals(true)).performTest();
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
				"id=P_pid_attribKey,value=2|"+
				"id=P_pname_attribKey,value=2|"+ 
				"found=true|" + 
				"found=true|" + 
				"found=false|" + 
				"found=true|" + 
				"found=false|" + 
				"found=true"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("P").throughMenuItem("findAll");
		s.screen("P").moveTo("PDetail").throughListItem("2->0");
		s.screen("PDetail").getData("P_pid_attribKey");
		s.screen("PDetail").getData("P_pname_attribKey");
		//Open C
		s.screen("PDetail").moveTo("C").throughMenuItem("Open C");
		//Add
		s.screen("C").moveTo("C_add").throughMenuItem("Add");
		s.screen("C_add").setData("C_create_cid_paramKey", "5");
		s.screen("C_add").setData("C_create_pid_paramKey", "2");
		s.screen("C_add").setData("C_create_cname_paramKey", "5");
		s.screen("C_add").moveTo("C").throughMenuItem("Create");
		//--------
		s.screen("C").moveTo("CDetail").throughListItem("11->0");
		//C_delete_instance
		s.screen("CDetail").moveTo("C").throughMenuItem("C_delete_instance");
		s.screen("C").moveTo("CDetail").throughListItem("12->0");
		//Open Screen C_update_instance
		s.screen("CDetail").moveTo("C_update_instance").throughMenuItem("Open Screen C_update_instance");
		s.screen("C_update_instance").setData("C_cname_attribKey", "flv2");
		s.screen("C_update_instance").moveTo("CDetail").throughMenuItem("Update");
		//Back
		s.screen("CDetail").moveTo("C").throughMenuItem("Back");
		s.screen("C").moveTo("PDetail").throughMenuItem("Back");
		s.screen("PDetail").moveTo("PDetail").throughMenuItem("Submit");;
		s.screen("PDetail").moveTo("P").throughMenuItem("Back");
		s.screen("P").moveTo("Start_Screen").throughMenuItem("Cancel Screen");
		//Verify data for the upon######################
		s.screen("Start_Screen").moveTo("P").throughMenuItem("findAll");
		s.screen("P").moveTo("PDetail").throughListItem("2->0");
		s.screen("PDetail").moveTo("C").throughMenuItem("Open C");
		s.screen("C").checkListItem("5", "0");
		s.screen("C").checkListItem("flv2", "2");
		s.screen("C").checkListItem("11", "0");
		//
		s.screen("C").moveTo("PDetail").throughMenuItem("Back");
		s.screen("PDetail").moveTo("P").throughMenuItem("Back");
		s.screen("P").moveTo("Start_Screen").throughMenuItem("Cancel Screen");
		s.screen("Start_Screen").moveTo("P_create").throughMenuItem("Open P_create");
		s.screen("P_create").setData("P_create_pid_paramKey", "4");
		s.screen("P_create").setData("P_create_pname_paramKey", "4");
		s.screen("P_create").moveTo("P_create").throughMenuItem("Create");
		s.screen("P_create").moveTo("Start_Screen").throughMenuItem("Cancel");
		s.screen("Start_Screen").moveTo("P").throughMenuItem("findAll");
		s.screen("P").checkListItem("4", "0");
		//
		s.screen("P").moveTo("PDetail").throughListItem("4->0");
		s.screen("PDetail").moveTo("P_delete_instance").throughMenuItem("Open P_delete_instance");
		s.screen("P_delete_instance").moveTo("P_delete_instance").throughMenuItem("Delete");
		s.screen("P_delete_instance").moveTo("PDetail").throughMenuItem("Cancel");
		s.screen("PDetail").moveTo("P").throughMenuItem("Back");
		s.screen("P").moveTo("Start_Screen").throughMenuItem("Cancel Screen");
		s.screen("Start_Screen").moveTo("P").throughMenuItem("findAll");
		s.screen("P").checkListItem("4", "0");
		//
		s.screen("P").moveTo("PDetail").throughListItem("3->0");
		s.screen("PDetail").moveTo("P_update_instance").throughMenuItem("Open P_update_instance");
		s.screen("P_update_instance").setData("P_pname_attribKey", "flv");
		s.screen("P_update_instance").moveTo("P_update_instance").throughMenuItem("Update");
		s.screen("P_update_instance").moveTo("PDetail").throughMenuItem("Cancel");
		s.screen("PDetail").moveTo("P").throughMenuItem("Back");
		s.screen("P").moveTo("Start_Screen").throughMenuItem("Cancel Screen");
		s.screen("Start_Screen").moveTo("P").throughMenuItem("findAll");
		s.screen("P").checkListItem("flv", "1");
		
		return s;
	}
}
