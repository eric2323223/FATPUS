package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Menu_ComplexTypeLoadParaHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.LoadArgument;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.WFScreenMenuItem;
import component.entity.resource.RC;
import component.entity.resource.SAP;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Menu_ComplexTypeLoadPara extends OLC_Menu_ComplexTypeLoadParaHelper
{
	/**
	 * Script Name   : <b>OLC_Menu_ComplexTypeLoadPara</b>
	 * Generated     : <b>Nov 23, 2011 12:12:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/23
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{

		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.useProject(Cfg.projectName);
		WN.createCacheGroup(new CacheGroup().startParameter(Cfg.projectName).name("online").type(CachePolicy.ONLINE));
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.connectionProfile("sap")
				.name("SalesOrder")
				.operation("Application Components->Sales and Distribution->Sales->SalesOrder,GetStatus")
				.parameter("SALESDOCUMENT,in")
				.parameter("STATUSINFO,out")
				.parameter("STATUSINFO,in")
				.parameterValue("SALESDOCUMENT,0000121895")
				);	
		WN.changeCacheGroup(Cfg.projectName, "SalesOrder", "Default (Default)", "online");	

		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.useProject(Cfg.projectName);
		WN.createCacheGroup(new CacheGroup().startParameter(Cfg.projectName).name("online").type(CachePolicy.ONLINE));
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
				.dataSourceType("SAP")
				.connectionProfile("sap")
				.name("SalesOrder")
				.operation("Application Components->Sales and Distribution->Sales->SalesOrder,GetStatus")
				.parameter("SALESDOCUMENT,in")
				.parameter("STATUSINFO,out")
				.parameter("STATUSINFO,in")
				.parameterValue("SALESDOCUMENT,0000121895")
				);	
		
		WN.changeCacheGroup(Cfg.projectName, "SalesOrder", "Default (Default)", "online");	
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "SalesOrder");
		mbo.setLoadArgument(new LoadArgument().name("SALESDOCUMENT").propagateTo("DOC_NUMBER"));

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "SalesOrder");
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("getAll")
				.project(Cfg.projectName)
				.mbo("SalesOrder")
				.type("Online Request")
				.objectQuery("findByParameter")
				.defaultSuccessScreen("SalesOrder"));
		
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
//		PropertiesView.set(new WFScreen().name("Bank_update"),new Modifications()
//				.mod("update", new WFScreenMenuItem().name("Open Bank_update")
//						.defaultSuccessScreen("BankDetail")));
		
		
	}
}

