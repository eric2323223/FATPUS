package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_Creation_Wizard_06Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.ObjectQuery;
import component.entity.model.WizardRunner;
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_Creation_Wizard_06 extends SP_Email_Creation_Wizard_06Helper
{
	/**
	 * Script Name   : <b>SP_Email_Creation_Wizard_06</b>
	 * Generated     : <b>Sep 14, 2011 10:16:52 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/14
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->customer (dba)"), 10, 10);
		ObjectQuery oq = new ObjectQuery().name("oq1")
			.parameter("p1,STRING,true,fname")
			.returnType(ObjectQueryWizard.RT_RESULTSET)
//			.queryDefinition("Select * from customer ORDER by DESC")
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Customer");
			new ObjectQueryWizard().create(oq, new WizardRunner());
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Customer")
				.objectQuery("oq1")
				);
		vpManual("haserror",0,Problems.getErrors().size()).performTest();
		
	}
}
//passed
