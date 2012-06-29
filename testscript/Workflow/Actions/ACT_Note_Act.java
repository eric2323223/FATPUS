package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Note_ActHelper;
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
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFEditBox;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class ACT_Note_Act extends ACT_Note_ActHelper
{
	/**
	 * Script Name   : <b>ACT_Note_Act</b>
	 * Generated     : <b>Sep 30, 2011 10:54:16 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/30
	 * @author flvVm
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		// add note
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), "Start Screen").click(RIGHT);
		DOF.getContextMenu().click(atPath("Add Note"));
		DOF.getRoot().inputKeys("Note Test." + "{ENTER}");
		MainMenu.saveAll();
		
		vpManual("exist", true, DOF.getWFNoteFigure(DOF.getRoot(), "Note Test.").exists()).performTest();
		DOF.getWFNoteFigure(DOF.getRoot(), "Note Test."); //{Note Test.} is the text displayed in the Note 
		DOF.getRoot().inputKeys("{ExtDelete}");
		
	}
}

