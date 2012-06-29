package testscript.Workflow.Designer_Prop;
import resources.testscript.Workflow.Designer_Prop.D_673801_Matching_Rule_ToolingHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.DeployOption;
import component.entity.model.ObjectQuery;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class D_673801_Matching_Rule_Tooling extends D_673801_Matching_Rule_ToolingHelper
{
	/**
	 * Script Name   : <b>Matching_Rule_Toolin</b>
	 * Generated     : <b>Aug 27, 2011 12:47:12 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/27
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKey")
//			.parameter("dept_id,int,true,dep_id")
//			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_id = :dept_id")
//			.returnType(ObjectQueryWizard.RT_SINGLE)
//			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Department");
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
			    .mbo("Department")
				.subject("BANK_CTRY=CN")
				.objectQuery("findByPrimaryKey")
				.subjectMatchingRule("BANK_CTRY="));
	}
}
//PASSED