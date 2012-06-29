package testscript.Workflow.OnlineCache;
import java.util.ArrayList;
import java.util.List;

import resources.testscript.Workflow.OnlineCache.OLC_Op_CRUD_ASync_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.LoadArgument;
import component.entity.model.Modifications;
import component.entity.model.WFKey;
import component.entity.model.WFKeyParameterMapping;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_CRUD_ASync_E2E extends OLC_Op_CRUD_ASync_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_CRUD_ASync_E2E</b>
	 * Generated     : <b>Oct 20, 2011 11:36:40 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/20
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->product (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "product");
		mbo.setMboDefinition("sql", "select * from product where name=:name");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("name"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "Product","create");
		WorkFlowEditor.dragOperation(Cfg.projectName, "Product","update");
		WorkFlowEditor.dragOperation(Cfg.projectName, "Product","delete");
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findpk")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Product")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("Productupdateinstance"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
		       .name("findpk1")
		       .type("Online Request")
		       .project(Cfg.projectName)
		       .mbo("Product")
		       .objectQuery("findByParameter")
		       .defaultSuccessScreen("Productdeleteinstance"));
		
		PropertiesView.set(new WFScreenMenuItem().name("findAll"), new Modifications().mod("update", new WFKeyParameterMapping()
				.parameter("name")
				.key(new WFKey().name("key1").type("string").sentByServer("false").toString())));
		PropertiesView.set(new WFScreen().name("States"), new Modifications().mod("update", new WFKey().name("States")
				.sentByServer("false")));
		
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
//	
//		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
//				.deployToServer("true")
//				.assignToUser("DT")
//				.unwiredServer("My Unwired Server"), 
//				customJsTestScripts(), "" );
	
	}

	private List<CustomJsTestScript> customJsTestScripts() {
		List<CustomJsTestScript> scripts = new ArrayList<CustomJsTestScript>();
		scripts.add(script1());
		scripts.add(script2());
		return scripts;
	}
	
	private CustomJsTestScript script1(){
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("States").throughMenuItem("findAll");
		s.screen("States").moveTo("StatesDetail").throughListItem("0");
		
		return s;
	}
	
	private CustomJsTestScript script2(){
		return null;
	}
}

