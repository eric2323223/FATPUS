package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Menu_Para_AllDatetypesHelper;
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
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Menu_Para_AllDatetypes extends OLC_Menu_Para_AllDatetypesHelper
{
	/**
	 * Script Name   : <b>OLC_Menu_Para_AllDatetypes</b>
	 * Generated     : <b>Oct 11, 2011 12:55:09 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/11
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
				.dataSourceType("JDBC")
				.name("Product")
				.connectionProfile("My Sample Database")
//				.sqlQuery("select * from product where name=:name"));
				.sqlQuery("select * from product where name=:name and quantity=:quantity and unit_price=:unit_price and  id=:id"));
//		WN.createObjectQuery(new ObjectQuery().name("oq1")
//				.startParameter(WN.mboPath(Cfg.projectName, "Product"))
//				.parameter("p1,INT,false,id")
//				);
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Product");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("name"));
		mbo.setLoadArgument( new LoadArgument().name("quantity").type("INT").propagateTo("quantity").defaultValue("0"));
		mbo.setLoadArgument( new LoadArgument().name("unit_price").type("DECIMAL").propagateTo("unit_price").defaultValue("0"));
		mbo.setLoadArgument( new LoadArgument().type("INT").name("id").propagateTo("id").defaultValue("0"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName).option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("item1")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Product")
				.objectQuery("findByParameter"));
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox()
				.label("box1")
				.newKey("key1,string"));
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox()
				.label("box2")
				.newKey("key2,int"));
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox()
				.label("box3")
				.newKey("key3,decimal"));
		WorkFlowEditor.setMenuItem("Start Screen", new WFScreenMenuItem().name("item1")
				.parametermapping("name,key1")
				.parametermapping("id,key2")
				.parametermapping("quantity,key2")
				.parametermapping("unit_price,key3")
				);
		vpManual("mapping", 4, PropertiesView.getMenuItem("item1").getParameterMapping().length).performTest();
		vpManual("mapping", "name,key1", PropertiesView.getMenuItem("item1").getParameterMapping()[0]).performTest();
		vpManual("mapping", "quantity,key2", PropertiesView.getMenuItem("item1").getParameterMapping()[1]).performTest();
		vpManual("mapping", "nit_price,key3", PropertiesView.getMenuItem("item1").getParameterMapping()[2]).performTest();
		vpManual("mapping", "id,key2", PropertiesView.getMenuItem("item1").getParameterMapping()[3]).performTest();
		
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
	}
}

