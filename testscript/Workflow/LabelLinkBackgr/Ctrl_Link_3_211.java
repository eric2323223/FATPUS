package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.Ctrl_Link_3_211Helper;
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
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFLabel;
import component.entity.model.WFLink;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Link_3_211 extends Ctrl_Link_3_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Nov 6, 2011 6:48:13 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/06
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test Ctrl_Link_3
//		WN.useProject(Cfg.projectName);
//		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
//	
//		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//					.server("My Unwired Server")
//					.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wflink")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		//******************add key from key tab	
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		PropertiesView.clickTab("Keys");
		PropertiesView.NewKey("keyforlink,string");
		
	//**************end
		WorkFlowEditor.addWidget(Cfg.projectName, "wflink.xbw", "Start Screen", new WFLink().label("linkctrl"));
		WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")),"keyforlink");
		MainMenu.saveAll();
		vpManual("bindkey","keyforlink",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
	}
}

