package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.Local_Update_MsgHelper;
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
import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Operation;
import component.entity.model.WFLocalFile;
import component.entity.model.WFScreenMenuItem;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class Local_Update_Msg extends Local_Update_MsgHelper
{
	/**
	 * Script Name   : <b>Local_Update_Msg</b>
	 * Generated     : <b>Oct 18, 2011 12:22:13 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/18
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		//MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
		);
		WorkFlowEditor.dragOperation(Cfg.projectName, "Department", "delete");
		WorkFlowEditor.link("Start Screen", "Department_delete");
		//
		WFLocalizationTab.WFAddLocalization(new WFLocalFile()
			.setLanguage("French")
			.setCountry("France"), new StringBuffer());
		WFLocalizationTab.WFLoad(Cfg.projectName, "myWF.xbw", "French (France)");
		//
		WorkFlowEditor.setMenuItem("Department_delete", new WFScreenMenuItem()
				.name("Delete")
				.submitConfirmMsg("submit confirm message")
		);
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		//
		WFLocalizationTab.WFUpdate(Cfg.projectName, "myWF.xbw", "French (France)");
		WFLocalizationTab.WFEdit(Cfg.projectName, "myWF.xbw", "French (France)");
		String fileContent = WFLocalizationTab.WFGetLocalFileContent("myWF_fr_FR.properties");
		vpManual("updatefile", true, fileContent.contains("submit confirm message")).performTest();
	}
}

