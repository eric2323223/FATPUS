package testscript.Workflow.Supplement;
import resources.testscript.Workflow.Supplement.S665751_Loneline_in_ResultData_2_AndroidHelper;
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
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class S665751_Loneline_in_ResultData_2_Android extends S665751_Loneline_in_ResultData_2_AndroidHelper
{
	/**
	 * Script Name   : <b>S665751_Loneline_in_ResultData_2_Android</b>
	 * Generated     : <b>Nov 4, 2011 7:29:58 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/04
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_txt_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/txt_ddl.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_txt_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/txt_data.sql");
//		 MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->txt (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
//		 WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Txt");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("id")
							   .logicalType("TEXT")
							   .newKey("id,int")
							   .setDefaultValue("2"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findByPrimaryKey")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Txt")
				.objectQuery("findByPrimaryKey")
				.parametermapping("id,id")
				.defaultSuccessScreen("TxtDetail"));
		sleep(1);
		MainMenu.saveAll();
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "TxtDetail", 
				new WFEditBox().label("text")
							   .logicalType("TEXT")
							   .ifReadonly(true)
							   .key("Txt_content_attribKey")
							   .lines("3"));
		MainMenu.saveAll();
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android");
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Txt_content_attribKey,value=format2\r\ntest"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("TxtDetail").throughMenuItem("findByPrimaryKey");
		s.screen("TxtDetail").getData("Txt_content_attribKey");
		return s;
	}
}