package testscript.Workflow.ConditionalScreen;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import resources.testscript.Workflow.ConditionalScreen.CdtnScr_Request_Alltypes_2_1_E2EHelper;
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

import component.entity.EE;
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
public class CdtnScr_Request_Alltypes_2_1_E2E extends CdtnScr_Request_Alltypes_2_1_E2EHelper
{
	/**
	 * Script Name   : <b>CdtnScr_Request_Alltypes_2_1_E2E</b>
	 * Generated     : <b>Mar 1, 2012 8:55:59 PM</b>
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
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		
		//create the object query with all data type
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
			"id=AllDT_int1_attribKey,value=2|"+
			"id=AllDT_int1_attribKey,value=2"));

		sleep(2);
		
		//vp2:verify the update record has added into backend db in ALLDT
		vpManual("db",0,DBUtil.getDB("select * from AllDT where int1=2 and string1='2'")).performTest();
		
		
		//restore the initial table record
		String sql = 
			"insert into dba.AllDT values(2,'2','2',2,'2012-02-03','2012-02-03 00:00:00.0','02:02:13',2.000,2.0,2.0,0,'2',2);";
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
			s.screen("Start").setData("int1","2");
			s.screen("Start").setData("string1","2");
			s.screen("Start").setData("string2","2");
			s.screen("Start").setData("long1","2");
			s.screen("Start").setData("date1","2012-02-03");
			s.screen("Start").setData("datetime1","2012-02-03 00:00:00.0");
			s.screen("Start").setData("time1","02:02:13");
			s.screen("Start").setData("decimal1","2");
			s.screen("Start").setData("float1","2");
			s.screen("Start").setData("double1","2");
			s.screen("Start").setData("bool1","false");
			s.screen("Start").setData("byte1","2");
			s.screen("Start").setData("short1","2");
			
			s.screen("Start").moveTo("AllDTDetail").throughMenuItem("findByALLDT");
			s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
			s.screen("AllDTDetail").moveTo("AllDTdeleteinstance").throughMenuItem("Open AllDTdeleteinstance");
			s.screen("AllDTdeleteinstance").moveTo("AllDTDetail").throughMenuItem("Delete");
			s.screen("AllDTDetail").getData("AllDT_int1_attribKey");
		return s;
		}
	}

//passed BB6T 20120305
