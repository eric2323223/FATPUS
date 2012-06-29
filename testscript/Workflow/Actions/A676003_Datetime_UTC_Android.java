package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.A676003_Datetime_UTC_AndroidHelper;
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
import component.entity.GlobalConfig;
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.LoadArgument;
import component.entity.model.ScrapbookCP;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class A676003_Datetime_UTC_Android extends A676003_Datetime_UTC_AndroidHelper
{
	/**
	 * Script Name   : <b>A676003_Datetime_UTC_Android</b>
	 * Generated     : <b>Oct 31, 2011 3:16:20 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/31
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/DiffTZ.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/DiffTZ_data.sql");
		// MBO
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->DiffTZ (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "DiffTZ");
		mbo.setMboDefinition("sql", "select * from DiffTZ where d=:d");
		mbo.setLoadArgument(new LoadArgument().name("d").type("DATETIME").propagateTo("d"));
		WN.deployProject(new DeployOption().startParameter(WN.mboPath(Cfg.projectName,"DiffTZ"))
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("DiffTZ")
				.objectQuery("findByParameter")
				.from("supAdmin")
				.to("supAdmin")
				.cc("supAdmin")
				.subject("2011-10-31T10:10:10")
				.toMatchingRule("supAdmin")
				.body("<name>[name]</name><value>{value}</value>")
				.setParameterValue("d,Subject")
				.verifyTo("To,supAdmin", true));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android", 
			new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
					.unwiredServer("My Unwired Server")
					.to("supAdmin")
					.from("RFT")
					.subject("2011-10-27T08:00:00")
					.body("bodybody")));
		WFCustomizer.verifyResult(new WFClientResult().data("found=true"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("DiffTZ").checkListItem("2", "0");
		return s;
	}
}
