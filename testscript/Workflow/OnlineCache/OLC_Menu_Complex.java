package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Menu_ComplexHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.LoadArgument;
import component.entity.model.PK;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Menu_Complex extends OLC_Menu_ComplexHelper
{
	/**
	 * Script Name   : <b>OLC_Menu_Complex</b>
	 * Generated     : <b>Oct 18, 2011 3:44:48 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/18
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("pk1")
				.type("INT")
				.nullable("false")
				.storage("Transient"));
		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
				.dataSourceType("JDBC")
				.name("Product")
				.connectionProfile("My Sample Database")
				.sqlQuery("select * from product where name=:name and quantity=:quantity"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Product");
//
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("name"));
		mbo.setLoadArgument(new LoadArgument().name("quantity").type("INT").pk("pk1").defaultValue("0"));
//		
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				 .mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT)
				);
		WorkFlowEditor.dragMbo(Cfg.projectName, "Product");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("item1")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Product")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("Product"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("strEdit").newKey("nameKey,string"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("intEdit").newKey("pkKey,int"));
		WorkFlowEditor.setMenuItem("Start", new WFScreenMenuItem()
				.name("item1")
				.parametermapping("name,nameKey")
				.pkMapping("pk1,pkKey"));	
		
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
	}
}

