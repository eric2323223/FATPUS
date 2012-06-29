package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.PKMapping_ReStartEclipseHelper;
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
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.LoadArgument;
import component.entity.model.PK;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.resource.Criteria;
import component.entity.resource.RC;
import component.entity.resource.SAP;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class PKMapping_ReStartEclipse extends PKMapping_ReStartEclipseHelper
{
	/**
	 * Script Name   : <b>PKMapping_ReStartEclipse</b>
	 * Generated     : <b>Oct 11, 2011 6:35:07 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/11
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
//		WN.useProject(Cfg.projectName);
//		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
//				.dataSourceType("SAP")
//				.name("Bank")
//				.connectionProfile("sap")
//				.operation("Application Components->Cross-Application Components->Bank->Bank,GetList")
//				.parameter("BANK_CTRY,in")
//				.parameter("BANK_LIST,out")
//				.parameter("BANK_LIST,in")
//				.parameterValue("BANK_CTRY,AM"));
//		
//		//Create operation "create"...
//		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Bank"))
//				.type("CREATE")
//				.name("create")
//				.operation("Application Components->Cross-Application Components->Bank->Bank,Create")
//				.parameter("BANK_ADDRESS->CITY[CHAR],in")
//				.parameter("BANK_ADDRESS->BANK_NAME[CHAR],in")
//				.parameter("BANK_CTRY,in")
//				.parameter("BANK_KEY,in")
//			);
//		
//		
//		WN.createPK(new PK().startParameter(Cfg.projectName)
//				.name("pkString")
//				.type("STRING(20)")
//				.storage("Transient"));
//		
//		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Bank");
//		mbo.setLoadArgument(new LoadArgument().name("BANK_CTRY").pk("pkString"));
//		MainMenu.saveAll();
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.dragMbo(Cfg.projectName, "Bank");
//		
//		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findall")
//				.type("Online Request")
//				.project(Cfg.projectName)
//				.mbo("Bank")
//		.objectQuery("findAll")
//				);
//		
//		
//		
//		WorkFlowEditor.addScreen("create");
//		
//		WorkFlowEditor.link("BankDetail", "create");
//		WorkFlowEditor.addWidget("create", new WFEditBox().label("CTRY").newKey("key1,string"));
		WorkFlowEditor.addMenuItem("create", new WFScreenMenuItem().name("crete")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Bank")
				.operation("create")
				.pkMapping("pkString,key1"));
		
		//TBD need to go on...
		
		
		
		
		
		
		
		
//		WN.createPK(new PK().startParameter(Cfg.projectName)
//				.name("pk1")
//				.type("STRING(20)")
//				.storage("Transient"));
//		WN.createPK(new PK().startParameter(Cfg.projectName)
//				.name("pk2")
//				.type("STRING(20)")
//				.storage("Transient"));
//		WN.createPK(new PK().startParameter(Cfg.projectName)
//				.name("pk2")
//				.type("STRING(20)")
//				.storage("Transient"));
//		WN.createPK(new PK().startParameter(Cfg.projectName)
//				.name("pk2")
//				.type("STRING(20)")
//				
//				.storage("Transient"));
//		MBOProperties mbo = new MBOProperties(Cfg.projectName,"Department");
//		mbo.setSQLQuery("SELECT dept_id,dept_name,dept_head_id FROM sampledb.dba.department where dept_id=:dept_name");
//		mbo.setLoadArgument(new LoadArgument().name("dept_name").pk("PersonalizationKey"));
//	
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF")
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.dragOperation(Cfg.projectName,"Department" ,"create");
		
		
//		WorkFlowEditor.addWidget("Department_create", new WFEditBox().label("My:")
//				.labelPosition("LEFT")
//				.newKey("key3,string"));
//		
//		PropertiesView.addPkMappingOfMenuItemInScreen("Department_create", "Create", "PersonalizationKey,key3");
//		vpManual("Properies", "PersonalizationKey,key3", PropertiesView.getPKMappingofMenuItem().getKey()).performTest();
//		MainMenu.saveAll();
//		
//		//restart ET...
//		WN.restart();
//		sleep(12);
//		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
//		sleep(2);
//		//OPEN ET...
//		
//		//VP:The PK mapping information should not be lost after restarting
//		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
//		DOF.getWFScreenFigure(DOF.getRoot(), "Department_create").doubleClick();
//		DOF.getWFMenuItemFigure(DOF.getRoot(), "Create").click();
//		vpManual("Properies", "PersonalizationKey,key3", PropertiesView.getPKMappingofMenuItem().getKey()).performTest();
//		
	}
}

