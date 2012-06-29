package testscript.Workflow.Designer_Prop;
import java.awt.Point;

import resources.testscript.Workflow.Designer_Prop.Prop_FlowDesign_General_1Helper;
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
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Module;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Prop_FlowDesign_General_1 extends Prop_FlowDesign_General_1Helper
{
	/**
	 * Script Name   : <b>Prop_FlowDesign_General_1</b>
	 * Generated     : <b>Sep 28, 2011 1:01:23 PM</b>
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
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WN.closeAll();
		WN.openWFWithRight(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		Point point = WFFlowDesigner.getValidPoint();
		DOF.getWFFlowDesignCanvas().click(atPoint(point.x,point.y));
		PropertiesView.clickTab("General");
		sleep(1);
		WorkFlowEditor.editModule(new Module().name("newmodulename"));
     	vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
		WorkFlowEditor.editModule(new Module().version("2"));
		vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
		
		WorkFlowEditor.editModule(new Module().discription("newmodule"));
		vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
		WorkFlowEditor.editModule(new Module().displayName("MyWF2"));
		vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
		vpManual("haserror", 0 , Problems.getErrors().size()).performTest();
	}
}
//passed
