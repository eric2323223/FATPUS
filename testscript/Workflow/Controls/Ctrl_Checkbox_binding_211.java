package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Checkbox_binding_211Helper;
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
import com.sybase.automation.framework.widget.temp.MenuItem;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFCheckbox;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Checkbox_binding_211 extends Ctrl_Checkbox_binding_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 17, 2011 12:07:04 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/17
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
//		 TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
//		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start", new WFCheckbox().label("checkbox:")
				.newKey("ckkey1,int"));
//		
		PropertiesView.NewKey("ckkey2,bool");
		PropertiesView.NewKey("ckkey3,string");

		
		PropertiesView.clickTab("General");
		String s = PropertiesView.verifykeylist();
		vpManual("keylist","ckkey2,ckkey3,",s).performTest();
		WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")),s.split(",")[0]);
		
		//reopen and verify the key binding save correctly
		DOF.getRoot().click();
		sleep(1);

	    WN.closeAll();
	    WN.openWorkFlow("wf", "wfmbocreate.xbw");
	    
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
        TestObject[] boxes = DOF.getWFCheckBoxFigures(DOF.getRoot());
        TestObject box = boxes[0];
        ((GefEditPartTestObject)box).click();
        PropertiesView.clickTab("General");
		String key = DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text").toString();
		vpManual("boundkey","ckkey2",key).performTest();
		
	}
}

