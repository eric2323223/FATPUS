package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Mail_ComplexTypeLoadParaHelper;
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
public class OLC_Mail_ComplexTypeLoadPara extends OLC_Mail_ComplexTypeLoadParaHelper
{
	/**
	 * Script Name   : <b>OLC_Mail_ComplexTypeLoadPara</b>
	 * Generated     : <b>Nov 23, 2011 1:16:18 AM</b>
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
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "SalesOrder");
		mbo.setLoadArgument(new LoadArgument().name("SALESDOCUMENT").propagateTo("DOC_NUMBER"));
		
		WN.changeCacheGroup(Cfg.projectName, "SalesOrder", "Default (Default)", "online");	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("SalesOrder")
				.objectQuery("findByParameter"));
		
		vpManual("noError", 0, Problems.getErrors().size()).performTest();
	}
}

