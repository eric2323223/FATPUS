package testscript.Workflow.Designer_Prop;
import java.awt.Point;

import resources.testscript.Workflow.Designer_Prop.Prop_ScreenDesign_General_1Helper;
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

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Module;
import component.entity.model.Screen;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Prop_ScreenDesign_General_1 extends Prop_ScreenDesign_General_1Helper
{
	/**
	 * Script Name   : <b>Prop_ScreenDesign_General_1</b>
	 * Generated     : <b>Sep 28, 2011 3:44:38 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/28
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WN.closeAll();
		WN.openWFWithRight(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WorkFlowEditor.showPropertiesViewInSD("Department");
		
		WorkFlowEditor.screenProperties(new Screen().name("newscreenname"));
		vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
		
		WorkFlowEditor.screenProperties(new Screen().key("newkey"));
		vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
	
		WorkFlowEditor.screenProperties(new Screen().backGroundColor("blue"));
		vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
		
		WorkFlowEditor.screenProperties(new Screen().action("Cancel"));
		vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
		
		WorkFlowEditor.screenProperties(new Screen().name("#@$%$%%$"));
		vpManual("getMse", "Value not updated, #@$%$%%$ is not a valid screen name. " , PropertiesView.getStatusMessage()).performTest();
		
//		WorkFlowEditor.screenProperties(new Screen().key("$%$#%^#^f"));
//		vpManual("getMse", "Value not updated, $%$#%^#^f is not a valid key name. " , PropertiesView.getStatusMessage()).performTest();
		
		WorkFlowEditor.screenProperties(new Screen().backGroundColor("d f @"));
		vpManual("getMse", "Value not updated, the backcolor must be black, blue, brown, gray, green, orange, pink, purple, red, white, yellow, or #rrggbb where rr, gg, and bb are the hexadecimal values for the red, green and blue." , PropertiesView.getStatusMessage()).performTest();
		
		vpManual("haserror", 0 , Problems.getErrors().size()).performTest();
	}
}
//passed
