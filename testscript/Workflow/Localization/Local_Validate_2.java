package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.Local_Validate_2Helper;
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
import component.view.properties.workflow.WFControlObject;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_Validate_2 extends Local_Validate_2Helper
{
	/**
	 * Script Name   : <b>Local_Validate_2</b>
	 * Generated     : <b>Oct 15, 2011 1:01:25 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/15
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
//		WN.useProject(Cfg.projectName);
//		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		//
		//StringBuffer msg = new StringBuffer();
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France"), new StringBuffer());
		//load the local file
		//WFLocalizationTab.WFLoad(Cfg.projectName, "myWF.xbw", "French (France)");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("français")
							   .logicalType("TEXT")
							   .newKey("eb1,string")
		);
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WFLocalizationTab.WFSetValidationDialog("false");
		//Whether to auto correct missing keys should not show
		vpManual("dialog", false, WFLocalizationTab.WFValidation(Cfg.projectName, "myWF.xbw", "French (France)")).performTest();
		//
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		String fileContent = WFLocalizationTab.WFGetLocalFileContent("myWF_fr_FR.properties");
		vpManual("validationfile", true, fileContent.contains("fran\\u00E7ais")
				|| fileContent.contains("français")).performTest();
		
		
		//
		WFLocalizationTab.WFSetValidationDialog("true");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("étiquette")
							   .logicalType("TEXT")
							   .newKey("eb2,string")
		);
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		//Whether to auto correct missing keys should not show
		vpManual("dialog", true, WFLocalizationTab.WFValidation(Cfg.projectName, "myWF.xbw", "French (France)")).performTest();
		//
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		fileContent = WFLocalizationTab.WFGetLocalFileContent("myWF_fr_FR.properties");
		vpManual("validationfile", true, (fileContent.contains("fran\\u00E7ais") && fileContent.contains("\\u00E9tiquette"))
				|| (fileContent.contains("français") && fileContent.contains("étiquette"))).performTest();
	}
}

