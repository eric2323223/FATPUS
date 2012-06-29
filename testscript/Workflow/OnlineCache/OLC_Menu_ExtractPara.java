package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Menu_ExtractParaHelper;
import testscript.Workflow.cfg.Cfg;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
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
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Menu_ExtractPara extends OLC_Menu_ExtractParaHelper
{
	/**
	 * Script Name   : <b>OLC_Menu_ExtractPara</b>
	 * Generated     : <b>Oct 13, 2011 1:59:03 AM</b>
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
//		WN.createMbo(new DbMbo().startParameter(Cfg.projectName)
//				.dataSourceType("JDBC")
//				.name("Product")
//				.connectionProfile("My Sample Database")
//				.sqlQuery("select * from product where name=:name and quantity=:quantity and unit_price=:unit_price and  id=:id"));
//		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Product");
//		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("name"));
//		mbo.setLoadArgument(new LoadArgument().name("quantity").type("INT").propagateTo("quantity").defaultValue("0"));
//		mbo.setLoadArgument(new LoadArgument().name("unit_price").type("DECIMAL").propagateTo("unit_price").defaultValue("0"));
//		mbo.setLoadArgument(new LoadArgument().name("id").type("INT").propagateTo("id").defaultValue("0"));
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("item1")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Product")
				.objectQuery("findByParameter"));
		WFScreenMenuItem menuItem = PropertiesView.getMenuItem("item1");
		vpManual("mappingcount", 4, menuItem.getParameterMapping().length).performTest();
		vpManual("mapping", "name,", menuItem.getParameterMapping()[0]).performTest();
		vpManual("mapping", "quantity,", menuItem.getParameterMapping()[1]).performTest();
		vpManual("mapping", "unit_price,", menuItem.getParameterMapping()[2]).performTest();
		vpManual("mapping", "id,", menuItem.getParameterMapping()[3]).performTest();
	}
}

