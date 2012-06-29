package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CSFO_One2One_Cascade_N_bi_dir_ChildHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Relationship;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CSFO_One2One_Cascade_N_bi_dir_Child extends CSFO_One2One_Cascade_N_bi_dir_ChildHelper
{
	/**
	 * Script Name   : <b>CSFO_One2One_Cascade_N_bi_dir_Child</b>
	 * Generated     : <b>Sep 15, 2011 3:49:26 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/15
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_b (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Wf_ff_a"))
			.target("Wf_ff_b")
			.mapping("aid,aid")
			.composite("true")
			.bidirectional("false")
			.type(Relationship.TYPE_OTO));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Wf_ff_b");
		
		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffb")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffbupdateinstance")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffbdeleteinstance")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("WfffbDetail")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffbcreate")).performTest();
		vpManual("creen", false, WorkFlowEditor.hasScreen("Wfffa")).performTest();
	}
}
//passed
