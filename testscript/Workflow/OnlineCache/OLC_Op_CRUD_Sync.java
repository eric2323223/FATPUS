package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Op_CRUD_SyncHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.LoadArgument;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Op_CRUD_Sync extends OLC_Op_CRUD_SyncHelper
{
	/**
	 * Script Name   : <b>OLC_Op_CRUD_Sync</b>
	 * Generated     : <b>Oct 13, 2011 4:04:14 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/13
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
		WorkFlowEditor.dragOperation(Cfg.projectName, "Product","update");
		
		vpManual("property", "Submit Workflow", PropertiesView.getMenuItem("Product_update_instance", "Update").getType()).performTest();
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
	}
}

