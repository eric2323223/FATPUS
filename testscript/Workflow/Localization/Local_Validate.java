package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.Local_ValidateHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFEditBox;
import component.entity.model.WFLocalFile;
import component.entity.model.WFScreenMenuItem;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_Validate extends Local_ValidateHelper
{
	/**
	 * Script Name   : <b>Local_Validate</b>
	 * Generated     : <b>Oct 15, 2011 12:01:57 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/15
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
//		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		//
		StringBuffer msg = new StringBuffer();
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France"), msg);
		//load the local file
		//WFLocalizationTab.WFLoad(Cfg.projectName, "myWF.xbw", "French (France)");
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
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("café")
				.type("Close Workflow"));
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WFLocalizationTab.WFValidation(Cfg.projectName, "myWF.xbw", "French (France)");
		//
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		String fileContent = WFLocalizationTab.WFGetLocalFileContent("myWF_fr_FR.properties");
		vpManual("validationfile", true, (fileContent.contains("caf\\u00E9") && fileContent.contains("fran\\u00E7ais") && fileContent.contains("\\u00E9tiquette"))
				|| (fileContent.contains("café") && fileContent.contains("français") && fileContent.contains("étiquette"))).performTest();
	}
}

