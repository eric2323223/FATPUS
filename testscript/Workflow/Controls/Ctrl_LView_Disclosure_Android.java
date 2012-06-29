package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_LView_Disclosure_AndroidHelper;
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
import component.entity.model.WFLview;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Ctrl_LView_Disclosure_Android extends Ctrl_LView_Disclosure_AndroidHelper
{
	/**
	 * Script Name   : <b>Ctrl_LView_Disclosure_Android</b>
	 * Generated     : <b>Sep 5, 2011 10:12:32 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/05
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"wflviewdisclosure").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addWidget(Cfg.projectName, "disclosure.xbw", "Start Screen", new WFLview()
				.newKey("key1,list")
				.emptyMessage("This is a empty list"));
		WorkFlowEditor.setWorkFlow(new WorkFlow().version("2"));
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "disclosure.xbw"))
				.unwiredServer("My Unwired Server")
				.assignToUser("DeviceTest")
				.verifyResult("Successfully deployed the workflow", true));
		
		//ET is OK,only need to deploy to BB66, to varify the cell of liseview have not disclosed indicatior..........
		
	}
}

