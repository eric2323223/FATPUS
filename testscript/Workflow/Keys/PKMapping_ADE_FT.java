package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.PKMapping_ADE_FTHelper;
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
import component.entity.model.PK;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class PKMapping_ADE_FT extends PKMapping_ADE_FTHelper
{
	/**
	 * Script Name   : <b>PKMapping_ADE_FT</b>
	 * Generated     : <b>Oct 10, 2011 7:09:17 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/10
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("PersonalizationKey1")
				.type("STRING(20)")
				.nullable("true")
				.storage("Transient"));
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("PersonalizationKey2")
				.type("STRING(20)")
				.nullable("true")
				.storage("Transient"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("Submit")
				.type("Submit Workflow"));
		
		PropertiesView.addPkMappingOfMenuItem("PersonalizationKey1,key4,string");
		vpManual("properties","PersonalizationKey1,key4",PropertiesView.getPKMappingofMenuItem().getKey()).performTest();
		
		PropertiesView.editPkMappingOfMenuItem("PersonalizationKey1","PersonalizationKey2,key4");
		vpManual("properties","PersonalizationKey2,key4",PropertiesView.getPKMappingofMenuItem().getKey()).performTest();
		
		PropertiesView.deletePkMappingOfStartPoint("PersonalizationKey2");
		vpManual("properties","null",PropertiesView.getPKMappingofMenuItem().getKey()).performTest();
	}
}
//passed
