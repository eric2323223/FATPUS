package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_WFWizard_MultiParaHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.DbMbo;
import component.entity.model.ObjectQuery;
import component.entity.model.WizardRunner;
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class OLC_WFWizard_MultiPara extends OLC_WFWizard_MultiParaHelper
{
	/**
	 * Script Name   : <b>OLC_WFWizard_MultiPara</b>
	 * Generated     : <b>Nov 24, 2011 6:16:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/24
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
				.dataSourceType("JDBC")
				.name("Mydept")
				.connectionProfile("My Sample Database")
				.sqlQuery("select * from department"));
		
		//create object query:
		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKey")
			.parameter("dept_id,int,false,dept_id:name,string(40),false,dept_name:head_id,int,false,dept_head_id")
			.queryDefinition("SELECT x.* FROM Mydept x WHERE x.dept_id = :id AND x.dept_name = :name AND x.dept_head_id = :head_id")
			.returnType(ObjectQueryWizard.RT_SINGLE)
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Mydept");
		new ObjectQueryWizard().create(oq, new WizardRunner()); 
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Mydept")
				.objectQuery("findByPrimaryKey")
				//vp1
				.verifyParameterList("dept_id [INT],name [STRING],head_id [INT]",true)
				.subject("dept_id=100")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("dept_id,Subject,dept_id=")
				//vp2
				.verifyParameterValue("dept_id,int,,name,string,,head_id,int,", true)
				);
		
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
	}
}
//passed 
