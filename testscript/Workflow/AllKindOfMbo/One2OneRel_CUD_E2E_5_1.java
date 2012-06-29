package testscript.Workflow.AllKindOfMbo;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.AllKindOfMbo.One2OneRel_CUD_E2E_5_1Helper;
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

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class One2OneRel_CUD_E2E_5_1 extends One2OneRel_CUD_E2E_5_1Helper
{
	/**
	 * Script Name   : <b>One2OneRel_CUD_E2E_5_1</b>
	 * Generated     : <b>Oct 25, 2011 11:19:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "delete from Asdepartment where dept_id=1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//create the record to update>>>>>>>
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "insert into Asdepartment values(1, 'gg', 201)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//child'delete...
		
		
		WN.useProject(Cfg.projectName);

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
		
		WorkFlowEditor.setMenuItem("AsdepartmentDetail", new WFScreenMenuItem().name("Asdepartment_delete_instance")
				.defaultSuccessScreen("DepartmentDetail"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser),
			customTestScript()
//			, 
//			"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
			);
		
		
		WFCustomizer.verifyResult(new WFClientResult()
			.data("id=Department_dept_id_attribKey,value=1|" +
			"id=Department_dept_name_attribKey,value=ff|" +
			"id=Department_dept_head_id_attribKey,value=12|"+
			"id=Department_dept_id_attribKey,value=1"));
		
		
		
//		sleep(2);
//		java.util.List<String> clause = new ArrayList<String>();
//		clause.add("dept_id=1");
//		clause.add("dept_name='gg'");
//		clause.add("dept_head_id=201");
//		vpManual("dbresult", 0, CDBUtil.getRecordCount("localhost", "wf", "Asdepartment", clause)).performTest();
//		
//		
//		//create the record which is deleted in this case
//		try {
//			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "insert into Asdepartment values(1, 'gg', 201)");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	//finished Child's delete
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		
		s.screen("Start").setData("dept_id","1");
		s.screen("Start").moveTo("DepartmentDetail").throughMenuItem("findByid");
		
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		
		s.screen("DepartmentDetail").moveTo("AsdepartmentDetail").throughMenuItem("Open AsdepartmentDetail");
		s.screen("AsdepartmentDetail").moveTo("DepartmentDetail").throughMenuItem("Asdepartment_delete_instance");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		return s;
		
	}
}
//passed 20120120