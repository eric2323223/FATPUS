package testscript.Workflow.Localization;
import java.io.UnsupportedEncodingException;

import resources.testscript.Workflow.Localization.Local_fr_Menuitem_RT_AndroidHelper;
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
import component.entity.model.WFEditBox;
import component.entity.model.WFLocalFile;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_fr_Menuitem_RT_Android extends Local_fr_Menuitem_RT_AndroidHelper
{
	/**
	 * Script Name   : <b>Local_fr_Menuitem_RT_Android</b>
	 * Generated     : <b>Nov 23, 2011 6:25:19 PM</b>
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
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Custom")
				.type("Custom"));
		StringBuffer msg = new StringBuffer();
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France")
				.setDefaultlocal("true"), msg);
		MainMenu.saveAll();
		vpManual("localfile", true, WFLocalizationTab.WFGetLocalFileByName(Cfg.projectName, "myWF.xbw", "French (France)")).performTest();
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		WFLocalizationTab.WFChangeLocalFileContent(Cfg.projectName, "myWF_fr_FR.properties", "SCREEN1MENUITEM1NAME=Custom", "SCREEN1MENUITEM1NAME=française");
		MainMenu.saveAll();
		//
//		String fileContent = WFLocalizationTab.WFGetLocalFileContent("myWF_fr_FR.properties");
//		vpManual("validationfile", true, fileContent.contains("personnalis\u00E9") || fileContent.contains("personnalis�")).performTest();
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
		WFCustomizer.verifyResult(new WFClientResult().data("displayName=française"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		try {
			s.screen("Start_Screen").getMenuItemDisplayName(java.net.URLDecoder.decode("française","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
