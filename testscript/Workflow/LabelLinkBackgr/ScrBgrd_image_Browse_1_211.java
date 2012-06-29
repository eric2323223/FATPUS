package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.ScrBgrd_image_Browse_1_211Helper;
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

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class ScrBgrd_image_Browse_1_211 extends ScrBgrd_image_Browse_1_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Nov 3, 2011 12:55:43 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/03
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//TEST ScrBgrd_image_Browse_1
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("scr1");
		WorkFlowEditor.link("Start Screen", "scr1");
		
		DOF.getWFScreenFigure(DOF.getRoot(), "scr1").doubleClick();
		DOF.getWFScreenDisplayFigure().click();
		
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		PropertiesView.clickTab("General");
		
		DOF.getButton(DOF.getRoot(), "&Browse...").click();
		//whether the dialog pop up 
		vpManual("dialogpopup","Open",getScreen().getActiveWindow().getText());
		
		
	}
}

