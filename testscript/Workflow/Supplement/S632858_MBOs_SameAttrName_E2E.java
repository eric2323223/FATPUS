package testscript.Workflow.Supplement;
import java.util.ArrayList;

import resources.testscript.Workflow.Supplement.S632858_MBOs_SameAttrName_E2EHelper;
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
import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class S632858_MBOs_SameAttrName_E2E extends S632858_MBOs_SameAttrName_E2EHelper
{
	/**
	 * Script Name   : <b>S632858_MBOs_SameAttrName</b>
	 * Generated     : <b>Mar 11, 2012 7:43:07 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/11
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
		//new create a table with the same attributes of department:
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Supplement/conf/mydept.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)", 10,10);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->mydept (dba)", 100,10);
		
		WN.createRelationship(new Relationship()
				.startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.target("Mydept")
				.mapping("dept_id,dept_id")
				.composite("true")
				.type(Relationship.TYPE_OTO));
	
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
			.server("My Unwired Server")
			.mode(DeployOption.MODE_REPLACE)
			.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
			.name(Cfg.wfName)
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName,"Department");
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript()
//			, 
//			Cfg.tplanScript_client_1
			);

		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Department_dept_id_attribKey,value=100|"+
				"id=Mydept_dept_id_attribKey,value=100|"+
				"id=Mydept_dept_name_attribKey,value=one"));
		
		//vp2:verify the update record  has added into backend db 
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("dept_id=100");
		clause.add("dept_head_id=123");
		vpManual("dbresult", true,1 == CDBUtil.getRecordCount("localhost", "wf", "Mydept", clause)).performTest();
		
	}
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("Department").throughMenuItem("findall");
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("100->0");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").moveTo("MydeptDetail").throughMenuItem("Open MydeptDetail");
		s.screen("MydeptDetail").getData("Mydept_dept_id_attribKey");
		s.screen("MydeptDetail").getData("Mydept_dept_name_attribKey");
		s.screen("MydeptDetail").moveTo("MydeptDetailupdateinstance").throughMenuItem("Open Mydeptupdateinstance");
		s.screen("Mydeptupdateinstance").setData("Mydept_dept_head_id_attribKey", "123");
		s.screen("Mydeptupdateinstance").menuItem("Update");
		return s;
	}
}
//passed BB6T 20120312