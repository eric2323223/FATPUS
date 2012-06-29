package testscript.Workflow.Localization;
import resources.testscript.Workflow.Localization.L689794_fr_Del_GenerateFileHelper;
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
import com.sybase.automation.framework.widget.DOF;

import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.WFLocalFile;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;
import component.view.properties.workflow.WFLocalizationTab;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class L689794_fr_Del_GenerateFile extends L689794_fr_Del_GenerateFileHelper
{
	/**
	 * Script Name   : <b>L689794_fr_Del_GenerateFile</b>
	 * Generated     : <b>Nov 24, 2011 11:58:33 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/24
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
		//
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.unwiredServer("My Unwired Server")
				.deployToServer("false")
				.verifyResult("Code generation complete", true));
		vpManual("isGenerated", true, WN.isPathExist(Cfg.projectName, "Generated Workflow->myWF->html->workflow.html")).performTest();
		//
		DOF.getWNTree().click(RIGHT, atPath(WN.projectNameWithVersion(Cfg.projectName)+"->Generated Workflow->myWF"));
		DOF.getContextMenu().click(SubitemFactory.atPath("Delete"));
		// Confirm dialog
		TopLevelTestObject dialog = DOF.getDialog("Confirm Resource Delete");
		if (null != dialog) {
			DOF.getButton(dialog, "&Yes").click();
		}
		//
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.unwiredServer("My Unwired Server")
				.deployToServer("false")
				.verifyResult("Code generation complete", true));
		vpManual("isGenerated", true, WN.isPathExist(Cfg.projectName, "Generated Workflow->myWF->html->workflow.html")).performTest();
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

