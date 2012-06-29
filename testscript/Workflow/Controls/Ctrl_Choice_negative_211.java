package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Choice_negative_211Helper;
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
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Choice_negative_211 extends Ctrl_Choice_negative_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 15, 2011 11:36:28 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/15
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
//	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wf2")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		//******go to Screen Flow
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), "Start" ).doubleClick();
		//**************end
	
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "Start", new WFChoice()
		        .label("choice:")
		        .logicalType("NUMERIC")
				.Numberofdecimal("-1"));
		String s = PropertiesView.getStatusMessage();
		vpManual("errmessage","Value not updated, the number of decimals must be between 0 and 28, inclusive.",s).performTest();
		
		PropertiesView.set(new WFChoice().Numberofdecimal("29"));
		String s1 = PropertiesView.getStatusMessage();
		vpManual("errmessage","Value not updated, the number of decimals must be between 0 and 28, inclusive.",s1).performTest();

		PropertiesView.clickTab("General");
		
		PropertiesView.set(new WFChoice()
		.option("Static,item1,1")
		);
		
			
		DOF.getButton(DOF.getRoot(), "&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Choice Option Item");
		WO.setTextField(dialog,DOF.getTextField(dialog, "Display name:"),"item1");
		WO.setTextField(dialog,DOF.getTextField(dialog, "Value:"),"10");
				String actual = DOF.getTextFieldByAncestorLine(DOF.getDialog("Choice Option Item"), "Composite->Shell->Shell").getProperty("text").toString();
				System.out.println(actual);
		
		//Verify option name could not be duplicated
		vpManual("nameduplicate"," Duplicate display values are not allowed",actual).performTest();
//		
		
	}
}

