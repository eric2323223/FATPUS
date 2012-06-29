package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.Local_fr_ScrName_RT_AndroidHelper;
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
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.WFLocalFile;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_fr_ScrName_RT_Android extends Local_fr_ScrName_RT_AndroidHelper
{
	/**
	 * Script Name   : <b>Local_fr_ScrName_RT_Android</b>
	 * Generated     : <b>Nov 23, 2011 9:06:48 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/23
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		StringBuffer msg = new StringBuffer();
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France")
				.setDefaultlocal("true"), msg);
		MainMenu.saveAll();
		vpManual("localfile", true, WFLocalizationTab.WFGetLocalFileByName(Cfg.projectName, "myWF.xbw", "French (France)")).performTest();
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
//		WFLocalizationTab.WFChangeLocalFileContent(Cfg.projectName, "myWF_fr_FR.properties", "SCREEN1NAME=Start Screen", "SCREEN1NAME=\u00E9cran de d\u00E9marrage");
		WFLocalizationTab.WFChangeLocalFileContent(Cfg.projectName, "myWF_fr_FR.properties", "SCREEN1NAME=Start Screen", "SCREEN1NAME=écran de démarrage");
		MainMenu.saveAll();
		String fileContent = WFLocalizationTab.WFGetLocalFileContent("myWF_fr_FR.properties");
		System.out.println(fileContent);
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
		WFCustomizer.verifyResult(new WFClientResult().data("value=écran de démarrage"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").getScreenDisplayName("Start_Screen");
		return s;
	}
}
