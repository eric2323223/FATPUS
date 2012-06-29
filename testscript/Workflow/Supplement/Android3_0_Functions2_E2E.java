package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.Android3_0_Functions2_E2EHelper;
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
import component.entity.MainMenu;
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
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Android3_0_Functions2_E2E extends Android3_0_Functions2_E2EHelper
{
	/**
	 * Script Name   : <b>Android3_0_Functions2_E2E</b>
	 * Generated     : <b>Mar 19, 2012 2:33:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/19
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
		EE.runSQL(new ScrapbookCP().database("sampledb")
			.type("Sybase_ASA_12.x").name("My Sample Database"), 
			GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/AllKindOfMbo/createMyTable/asdepartment.sql");

		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"),10,10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->asdepartment (dba)"),100,10);
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("Asdepartment")
			.mapping("dept_id,dept_id")
			.composite("true")
			.bidirectional("true")
			.type(Relationship.TYPE_OTO));
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("dept_id=100")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("dept_id,Subject,dept_id="));
		
		MainMenu.saveAll();
		
		//used to Android3:
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser(Cfg.deviceUser)
		.unwiredServer("My Unwired Server")
		.deployToServer("true"),
		 customTestScript(), 
//		"tplan.Workflow.iconcommon.Android.myWF_Client_android3.Script",
			new CallBackMethod().receiver(WorkFlowEditor.class)
					.methodName("sendNotification")
					.parameter(new Email()
					.selectTo(Cfg.deviceUser)
					.subject("dept_id=1")));

		//vp1:verify the number of record:
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Department_dept_id_attribKey,value=1|"+
				"id=Asdepartment_dept_id_attribKey,value=1|"+
				"id=Asdepartment_dept_name_attribKey,value=my"));
	}

	private CustomJsTestScript customTestScript() {
		// TODO Auto-generated method stub
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").moveTo("AsdepartmentDetail").throughMenuItem("Open AsdepartmentDetail");
		s.screen("AsdepartmentDetail").getData("Asdepartment_dept_id_attribKey");
		s.screen("AsdepartmentDetail").getData("Asdepartment_dept_name_attribKey");
		return null;
	}
}
//passed Android3 20120320
