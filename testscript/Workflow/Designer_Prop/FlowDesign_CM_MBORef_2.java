package testscript.Workflow.Designer_Prop;
import java.awt.Point;

import resources.testscript.Workflow.Designer_Prop.FlowDesign_CM_MBORef_2Helper;
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
import com.sybase.automation.framework.common.ObjectMarshaller;
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.dialog.WFKeyDialog;
import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.entity.model.WFKey;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class FlowDesign_CM_MBORef_2 extends FlowDesign_CM_MBORef_2Helper
{
	/**
	 * Script Name   : <b>FlowDesign_CM_MBORef_2</b>
	 * Generated     : <b>Nov 26, 2011 3:11:34 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/26
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->bonus (dba)");
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		
		Point point = getValidPoint();
		DOF.getWFFlowDesignCanvas().click(RIGHT, atPoint(point.x, point.y));
		DOF.getContextMenu().click(atPath("Mobile Business Object References..."));
		
		String mbo = getTableOfMBOReferences().getKey();
		System.out.println("after edit:"+mbo);
		vpManual("before","Department,1.0,wf:1.0",mbo).performTest();
		
		//edit...
		editMboReferences("Department","Bonus");
		
		String mbo2 = getTableOfMBOReferences().getKey();
		System.out.println("after edit:"+mbo2);
		vpManual("after","Bonus,1.0,wf:1.0",mbo2).performTest();
		
		//close the dialog..
		TopLevelTestObject dialog = DOF.getDialog("Mobile Business Object References");
		dialog.close();
		
	}
	
	public static Point getValidPoint() {
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		while (true) {
			int x = (int) (Math.random() * 500);
			int y = (int) (Math.random() * 500);
			DOF.getWFFlowDesignCanvas().click(atPoint(x, y));
			sleep(1);
			if (DOF.getCLabelByContent(DOF.getRoot(), "Application") != null) {
				return new Point(x, y);
			}
		}
	}
	
	public static StartPoint getTableOfMBOReferences(){
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getDialog("Mobile Business Object References")), "Name");
		String[] version = TableHelper.getColumnData(DOF.getTable(DOF.getDialog("Mobile Business Object References")), "Version");
		String[] packge = TableHelper.getColumnData(DOF.getTable(DOF.getDialog("Mobile Business Object References")), "Package");
		StartPoint sp = new StartPoint();
		if(name.length==0)
			sp.key("null");
		else{
			for(int i=0;i<name.length;i++){
				if(name[i]!="")
					sp.key(name[i]+","+version[i]+","+packge[i]);
			}
		}
		return sp;
	}
	
	public static void editMboReferences(String from,String to){
		GuiSubitemTestObject table = DOF.getTable(DOF.getDialog("Mobile Business Object References"));
		int row = TableHelper.getRowIndexOfRecordInColumn(table, "Name", from);
			table.click(atCell(atRow(row), atColumn("Name")));
			DOF.getButton(DOF.getDialog("Mobile Business Object References"), "Ed&it...").click();
			TopLevelTestObject dialog = DOF.getDialog("Mobile Business Object Reference");
			DOF.getButton(dialog,"&Search...").click();
			
			////////////////dialog2 begin.........
			TopLevelTestObject dialog2 = DOF.getDialog("Search For Mobile Business Object");
			DOF.getButton(dialog2, "&Search").click();
			sleep(1);
			GuiSubitemTestObject table2 = DOF.getTable(dialog2);
			int row2 = TableHelper.getRowIndexOfRecordInColumn(table2, "Name", to);
			table2.click(atCell(atRow(row2), atColumn("Name")));
			DOF.getButton(dialog2, "OK").click();
			////////////////////////dialog2 end........
			
			DOF.getButton(dialog, "OK").click();
	}
}

//passed