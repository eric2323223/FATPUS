package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.PKMapping_TransRule_Extract_FTHelper;
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
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.LoadArgument;
import component.entity.model.PK;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class PKMapping_TransRule_Extract_FT extends PKMapping_TransRule_Extract_FTHelper
{
	/**
	 * Script Name   : <b>PKMapping_TransRule_Extract_FT</b>
	 * Generated     : <b>Oct 11, 2011 12:44:38 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/11
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("PersonalizationKey")
				.type("STRING(20)")
				.storage("Transient")
				.nullable("false")
				.protect("false"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName,"Department");
		mbo.setSQLQuery("SELECT dept_id,dept_name,dept_head_id FROM sampledb.dba.department where dept_id=:dept_name");
		mbo.setLoadArgument(new LoadArgument().name("dept_name").pk("PersonalizationKey"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall")
				);
		vpManual("Properies", "Body,,,,PersonalizationKey", PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
		PropertiesView.editExtractionRulesofStartPoint("Body","To,,Department_delete_dept_name_paramKey,,PersonalizationKey");
		vpManual("Properies", "To,,Department_delete_dept_name_paramKey,,PersonalizationKey", PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
		
	}
}
//passed
