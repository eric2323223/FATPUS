package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Op_CRUD_ASyncHelper;
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
import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.LoadArgument;
import component.entity.model.LoadParameter;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_CRUD_ASync extends OLC_Op_CRUD_ASyncHelper
{
	/**
	 * Script Name   : <b>OLC_Op_CRUD_ASync</b>
	 * Generated     : <b>Oct 18, 2011 4:52:07 AM</b>
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
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->product (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Product");
		mbo.setMboDefinition("sql", "select * from product where name=:name");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("name"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "Product", "create");
		WorkFlowEditor.setMenuItem("Product_create", new WFScreenMenuItem()
			.name("Create").type("Online Request"));
		vpManual("property", "Online Request", PropertiesView.getMenuItem("Product_create", "Create").getType()).performTest();
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
	}
}

