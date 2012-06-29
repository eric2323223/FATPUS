package testscript.Workflow.AllKindOfMbo;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.AllKindOfMbo.One2OneRel_CUD_E2E_1_1Helper;
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
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class One2OneRel_CUD_E2E_1_1 extends One2OneRel_CUD_E2E_1_1Helper
{
	/**
	 * Script Name   : <b>One2OneRel_CUD_E2E_1_1</b>
	 * Generated     : <b>Oct 25, 2011 11:17:55 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		//delete the date which will be created in the following code
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "delete from Department where dept_id=3");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/AllKindOfMbo/createMyTable/asdepartment.sql");

		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->asdepartment (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("Asdepartment")
			.mapping("dept_id,dept_id")
			.composite("true")
			.bidirectional("true")
			.type(Relationship.TYPE_OTO));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("dept_id")
				.newKey("dept_id,int"));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findByid")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("DepartmentDetail")
				.parametermapping("dept_id,dept_id"));
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Open Departmentcreate")
				.type("Open")
				.screen("Departmentcreate"));
		WorkFlowEditor.setMenuItem("Departmentcreate", new WFScreenMenuItem().name("Create")
				.defaultSuccessScreen("Start"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser),
			customTestScript()
//			, 
//			"tplan.Workflow.iconcommon.BB.myWF_icon.Script"       //used BB6T
			
			);
		sleep(2);
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("dept_id=3");
		clause.add("dept_name='three'");
		clause.add("dept_head_id=123");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "wf", "Department", clause)).performTest();
	
	}
	
	//finished parent's create...
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("Departmentcreate").throughMenuItem("Open Departmentcreate");
		
		s.screen("Departmentcreate").setData("Department_create_dept_id_paramKey","3");
		s.screen("Departmentcreate").setData("Department_create_dept_name_paramKey","three");
		s.screen("Departmentcreate").setData("Department_create_dept_head_id_paramKey","123");
		
		s.screen("Departmentcreate").moveTo("Start").throughMenuItem("Create");
		s.screen("Start").menuItem("Cancel");
		return s;
	}
}
	//passed in 20120120