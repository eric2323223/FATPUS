package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Email_ComplexRelationshipHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.LoadArgument;
import component.entity.model.PK;
import component.entity.model.Relationship;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Email_ComplexRelationship extends OLC_Email_ComplexRelationshipHelper
{
	/**
	 * Script Name   : <b>OLC_Email_ComplexRelationship</b>
	 * Generated     : <b>Nov 18, 2011 1:05:36 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/18
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
<<<<<<< .mine
//		WN.useProject(Cfg.projectName);
//		WN.createPK(new PK().startParameter(Cfg.projectName).name("intPk").type("INT"));
//		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->bonus (dba)");
//		MBOProperties department = new MBOProperties(Cfg.projectName, "Department");
//		department.setMboDefinition("sql", "select * from department where dept_id=:dept_id");
//		department.setLoadArgument(new LoadArgument().name("dept_id").propagateTo("dept_id"));
//		MBOProperties employee = new MBOProperties(Cfg.projectName, "Employee");
//		employee.setMboDefinition("sql", "select * from employee where emp_id=:emp_id");
//		employee.setLoadArgument(new LoadArgument().name("emp_id").propagateTo("emp_id"));
=======
		WN.useProject(Cfg.projectName);
		WN.createPK(new PK().startParameter(Cfg.projectName).name("intPk").type("INT"));
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->bonus (dba)");
		MBOProperties department = new MBOProperties(Cfg.projectName, "Department");
		department.setMboDefinition("sql", "select * from department where dept_id=:dept_id");
		department.setLoadArgument(new LoadArgument().type("INT").name("dept_id").propagateTo("dept_id"));
		MBOProperties employee = new MBOProperties(Cfg.projectName, "Employee");
		employee.setMboDefinition("sql", "select * from employee where emp_id=:emp_id");
		employee.setLoadArgument(new LoadArgument().type("INT").name("emp_id").propagateTo("emp_id"));
>>>>>>> .r3355
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.type(Relationship.TYPE_OTM)
				.target("Employee")
				.mapping("Department->Arguments->dept_id[INT],dept_id"));
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Employee"))
				.type(Relationship.TYPE_OTM)
				.target("Bonus")
				.mapping("Employee->Arguments->emp_id[INT],emp_id"));
		vpManual("error", 5, Problems.getErrors().size()).performTest();

	}
}

