package testscript.Workflow.ConditionalScreen;

import java.util.ArrayList;

import com.sybase.automation.framework.common.CDBUtil;

import resources.testscript.Workflow.ConditionalScreen.CdtnScr_Result_Alltypes_1_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.Modifications;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description : Functional Test Script
 * 
 * @author eric
 */
public class CdtnScr_Result_Alltypes_1_E2E extends CdtnScr_Result_Alltypes_1_E2EHelper {
	/**
	 * Script Name : <b>CdtnScr_Result_Alltypes_1_E2E</b> Generated : <b>Oct 27,
	 * 2011 7:40:33 PM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2011/10/27
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF")
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
//		WorkFlowEditor.addWidget("Start", new WFEditBox().label("dept_id")
//				.newKey("dept_id,int"));
//		
//		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
//				.name("findByID")
//				.type("Online Request")
//				.mbo("Department")
//				.objectQuery("findByPrimaryKey")
//				.project(Cfg.projectName)
//				.defaultSuccessScreen("DepartmentDetail")
//				.parametermapping("dept_id,dept_id")
//				);
//		
//		WorkFlowEditor.setMenuItem("Departmentupdateinstance", 	new WFScreenMenuItem().name("Update")
//				.type("Online Request")
//				.defaultSuccessScreen("DepartmentDetail"));
//		
//		PropertiesView.addConditionTableOfMenuItem("c1", "Start");
//		
//		WFCustomizer.runTest(new WorkFlowPackage()
//				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
//				.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser)
//				.deployToServer("true")
//				.unwiredServer("My Unwired Server"), script(), 
//				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
//		
//		//vp:data in device
//		WFCustomizer.verifyResult(new WFClientResult().data(
//				"id=dept_id,value=100"));	
//		
//		//vp2:verify the update record  has added into backend db in state
//		java.util.List<String> clause = new ArrayList<String>();
//		clause.add("dept_id=100");
//		clause.add("dept_name='new'");
//		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("localhost", "wf", "Department", clause)).performTest();
//	}
//		
//	
//	private CustomJsTestScript script() {
//		CustomJsTestScript s = new CustomJsTestScript();
//		s.setConditionScreenRule(Cfg.CdtnScr_Result_Alltypes_1_E2E);
//		s.screen("Start").setData("dept_id","100");
//		s.screen("Start").moveTo("DepartmentDetail").throughMenuItem("findByID");
//		s.screen("DepartmentDetail").moveTo("Departmentupdateinstance").throughMenuItem("Open Departmentupdateinstance");
//		s.screen("Departmentupdateinstance").setData("Department_dept_name_attribKey", "new");
//		s.screen("Departmentupdateinstance").moveTo("Start").throughMenuItem("Update");
//		s.screen("Start").getData("dept_id");
//		return s;
	}
}
//passed BB6T 20120301