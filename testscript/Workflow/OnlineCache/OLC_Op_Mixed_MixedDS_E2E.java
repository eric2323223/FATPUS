package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OLC_Op_Mixed_MixedDS_E2EHelper;
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
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.CacheGroup;
import component.entity.model.CachePolicy;
import component.entity.model.DeployOption;
import component.entity.model.LoadArgument;
import component.entity.model.OperationParameter;
import component.entity.model.SAPCP;
import component.entity.model.SAPMBO;
import component.entity.model.SAPOperation;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WSCP;
import component.entity.model.WSMBO;
import component.entity.model.WSOperation;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.RC;
import component.entity.resource.SAP;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class OLC_Op_Mixed_MixedDS_E2E extends OLC_Op_Mixed_MixedDS_E2EHelper
{
	/**
	 * Script Name   : <b>OLC_Op_Mixed_MixedDS_E2E</b>
	 * Generated     : <b>Apr 24, 2012 1:50:43 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/24
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createCacheGroup(new CacheGroup().startParameter(Cfg.projectName).name("online").type(CachePolicy.ONLINE));
		
		//1.create a SAP to do update operation:
		EE.createSAPCP(new SAPCP(RC.getResource(SAP.class)).name("sap"), true);
		WN.createMbo(new SAPMBO().startParameter(Cfg.projectName)
			.dataSourceType("SAP")
			.name("Bank")
			.connectionProfile("sap")
			.operation("Application Components->Cross-Application Components->Bank->Bank,GetList")
			.parameter("BANK_CTRY,in")
			.parameter("BANK_LIST,out")
			.parameter("BANK_LIST,in")
			.parameterValue("BANK_CTRY,AM"));
		WN.addSAPOperation(new SAPOperation().startParameter(WN.mboPath(Cfg.projectName, "Bank"))
				.type("UPDATE")
				.name("update")
				.operation("Application Components->Cross-Application Components->Bank->Bank,Change")
				.parameter("BANK_ADDRESS->CITY[CHAR],in")
				.parameter("BANKCOUNTRY,in")
				.parameter("BANKKEY,in")
				.parameter("RETURN,out")
				);
		MBOProperties mbodb = new MBOProperties(Cfg.projectName, "Bank");
		mbodb.setLoadArgument(new LoadArgument().name("BANK_CTRY")
				.propagateTo("BANK_CTRY")
				);
		
		//2.create a DB(department) to do update Operation
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		
		//3.create a WS (Online) to do delete operation
		EE.createWSCP(new WSCP().name("ws").wsdl(Cfg.wsdl_file), true);
		WN.createMbo(new WSMBO().startParameter(Cfg.projectName)
				.dataSourceType("Web Service")
				.connectionProfile("ws")
				.name("UserInfoByID")
				.method("SUPWebServicesSoapPort:UserInfoByID"));
		
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "UserInfoByID");
		mbo.setLoadArgument(new LoadArgument().name("userID").propagateTo("id"));
		//add delete operation
		WN.addWSOperation(new WSOperation().startParameter(WN.mboPath(Cfg.projectName, "UserInfoByID"))
				.type("DELETE")
				.name("delete")
				.method("SUPWebServicesSoapPort:DeleteUser")							
				);
		WN.changeCacheGroup(Cfg.projectName, "UserInfoByID", "Default (Default)", "online");
		MainMenu.saveAll();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb")
				.serverConnectionMapping("ws,ws")
				.serverConnectionMapping("sap,sap")
				);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "UserInfoByID");
		WorkFlowEditor.dragOperation(Cfg.projectName, "Department","update");
		WorkFlowEditor.dragMbo(Cfg.projectName, "Bank");
		
		//1.ws>>>>>>>>>>
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("ws_id").newKey("ws_idkey,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("OpenWSByID")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("UserInfoByID")
				.defaultSuccessScreen("UserInfoByIDdelete1")
				.objectQuery("findByParameter")
				.parametermapping("userID,ws_idkey"));
		
		WorkFlowEditor.setMenuItem("UserInfoByIDdelete1", new WFScreenMenuItem().name("Delete1").defaultSuccessScreen("Start"));
		
		//2.SAP >>>>>>>>>
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findallBANK")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Bank")
				.defaultSuccessScreen("Bank")
				.objectQuery("findAll")
				);
		
		WorkFlowEditor.setMenuItem("Bankupdate1", new WFScreenMenuItem().name("Update1").defaultSuccessScreen("Start"));
		
		
		//3.DB>>>
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("dept_id").newKey("dept_idkey,int"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("OpenDeptUpdate")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.defaultSuccessScreen("Departmentupdateinstance")
				.objectQuery("findByPrimaryKey")
				.parametermapping("dept_id,dept_idkey"));
		
		MainMenu.saveAll();
		
		WFCustomizer.runTest(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.deployToServer("true")
				.assignToUser("DT")
				.unwiredServer("My Unwired Server"), 
				customJsTestScript(),
				testscript.Workflow.cfg.Cfg.tplanScript_client_1);
		
		//vp:data in device
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Bank_BANK_CTRY_attribKey,value=AM|"+
				"id=Bank_BANK_KEY_attribKey,value=800102|"+
				"id=Bank_BANK_NAME_attribKey,value=AM|"+
				"id=Bank_CITY_attribKey,value=c|"+
				"id=UserInfoByID_delete1_userID_paramKey,value=2337|"+
				"id=Department_dept_id_attribKey,value=100"));	
		
}
	private CustomJsTestScript customJsTestScript() {
		// TODO Auto-generated method stub
		CustomJsTestScript s = new CustomJsTestScript();
		
		//1.SAP Bank>>>>>>>>>>
	
		s.screen("Start").moveTo("Bank").throughMenuItem("findallBANK");
		s.screen("Bank").moveTo("BankDetail").throughListItem("800102->1");
		s.screen("BankDetail").getData("Bank_BANK_CTRY_attribKey");
		s.screen("BankDetail").getData("Bank_BANK_KEY_attribKey");
		s.screen("BankDetail").getData("Bank_BANK_NAME_attribKey");
		s.screen("BankDetail").getData("Bank_CITY_attribKey");
		s.screen("BankDetail").moveTo("Bankupdate1").throughMenuItem("Open Bankupdate1");
		s.screen("Bankupdate1").setData("Bank_update1_CITY_paramKey", "AM");
		s.screen("Bankupdate1").setData("Bank_update1_BANKCOUNTRY_paramKey", "AM");
		s.screen("Bankupdate1").moveTo("Start").throughMenuItem("Update1");
		
		//2.WS UserInfoByIDdelete1
		s.screen("Start").setData("ws_idkey", "2337");
		s.screen("Start").moveTo("UserInfoByIDdelete1").throughMenuItem("OpenWSByID");
		s.screen("Start").getData("UserInfoByID_delete1_userID_paramKey");
		s.screen("UserInfoByIDdelete1").moveTo("Start").throughMenuItem("Delete1");
		
		//3.DB department
		s.screen("Start").setData("dept_idkey", "100");
		s.screen("Start").moveTo("Departmentupdateinstance").throughMenuItem("OpenDeptUpdate");
		s.screen("Departmentupdateinstance").getData("Department_dept_id_attribKey");
		s.screen("Departmentupdateinstance").setData("Department_dept_name_attribKey","ff");
		s.screen("Departmentupdateinstance").menuItem("Update");
		
		return s;
	}	
}
