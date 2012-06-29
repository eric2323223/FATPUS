package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_JumpStart_ExtractParaHelper;
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
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_JumpStart_ExtractPara extends OLC_JumpStart_ExtractParaHelper
{
	/**
	 * Script Name   : <b>OLC_JumpStart_ExtractPara</b>
	 * Generated     : <b>Oct 12, 2011 10:50:32 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
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
				.sqlQuery("select * from product where name=:name"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Product");
		mbo.setLoadArgument(new LoadArgument().name("name").type("STRING").propagateTo("name"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT)
				.mbo("Product"));
		WorkFlowEditor.setStartPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT)
				.objectQuery("findByParameter"));
		vpManual("mapping", "name,name", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getParameterMapping()).performTest();
	}
}

