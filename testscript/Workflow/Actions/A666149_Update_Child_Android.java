package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.A666149_Update_Child_AndroidHelper;
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
public class A666149_Update_Child_Android extends A666149_Update_Child_AndroidHelper
{
	/**
	 * Script Name   : <b>A666149_Update_Child_Android</b>
	 * Generated     : <b>Oct 31, 2011 3:08:57 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/31
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/relationship.sql");
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/P_data.sql");
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/C_data.sql");
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->P (dba)");
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->C (dba)");
//		//
//		WN.createRelationship(new Relationship()
//			.startParameter(WN.mboPath(Cfg.projectName, "P"))
//			.target("C")
//			.mapping("pid,pid")
//			.composite("true")
//			.type(Relationship.TYPE_OTM));
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.mode(DeployOption.MODE_REPLACE)
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		//wf
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF")
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.dragMbo(Cfg.projectName, "P");
//		//
//		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
//				new WFEditBox().label("pid")
//							   .logicalType("TEXT")
//							   .newKey("pid,int")
//							   .setDefaultValue("1"));
		//
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findByPrimaryKey")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("P")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("PDetail")
				.parametermapping("pid,pid"));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			""
//		"tplan.Workflow.common.StartWF_android"
			);
		WFCustomizer.verifyResult(new WFClientResult().data("found=true"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("PDetail").throughMenuItem("findByPrimaryKey");
		s.screen("PDetail").getData("P_pid_attribKey");
		s.screen("PDetail").getData("P_pname_attribKey");
		//Open C
		s.screen("PDetail").moveTo("C").throughMenuItem("Open C");
		s.screen("C").moveTo("CDetail").throughListItem("0");
		//Open Screen C_update_instance
		s.screen("CDetail").moveTo("C_update_instance").throughMenuItem("Open Screen C_update_instance");
		s.screen("C_update_instance").setData("C_cname_attribKey", "flvcupd");
		s.screen("C_update_instance").moveTo("CDetail").throughMenuItem("Update");
		//Back
		s.screen("CDetail").moveTo("C").throughMenuItem("Back");
		s.screen("C").moveTo("PDetail").throughMenuItem("Back");
		s.screen("PDetail").moveTo("PDetail").throughMenuItem("Submit");
		// verify data
		s.screen("PDetail").moveTo("P").throughMenuItem("Back");
		s.screen("P").moveTo("Start_Screen").throughMenuItem("Cancel Screen");
		s.screen("Start_Screen").moveTo("PDetail").throughMenuItem("findByPrimaryKey");
		s.screen("PDetail").moveTo("C").throughMenuItem("Open C");
		s.screen("C").checkListItem("flvcupd", "2");
		
		return s;
	}
}
