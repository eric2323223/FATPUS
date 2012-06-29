package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Email_ComplexHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.LoadArgument;
import component.entity.model.LoadParameter;
import component.entity.model.PK;
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Email_Complex extends OLC_Email_ComplexHelper
{
	/**
	 * Script Name   : <b>OLC_Email_Complex</b>
	 * Generated     : <b>Oct 13, 2011 2:35:29 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/13
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
//		WN.createPK(new PK().startParameter(Cfg.projectName)
//				.name("pk1")
//				.type("INT")
//				.nullable("false")
//				.storage("Transient"));
//		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
//				.dataSourceType("JDBC")
//				.name("Product")
//				.connectionProfile("My Sample Database")
//				.sqlQuery("select * from product where name=:name and quantity=:quantity"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Product");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("name"));
		mbo.setLoadArgument(new LoadArgument().name("quantity").type("INT").pk("pk1").defaultValue("0"));
		sleep(5);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Product")
//				.objectQuery("findByParameter")
				);
		WorkFlowEditor.setStartPoint(new StartPoint().type(WorkFlow.SP_SERVER_INIT)
				.objectQuery("findByParameter"));
		StartPoint sp = PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT);
		
		vpManual("mapping", "name,name", sp.getParameterMapping()).performTest();
		vpManual("mapping", "pk1,pk1", sp.getPkMapping()).performTest();
		vpManual("noErro", 0, Problems.getErrors().size()).performTest();
	}
}

