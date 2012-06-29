package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CSFO_One2One_N_Cascade_Bi_dir_ParentHelper;
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
public class CSFO_One2One_N_Cascade_Bi_dir_Parent extends CSFO_One2One_N_Cascade_Bi_dir_ParentHelper
{
	/**
	 * Script Name   : <b>CSFO_One2One_Not_Cascade_BiDir_Parent</b>
	 * Generated     : <b>Sep 8, 2011 2:47:03 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/08
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_b (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Wf_ff_a"))
			.target("Wf_ff_b")
			.mapping("aid,aid")
			.composite("false")
			.bidirectional("true")
			.type(Relationship.TYPE_OTO));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Wf_ff_a");
		
		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffa")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffaupdateinstance")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffadeleteinstance")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("WfffaDetail")).performTest();
		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffacreate")).performTest();
		vpManual("creen", false, WorkFlowEditor.hasScreen("Wfffb")).performTest();
	}
}

