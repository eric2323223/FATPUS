package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.SISP_ExtractionRule_ADEHelper;
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
import component.entity.model.PK;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SISP_ExtractionRule_ADE extends SISP_ExtractionRule_ADEHelper
{
	/**
	 * Script Name   : <b>SISP_ExtractionRule_ADE</b>
	 * Generated     : <b>Oct 10, 2011 3:01:33 PM</b>
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
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("dept_id=1")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("dept_id,Subject,dept_id="));
		
//		PropertiesView.newExtractionRulesofStartPoint("From,d,d,");
//		vpManual("Properies", "From,d,d,", PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
		
		PropertiesView.editExtractionRulesofStartPoint("Subject","To,d1,d2,dept_id");
		vpManual("Properies", "To,d1,d2,,dept_id", PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
		
		PropertiesView.removeExtractionRulesofStartPoint("To");
		vpManual("Properies", "null", PropertiesView.getExtractionRulesofStartPoint().getKey()).performTest();
	}
}
//passed ,there is not key to show when new a extractionRules
