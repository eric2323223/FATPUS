package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_674011_Boolean_PKHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.PK;
import component.entity.model.WFCheckbox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_674011_Boolean_PK extends Ctrl_674011_Boolean_PKHelper
{
	/**
	 * Script Name   : <b>Ctrl_674011_Boolean_PK</b>
	 * Generated     : <b>Dec 12, 2011 4:33:56 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/12
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		//create a mbo:
		WN.createDbMbo(new DbMbo().startParameter(Cfg.projectName)
				.dataSourceType("JDBC")
				.connectionProfile("My Sample Database")
				.name("Object1")
				.sqlQuery("select * from ff_wf_employee where emp_gender =:emp_gender")
				.parameter("TBD")
				);
		
		WN.createPK(new PK().name("pk1")
				.type("BOOLEAN")
				.nullable("true")
				.storage("Transient")
				);
		MainMenu.saveAll();
		
		PropertiesOfMBO mbo = new PropertiesOfMBO();
		mbo.setPKforParameter("emp_gender", "pk1", "true", "boolean");
		MainMenu.saveAll();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
			.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT)
			);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Object1");
		WorkFlowEditor.addWidget("Start Screen", new WFCheckbox().label("bool:")
				.newKey("key1,bool"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAll")
				.project(Cfg.projectName)
				.type("Online Request")
				.mbo("Object1")
				.objectQuery("findAll")
				.defaultSuccessScreen("Object1")
				.pkMapping("pk1,key1"));
		
		MainMenu.saveAll();
		
		//TPlan:
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("DT")
			.verifyResult("Successfully deployed the workflow", true));
	
		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_674011_Boolean_PK.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
	}
}

