package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.Local_UpdateHelper;
import testscript.Workflow.cfg.Cfg;

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
public class Local_Update extends Local_UpdateHelper
{
	/**
	 * Script Name   : <b>Local_Update</b>
	 * Generated     : <b>Oct 14, 2011 6:28:51 PM</b>
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
		//load the local file
		WFLocalizationTab.WFLoad(Cfg.projectName, "myWF.xbw", "French (France)");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("français")
							   .logicalType("TEXT")
							   .newKey("eb1,string")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("étiquette")
							   .logicalType("TEXT")
							   .newKey("eb2,string")
		);
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		//
		WFLocalizationTab.WFUpdate(Cfg.projectName, "myWF.xbw", "French (France)");
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		String fileContent = WFLocalizationTab.WFGetLocalFileContent("myWF_fr_FR.properties");
		vpManual("updatefile", true, fileContent.contains("fran\\u00E7ais") && fileContent.contains("\\u00E9tiquette")).performTest();
	}
}

