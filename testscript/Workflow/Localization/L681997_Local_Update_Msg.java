package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.L681997_Local_Update_MsgHelper;
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
import component.view.Problems;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class L681997_Local_Update_Msg extends L681997_Local_Update_MsgHelper
{
	/**
	 * Script Name   : <b>L681997_Local_Update_Msg</b>
	 * Generated     : <b>Oct 18, 2011 6:12:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/18
	 * @author flv
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("English")
				.setCountry("United States")
				.setDefaultlocal("true"), new StringBuffer());
		vpManual("localfile", true, WFLocalizationTab.WFIsLoaded(Cfg.projectName, "myWF.xbw", "default")).performTest();
		//
		WFLocalizationTab.WFAddLocalFileContent(Cfg.projectName, "myWF_en_US.properties", "#error messages used by customBeforeReportErrorFromNative function \nUNKNOWN_ERROR=Unknown error");
		WFLocalizationTab.WFAddLocalFileContent(Cfg.projectName, "myWF.properties", "#error messages used by customBeforeReportErrorFromNative function \nUNKNOWN_ERROR=Unknown error");
		WorkFlowEditor.addScreen("newScreen");
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "newScreen", 
				new WFEditBox().label("newScreenlabel")
							   .logicalType("TEXT")
							   .newKey("nameVal,string")
		);
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		//
		WFLocalizationTab.WFUpdate(Cfg.projectName, "myWF.xbw", "default");
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "default");
		String fileContent = WFLocalizationTab.WFGetLocalFileContent("myWF.properties");
		vpManual("defaultfile", true, fileContent.contains("newScreen") && fileContent.contains("newScreenlabel")).performTest();
		vpManual("defaultfile", true, fileContent.contains("UNKNOWN_ERROR=Unknown error")).performTest();
		//load the local file
		WFLocalizationTab.WFLoad(Cfg.projectName, "myWF.xbw", "English (United States)");
		MainMenu.saveAll();
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "English (United States)");
		fileContent = WFLocalizationTab.WFGetLocalFileContent("myWF_en_US.properties");
		vpManual("defaultfile", true, fileContent.contains("newScreen") && fileContent.contains("newScreenlabel")).performTest();
		vpManual("defaultfile", true, fileContent.contains("UNKNOWN_ERROR=Unknown error")).performTest();
	    //
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

