package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.A632557_ACT_CUD_DiffTZ_E2E_AndroidHelper;
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
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Operation;
import component.entity.model.OperationParameter;
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class A632557_ACT_CUD_DiffTZ_E2E_Android extends A632557_ACT_CUD_DiffTZ_E2E_AndroidHelper
{
	/**
	 * Script Name   : <b>A632557_ACT_CUD_DiffTZ_E2E_Android</b>
	 * Generated     : <b>Oct 26, 2011 3:36:12 AM</b>
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
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/DiffTZ.sql");
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->DiffTZ (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "DiffTZ");
		//set operation
		mbo.setOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "DiffTZ"))
				.name("delete")
				.sqlQuery("DELETE FROM sampledb.dba.DiffTZ WHERE (dateformat(d, 'yyyy-mm-dd hh:mm:ss.ss') = dateformat(:d, 'yyyy-mm-dd hh:mm:ss.ss')) AND (dateformat(e, 'yyyy-mm-dd hh:mm:ss.ss') = dateformat(:e, 'yyyy-mm-dd hh:mm:ss.ss'))"));
		mbo.setOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "DiffTZ"))
				.name("update")
				.sqlQuery("UPDATE sampledb.dba.DiffTZ SET b=:b, c=:c, d=:d,e=:e " + 
						  " WHERE (a = :a) AND (dateformat(d, 'yyyy-mm-dd hh:mm:ss.ss') = dateformat(:d, 'yyyy-mm-dd hh:mm:ss.ss')) AND (dateformat(e, 'yyyy-mm-dd hh:mm:ss.ss') = dateformat(:e, 'yyyy-mm-dd hh:mm:ss.ss'))"));
		//
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "DiffTZ");
		//
		WorkFlowEditor.link("Start Screen", "DiffTZ_create");
		WorkFlowEditor.link("Start Screen", "DiffTZ_delete");
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("id")
							   .newKey("id,int")
							   .setDefaultValue("6"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findByPk")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("DiffTZ")
				.objectQuery("findByPrimaryKey")
				.parametermapping("a,id")
				.defaultSuccessScreen("DiffTZDetail"));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"");
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=DiffTZ_a_attribKey,value=6|"+
				"id=DiffTZ_d_attribKey,value=2011-10-27T10:10:10|"+ 
				"id=DiffTZ_e_attribKey,value=2011-10-27T10:10:10|" + 
				"id=DiffTZ_c_attribKey,value=2011-01-27"));
		//
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("d='2011-10-27T10:10:10'");
		clause.add("e='2011-10-27T10:10:10'");
		clause.add("LOGICAL_DEL=1");
		vpManual("dbresult", true, CDBUtil.getRecordCount("flv-vmpc", "wf", "Department", clause) > 0).performTest();
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("DiffTZ_create").throughMenuItem("Open DiffTZ_create");
		s.screen("DiffTZ_create").setData("DiffTZ_create_a_paramKey", "6");
		s.screen("DiffTZ_create").setData("DiffTZ_create_b_paramKey", "10:10:10");
		s.screen("DiffTZ_create").setData("DiffTZ_create_c_paramKey", "2011-10-27");
		s.screen("DiffTZ_create").setData("DiffTZ_create_d_paramKey", "2011-10-27T10:10:10");
		s.screen("DiffTZ_create").setData("DiffTZ_create_e_paramKey", "2011-10-27T10:10:10");
		s.screen("DiffTZ_create").moveTo("DiffTZ_create").throughMenuItem("Create");
		s.screen("DiffTZ_create").moveTo("Start_Screen").throughMenuItem("Cancel");
		//
		s.screen("Start_Screen").moveTo("DiffTZDetail").throughMenuItem("findByPk");
		s.screen("DiffTZDetail").getData("DiffTZ_a_attribKey");
		s.screen("DiffTZDetail").getData("DiffTZ_d_attribKey");
		s.screen("DiffTZDetail").getData("DiffTZ_e_attribKey");
		//
		s.screen("DiffTZDetail").moveTo("DiffTZ_update_instance").throughMenuItem("Open DiffTZ_update_instance");
		s.screen("DiffTZ_update_instance").setData("DiffTZ_c_attribKey", "2011-01-27");
		s.screen("DiffTZ_update_instance").moveTo("DiffTZ_update_instance").throughMenuItem("Update");
		s.screen("DiffTZ_update_instance").moveTo("DiffTZDetail").throughMenuItem("Cancel");
		s.screen("DiffTZDetail").moveTo("Start_Screen").throughMenuItem("Back");
		//
		s.screen("Start_Screen").moveTo("DiffTZDetail").throughMenuItem("findByPk");
		s.screen("DiffTZDetail").getData("DiffTZ_c_attribKey");
		//
		s.screen("Start_Screen").moveTo("DiffTZ_delete").throughMenuItem("Open DiffTZ_delete");
		s.screen("DiffTZ_create").setData("DiffTZ_delete_d_paramKey", "2011-10-27T10:10:10");
		s.screen("DiffTZ_create").setData("DiffTZ_delete_e_paramKey", "2011-10-27T10:10:10");
		
		return s;
	}
}