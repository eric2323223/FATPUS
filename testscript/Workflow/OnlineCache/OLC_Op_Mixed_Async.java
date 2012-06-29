package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Op_Mixed_AsyncHelper;
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
public class OLC_Op_Mixed_Async extends OLC_Op_Mixed_AsyncHelper
{
	/**
	 * Script Name   : <b>OLC_Op_Mixed_Async</b>
	 * Generated     : <b>Oct 19, 2011 3:45:43 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/19
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
		WorkFlowEditor.dragOperation(Cfg.projectName, "Product","create");
		WorkFlowEditor.dragOperation(Cfg.projectName, "Product","update");
		WorkFlowEditor.dragOperation(Cfg.projectName, "Product","delete");
		
		vpManual("property", "Submit Workflow", PropertiesView.getMenuItem("Product_update_instance", "Update").getType()).performTest();
		WorkFlowEditor.setMenuItem("Product_create", new WFScreenMenuItem()
			.name("Create").type("Online Request"));
		
		vpManual("property", "Online Request", PropertiesView.getMenuItem("Product_create", "Create").getType()).performTest();
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
	}
}

