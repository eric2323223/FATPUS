package testscript.Workflow.AllKindOfMbo;
import java.sql.SQLException;
import java.util.ArrayList;

import resources.testscript.Workflow.AllKindOfMbo.One2OneRel_CUD_E2E_2_1Helper;
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
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
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
public class One2OneRel_CUD_E2E_2_1 extends One2OneRel_CUD_E2E_2_1Helper
{
	/**
	 * Script Name   : <b>One2OneRel_CUD_E2E_2_1</b>
	 * Generated     : <b>Oct 25, 2011 11:18:30 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		

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
		
		
		//create the record to update>>>>>>>
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), "insert into department values(3, 'three', 123)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//parent's update....
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
		
		MainMenu.saveAll();
		sleep(1);
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findByid")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("DepartmentDetail")
				.parametermapping("dept_id,dept_id"));
		MainMenu.saveAll();
		
		WorkFlowEditor.setMenuItem("Departmentupdateinstance", new WFScreenMenuItem().name("Update")
				.defaultSuccessScreen("Start"));
		
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
			.data("id=Department_dept_id_attribKey,value=3|" +
			"id=Department_dept_name_attribKey,value=three|" +
			"id=Department_dept_head_id_attribKey,value=123"));
		sleep(2);
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("dept_id=3");
		clause.add("dept_name='my'");
		clause.add("dept_head_id=902");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "wf", "Department", clause)).performTest();
	}
	
	//finished parent's Update
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("dept_id","3");
		s.screen("Start").moveTo("DepartmentDetail").throughMenuItem("findByid");
		
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		
		s.screen("DepartmentDetail").moveTo("Departmentupdateinstance").throughMenuItem("Open Departmentupdateinstance");
		s.screen("Departmentupdateinstance").setData("Department_dept_name_attribKey","my");
		s.screen("Departmentupdateinstance").setData("Department_dept_head_id_attribKey","902");
		s.screen("Departmentupdateinstance").moveTo("Start").throughMenuItem("Update");
		s.screen("Start").menuItem("Cancel");
		return s;
	}
}
//passed in 20120120