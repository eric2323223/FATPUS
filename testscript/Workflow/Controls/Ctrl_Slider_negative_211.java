package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Slider_negative_211Helper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFChoice;
import component.entity.model.WFSlider;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Slider_negative_211 extends Ctrl_Slider_negative_211Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Oct 18, 2011 10:24:37 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/18
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wf2")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		
	
		//******go to Screen Flow
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), "Start Screen" ).doubleClick();
		//**************end
	
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "Start Screen", new WFSlider()       
                .label("slider:")
		        .maxValue("999999999999")
		        );
		String s = PropertiesView.getStatusMessage();
		vpManual("errmessage","Value not updated, 999999999999 is not a valid integer value.",s).performTest();
		DOF.getTextField(DOF.getRoot(), "Minimum value:").click();
		WO.setTextField(DOF.getRoot(), DOF.getTextField(DOF.getRoot(), "Maximum value:"), "100");
				
		PropertiesView.set(new WFSlider().minValue("char test"));
		String s1 = PropertiesView.getStatusMessage();
		vpManual("errmessage","Value not updated, char test is not a valid integer value.",s1).performTest();

	}
}

