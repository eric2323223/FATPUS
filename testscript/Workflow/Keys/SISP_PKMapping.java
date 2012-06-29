package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.SISP_PKMappingHelper;
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
import component.entity.model.LoadArgument;
import component.entity.model.PK;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SISP_PKMapping extends SISP_PKMappingHelper
{
	/**
	 * Script Name   : <b>SISP_PKMapping</b>
	 * Generated     : <b>Oct 10, 2011 4:18:34 PM</b>
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
				.subjectMatchingRule("findall"));
		
		vpManual("Properies", "PersonalizationKey,PersonalizationKey", PropertiesView.getPKMappingofStartPoint().getKey()).performTest();

		PropertiesView.editPkMappingOfStartPoint("PersonalizationKey","PersonalizationKey,Department_dept_name_attribKey");
		vpManual("Properies", "PersonalizationKey,Department_dept_name_attribKey", PropertiesView.getPKMappingofStartPoint().getKey()).performTest();
		
		PropertiesView.deletePkMappingOfStartPoint("PersonalizationKey");
		vpManual("Properies", "null", PropertiesView.getPKMappingofStartPoint().getKey()).performTest();
		
		PropertiesView.addPkMappingOfStartPoint("PersonalizationKey,Department_dept_id_attribKey");
		vpManual("Properies", "PersonalizationKey,Department_dept_id_attribKey", PropertiesView.getPKMappingofStartPoint().getKey()).performTest();
		
	}
}
//passed
