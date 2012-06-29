package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_WFWizard_SingleParaHelper;
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
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.LoadArgument;
import component.entity.model.LoadParameter;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_WFWizard_SinglePara extends OLC_WFWizard_SingleParaHelper
{
	/**
	 * Script Name   : <b>OLC_WFWizard_SinglePara</b>
	 * Generated     : <b>Oct 12, 2011 8:37:04 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
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
//				.sqlQuery("select * from product where name=:name"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Product");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("name"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Product")
				.objectQuery("findByParameter"));
		vpManual("mapping", "name,name", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getParameterMapping()).performTest();
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
	}
}

