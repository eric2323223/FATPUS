package testscript.Workflow.ConditionalScreen;
import resources.testscript.Workflow.ConditionalScreen.CdtnSrcNavigate_Line_01Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MainMenu;
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
public class CdtnSrcNavigate_Line_01 extends CdtnSrcNavigate_Line_01Helper
{
	/**
	 * Script Name   : <b>CdtnSrcNavigate_Line_01</b>
	 * Generated     : <b>Nov 25, 2011 2:38:43 AM</b>
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

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Submit")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("Department")
				);
		PropertiesView.addConditionTableOfMenuItem("MYY","Department");
		MainMenu.saveAll();
		String condition = PropertiesView.getConditionTableOfMenuItem().getKey();
		System.out.println("condition = "+ condition);
		vpManual("hasdata","MYY,Department",condition).performTest();
		
		String link = WorkFlowEditor.getLinkTypeBetween("Start", "Department");
		vpManual("hasline","/OperationSuccess/ConditionnalOperation",link).performTest();
	}
}
//passed
