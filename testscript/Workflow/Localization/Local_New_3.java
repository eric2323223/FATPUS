package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.Local_New_3Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.WFLocalFile;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_New_3 extends Local_New_3Helper
{
	/**
	 * Script Name   : <b>Local_New_3</b>
	 * Generated     : <b>Oct 13, 2011 10:47:14 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/13
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
//		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		StringBuffer msg = new StringBuffer();
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France"), msg);
		vpManual("localfile", true, WFLocalizationTab.WFGetLocalFileByName(Cfg.projectName, "myWF.xbw", "French (France)")).performTest();
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France")
				.setOverwrite("true"), msg);
		vpManual("localfile1", true, WFLocalizationTab.WFGetLocalFileByName(Cfg.projectName, "myWF.xbw", "French (France)")).performTest();
		//
		String [] msgs = msg.toString().split(";");
		vpManual("validationmessage", true, msgs[0].trim().contains("'myWF_fr_FR.properties' already exists")).performTest();
		vpManual("validationmessage1", true, msgs[1].trim().contains("Create a new locale.")).performTest();
		
	}
}

