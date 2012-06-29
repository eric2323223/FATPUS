package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Submit_4Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DbMbo;
import component.entity.model.LoadArgument;
import component.entity.model.LoadParameter;
import component.entity.model.Operation;
import component.entity.model.PK;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class ACT_Submit_4 extends ACT_Submit_4Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_4</b>
	 * Generated     : <b>Sep 21, 2011 3:36:10 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/21
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
				.dataSourceType("JDBC")
				.connectionProfile("My Sample Database")
				.name("Department")
				.sqlQuery("select * from department where dept_id=:dept_id"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("delete")
				.sqlQuery("DELETE FROM sampledb.dba.department WHERE dept_id = :dept_id"));
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("pk1")
				.type("INT")
				.nullable("false")
				.storage("Transient"));
		mbo.setLoadArgument(new LoadArgument().name("dept_id").pk("pk1")
				.type("INT")
				.defaultValue("0"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.link("Start Screen","Department");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("item1")
				.project(Cfg.projectName)
				.mbo("Department")
				.type("Submit Workflow")
				.operation("delete")
				);
//		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem().name("item1")
//				.mbo(null));
//		vpManual("properties", 0, PropertiesView.getMenuItem("item1").getParameterMapping().length).performTest();
//		vpManual("properties", "", PropertiesView.getMenuItem("item1").getMbo()).performTest();

	}
}

