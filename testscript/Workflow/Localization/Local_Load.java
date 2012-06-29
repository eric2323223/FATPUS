package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.Local_LoadHelper;
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
import component.entity.model.WFEditBox;
import component.entity.model.WFLocalFile;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_Load extends Local_LoadHelper
{
	/**
	 * Script Name   : <b>Local_Load</b>
	 * Generated     : <b>Oct 14, 2011 10:58:08 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/14
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		//
		StringBuffer msg = new StringBuffer();
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France"), msg);
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		WFLocalizationTab.WFChangeLocalFileContent(Cfg.projectName, "myWF_fr_FR.properties", "SCREEN1NAME=Start Screen", "SCREEN1NAME=\\u00E9cran de d\\u00E9marrage");
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		WFLocalizationTab.WFChangeLocalFileContent(Cfg.projectName, "myWF_fr_FR.properties", "SCREEN1MENUITEM0NAME=Cancel Screen", "SCREEN1MENUITEM0NAME=Annuler Ecran");
		//load the local file
		WFLocalizationTab.WFLoad(Cfg.projectName, "myWF.xbw", "French (France)");
		MainMenu.saveAll();
		vpManual("loadfile", true, WorkFlowEditor.hasScreen("écran de démarrage")).performTest();
		vpManual("loadfile", true, WorkFlowEditor.hasMenuItemInScreen("écran de démarrage", "Annuler Ecran")).performTest();
	}
}

