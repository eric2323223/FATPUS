package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.Local_New_2Helper;
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
import component.entity.model.WFLocalFile;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_New_2 extends Local_New_2Helper
{
	/**
	 * Script Name   : <b>Local_New_2</b>
	 * Generated     : <b>Oct 17, 2011 9:40:40 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/17
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
//		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France"), new StringBuffer());
		vpManual("localfile", true, WFLocalizationTab.WFGetLocalFileByName(Cfg.projectName, "myWF.xbw", "French (France)")).performTest();
		StringBuffer msg = new StringBuffer();
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
				.setLanguage("French")
				.setCountry("France")
				.setVariant("2")
				.setVariant("")
				.setVariant("2"), msg);
		vpManual("localfile1", true, WFLocalizationTab.WFGetLocalFileByName(Cfg.projectName, "myWF.xbw", "French (France,2)")).performTest();
		//
		String [] msgs = msg.toString().split(";");
		vpManual("validationmessage", true, msgs[0].trim().contains("'myWF_fr_FR.properties' already exists")).performTest();
		vpManual("validationmessage1", true, msgs[1].trim().contains("Create a new locale.")).performTest();
		vpManual("validationmessage", true, msgs[2].trim().contains("'myWF_fr_FR.properties' already exists")).performTest();
		vpManual("validationmessage1", true, msgs[3].trim().contains("Create a new locale.")).performTest();
	}
}

