package testscript.Workflow.Designer_Prop;
import resources.testscript.Workflow.Designer_Prop.Prop_ScreenDesign_OKaction_1Helper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Screen;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Prop_ScreenDesign_OKaction_1 extends Prop_ScreenDesign_OKaction_1Helper
{
	/**
	 * Script Name   : <b>Prop_ScreenDesign_OKaction_1</b>
	 * Generated     : <b>Sep 28, 2011 5:28:24 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/28
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("screen2");
		vpManual("screen", true , WorkFlowEditor.hasScreen("screen2") ).performTest();
		WorkFlowEditor.link("Start", "screen2");
		vpManual("haserror", 0 , Problems.getErrors().size()).performTest();
		
		WorkFlowEditor.showPropertiesViewInSD("screen2");
		
		WorkFlowEditor.screenProperties(new Screen().action("Save"));
		vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
		
		WorkFlowEditor.screenProperties(new Screen().action("Cancel"));
		vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
	}
}
//passed
