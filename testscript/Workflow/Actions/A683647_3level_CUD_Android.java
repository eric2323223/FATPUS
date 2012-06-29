package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.A683647_3level_CUD_AndroidHelper;
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
import com.sybase.automation.framework.widget.DOF;

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
public class A683647_3level_CUD_Android extends A683647_3level_CUD_AndroidHelper
{
	/**
	 * Script Name   : <b>A683647_3level_CUD_Android</b>
	 * Generated     : <b>Nov 3, 2011 3:04:14 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/03
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
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/cc_ddl.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/P_data.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/C_data.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/CC_data.sql");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->P (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->C (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->CC (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "P"))
			.target("C")
			.mapping("pid,pid")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "C"))
			.target("CC")
			.mapping("cid,cid")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "P");
		DOF.getWFScreenFigure(DOF.getRoot(), "CC").click();
		WorkFlowEditor.link("C_add", "CC");
		DOF.getWFScreenFigure(DOF.getRoot(), "P_create").click();
		WorkFlowEditor.link("Start Screen", "P_create");
		WorkFlowEditor.link("P_create", "C");
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("pid")
							   .logicalType("TEXT")
							   .newKey("pid,int")
							   .setDefaultValue("6"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findByPrimaryKey")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("P")
				.objectQuery("findByPrimaryKey")
				.parametermapping("pid,pid")
				.defaultSuccessScreen("PDetail"));
		WorkFlowEditor.setMenuItem("P_create", new WFScreenMenuItem()
				.name("Create")
				.defaultSuccessScreen("Start Screen"));
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
				"id=P_pid_attribKey,value=6|"+
				"id=P_pname_attribKey,value=6|"+ 
				"id=C_cid_attribKey,value=6|" + 
				"id=C_pid_attribKey,value=6|" +
				"id=C_cname_attribKey,value=6|"+ 
				"found=true"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("P_create").throughMenuItem("Open P_create");
		s.screen("P_create").setData("P_create_pid_paramKey", "6");
		s.screen("P_create").setData("P_create_pname_paramKey", "6");
		s.screen("P_create").moveTo("C").throughMenuItem("Open C");
		s.screen("C").moveTo("C_add").throughMenuItem("Add");
		s.screen("C_add").setData("C_create_cid_paramKey", "6");
		s.screen("C_add").setData("C_create_pid_paramKey", "6");
		s.screen("C_add").setData("C_create_cname_paramKey", "6");
		s.screen("C_add").moveTo("CC").throughMenuItem("Open CC");
		s.screen("CC").moveTo("CC_add").throughMenuItem("Add");
		s.screen("CC_add").setData("CC_create_ccid_paramKey", "6");
		s.screen("CC_add").setData("CC_create_cid_paramKey", "6");
		s.screen("CC_add").setData("CC_create_ccname_paramKey", "6");
		//Create CC record
		s.screen("CC_add").moveTo("CC").throughMenuItem("Create");
		s.screen("CC").moveTo("C_add").throughMenuItem("Back");
		//Create C record
		s.screen("C_add").moveTo("C").throughMenuItem("Create");
		s.screen("C").moveTo("P_create").throughMenuItem("Back");
		//Create P record
		s.screen("P_create").moveTo("Start Screen").throughMenuItem("Create");
		s.screen("Start_Screen").moveTo("PDetail").throughMenuItem("findByPrimaryKey");
		s.screen("PDetail").getData("P_pid_attribKey");
		s.screen("PDetail").getData("P_pname_attribKey");
		s.screen("PDetail").moveTo("C").throughMenuItem("Open C");
		s.screen("C").moveTo("CDetail").throughListItem("6->0");
		s.screen("CDetail").getData("C_cid_attribKey");
		s.screen("CDetail").getData("C_pid_attribKey");
		s.screen("CDetail").getData("C_cname_attribKey");
		s.screen("CDetail").moveTo("CC").throughMenuItem("Open CC");
		s.screen("CDetail").checkListItem("6", "0");
		
		return s;
	}
}