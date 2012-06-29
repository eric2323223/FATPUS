package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.ActivationKey_1Helper;
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

import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Module;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class ActivationKey_1 extends ActivationKey_1Helper
{
	/**
	 * Script Name   : <b>ActivationKey_1</b>
	 * Generated     : <b>Sep 30, 2011 8:11:07 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/30
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF_Activation_1")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE));
		
		MainMenu.saveAll();
		
		WorkFlowEditor.showPropertiesViewInFD();
		System.out.println(PropertiesView.getContent("rak"));
		vpManual("getMse","",PropertiesView.getContent("rak") ).performTest();
		
		PropertiesView.editModule(new Module().activeKey("newActiveKey"));
    	vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
    	
    	PropertiesView.editModule(new Module().activeKey("!@#$%^&*(~"));
    	vpManual("getMse", "Value not updated, !@#$%^&&*(~ is not a valid requires activation key." , PropertiesView.getStatusMessage()).performTest();
	}
}
//passed
