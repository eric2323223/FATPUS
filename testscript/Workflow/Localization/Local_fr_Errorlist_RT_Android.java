package testscript.Workflow.Localization;
import java.util.ArrayList;

import resources.testscript.Workflow.Localization.Local_fr_Errorlist_RT_AndroidHelper;
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
import com.sybase.automation.framework.common.CDBUtil;

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
import component.entity.model.WFLocalFile;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_fr_Errorlist_RT_Android extends Local_fr_Errorlist_RT_AndroidHelper
{
	/**
	 * Script Name   : <b>Local_fr_Errorlist_RT_Android</b>
	 * Generated     : <b>Nov 24, 2011 12:56:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/24
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Localization/conf/ASA_ddl.sql");
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ASA (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "ASA");
		WorkFlowEditor.link("Start Screen", "ASA_create");
		WorkFlowEditor.setMenuItem("ASA_create", new WFScreenMenuItem()
				.name("Create")
				.generateErrorScreen(true));
		
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		StringBuffer msg = new StringBuffer();
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France")
				.setDefaultlocal("true"), msg);
		MainMenu.saveAll();
		vpManual("localfile", true, WFLocalizationTab.WFGetLocalFileByName(Cfg.projectName, "myWF.xbw", "French (France)")).performTest();
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		WFLocalizationTab.WFChangeLocalFileContent(Cfg.projectName, "myWF_fr_FR.properties", "SCREEN7NAME=ErrorList", "SCREEN7NAME=Liste d'erreurs");
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		WFLocalizationTab.WFChangeLocalFileContent(Cfg.projectName, "myWF_fr_FR.properties", "SCREEN8NAME=ErrorDetail", "SCREEN8NAME=D\u00E9tails de l'erreur");
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
				"list_items_count=1|value=Liste d'erreurs|value=Détails de l'erreur"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("ASA_create").throughMenuItem("Open ASA_create");
		s.screen("ASA_create").setData("ASA_create_a_paramKey", "1");
		s.screen("ASA_create").setData("ASA_create_b_paramKey", "1");
		s.screen("ASA_create").setData("ASA_create_c_paramKey", "1");
		s.screen("ASA_create").moveTo("ErrorList").throughMenuItem("Create");
		s.screen("ErrorList").getListItemsCount();
		s.screen("ErrorList").getScreenDisplayName("ErrorList");
		s.screen("ErrorList").moveTo("ErrorDetail").throughListItem("0");
		s.screen("ErrorDetail").getScreenDisplayName("ErrorDetail");
		return s;
	}
}
