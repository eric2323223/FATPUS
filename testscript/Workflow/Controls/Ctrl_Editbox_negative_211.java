package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Editbox_negative_211Helper;
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
import com.sybase.automation.framework.widget.helper.MenuHelper;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Editbox_negative_211 extends Ctrl_Editbox_negative_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 27, 2011 3:12:07 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/27
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test Ctrl_Editbox_negative
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("editboxnav")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addWidget(Cfg.projectName, "editboxnav.xbw", "Start", new WFEditBox().label("editbox:"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("e"));	
		DOF.getWFMenuItemFigure(DOF.getRoot(), "e").click(RIGHT);
		DOF.getContextMenu().click(atPath("Edit->Copy"));
		//******verify menuitem could not paste to control scr
		DOF.getWFScreenDisplayFigure().click(RIGHT);
		
		vpManual("pastedisable","false",MenuHelper.isItemEnabled(DOF.getMenu(), "Edit->Paste")).performTest();
	
		//******verify control could not paste to menu scr
	    TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
	    TestObject box = boxes[0];
	   ((GefEditPartTestObject)box).click(RIGHT);
	   DOF.getContextMenu().click(atPath("Edit->Copy"));
		DOF.getWFMenuFigure(DOF.getRoot()).click(atPoint(3,3));
		vpManual("pastedisable1","false",MenuHelper.isItemEnabled(DOF.getMenu(), "Edit->Paste")).performTest();
		
	    // go to flow design page and paste disabled
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		sleep(1);
		vpManual("pastedisable2", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Paste")).performTest();
		System.out.print(MenuHelper.isItemEnabled(DOF.getMenu(), "Edit->Paste"));
		
		// go to screen design page and paste screen disabled
		DOF.getWFScreenFigure(DOF.getRoot(), "Start").click(RIGHT);
		DOF.getContextMenu().click(atPath("Edit->Copy"));
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		DOF.getWFScreenDesignCanvas().click();
		vpManual("pastedisable3","false",MenuHelper.isItemEnabled(DOF.getMenu(), "Edit->Paste")).performTest();
		
		
		//verify control attribute 
		TestObject[] boxes1 = DOF.getWFEditBoxFigures(DOF.getRoot());
	    TestObject box1 = boxes1[0];
	    ((GefEditPartTestObject)box1).click();
	    PropertiesView.clickTab("General");
		PropertiesView.set(new WFEditBox().logicalType("NUMERIC")
				.Numberofdecimal("29"));
		String s = PropertiesView.getStatusMessage();
		vpManual("errmessage1","Value not updated, the number of decimals must be between 0 and 28, inclusive.",s).performTest();
		
		PropertiesView.set(new WFEditBox().logicalType("NUMERIC")
				.Numberofdecimal("-1"));
		sleep(1);
		vpManual("errmessage2","Value not updated, the number of decimals must be between 0 and 28, inclusive.",PropertiesView.getStatusMessage()).performTest();
		
		
		PropertiesView.set(new WFEditBox().logicalType("NUMERIC")
				.maxLength("-1"));
		DOF.getTextField(DOF.getRoot(), "Number of decimals:").click();
		sleep(1);
		vpManual("errmessage3","Value not updated, the maximum length must be greater than or equal to 0.",PropertiesView.getStatusMessage()).performTest();
		
		PropertiesView.set(new WFEditBox().logicalType("NUMERIC")
				.lines("sssss"));
		DOF.getTextField(DOF.getRoot(), "Number of decimals:").click();
		sleep(1);
		vpManual("errmessage4","Value not updated, the valid value should be integer.",PropertiesView.getStatusMessage()).performTest();
		
		PropertiesView.set(new WFEditBox().logicalType("NUMERIC")
				.maxValue("WWW")
				);
		DOF.getTextField(DOF.getRoot(), "Maximum value:").click();
		sleep(1);
		vpManual("errmessage5","Value not updated, WWW is not a valid numeric.",PropertiesView.getStatusMessage()).performTest();
		
		PropertiesView.set(new WFEditBox().logicalType("NUMERIC")
				.validationExpression("("));
		DOF.getTextField(DOF.getRoot(), "Number of decimals:").click();
		sleep(1);
		vpManual("errmessage6","Value not updated, ( is not a valid regular expression.",PropertiesView.getStatusMessage()).performTest();
		
		PropertiesView.set(new WFEditBox().logicalType("TEXT")
				.password(true));

	}
}

