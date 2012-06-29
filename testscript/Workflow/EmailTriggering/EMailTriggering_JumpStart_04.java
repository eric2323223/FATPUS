package testscript.Workflow.EmailTriggering;
import resources.testscript.Workflow.EmailTriggering.EMailTriggering_JumpStart_04Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.ObjectQuery;
import component.entity.model.ScrapbookCP;
import component.entity.model.WizardRunner;
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_JumpStart_04 extends EMailTriggering_JumpStart_04Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_JumpStart_04</b>
	 * Generated     : <b>Nov 25, 2011 3:24:27 AM</b>
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
		
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/EmailTriggering/setup/createT_olc.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->bonus (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)");
		
		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKeyTime")
			.parameter("c,time,true,c")
			.queryDefinition("SELECT x.* FROM T_olc x WHERE x.c = :c")
			.returnType(ObjectQueryWizard.RT_SINGLE)
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->T_olc");
		new ObjectQueryWizard().create(oq, new WizardRunner()); 
//		
		//vp:datetime or date
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("datetime")
//				.option(WorkFlow.SP_SERVER_INIT)
//				.project(Cfg.projectName)
//				.mbo("Bonus")
//				.objectQuery("findByPrimaryKey")
//				.subject("datetime=1")
//				.subjectMatchingRule("datatime=")
//				.setParameterValue("bonus_date,Subject,datetime=,,yyyy-mm-ddThh:mm:ss"));
//		
//		System.out.println("ExtractionRules = "+PropertiesView.getExtractionRulesofStartPoint().getKey());
//		vpManual("extractiorules",true,PropertiesView.getExtractionRulesofStartPoint().getKey().contains("Subject,datetime=,,yyyy-mm-ddThh:mm:ss,bonus_date")).performTest();
		
		//vp:time type:
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("time")
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("T_olc")
				.objectQuery("findByPrimaryKeyTime")
				.subject("time=20:00:00")
				.subjectMatchingRule("time=")
				.setParameterValue("c,Subject,time=,,HH:mm:ss"));
		
		System.out.println("ExtractionRules = "+PropertiesView.getExtractionRulesofStartPoint().getKey());
		vpManual("extractiorules",true,PropertiesView.getExtractionRulesofStartPoint().getKey().contains("Subject,time=,,HH:mm:ss,c")).performTest();
		vpManual("noerror",0,Problems.getErrors().size()).performTest();
	}
}
//passed
