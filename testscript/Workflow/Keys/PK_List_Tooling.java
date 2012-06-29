package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.PK_List_ToolingHelper;
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

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class PK_List_Tooling extends PK_List_ToolingHelper
{
	/**
	 * Script Name   : <b>PK_List_Tooling</b>
	 * Generated     : <b>Oct 11, 2011 4:12:51 PM</b>
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
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->customer (dba)");
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("PersonalizationKey")
				.type("STRING(20)")
				.storage("Transient")
				.nullable("false")
				.protect("false")
				.defaultValue("<null>"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName,"Customer");
		mbo.setSQLQuery("SELECT id,fname,lname,address,city,state,zip,phone,company_name FROM sampledb.dba.customer where fname=:fname");
		mbo.setLoadArgument(new LoadArgument().name("fname").pk("PersonalizationKey"));
		mbo.setOperationParameterPK("create", "fname", "PersonalizationKey");
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "Customer", "create");
		PropertiesView.addPkMappingOfMenuItemInScreen("Customer_create","Create","PersonalizationKey,key1,string");
		vpManual("properties","PersonalizationKey,key1",PropertiesView.getPKMappingofMenuItem().getKey());
	}
}
//passed
