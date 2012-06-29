package testscript.Workflow.ConditionalScreen;
import resources.testscript.Workflow.ConditionalScreen.CdtnSrcNavigate_Add_AlltypesHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ObjectQuery;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WizardRunner;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class CdtnSrcNavigate_Add_Alltypes extends CdtnSrcNavigate_Add_AlltypesHelper
{
	/**
	 * Script Name   : <b>CdtnSrcNavigate_Add_Alltypes</b>
	 * Generated     : <b>Nov 25, 2011 1:43:34 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/25
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKey")
//			.parameter("dept_id,int,true,dept_id")
//			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_id = :dept_id")
//			.returnType(ObjectQueryWizard.RT_SINGLE)
//			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Department");
//		new ObjectQueryWizard().create(oq, new WizardRunner()); 

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findall")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department")	);
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Submit")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.parametermapping("dept_id,Department_dept_id_attribKey"));
		
		PropertiesView.addConditionTableOfMenuItem("MYY","Department");
		
		String condition = PropertiesView.getConditionSPNewTableOfMenuItem().getKey();
		System.out.println("condition = "+ condition);
		vpManual("hasdata","MYY,Department",condition).performTest();
	}
}
//passed 2
