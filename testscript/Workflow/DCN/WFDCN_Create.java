// DO NOT EDIT: This file is automatically generated each time the script is modified.
// To modify this file either use 'Insert Java Code Snippet'or 'Insert Java Method' 
// option from simplified script.
package testscript.Workflow.DCN;
import resources.testscript.Workflow.DCN.WFDCN_CreateHelper;
import testscript.Workflow.cfg.Cfg;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.ScrapbookCP;

/**
 * Description   : Functional Test Script
 * @author yangg
 */
public class WFDCN_Create extends WFDCN_CreateHelper
{	
	/**
	 * Script Name   : <b>WFDCN_Create</b>
	 * Generated     : <b>Mar 6, 2012 11:05:27 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/06
	 * @author yangg
	 */
	public void testMain(Object[] args) 
	{ 	

		WN.useProject(Cfg.projectName);
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//		.type("Sybase_ASA_12.x").name("My Sample Database"), 
//		GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/TupleSupport/setup/mbo1.sql");
////		
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->AllMBODataType (dba)"), 100, 100);

        WN.createCacheGroup(new CacheGroup()
				.startParameter(Cfg.projectName)
				.name("dcn")
				.type(CachePolicy.DCN));
		WN.changeCacheGroup(Cfg.projectName, "Department", "Default (Default)", "dcn");	
		WN.changeCacheGroup(Cfg.projectName, "AllMBODataType", "Default (Default)", "dcn");	
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("Department")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("updated")
				.subjectMatchingRule("updated")
				);	
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
							
	}
}
		