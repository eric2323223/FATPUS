package testscript.Workflow.AllKindOfMbo;
import java.util.ArrayList;

import resources.testscript.Workflow.AllKindOfMbo.One2OneRel_CUD_E2E_4_1Helper;
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

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class One2OneRel_CUD_E2E_4_1 extends One2OneRel_CUD_E2E_4_1Helper
{
	/**
	 * Script Name   : <b>One2OneRel_CUD_E2E_4_1</b>
	 * Generated     : <b>Oct 25, 2011 11:19:28 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//child's update
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
		sleep(1);
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findByid")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("DepartmentDetail")
				.parametermapping("dept_id,dept_id"));
		MainMenu.saveAll();
		
		WorkFlowEditor.setMenuItem("Asdepartmentupdateinstance", new WFScreenMenuItem().name("Update")
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
			.data("id=Department_dept_id_attribKey,value=1|" +
			"id=Department_dept_name_attribKey,value=ff|" +
			"id=Department_dept_head_id_attribKey,value=12"));
		
		sleep(2);
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("dept_id=1");
		clause.add("dept_name='gg'");
		clause.add("dept_head_id=201");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "wf", "Asdepartment", clause)).performTest();
	
	}
	
	//finished Child's update
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		
		s.screen("Start").setData("dept_id","1");
		s.screen("Start").moveTo("DepartmentDetail").throughMenuItem("findByid");
		
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		
		s.screen("DepartmentDetail").moveTo("AsdepartmentDetail").throughMenuItem("Open AsdepartmentDetail");
		s.screen("AsdepartmentDetail").moveTo("Asdepartmentupdateinstance").throughMenuItem("Open Asdepartmentupdateinstance");
		
		s.screen("Asdepartmentupdateinstance").setData("Asdepartment_dept_name_attribKey","gg");
		s.screen("Asdepartmentupdateinstance").setData("Asdepartment_dept_head_id_attribKey","201");
	
		s.screen("Asdepartmentupdateinstance").moveTo("Start").throughMenuItem("Update");
		s.screen("Start").menuItem("Cancel");
		return s;
	}
}
//passed in 20120120