package testscript.Workflow.Designer_Prop;
import java.awt.Point;

import resources.testscript.Workflow.Designer_Prop.Prop_ScreenDesign_Keys_1Helper;
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
import component.entity.Properties;
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFCheckbox;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Prop_ScreenDesign_Keys_1 extends Prop_ScreenDesign_Keys_1Helper
{
	/**
	 * Script Name   : <b>Prop_ScreenDesign_Keys_1</b>
	 * Generated     : <b>Aug 27, 2011 9:15:17 AM</b>
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
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click();
		WFScreenDesigner.showPropertiesView();
		
		String one = PropertiesView.getKeyAttributesofScreenDesign().getKey();
		System.out.println("intinial key in blank:"+one);
		vpManual("one", "Department_create_dept_id_paramKey,int,,:Department_create_dept_name_paramKey,string,,:Department_create_dept_head_id_paramKey,int,,:ErrorLogs,list,,", one).performTest();
		
		String two = PropertiesView.getKeyOfScreen("Departmentcreate").getKey();
		System.out.println("intinial key in screen:"+two);
		vpManual("two",one,two).performTest();
		
		WFScreenDesigner.addMenuItem(new WFScreenMenuItem()
			.name("testmenu"));
		
		Point point = WFScreenDesigner.getValidPoint();
		DOF.getWFScreenDesignCanvas().click(atPoint(point.x,point.y));
		
		String three = PropertiesView.getKeyOfScreen("Departmentcreate").getKey();
		System.out.println("menu key in screen:"+three);
		vpManual("three",one,three).performTest();
	
		WFScreenDesigner.addWidget(new WFCheckbox()
			.label("ckbox1")
			.labelPosition("LEFT")
			.newKey("key1,string"));
		
		vpManual("four", "Department_create_dept_id_paramKey,int,,:Department_create_dept_name_paramKey,string,,:Department_create_dept_head_id_paramKey,int,,:key1,string,,:ErrorLogs,list,,", PropertiesView.getKeyOfScreenByClick("Departmentcreate").getKey()).performTest();
	}
}
//passed
