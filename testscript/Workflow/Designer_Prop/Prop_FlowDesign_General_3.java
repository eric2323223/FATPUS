package testscript.Workflow.Designer_Prop;
import java.awt.Point;

import resources.testscript.Workflow.Designer_Prop.Prop_FlowDesign_General_3Helper;
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

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Prop_FlowDesign_General_3 extends Prop_FlowDesign_General_3Helper
{
	/**
	 * Script Name   : <b>Prop_FlowDesign_General_3</b>
	 * Generated     : <b>Aug 27, 2011 11:29:34 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/27
	 * @author ffan
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
		
		WorkFlowEditor.editModule(new Module().deleteMessage(true));
		WorkFlowEditor.editModule(new Module().markMessage(true));
		vpManual("state","NOT_SELECTED",PropertiesView.getStateOfButton("deleteMessage")).performTest();
		WorkFlowEditor.editModule(new Module().deleteMessage(true));
		vpManual("state","NOT_SELECTED",PropertiesView.getStateOfButton("markMessage")).performTest();
		WorkFlowEditor.editModule(new Module().deleteMessage(false));
	}
}
//passed
