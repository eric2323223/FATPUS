package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_DB_mbo_attr_booleanHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_DB_mbo_attr_boolean extends SP_Email_DB_mbo_attr_booleanHelper
{
	/**
	 * Script Name   : <b>SP_Email_DB_mbo_attr_boolean</b>
	 * Generated     : <b>Sep 13, 2011 3:16:49 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/13
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), Cfg.create_table_T_olc);
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.setEmailMbo("T_olc", Cfg.projectName);
		WorkFlowEditor.addScreen("A");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "A");
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		
		PropertiesView.clickTab("Keys");
		PropertiesView.setNewKeyBindMBO("key1,bool,T_olc,b");
		vpManual("Properies", "key1,bool,T_olc/b", PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey()).performTest();
		
		PropertiesView.setKeyUserDefined("key1");
		vpManual("Properies", "key1,bool,",PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey()).performTest();
		vpManual("noerror", 0,Problems.getErrors().size()).performTest();
	
	}
}
//passed
//passed

