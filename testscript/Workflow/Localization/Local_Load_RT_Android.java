package testscript.Workflow.Localization;
import java.util.ArrayList;

import resources.testscript.Workflow.Localization.Local_Load_RT_AndroidHelper;
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

import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.WFLocalFile;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_Load_RT_Android extends Local_Load_RT_AndroidHelper
{
	/**
	 * Script Name   : <b>Local_Load_RT_Android</b>
	 * Generated     : <b>Nov 14, 2011 10:40:39 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/14
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("Temp");
		//
		StringBuffer msg = new StringBuffer();
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France"), msg);
		//add a new english local file
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("English")
				.setCountry("United States"), new StringBuffer());
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		WFLocalizationTab.WFChangeLocalFileContent(Cfg.projectName, "myWF_fr_FR.properties", "SCREEN1NAME=Start Screen", "SCREEN1NAME=\\u00E9cran de d\\u00E9marrage");
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		WFLocalizationTab.WFChangeLocalFileContent(Cfg.projectName, "myWF_fr_FR.properties", "SCREEN1MENUITEM0NAME=Cancel Screen", "SCREEN1MENUITEM0NAME=Annuler Ecran");
		//load the local file
		WFLocalizationTab.WFLoad(Cfg.projectName, "myWF.xbw", "French (France)");
		//
		MainMenu.saveAll();
		vpManual("loadfile", true, WorkFlowEditor.hasScreen("�cran de d�marrage")).performTest();
		vpManual("loadfile", true, WorkFlowEditor.hasMenuItemInScreen("�cran de d�marrage", "Annuler Ecran")).performTest();
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
	}
	
	private java.util.List<CustomJsTestScript> customTestScript() {
		java.util.List<CustomJsTestScript> list = new ArrayList<CustomJsTestScript>();
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("_cran_de_d_marrage").menuItem("Annuler Ecran");
		list.add(s);
		//
		CustomJsTestScript s1 = new CustomJsTestScript();
		s1.screen("_cran_de_d_marrage").menuItem("Cancel Screen");
		list.add(s1);

		return list;
	}
}
