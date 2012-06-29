package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.A635324_InstanceLevel_ReadHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
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
import component.entity.model.ObjectQuery;
import component.entity.model.Relationship;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class A635324_InstanceLevel_Read extends A635324_InstanceLevel_ReadHelper
{
	/**
	 * Script Name   : <b>A635324_InstanceLevel_Read</b>
	 * Generated     : <b>Oct 26, 2011 7:56:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/26
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{

		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("Employee")
			.mapping("dept_id,dept_id")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		WN.createObjectQuery(new ObjectQuery()
			.name("GetEmps")
			.startParameter(WN.mboPath(Cfg.projectName, "Employee"))
			.parameter("dept_id,int,true,dept_id")
			.queryDefinition("SELECT x.* FROM Employee x WHERE x.dept_id = :dept_id")
			.returnType(ObjectQueryWizard.RT_MULTIPLE));
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		//Jump Start
		PropertiesView.jumpStart(new WorkFlow()
				.from("supAdmin")
				.to("supAdmin")
				.cc("supAdmin")
				.subject("all")
				.subjectMatchingRule("all")
				.mbo("Department")
				.objectQuery("findAll")
				.received("2011-10-21")
				.body("<name>[name]</name><value>{value}</value>")
				.verifySubject("Subject,all", true));
		WorkFlowEditor.link("Server-initiated", "Department");
		//
		WorkFlowEditor.addMenuItem("DepartmentDetail", new WFScreenMenuItem()
				.name("GetEmps")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Employee")
				.objectQuery("GetEmps")
				.parametermapping("dept_id,Department_dept_id_attribKey")
				.defaultSuccessScreen("Employee"));
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"", 
			new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
					.unwiredServer("My Unwired Server")
					.to("supAdmin")
					.from("RFT")
					.subject("all")
					.body("bodybody")));
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Department_dept_id_attribKey,value=100|"+
				"id=Department_dept_name_attribKey,value=R & D|"+ 
				"id=Department_dept_head_id_attribKey,value=501|" + 
				"found=true"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("100->0");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		//Open Employee
		s.screen("DepartmentDetail").moveTo("Employee").throughMenuItem("GetEmps");
		s.screen("Employee").checkListItem("102", "0");
		
		return s;
	}
}