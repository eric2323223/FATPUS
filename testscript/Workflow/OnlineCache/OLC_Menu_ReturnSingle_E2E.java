package testscript.Workflow.OnlineCache;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;
import com.sybase.automation.framework.widget.helper.WO;

import resources.testscript.Workflow.OnlineCache.OLC_Menu_ReturnSingle_E2EHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DbMbo;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.ObjectQuery;
import component.entity.model.WFCheckbox;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class OLC_Menu_ReturnSingle_E2E extends OLC_Menu_ReturnSingle_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Menu_ReturnSingle_E2E</b>
	 * Generated     : <b>Oct 21, 2011 12:12:28 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/21
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.setCacheGroup(Cfg.projectName, "Default (Default)", new CacheGroup().type(CachePolicy.ONLINE));
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/Alldate.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllData (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "AllData");
		mbo.setMboDefinition("sql", "SELECT * FROM sampledb.dba.AllData where type_INT=:type_INT and type_STRING=:type_STRING"+
                                     " and type_DATETIME=:type_DATETIME and type_DECIMAL=:type_DECIMAL and " +
                                     " type_DOUBLE=:type_DOUBLE " +
                                     " and type_FLOAT=:type_FLOAT and type_BOOLEAN=:type_BOOLEAN");
		
		TopLevelTestObject dialog = DOF.getDialog("Execution Error");
		DOF.getButton(dialog,"OK").click();
//		
//		mbo.setLoadArgument(new LoadArgument().name("type_INT").type("INT").propagateTo("type_INT"));
//		mbo.setLoadArgument(new LoadArgument().name("type_STRING").type("STRING").propagateTo("type_STRING"));
//		mbo.setLoadArgument(new LoadArgument().name("type_DATETIME").type("DATETIME").propagateTo("type_DATETIME"));
//		mbo.setLoadArgument(new LoadArgument().name("type_DECIMAL").type("DECIMAL").propagateTo("type_DECIMAL"));
//		mbo.setLoadArgument(new LoadArgument().name("type_DOUBLE").type("DOUBLE").propagateTo("type_DOUBLE"));
//		mbo.setLoadArgument(new LoadArgument().name("type_FLOAT").type("FLOAT").propagateTo("type_FLOAT"));
//		mbo.setLoadArgument(new LoadArgument().name("type_BOOLEAN").type("BOOLEAN").propagateTo("type_BOOLEAN"));
//		
//		// modify the object queries' default value
//		PropertiesView.maximize();
//		PropertiesTabHelper.clickTabName("Attributes");
//		DOF.getCTabFolder(DOF.getRoot(), "Object Queries").click(atText("Object Queries"));
//		
//		DOF.getTable(DOF.getRoot()).click(atCell(atRow(atIndex(0)), atColumn("Return type"))); 
//		WO.setCCombo(DOF.getCCombo(DOF.getTable(DOF.getRoot())),"Single object");
//		PropertiesView.restore();
	//	MainMenu.saveAll();
		
//	//	TopLevelTestObject dialog1 = DOF.getDialog("Save Resoure");
//	//	DOF.getButton(dialog1,"OK").click();
		
////		
//		
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.server("My Unwired Server")
//				 .mode(DeployOption.MODE_REPLACE)
//				.serverConnectionMapping("My Sample Database,sampledb"));
//		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name(Cfg.wfName)
//				.option(WorkFlow.SP_CLIENT_INIT));
//		
//		WorkFlowEditor.dragMbo(Cfg.projectName, "AllData");
//		WorkFlowEditor.addWidget("Start", new WFEditBox().label("type_INT")
//				.newKey("key1,int"));
//		WorkFlowEditor.addWidget("Start", new WFEditBox().label("type_STRING")
//				.newKey("key2,string"));
//		WorkFlowEditor.addWidget("Start", new WFEditBox().label("type_DATETIME")
//				.newKey("key3,DateTime"));
//		WorkFlowEditor.addWidget("Start", new WFEditBox().label("type_DECIMAL")
//				.newKey("key4,decimal"));
//		WorkFlowEditor.addWidget("Start", new WFEditBox().label("type_DOUBLE")
//				.newKey("key5,double"));
//		WorkFlowEditor.addWidget("Start", new WFEditBox().label("type_FLOAT")
//				.newKey("key6,double"));
//		WorkFlowEditor.addWidget("Start", new WFCheckbox().label("type_BOOLEAN")
//				.newKey("key7,bool"));
//		
//		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
//				.name("find")
//				.project(Cfg.projectName)
//				.mbo("AllData")
//				.type("Online Request")
//				.objectQuery("findByParameter")
//				.defaultSuccessScreen("AllDataDetail")
//				.parametermapping("type_INT,key1")
//		        .parametermapping("type_STRING,key2")
//		        .parametermapping("type_DATETIME,key3")
//		        .parametermapping("type_DECIMAL,key4")
//		        .parametermapping("type_DOUBLE,key5")
//		        .parametermapping("type_FLOAT,key6")
//		        .parametermapping("type_BOOLEAN,key7")
//		        
//		);
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("dt")
				.unwiredServer("My Unwired Server"), 
				customJsTestScripts(), "tplan.Workflow.iconcommon.BB.myWF_icon2.Script" );
		
		WFCustomizer.verifyResult(new WFClientResult()
		.data("id=AllData_id_attribKey,value=3"));
	
	}

	private CustomJsTestScript customJsTestScripts() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("key1", "0");
		s.screen("Start").setData("key2", "!@#$%^&*()");
		s.screen("Start").setData("key3", "2010-08-08T20:00:00");
		s.screen("Start").setData("key4", "2");
		s.screen("Start").setData("key5", "0");
		s.screen("Start").setData("key6", "0");
		s.screen("Start").setData("key7", "false");
		
		s.screen("Start").moveTo("AllData").throughMenuItem("find");
		s.screen("AllDataDetail").getData("AllData_id_attribKey");
		return s;
	}
}

