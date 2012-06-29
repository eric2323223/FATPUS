package testscript.Workflow.ConditionalScreen;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import resources.testscript.Workflow.ConditionalScreen.CdtnScr_Request_Alltypes_1_1_E2EHelper;
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
import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.common.DBUtil;
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Modifications;
import component.entity.model.ObjectQuery;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFCheckbox;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WizardRunner;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class CdtnScr_Request_Alltypes_1_1_E2E extends CdtnScr_Request_Alltypes_1_1_E2EHelper
{
	/**
	 * Script Name   : <b>CdtnScr_Request_Alltypes_1_1_E2E</b>
	 * Generated     : <b>Mar 1, 2012 8:43:35 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/01
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
			//need to create the table AllDT;
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/create_ALLDT.sql");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		
		//create the object query with all data type
		ObjectQuery oq = new ObjectQuery().name("findByALLDT")
		.parameter("int1,,,int1" +
				":string1,,,string1"+
				":string2,,,string2"+
				":long1,,,int1"+
				":date1,,,date1"+
				":datetime1,,,datetime1"+
				":time1,,,time1"+
				":decimal1,,,decimal1"+
				":float1,,,float1"+
				":double1,,,double1"+
				":bool1,,,bool1"+
				":byte1,,,byte1"+
				":short1,,,short1")
			.returnType(ObjectQueryWizard.RT_SINGLE)
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->AllDT");
		new ObjectQueryWizard().create(oq, new WizardRunner()); 
		
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllDT");
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("int1").newKey("int1,int"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("string1").newKey("string1,string"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("string2").newKey("string2,string"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("long1").newKey("long1,int"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("date1").newKey("date1,DateTime"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("datetime1").newKey("datetime1,DateTime"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("time1").newKey("time1,DateTime"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("decimal1").newKey("decimal1,decimal"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("float1").newKey("float1,double"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("double1").newKey("double1,double"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("short1").newKey("short1,int"));
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("byte1").newKey("byte1,string"));
		WorkFlowEditor.addWidget("Start", new WFCheckbox().label("bool1").newKey("bool1,bool"));
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("findByALLDT")
				.type("Online Request")
				.mbo("AllDT")
				.objectQuery("findByALLDT")
				.project(Cfg.projectName)
				.defaultSuccessScreen("AllDTDetail")
				.parametermapping("int1,int1")
				.parametermapping("string1,string1")
				.parametermapping("string2,string2")
				.parametermapping("long1,long1")
				.parametermapping("date1,date1")
				.parametermapping("datetime1,datetime1")
				.parametermapping("time1,time1")
				.parametermapping("decimal1,decimal1")
				.parametermapping("float1,float1")
				.parametermapping("double1,double1")
				.parametermapping("bool1,bool1")
				.parametermapping("byte1,byte1")
				.parametermapping("short1,short1")
				);
		
		WorkFlowEditor.setMenuItem("AllDTdeleteinstance", 	new WFScreenMenuItem().name("Delete")
				.type("Online Request")
				.defaultSuccessScreen("AllDTDetail"));
		
		PropertiesView.addConditionTableOfMenuItem("c1", "Start");
		MainMenu.saveAll();
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser(testscript.Workflow.cfg.Cfg.deviceUser)
			.deployToServer("true")
			.unwiredServer("My Unwired Server"), script()
//			,
//			testscript.Workflow.cfg.Cfg.tplanScript_client_1
			);

		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=AllDT_int1_attribKey,value=1|"+
				"id=int1,value=1"));
		
		sleep(2);
		//vp2:verify the update record has added into backend db in ALLDT
		vpManual("db",0,DBUtil.getDB("select * from AllDT where int1=1 and string1='1'")).performTest();
		
	
		sleep(2);
		//restore the initial table record
		String sql = 
			"insert into dba.AllDT values(1,'1','1',1,'2012-01-03','2012-01-03 00:00:00.0','12:25:13',1.000,1.0,1.0,0,'1',1);";
		try {
			DBUtil.runSQL((IDBResource) RC.getResource(ASA.class), sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

		private CustomJsTestScript script() {
		CustomJsTestScript s = new CustomJsTestScript();
			s.setConditionScreenRule(Cfg.CdtnScr_Request_Alltypes_1_1_E2E);
		
			s.screen("Start").setData("int1","1");
			s.screen("Start").setData("string1","1");
			s.screen("Start").setData("string2","1");
			s.screen("Start").setData("long1","1");
			s.screen("Start").setData("date1","2012-01-03");
			s.screen("Start").setData("datetime1","2012-01-03 00:00:00.0");
			s.screen("Start").setData("time1","12:25:13");
			s.screen("Start").setData("decimal1","1");
			s.screen("Start").setData("float1","1");
			s.screen("Start").setData("double1","1");
			s.screen("Start").setData("byte1","1");
			s.screen("Start").setData("bool1","false");
			s.screen("Start").setData("short1","1");
			s.screen("Start").moveTo("AllDTDetail").throughMenuItem("findByALLDT");
			
			s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
			s.screen("AllDTDetail").moveTo("AllDTdeleteinstance").throughMenuItem("Open AllDTdeleteinstance");
			s.screen("AllDTdeleteinstance").moveTo("Start").throughMenuItem("Delete");
			s.screen("Start").getData("int1");
		return s;
		}
		
	}

//passed BB6T 20120305