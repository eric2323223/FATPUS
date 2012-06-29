package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_DB_mbo_attr_binaryHelper;
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
import component.entity.model.ScrapbookCP;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_DB_mbo_attr_binary extends SP_Email_DB_mbo_attr_binaryHelper
{
	/**
	 * Script Name   : <b>SP_Email_DB_mbo_attr_binary</b>
	 * Generated     : <b>Sep 13, 2011 4:18:22 PM</b>
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
			.type("Sybase_ASA_12.x").name("My Sample Database"), Cfg.create_table_ff_startPoint);
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wfff (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT).mbo("Wfff"));
		PropertiesView.clickTab("Keys");
		PropertiesView.setNewKeyBindMBO("key1,int,Wfff,dept_binary");
		vpManual("Properies", "key1,string,Wfff/dept_binary", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getKey()).performTest();
		
		PropertiesView.setKeyUserDefined("key1");
		vpManual("Properies", "key1,string,", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getKey()).performTest();
	}
}
//passed
