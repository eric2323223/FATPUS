package testscript.Workflow.ConditionalScreen;
import resources.testscript.Workflow.ConditionalScreen.CdtnScrNavigate_Default_01Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ObjectQuery;
import component.entity.model.WFScreenMenuItem;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class CdtnScrNavigate_Default_01 extends CdtnScrNavigate_Default_01Helper
{
	/**
	 * Script Name   : <b>CdtnScrNavigate_Default_01</b>
	 * Generated     : <b>Oct 12, 2011 11:27:27 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		// WN.createObjectQuery(new ObjectQuery().name("findByPrimaryKey")
		// .parameter("dept_id,int,true,dept_id")
		// .queryDefinition("SELECT x.* FROM Department x WHERE x.dept_id = :dept_id")
		// .returnType(ObjectQueryWizard.RT_SINGLE)
		// .startParameter(WN.mboPath(Cfg.projectName, "Department")));

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
		.name("myWF")
		.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Submit")
		.type("Online Request")
		.project(Cfg.projectName)
		.mbo("Department")
		.objectQuery("findByPrimaryKey")
		.parametermapping("dept_id,Department_dept_id_attribKey"));

		System.out.println("list has:"+PropertiesView.getListOfDefaultSuccessScreen());
		vpManual("haslist",",Start",PropertiesView.getListOfDefaultSuccessScreen()).performTest();
	}
}



//passed  2
