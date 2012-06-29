package testscript.Workflow.EmailTriggering;
import resources.testscript.Workflow.EmailTriggering.EMailTriggering_JumpStart_03Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ObjectQuery;
import component.entity.model.StartPoint;
import component.entity.model.WizardRunner;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_JumpStart_03 extends EMailTriggering_JumpStart_03Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_JumpStart_03</b>
	 * Generated     : <b>Oct 18, 2011 12:47:51 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/18
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKeyMy")
			.parameter("dept_id,int,false,dept_id:dept_name,string(40),false,dept_name")
			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_id = :dept_id AND x.dept_name = :dept_name")
			.returnType(ObjectQueryWizard.RT_SINGLE)
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Department");
		new ObjectQueryWizard().create(oq, new WizardRunner()); 
//		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT)
			.name("Department")
			.project(Cfg.projectName)
			.mbo("Department")
			.objectQuery("findAll"));
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findByPrimaryKeyMy")
				.from("imo@localhost")
				.to("test@localhost")
				.cc("test1@localhost")
				.subject("Test(1)")
				.received("2009-07-20")
				.body("<name>[name]</name><value>{value}</value>")
				.setParameterValue("dept_id,Subject,Test(,)")
				);
		
		System.out.println("ExtractionRules = "+PropertiesView.getExtractionRulesofStartPoint());
		vpManual("extractiorules","Subject,Test(,),,dept_id",PropertiesView.getExtractionRulesofStartPoint().getKey().split(":")[0]).performTest();
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findByPrimaryKeyMy")
				.from("imo@localhost")
				.to("test@localhost")
				.cc("test1@localhost")
				.subject("Test(1)")
				.received("2009-07-20")
				.body("<name>[name]</name><value>{value}</value>")
				.setParameterValue("dept_id1,Subject,Test\\(,)")
				);
		
		System.out.println("ExtractionRules = "+PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("extractiorules","Subject,Test\\(,),,dept_id1",PropertiesView.getExtractionRulesofStartPoint().getKey().split(":")[0]).performTest();
	}
}
//passed
